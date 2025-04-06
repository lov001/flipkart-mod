package com.shopping.flipkart.security.impl;

import com.shopping.flipkart.dto.UserRequest;
import com.shopping.flipkart.error.ErrorCode;
import com.shopping.flipkart.error.exception.BaseException;
import com.shopping.flipkart.error.exception.UserNotFoundException;
import com.shopping.flipkart.model.User;
import com.shopping.flipkart.repository.UserRepository;
import com.shopping.flipkart.security.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;

   public UserServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Override
   public User createUser(UserRequest userRequest) {
      if (userRepository.existsByEmail(userRequest.email())) {
         throw new BaseException(ErrorCode.EMAIL_ALREADY_EXISTS);
      }
      User user = User.builder()
         .name(userRequest.name())
         .email(userRequest.email())
         .phone(userRequest.phone())
         .password(userRequest.password())
         .build();
      return userRepository.save(user);
   }

   @Override
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   @Override
   public User getUserById(Long id) {
      return userRepository.findById(id)
         .orElseThrow(UserNotFoundException::new);
   }

   @Override
   public User getUserByEmail(String email) {
      return userRepository.findByEmail(email)
         .orElseThrow(UserNotFoundException::new);
   }

   @Override
   public User updateUser(Long id, UserRequest userRequest) {
      Optional<User> userOptional = userRepository.findById(id);
      if (userOptional.isPresent()) {
         User user = userOptional.get();
         user.setName(userRequest.name());
         user.setEmail(userRequest.email());
         user.setPhone(userRequest.phone());
         user.setPassword(userRequest.password());
         return userRepository.save(user);
      }
      throw new UserNotFoundException();
   }

   @Override
   public void deleteUser(Long id) {
      if (!userRepository.existsById(id)) {
         throw new UserNotFoundException();
      }
      userRepository.deleteById(id);
   }
}
