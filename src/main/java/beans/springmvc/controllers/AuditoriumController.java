package beans.springmvc.controllers;

import beans.models.Auditorium;
import beans.models.User;
import beans.services.AuditoriumService;
import beans.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
public class AuditoriumController {

    Logger log = LoggerFactory.getLogger(AuditoriumController.class);


    @Autowired
    private AuditoriumService auditoriumService;


    @RequestMapping(value = "/auditorias", method = RequestMethod.GET)
    public String auditioriumMain(@ModelAttribute("model") ModelMap model) {
        log.info("Show all auditorias");
        model.addAttribute("auditoriumList", auditoriumService.getAuditoriums());
        return "auditorium_list";
    }

    @RequestMapping(value = "/auditorium/{name}", method = RequestMethod.GET)
    public String getAuditoriumByName(@ModelAttribute("model") ModelMap model, @PathVariable final String name) {
        log.info("Show auditorium '{}'", name);
        Auditorium auditorium = auditoriumService.getByName(name);
        if(auditorium != null) {
            model.addAttribute("auditoriumList", Arrays.asList(auditorium));
        }
        return "auditorium_list";
    }

}