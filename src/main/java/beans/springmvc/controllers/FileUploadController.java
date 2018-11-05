package beans.springmvc.controllers;

import beans.models.User;
import beans.services.UserService;
import com.fasterxml.jackson.databind.MappingIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import util.CsvFileReader;

import java.io.File;
import java.io.IOException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@Controller
@ComponentScan("util")
public class FileUploadController  {
    Logger log = LoggerFactory.getLogger(FileUploadController.class);


    @Autowired
    CsvFileReader csvFileReader;

    @Autowired
    UserService userServiceImpl;

    @RequestMapping(value = "/uploadUsers", method = RequestMethod.GET)
    public String getImageView() {


        File currDir = new File("/Users/wojcptak/Develop/Epam/laory-spring-course-dcd507d35a13/src/main/resources/csv/users.csv");

        try (MappingIterator<User> reader = this.csvFileReader.open(currDir, User.class)) {
            //final AtomicLong inputLineCount = new AtomicLong(0);

            StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(reader, Spliterator.ORDERED), false)
                .onClose(() -> log.info("Finished reading file"))
                .forEach( userServiceImpl::register);


        } catch (IOException e) {
            log.error(e.getMessage());
        }


        return "file";
    }

    @RequestMapping(value = "/uploadUsers", method = RequestMethod.POST)
    public ModelAndView uploadFile(MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView("file");



            modelAndView.getModel()
                .put("message", "File uploaded successfully!");
        return modelAndView;
    }

}
