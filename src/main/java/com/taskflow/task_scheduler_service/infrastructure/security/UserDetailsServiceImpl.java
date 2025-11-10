package com.taskflow.task_scheduler_service.infrastructure.security;


import com.taskflow.task_scheduler_service.business.dto.UserDTO;
import com.taskflow.task_scheduler_service.infrastructure.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UserClient userClient;

    public UserDetails loadUserByUserName(String email, String token) {

            String bearerToken = token.startsWith("Bearer ") ? token : "Bearer " + token;
            // Calls the UserClient to get user details by email
            UserDTO userDTO = userClient.getUserByEmail(email, bearerToken);
            // Creates and returns a UserDetails object based on the retrieved user data
            return User
                    .withUsername(userDTO.getEmail()) // Sets the username as the email
                    .password(userDTO.getPassword()) // Sets the user's password
                    .build(); // Builds the UserDetails object

    }
}
