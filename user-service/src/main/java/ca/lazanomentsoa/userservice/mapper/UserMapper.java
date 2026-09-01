package ca.lazanomentsoa.userservice.mapper;

import ca.lazanomentsoa.dto.user.AddressDTO;
import ca.lazanomentsoa.dto.user.UserResponse;
import ca.lazanomentsoa.userservice.dto.UserRequest;
import ca.lazanomentsoa.userservice.model.Address;
import ca.lazanomentsoa.userservice.model.User;

public class UserMapper {
    public static User mapToUser(UserRequest userRequest){
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());

        Address address;
        if(user.getAddress() == null){
            address = new Address();
        }else{
            address = user.getAddress();
        }

        address.setStreet(userRequest.getAddress().getStreet());
        address.setCity(userRequest.getAddress().getCity());
        address.setState(userRequest.getAddress().getState());
        address.setCountry(userRequest.getAddress().getCountry());
        address.setZipcode(userRequest.getAddress().getZipcode());
        user.setAddress(address);

        return user;
    }

    public static UserResponse mapToUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());


        if(user.getAddress() != null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddressDTO(addressDTO);
        }

        return response;
    }
}
