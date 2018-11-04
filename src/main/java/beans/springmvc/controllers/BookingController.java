package beans.springmvc.controllers;

import beans.services.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    Logger log = LoggerFactory.getLogger(BookingController.class);



    @Autowired
    @Qualifier("bookingServiceImpl")
    private BookingService bookingService;



}