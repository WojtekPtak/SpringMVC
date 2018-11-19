package beans.springmvc.controllers;

import beans.models.Event;
import beans.models.Rate;
import beans.services.AuditoriumService;
import beans.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventController {

    Logger log = LoggerFactory.getLogger(EventController.class);


    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String eventMain(@ModelAttribute("model") ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

/*        for (GrantedAuthority authority : auth.getAuthorities()) {
            String role = authority.getAuthority();
            System.out.println(role);
        }*/

        log.info("Show all events");
        model.addAttribute("eventRates", Rate.values());
        model.addAttribute("eventList", eventService.getAll());
        model.addAttribute("audList", auditoriumService.getAuditoriums());
        return "event_list";
    }


    @RequestMapping(value = "/admin/event_register", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute("event") Event event) {
        log.info("Register new event ?");

        if (!event.getName().isEmpty()) {
            log.info("Register new event {}", event);
            eventService.create(event);
        }
        return "redirect:/event_list";
    }

}