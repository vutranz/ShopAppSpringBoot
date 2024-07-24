package com.project.shopapp.Service;

import com.project.shopapp.DTO.UserDTO;
import com.project.shopapp.Models.User;
import com.project.shopapp.exception.DataNotFoundException;

public interface UserService {
    public User createUser(UserDTO userDTO) throws Exception;
    public String login(String phoneNumber, String password) throws Exception;
}
