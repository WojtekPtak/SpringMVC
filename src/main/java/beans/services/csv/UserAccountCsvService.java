package beans.services.csv;

import beans.models.User;
import beans.models.UserAccount;
import beans.services.UserAccountService;
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
public class UserAccountCsvService implements CsvService {
    Logger log = LoggerFactory.getLogger(UserAccountCsvService.class);

    @Autowired
    CsvFileReader csvFileReader;
    @Autowired
    UserAccountService accountService;

    @Autowired
    UserService userService;

    @Override
    public int loadData(File csvFile) {
        log.info("File " + csvFile + " is being loaded...");
        int counter = accountService.getAllAccounts().size();
        try (MappingIterator<UserAccountBuilder> reader = csvFileReader.open(csvFile, UserAccountBuilder.class)) {
           StreamSupport
                    .stream(Spliterators.spliteratorUnknownSize(reader, Spliterator.ORDERED), false)
                    .forEach( csv -> accountService.create(csv.build(userService)));

        } catch (IOException e) {
            log.error(e.getMessage());
        }
        log.info("File loaded.");
        return accountService.getAllAccounts().size() - counter;
    }

    // Added to load data from CSV files when UserName is a string and we need to convert it to User Object
    private static class UserAccountBuilder {
        private String userName;
        private Double balance;

        public void setUserName(String userName) {
            this.userName = userName;
        }
        public void setBalance(Double balance) {
            this.balance = balance;
        }

        public UserAccount build(UserService userService) {

            User user = userService.getUserByName(userName);
            if(user == null) {
                throw new IllegalStateException(String.format("User '%s' used in CSV file doesn't exist!", userName));
            }
            return new UserAccount(user, balance);
        }
    }

}
