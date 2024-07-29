package com.project.shopapp.Service.Impl;

import com.project.shopapp.DTO.UserDTO;
import com.project.shopapp.Models.Role;
import com.project.shopapp.Models.User;
import com.project.shopapp.Repository.RoleRepository;
import com.project.shopapp.Repository.UserRepository;
import com.project.shopapp.Service.UserService;
import com.project.shopapp.components.JwtTokenUtil;
import com.project.shopapp.config.MyUserDetails;
import com.project.shopapp.exception.DataNotFoundException;
import com.project.shopapp.exception.PermissionDenyException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public User createUser(UserDTO userDTO) throws Exception {
       String phoneNumber = userDTO.getPhoneNumber();
       // kểm tra sdt tồn tại chưa ?
        if(userRepository.existsByPhoneNumber(phoneNumber))
        {
            throw new DataIntegrityViolationException("Phone number already exists");
        }

        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Role not found"));
        if(role.getName().toUpperCase().equals(Role.ADMIN))
        {
            throw new PermissionDenyException("you can not resgister an admin");
        }
        //convert from userDTO => user
        User user = User.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(phoneNumber)
                .password(userDTO.getPassword())
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .build();

        user.setRole(role);
        // kiểm tra nếu có accountId, không yêu cầu password
        if(userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0)
        {
            String password = userDTO.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
        }
            return userRepository.save(user);
    }

    @Override
    public String login(String phoneNumber, String password) throws Exception {
        Optional<User> optionalUser =userRepository.findByPhoneNumber(phoneNumber);
        if(optionalUser.isEmpty()){
            throw new DataNotFoundException("invalid phoneNumber or password");
        }
        //return optionalUser.get();
        User existingUser = optionalUser.get();
        MyUserDetails userDetails = new MyUserDetails(existingUser);

        if(existingUser.getFacebookAccountId() == 0 && existingUser.getGoogleAccountId() == 0){
            if(!passwordEncoder.matches(password, existingUser.getPassword())){
                throw new BadCredentialsException("invalid username or password");
            }
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(phoneNumber, password
        ,userDetails.getAuthorities());

        //authenticate with spring security
        authenticationManager.authenticate(authToken);
        return jwtTokenUtil.generateToken(existingUser);
    }
}
