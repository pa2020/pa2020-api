package fr.esgi.api.controller;


import fr.esgi.api.models.user.User;
import fr.esgi.api.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<User> get(){
        return userService.getUser();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User getId(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/username")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User getUsername(@RequestParam(required=false) String name){
        return userService.getUserByUsername(name);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> updateProfile(@RequestBody @Valid User user, @PathVariable Long userId){
        return new ResponseEntity<>(userService.updateUser(user, userId), HttpStatus.OK);
    }
}
