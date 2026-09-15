package ca.lazanomentsoa.userservice.controller;

import ca.lazanomentsoa.dto.user.UserResponse;
import ca.lazanomentsoa.userservice.dto.UserRequest;
import ca.lazanomentsoa.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;


    // user of @slf4j
//    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") String id) {
        log.info("Request received for user with id {}", id);
        log.trace("Request received for user with id {}", id);
        log.debug("Request received for user with id {}", id);
        log.warn("Request received for user with id {}", id);
        log.error("Request received for user with id {}", id);
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
