package beans.configuration;

import beans.daos.EventDAO;
import beans.daos.mocks.AuditoriumDAOMock;
import beans.daos.mocks.EventDAOMock;
import beans.daos.mocks.UserDAOMock;
import beans.models.Auditorium;
import beans.models.Event;
import beans.models.User;
import beans.services.*;
import beans.services.csv.CsvService;
import beans.services.csv.EventCsvService;
import beans.services.csv.UserCsvService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import util.CsvFileReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class TestCsvServiceConfiguration {

    @Bean("auditoriumService")
    public AuditoriumService auditoriumService() {
        return new AuditoriumServiceImpl(new AuditoriumDAOMock(Arrays.asList(
                new Auditorium("Yellow hall", 1000,
                        Arrays.asList(25,26,27,28,29,30,31,32,33,34,35,75,76,77,78,79,80,81,82,83,84,85,105,106,107,108,109,110,111,112,113,114,115)))));
    }

    @Bean
    public User testUser1() {
        return new User(0, "chuck@gmail.com", "Chuck Norris", LocalDate.of(1940, 3, 10), "REGISTERED_USER,BOOKING_MANAGER");
    }
    @Bean
    public Event testEvent1() {
        return new Event("The revenant", beans.models.Rate.HIGH, 60, LocalDateTime.of(2016, 7, 2, 9, 0, 0), "Yellow hall");
    }


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
