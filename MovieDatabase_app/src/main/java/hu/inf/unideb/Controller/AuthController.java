package hu.inf.unideb.Controller;

import hu.inf.unideb.Entity.MyUser;
import hu.inf.unideb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public ModelAndView createUser(@ModelAttribute MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        MyUser savedUser = myUserRepository.save(user);
        System.out.println("Saved user: " + savedUser.getUsername() + ", Password: " + savedUser.getPassword());
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<MyUser> users = myUserRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/users/changeRole/{id}")
    public String changeUserRole(@PathVariable Long id, @RequestParam String newRole) {
        Optional<MyUser> userOptional = myUserRepository.findById(id);
        if (userOptional.isPresent()) {
            MyUser user = userOptional.get();
            if (newRole != null && (newRole.equals("ADMIN") || newRole.equals("USER"))) {
                user.setRole(newRole);
                myUserRepository.save(user);
            }
        }
        return "redirect:/users";
    }

    // Endpoint for JSON data -> use it to testing via Postman
    //@PostMapping("/register/user/json")
    //public MyUser createUserFromJson(@RequestBody MyUser user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        //return myUserRepository.save(user);
    //}
}