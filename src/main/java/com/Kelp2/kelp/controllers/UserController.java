package com.Kelp2.kelp.controllers;


import com.Kelp2.kelp.models.User;
import com.Kelp2.kelp.services.UserService;
import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping(path="/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController (UserService userService){this.userService = userService;}

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByID(@PathVariable(name="id") int id){
        logger.info("Received request for User by ID");
        User calledUser = userService.findUserByID(id);
        return new ResponseEntity<>(calledUser, HttpStatus.OK);
    }

    @GetMapping(path="/name/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByUserName(@PathVariable(name="userName") String name){
        logger.info("Received request for User by Username");
        User calledUser = userService.findByUserName(name);
        return new ResponseEntity<>(calledUser, HttpStatus.OK);
    }

    @GetMapping(path="/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByEmail(@PathVariable(name="email") String email){
            logger.info("Received request for User by Email");
            User calledUser = userService.findByEmail(email);
            return new ResponseEntity<>(calledUser, HttpStatus.OK);

    }

    @PutMapping(path="/update/{token}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUserInfo(@RequestBody String json){
        logger.info("Updating info for requested User");
        User updatedUser = userService.updateUserInfoByEmail(json);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping(path="/create/{token}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody String json){
        logger.info("Creating User");
        User createdUser = userService.saveUser(json);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

}
