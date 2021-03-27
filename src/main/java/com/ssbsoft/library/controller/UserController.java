package com.ssbsoft.library.controller;

import com.ssbsoft.library.common.Constants;
import com.ssbsoft.library.model.Role;
import com.ssbsoft.library.model.User;
import com.ssbsoft.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping( value = "/addOrUpdateUser" , method = RequestMethod.POST)
    public String addOrUpdateUser(@ModelAttribute("user") User user,ModelMap modelMap) {
        try {
            if(user.getId()==0) {
            Role role = new Role();
            role.setId(3);
            user.setRole(role);
            user.setAccount_enabled(true);

            boolean isverificationEmail = userService.emailId(user);
            if (!isverificationEmail) {
                modelMap.addAttribute("MailError", Constants.MAIL_ERROR);
                return "adduser";
            }
            boolean isVerification = userService.contactNumberVerification(user);
            if (!isVerification) {
                modelMap.addAttribute("ContactNumberError", Constants.CONTACTNUMBER_ERROR);
                return "adduser";
            }
            boolean isverificationName = userService.name(user);
            if (!isverificationName) {
                modelMap.addAttribute("NameError", Constants.NAME_ERROR);
                return "adduser";
            }
            boolean isverificationPassword = userService.passwordVerify(user);
            if(!isverificationPassword){
                modelMap.addAttribute("PasswordError", Constants.PASSWORD_ERROR);
                return "adduser";
            }
            userService.createUser(user);
            modelMap.addAttribute("success", Constants.USER_ADDED);
        }else {
                Role role = new Role();
                role.setId(3);
                user.setRole(role);
                user.setAccount_enabled(true);

                boolean isverificationEmail = userService.emailId(user);
                if (!isverificationEmail) {
                    modelMap.addAttribute("MailError", Constants.MAIL_ERROR);
                }
                boolean isVerification = userService.contactNumberVerification(user);
                if (!isVerification) {
                    modelMap.addAttribute("ContactNumberError", Constants.CONTACTNUMBER_ERROR);
                }
                boolean isverificationName = userService.name(user);
                if (!isverificationName) {
                    modelMap.addAttribute("NameError", Constants.NAME_ERROR);
                }
                boolean isverificationPassword = userService.passwordVerify(user);
                if(!isverificationPassword){
                    modelMap.addAttribute("PasswordError", Constants.PASSWORD_ERROR);
                }
                userService.createUser(user);
            modelMap.addAttribute("success", Constants.USER_UPDATED);
          }
        }catch(Exception exception) {
            return "adduser";
        }
        return "redirect:/user/viewUser";
    }
    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id, ModelMap modelMap) {
       User user = userService.getUserId(id);
       user.setAccount_enabled(false);
       userService.createUser(user);
        modelMap.addAttribute("deleteMessage", Constants.ACCOUNT_DISABLE);
        return "redirect:/user/viewUser";
    }
    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable int id,ModelMap modelMap) {
        User user = userService.getUserId(id);
        modelMap.addAttribute("users", user);
        return "adduser";
    }
    @RequestMapping(value = "/viewUser", method = RequestMethod.GET)
    public String viewUsers(ModelMap modelMap,  @RequestParam(value = "deleteMessage", required = false) String deleteMessage,@RequestParam(value = "success", required = false) String success) {
        List<User> users = userService.viewUser();
        modelMap.addAttribute("view_id", 1);
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("deleteMessage", deleteMessage);
        modelMap.addAttribute("success", success);
        return "admin";
    }
    @RequestMapping( value = "/register" , method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user,ModelMap modelMap) {
        try {
        Role role = new Role();
        role.setId(3);
        user.setRole(role);
        user.setAccount_enabled(true);

            boolean isverificationEmail = userService.emailId(user);
            if (!isverificationEmail) {
                modelMap.addAttribute("MailError", Constants.MAIL_ERROR);
                return "register";
            }
            boolean isVerification = userService.contactNumberVerification(user);
            if (!isVerification) {
                modelMap.addAttribute("ContactNumberError", Constants.CONTACTNUMBER_ERROR);
                return "register";
            }
            boolean isverificationName = userService.name(user);
            if (!isverificationName) {
                modelMap.addAttribute("NameError", Constants.NAME_ERROR);
                return "register";
            }
            boolean isverificationPassword = userService.passwordVerify(user);
            if(!isverificationPassword){
                modelMap.addAttribute("PasswordError", Constants.PASSWORD_ERROR);
                return "register";
            }
            userService.createUser(user);
        }catch(Exception exception) {
            return "register";
        }
        modelMap.addAttribute("register",Constants.REGISTER);
        return "login";
    }

    @RequestMapping(value = "/admin")
    public String admin(ModelMap modelMap,@RequestParam(value = "success_reply", required = false) String success_reply) {
        modelMap.addAttribute("success_reply", success_reply);
        return "admin";
    }

    @RequestMapping(value = "/librarian")
    public String librarian(ModelMap modelMap) {
        return "librarian";
    }

    @RequestMapping(value = "/user")
    public String user() {
        return "user";
    }

    @RequestMapping(value = "/adduser")
    public String add() {
        return "adduser";
    }

    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

}
