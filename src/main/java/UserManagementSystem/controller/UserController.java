package UserManagementSystem.controller;

import UserManagementSystem.constant.UrlPaths;
import UserManagementSystem.dto.Response;
import UserManagementSystem.dto.UpdateUserDto;
import UserManagementSystem.dto.UpdateUserResponse;
import UserManagementSystem.dto.UserRegisterDto;
import UserManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(UrlPaths.CREATE_USER)
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(UrlPaths.CREATE)
    public ResponseEntity<Response> createUser(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.create(userRegisterDto);
    }

    @GetMapping(UrlPaths.GET)
    public ResponseEntity<Response> getUserById(@PathVariable int userId) {
        return userService.view(userId);
    }

    @PutMapping(UrlPaths.EDIT)
    public ResponseEntity<UpdateUserResponse> editUser(@RequestBody UpdateUserDto updateUserDto) {

        return userService.edit(updateUserDto);
    }

    @DeleteMapping(UrlPaths.DELETE)
    public ResponseEntity<UpdateUserResponse> deleteUser (@RequestParam (name = "userId") int userId) {
        return userService.delete(userId);
    }
}
