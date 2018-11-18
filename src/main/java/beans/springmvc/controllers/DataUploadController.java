package beans.springmvc.controllers;

import beans.services.csv.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


//TODO: use multipart to load more than one file at once
@Controller
@ComponentScan("util")
@RequestMapping("/data")
public class DataUploadController {
    Logger log = LoggerFactory.getLogger(DataUploadController.class);

    private String csvDir;

    @Autowired
    CsvService userCsvService;

    @Autowired
    CsvService userAccountCsvService;

    @Autowired
    CsvService eventCsvService;


    @RequestMapping(value = "/csv", method = RequestMethod.POST)
    public String downloadUsersFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Start bulkloading data from CSV files...");
        csvDir = req.getServletContext().getRealPath("/WEB-INF/data/csv/");

        loadData(userCsvService, "users.csv");
        loadData(userAccountCsvService, "accounts.csv");
        loadData(eventCsvService, "events.csv");
        log.info("Data from CSV files bulkloaded!");
        return "redirect:/index";
    }

    private void loadData(CsvService csvService, String fileName) {
        File dataFile = new File(csvDir + fileName);
        if (dataFile != null && dataFile.exists() && dataFile.isFile()) {
            csvService.loadData(dataFile);
        } else {
            throw new IllegalArgumentException(String.format("Requested CSV file '%s' not found!", dataFile));
        }
    }

}
