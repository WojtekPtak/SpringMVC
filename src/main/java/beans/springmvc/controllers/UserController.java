package beans.springmvc.controllers;

import beans.daos.UserDAO;
import beans.models.User;
import beans.models.UserAccount;
import beans.services.UserAccountService;
import beans.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("userAccountServiceImpl")
    private UserAccountService userAccountService;

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String usersMain(@ModelAttribute("model") ModelMap model) {
        log.info("Show all users");
        //userService.register(new User(""));
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("accountList", userAccountService.getAllAccounts());
        return "user_main";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserByName(@ModelAttribute("model") ModelMap model, @RequestParam("name") final String name) {
        log.info("Show user '{}'", name);
        List<User> users = userService.getUsersByName(name);
        model.addAttribute("userList", users);
        model.addAttribute("accountList", userAccountService.getAllAccounts());
        return "user_search";
    }

    @RequestMapping(value = "/user/email/", method = RequestMethod.GET)
    public String getUserByEmail(@ModelAttribute("model") ModelMap model, @RequestParam final String email) {
        log.info("Show user with email '{}'", email);
        User user = userService.getUserByEmail(email);
        List<User> users = new ArrayList<>();
        if (user != null) {
            users.add(user);
            model.addAttribute("userList", users);
        }
        model.addAttribute("userList");
        model.addAttribute("accountList", userAccountService.getAllAccounts());
        return "user_search";
    }

    @RequestMapping(value = "/user_register", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        log.info("Register new user ?");

        UserDAO.validateUser(user);
        log.info("Register new user {}", user);
        userService.register(user);
        userAccountService.create(new UserAccount(user));
        return "redirect:/admin/users";
    }


}