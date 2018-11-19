package beans.springmvc.controllers;

import beans.models.Event;
import beans.models.Ticket;
import beans.services.BookingService;
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
public class BookingController {

    Logger log = LoggerFactory.getLogger(BookingController.class);


    @Autowired
    @Qualifier("bookingServiceImpl")
    private BookingService bookingService;

    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;


    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public String eventMain(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("events", eventService.getAll());
        return "booking";
    }


    //TODO: fix this path!
    @RequestMapping(value = "/buy_ticket", method = RequestMethod.POST)
    public String buyTicket(@ModelAttribute("event") Event event) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Buy a ticket for event " + event + " and user " + auth.getName());
        //TODO: add booking!
        Ticket ticket = new Ticket();
        return "redirect:/index";
    }
}