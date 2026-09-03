package ca.lazanomentsoa.cartservice.clientConfig.user;

import ca.lazanomentsoa.dto.user.UserResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/users/")
public interface UserHttpInterface {

    @GetExchange("/{id}")
    UserResponse getUserById(@PathVariable("id") String id);
}
