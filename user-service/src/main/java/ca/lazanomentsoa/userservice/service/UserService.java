package ca.lazanomentsoa.userservice.service;

import ca.lazanomentsoa.dto.user.UserResponse;
import ca.lazanomentsoa.userservice.dto.UserRequest;
import ca.lazanomentsoa.userservice.mapper.UserMapper;
import ca.lazanomentsoa.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final KeyCloakAdminService keyCloakAdminService;

    public void createUser(UserRequest userRequest) {
        String token = keyCloakAdminService.getAdminAccessToken();
        String keycloakdUserId = keyCloakAdminService.createUser(token, userRequest);


        userRepository.save(UserMapper.mapToUser(userRequest, keycloakdUserId));
    }

    public Optional<UserResponse> findUserById(Long id){
        return userRepository.findById(id)
                .map(UserMapper::mapToUserResponse);
    }
}
