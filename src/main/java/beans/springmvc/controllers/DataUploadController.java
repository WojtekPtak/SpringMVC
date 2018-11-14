package beans.springmvc.controllers;

import beans.models.Event;
import beans.models.User;
import beans.services.EventService;
import beans.services.UserService;
import com.fasterxml.jackson.databind.MappingIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.CsvFileReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;


//TODO: use multipart to load more than one file at once
@Controller
@ComponentScan("util")
@RequestMapping("/data")
public class DataUploadController {
    Logger log = LoggerFactory.getLogger(DataUploadController.class);


    @Autowired
    CsvFileReader csvFileReader;

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("eventServiceImpl")
    EventService eventService;

    @RequestMapping(value = "/csv", method = RequestMethod.POST)
    public String downloadUsersFile(HttpServletRequest req, HttpServletResponse resp
                                    ) throws IOException {

        String dataDirectory = req.getServletContext().getRealPath("/WEB-INF/data/csv/");
        File dir = new File(dataDirectory);
        for (File csvFile : dir.listFiles()) {
            if (csvFile != null && csvFile.exists() && csvFile.isFile()) {
                if (csvFile.getName().equals("users.csv")) {
                    loadUsersData(csvFile);
                } else if (csvFile.getName().equals("events.csv")) {
                    loadEventsData(csvFile);
                } else {
                    log.warn("Unknown CSV file '{}'!", csvFile);
                }

            } else {
                log.error("Requested CSV file '{}' not found!", csvFile);
            }
        }
        return "redirect:/index";
    }

    private void loadUsersData(File csvFile) {
        try (MappingIterator<User> reader = csvFileReader.open(csvFile, User.class)) {
            StreamSupport
                    .stream(Spliterators.spliteratorUnknownSize(reader, Spliterator.ORDERED), false)
                    .forEach(userService::register);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void loadEventsData(File csvFile) {
        try (MappingIterator<Event> reader = csvFileReader.open(csvFile, Event.class)) {
            StreamSupport
                    .stream(Spliterators.spliteratorUnknownSize(reader, Spliterator.ORDERED), false)
                    .forEach(eventService::create);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
