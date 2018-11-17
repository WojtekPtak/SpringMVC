package beans.configuration;

import beans.daos.EventDAO;
import beans.daos.mocks.EventDAOMock;
import beans.daos.mocks.UserDAOMock;
import beans.services.EventService;
import beans.services.EventServiceImpl;
import beans.services.UserService;
import beans.services.UserServiceImpl;
import beans.services.csv.CsvService;
import beans.services.csv.EventCsvService;
import beans.services.csv.UserCsvService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import util.CsvFileReader;

import java.util.Collections;

@Configuration
public class TestCsvServiceConfiguration {

    @Bean
    public UserDAOMock userDAO() {
        return new UserDAOMock(Collections.emptyList());
    }

    @Bean()
    public EventDAO eventDAOMock() {
        return new EventDAOMock(Collections.emptyList());
    }

    @Bean
    public UserService userServiceImpl() {
        return new UserServiceImpl(userDAO());
    }
    @Bean
    public EventService eventServiceImpl() {
        return new EventServiceImpl(eventDAOMock());
    }


    @Bean
    public CsvService userCsvService() {
        return new UserCsvService();
    }
    @Bean
    public CsvService eventCsvService() {
        return new EventCsvService();
    }

    @Bean
    public CsvFileReader csvFileReader() {
        return new CsvFileReader();
    }
}
