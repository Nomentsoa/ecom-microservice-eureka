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

    public void createUser(UserRequest userRequest) {
        userRepository.save(UserMapper.mapToUser(userRequest));
    }

    public Optional<UserResponse> findUserById(Long id){
        return userRepository.findById(id)
                .map(UserMapper::mapToUserResponse);
    }
}
