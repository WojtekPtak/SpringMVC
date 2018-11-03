package beans.springmvc.controllers;

import beans.models.Event;
import beans.models.User;
import beans.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;



    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String usersMain(@ModelAttribute("model") ModelMap model) {
        log.info("Show all users");
        model.addAttribute("userList", userService.getUsersByName("Wojciech Ptak"));
        return "user_main";
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public String getUserByName(@ModelAttribute("model") ModelMap model, @PathVariable final String name) {
        log.info("Show user '{}'", name);
        List<User> users = userService.getUsersByName(name);
        model.addAttribute("userList", users);
        return "user_main";
    }

    @RequestMapping(value = "/user/email/{email}", method = RequestMethod.GET)
    public String getUserByEmail(@ModelAttribute("model") ModelMap model, @PathVariable final String email) {
        log.info("Show user with email '{}'", email);
        User user = userService.getUserByEmail(email);
        List<User> users = new ArrayList<>();
        if(user != null) {
            users.add(user);
        }
        model.addAttribute("userList", Arrays.asList(users));
        return "user_main";
    }

    @RequestMapping(value = "/user_register", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        log.info("Register new user ?");
        user.setBirthday(LocalDate.now().minus(40, ChronoUnit.YEARS));
        if (!user.getName().isEmpty()) {
            log.info("Register new user {}", user);
            userService.register(user);
        }
        return "redirect:/users";
    }


}