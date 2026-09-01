package ca.lazanomentsoa.userservice.dto;

import ca.lazanomentsoa.dto.user.AddressDTO;
import ca.lazanomentsoa.userservice.model.UserRole;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;
}
