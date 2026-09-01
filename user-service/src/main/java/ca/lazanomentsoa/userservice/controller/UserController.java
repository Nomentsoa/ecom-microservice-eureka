package ca.lazanomentsoa.userservice.controller;

import ca.lazanomentsoa.dto.user.UserResponse;
import ca.lazanomentsoa.userservice.dto.UserRequest;
import ca.lazanomentsoa.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") String id) {
        return userService.findUserById(Long.valueOf(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return new ResponseEntity<>("User added", HttpStatus.CREATED);
    }
}
