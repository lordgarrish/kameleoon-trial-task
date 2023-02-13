package lordgarrish.kameleoontrialtask.controller;

import lordgarrish.kameleoontrialtask.entity.User;
import lordgarrish.kameleoontrialtask.exception.UserAlreadyExistException;
import lordgarrish.kameleoontrialtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            // Can't create already existing user
            return ResponseEntity.badRequest().body("User with email " + user.getEmail() + " already exists.");
        }
    }
}
