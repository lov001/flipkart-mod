package com.shopping.flipkart.security.impl;

import com.shopping.flipkart.dto.UserKafkaDTO;
import com.shopping.flipkart.dto.UserRequest;
import com.shopping.flipkart.error.ErrorCode;
import com.shopping.flipkart.error.exception.BaseException;
import com.shopping.flipkart.error.exception.UserNotFoundException;
import com.shopping.flipkart.messaging.producer.UserEventProducer;
import com.shopping.flipkart.model.User;
import com.shopping.flipkart.repository.UserRepository;
import com.shopping.flipkart.security.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final UserEventProducer userEventProducer;

   public UserServiceImpl(UserRepository userRepository, UserEventProducer userEventProducer) {
      this.userRepository = userRepository;
      this.userEventProducer = userEventProducer;
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
      User savedUser = userRepository.save(user);
      UserKafkaDTO dto = new UserKafkaDTO(user.getId(), user.getName(), user.getEmail(),
         user.getPhone());
      userEventProducer.sendUserEvent(dto);
      return savedUser;
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
      Optional<User> userOptional = userRepository.findById(id);
      if (userOptional.isEmpty()) {
         throw new UserNotFoundException();
      }
      userRepository.deleteById(id);
      UserKafkaDTO dto = new UserKafkaDTO(userOptional.get().getId(), userOptional.get().getName(),
         userOptional.get().getEmail(), userOptional.get().getPhone());
      userEventProducer.sendUserEvent(dto);
   }
}
