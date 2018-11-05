package beans.springmvc.controllers;

import beans.models.User;
import beans.services.UserService;
import com.fasterxml.jackson.databind.MappingIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.CsvFileReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    @RequestMapping(value = "/csv/{fileName:.+}", method = RequestMethod.POST)
    public String downloadUsersFile(HttpServletRequest req, HttpServletResponse resp,
                                    @PathVariable("fileName") String fileName) throws IOException {

        String dataDirectory = req.getServletContext().getRealPath("/WEB-INF/data/csv/");
        Path csvFilePath = Paths.get(dataDirectory, fileName);
        log.info("Absolute path of CSV file is: " + csvFilePath);

        File csvFile = csvFilePath.toFile();
        if (csvFile.exists()) {
            loadUsersData(csvFile);
        } else {
            log.error("Requested CSV file not found!");
        }
        // TODO: how to reload only model...
        return "redirect:/users";
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

}
