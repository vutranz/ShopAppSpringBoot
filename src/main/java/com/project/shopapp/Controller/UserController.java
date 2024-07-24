package com.project.shopapp.Controller;

import com.project.shopapp.DTO.UserDTO;
import com.project.shopapp.DTO.UserLoginDTO;
import com.project.shopapp.Models.User;
import com.project.shopapp.Service.UserService;
import com.project.shopapp.exception.DataNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(
            @Valid
            @RequestBody UserDTO userDTO,
            BindingResult result) {
            try{
                if (result.hasErrors()) {
                    List<String> errorMessages = result.getFieldErrors()
                            .stream()
                            .map(FieldError::getDefaultMessage)
                            .collect(Collectors.toList());
                    return ResponseEntity.badRequest().body(errorMessages);
                }
                if(!userDTO.getPassword().equals(userDTO.getRetypePassword()))
                {
                    return ResponseEntity.badRequest().body("Passwords do not match");
                }
               User user = userService.createUser(userDTO);
                return ResponseEntity.ok(user);
            }catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@Valid  @RequestBody UserLoginDTO userLoginDTO)  {
        try {
            String toke = userService.login(userLoginDTO.getPhoneNumber(),userLoginDTO.getPassword());
            return ResponseEntity.ok(toke);
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
