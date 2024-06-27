package hu.inf.unideb.Controller;

import hu.inf.unideb.Entity.MyUser;
import hu.inf.unideb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public MyUser createUser(@ModelAttribute MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        MyUser savedUser = myUserRepository.save(user);
        System.out.println("Saved user: " + savedUser.getUsername() + ", Password: " + savedUser.getPassword());
        return savedUser;
    }

    @GetMapping("/users")
    public List<MyUser> getAllUsers() {
        return myUserRepository.findAll();
    }

    // Endpoint for JSON data -> use it to testing via Postman
    //@PostMapping("/register/user/json")
    //public MyUser createUserFromJson(@RequestBody MyUser user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        //return myUserRepository.save(user);
    //}

}