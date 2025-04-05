package com.shopping.flipkart.security;

import com.shopping.flipkart.dto.UserRequest;
import com.shopping.flipkart.model.User;
import java.util.List;

public interface UserService {

   User createUser(UserRequest userRequest);

   List<User> getAllUsers();

   User getUserById(Long id);

   User getUserByEmail(String email);

   User updateUser(Long id, UserRequest userRequest);

   void deleteUser(Long id);
}
