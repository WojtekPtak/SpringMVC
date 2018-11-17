package beans.services.csv;

import beans.models.User;
import beans.services.UserService;
import com.fasterxml.jackson.databind.MappingIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.CsvFileReader;

import java.io.File;
import java.io.IOException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;


@Service
public class UserCsvService implements CsvService {

    Logger log = LoggerFactory.getLogger(UserCsvService.class);

    @Autowired
    CsvFileReader csvFileReader;
    @Autowired
    UserService userService;

    public int loadData(File csvFile) {
        int counter = userService.getAllUsers().size();
        try (MappingIterator<User> reader = csvFileReader.open(csvFile, User.class)) {
            StreamSupport
                    .stream(Spliterators.spliteratorUnknownSize(reader, Spliterator.ORDERED), false)
                    .forEach(userService::register);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return userService.getAllUsers().size() - counter;
    }

}
