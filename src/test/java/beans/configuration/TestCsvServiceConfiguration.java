package beans.configuration;

import beans.daos.AuditoriumDAO;
import beans.daos.EventDAO;
import beans.daos.UserAccountDAO;
import beans.daos.mocks.AuditoriumDAOMock;
import beans.daos.mocks.EventDAOMock;
import beans.daos.mocks.UserAccountDAOMock;
import beans.daos.mocks.UserDAOMock;
import beans.services.*;
import beans.services.csv.*;
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

    @Bean
    public EventDAO eventDAOMock() {
        return new EventDAOMock(Collections.emptyList());
    }

    @Bean
    public UserAccountDAO accountDAOMock() {
        return new UserAccountDAOMock(Collections.emptyList());
    }

    @Bean
    public AuditoriumDAO auditoriumDAOMock() {
        return new AuditoriumDAOMock(Collections.emptyList());
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
    public UserAccountService accountServiceImpl() {
        return new UserAccountServiceImpl(accountDAOMock());
    }

    @Bean
    public AuditoriumService auditoriumServiceImpl() {
        return new AuditoriumServiceImpl(auditoriumDAOMock());
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
    public CsvService accountCsvService() {
        return new UserAccountCsvService();
    }

    @Bean
    public CsvService auditoriumCsvService() {
        return new AuditoriumCsvService();
    }



    @Bean
    public CsvFileReader csvFileReader() {
        return new CsvFileReader();
    }
}
