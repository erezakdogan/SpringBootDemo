package com.compx.demo;

import java.util.List;
import com.compx.demo.user.User;
import com.compx.demo.user.UserNotFoundException;
import com.compx.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);

        return "user";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }
    
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes rAttributes) {
        userService.save(user);
        rAttributes.addFlashAttribute("message", "The User Have Been Saved Succesfully");
        return "redirect:/users";
    }
    
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        User user;
        try {
            user = userService.get(id);        
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User ID : "+id);  
            return "user_form";
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            ra.addFlashAttribute("message", "The User Have Been Saved Succesfully");
        }
        return "redirect:/users";
        }
    
    @GetMapping("/users/delete/{id}")
    public String userDelete(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        User user;
        try {
            user = userService.get(id);
            userService.delete(user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            ra.addFlashAttribute("message", "The User Have Been Deleted Succesfully");
        }
        return "redirect:/users";
        }
}
