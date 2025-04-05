package com.shopping.flipkart.contoller;

import com.shopping.flipkart.dto.UserRequest;
import com.shopping.flipkart.model.User;
import com.shopping.flipkart.security.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

   private final UserService userService;

   public UserController(UserService userService) {
      this.userService = userService;
   }

   @PostMapping
   public ResponseEntity<User> createUser(@RequestBody UserRequest user) {
      User createdUser = userService.createUser(user);
      return ResponseEntity.ok(createdUser);
   }

   @GetMapping("/{id}")
   public ResponseEntity<User> getUserById(@PathVariable Long id) {
      User user = userService.getUserById(id);
      return ResponseEntity.ok(user);
   }

   @GetMapping
   public ResponseEntity<List<User>> getAllUsers() {
      List<User> users = userService.getAllUsers();
      return ResponseEntity.ok(users);
   }

   @DeleteMapping
   public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
      userService.deleteUser(id);
      return ResponseEntity.noContent().build();
   }

   @PutMapping
   public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserRequest user) {
      User updatedUser = userService.updateUser(id, user);
      return ResponseEntity.ok(updatedUser);
   }

   @GetMapping("/email")
   public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
      User user = userService.getUserByEmail(email);
      return ResponseEntity.ok(user);
   }
}
