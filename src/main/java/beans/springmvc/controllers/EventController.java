package beans.springmvc.controllers;

import beans.models.User;
import beans.services.EventService;
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

import java.util.Arrays;
import java.util.List;

@Controller
public class EventController {

    Logger log = LoggerFactory.getLogger(EventController.class);


    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String eventMain(@ModelAttribute("model") ModelMap model) {
        log.info("Show all events");
        model.addAttribute("eventList", eventService.getAll());
        return "event_list";
    }


}