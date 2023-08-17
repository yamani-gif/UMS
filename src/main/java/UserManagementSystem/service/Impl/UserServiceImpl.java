package UserManagementSystem.service.Impl;

import UserManagementSystem.constant.ErrorMessages;
import UserManagementSystem.dto.Response;
import UserManagementSystem.dto.UpdateUserDto;
import UserManagementSystem.dto.UpdateUserResponse;
import UserManagementSystem.dto.UserRegisterDto;
import UserManagementSystem.model.User;
import UserManagementSystem.repo.UserRepo;
import UserManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Response> create(UserRegisterDto userRegisterDto) {
        List<User> userFromDB = userRepo.findByEmailOrMobileNumber(userRegisterDto.getEmail(),userRegisterDto.getMobileNumber());
        if(userFromDB.isEmpty()) {
            return saveUser(userRegisterDto);
        }
        if (userFromDB.get(0).getEmail().equalsIgnoreCase(userRegisterDto.getEmail())) {
            return new ResponseEntity<>(new Response(400,HttpStatus.BAD_REQUEST, ErrorMessages.EMAIL_ALREADY_EXIST,null),HttpStatus.BAD_REQUEST);
        }
        if (userFromDB.get(0).getEmail().equalsIgnoreCase(userRegisterDto.getMobileNumber())) {
            return new ResponseEntity<>(new Response(400,HttpStatus.BAD_REQUEST, ErrorMessages.MOBILE_ALREADY_EXIST,null),HttpStatus.BAD_REQUEST);
        }
        return saveUser(userRegisterDto);
    }

    @Override
    public ResponseEntity<Response> view(int userId) {
        Optional<User> userById = userRepo.findById(userId);
        if (userById.isPresent()){
            return new ResponseEntity<>(new Response(200,HttpStatus.OK, "User Found",userById.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response(200,HttpStatus.NO_CONTENT,"User Not found for given Id",null),HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<UpdateUserResponse> edit(UpdateUserDto updateUserDto) {
        Optional<User> userFromDB = userRepo.findById(updateUserDto.getId());
        if(userFromDB.isEmpty()) {
            return new ResponseEntity<>(new UpdateUserResponse(400,HttpStatus.BAD_REQUEST,"User not found for the given Id"),HttpStatus.BAD_REQUEST);
        }
        return updateUser(updateUserDto,userFromDB.get());

    }

    @Override
    public ResponseEntity<UpdateUserResponse> delete(int userId) {
        Optional<User> userFromDb = userRepo.findById(userId);
        if(userFromDb.isEmpty()){
            return new ResponseEntity<>(new UpdateUserResponse(400,HttpStatus.BAD_REQUEST,"User not found for the given Id"),HttpStatus.BAD_REQUEST);
        }
        userRepo.deleteById(userId);
        return new ResponseEntity<>(new UpdateUserResponse(200,HttpStatus.OK,"User deleted successfully!"),HttpStatus.OK);
    }

    private ResponseEntity<UpdateUserResponse> updateUser(UpdateUserDto updateUserDto, User userFromDB) {

        userFromDB.setUserName(updateUserDto.getUserName());
        userFromDB.setMobileNumber(updateUserDto.getMobileNumber());
        userFromDB.setEmail(updateUserDto.getEmail());
        userFromDB.setJobRole(updateUserDto.getJobRole());
        userFromDB.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
        userRepo.save(userFromDB);
        return new ResponseEntity<>(new UpdateUserResponse(200,HttpStatus.OK,"UserDetails Updated successfully!"),HttpStatus.OK);
    }

    private ResponseEntity<Response> saveUser(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setUserName(userRegisterDto.getUserName());
        user.setMobileNumber(userRegisterDto.getMobileNumber());
        user.setEmail(userRegisterDto.getEmail());
        user.setJobRole(userRegisterDto.getJobRole());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        User savedUser = userRepo.save(user);
        return new ResponseEntity<>(new Response(201,HttpStatus.CREATED,"User Successfully created",savedUser),HttpStatus.CREATED);
    }
}
