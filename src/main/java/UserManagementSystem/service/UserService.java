package UserManagementSystem.service;

import UserManagementSystem.dto.Response;
import UserManagementSystem.dto.UpdateUserDto;
import UserManagementSystem.dto.UpdateUserResponse;
import UserManagementSystem.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;


public interface UserService {
    ResponseEntity<Response> create(UserRegisterDto userRegisterDto);

    ResponseEntity<Response> view(int userId);

    ResponseEntity<UpdateUserResponse> edit(UpdateUserDto updateUserDto);

    ResponseEntity<UpdateUserResponse> delete(int userId);
}
