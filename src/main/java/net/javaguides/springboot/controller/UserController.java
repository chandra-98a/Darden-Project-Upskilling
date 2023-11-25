package net.javaguides.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.Exception.ErrorDetails;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.internal.bytebuddy.build.Plugin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import net.javaguides.springboot.Exception.EmailAlreadyExistsException;
import org.springframework.web.context.request.WebRequest;

@Tag(
        name = "CRUD REST APIs for user resource",
        description = "Create User, Update User,Get User, Get All Users,Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    // build create User REST API
    @Operation(
            summary =" Create User Rest Api",
            description = " Create User Rest Api is used to SAVE user  from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // build get user by id REST API
    // http://localhost:8080/api/users/1
    @Operation(
            summary =" Get single User Rest Api",
            description = " getUserById is used toget a single user  from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESSFUL"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Build Get All Users REST API
    // http://localhost:8080/api/users
    @Operation(
            summary =" Get All Users Rest Api",
            description = " getAllUsers Api is used to get all users  from database"
    )
    @ApiResponse(
            responseCode = "203",
            description = "Http Status 203 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Build Update User REST API
    @Operation(
            summary =" update User Rest Api",
            description = " Update User Api is used to update users  from database"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Http Status 204 UPDATED"
    )
    @PutMapping("{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserDto user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Build Delete User REST API
    @Operation(
            summary =" Delete User Rest Api",
            description = " Delete User Api is used to delete users  from database"
    )
    @ApiResponse(
            responseCode = "205",
            description = "Http Status 205 DELETED"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

   // @ExceptionHandler(ResourceNotFoundException.class)
  //public ResponseEntity<ErrorDetails> handleResourceNotFoundException
    //        (ResourceNotFoundException exception, WebRequest webRequest){
      //  ErrorDetails errorDetails = new ErrorDetails(
        //        LocalDateTime.now(),
          //      exception.getMessage(),
            //    webRequest.getDescription(false),
              //  "USER_NOT_FOUND"
      //  );

      //  return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    //}
    @ExceptionHandler(EmailAlreadyExistsException.class)
   public ResponseEntity<ErrorDetails> handleEmailAlreadyExistException(EmailAlreadyExistsException exception,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
               LocalDateTime.now(),
              exception.getMessage(),
              webRequest.getDescription(false),
             "EMAIL_ALREADY_EXIST"
        );

      return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
}
