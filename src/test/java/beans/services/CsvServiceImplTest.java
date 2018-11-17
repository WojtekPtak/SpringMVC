package beans.services;

import beans.configuration.AppConfiguration;
import beans.configuration.TestCsvServiceConfiguration;
import beans.configuration.db.DataSourceConfiguration;
import beans.configuration.db.DbSessionFactory;
import beans.services.csv.CsvService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class, DataSourceConfiguration.class, DbSessionFactory.class, TestCsvServiceConfiguration.class})
@Transactional
public class CsvServiceImplTest {

    private final File csvDir;

    @Autowired
    private CsvService userCsvService;

    @Autowired
    private CsvService eventCsvService;

    public CsvServiceImplTest() {
        //TODO: path from context
        String dataDirectory = "/Users/wojcptak/Develop/Epam/laory-spring-course-dcd507d35a13/src/main/webapp/WEB-INF/data/csv";
        csvDir = new File(dataDirectory);
    }

    private void loadCsvData(String csvFileName, CsvService csvService, int expected) {
        int counter = -1;
        for (File csvFile : csvDir.listFiles()) {
            if (csvFile != null && csvFile.exists() && csvFile.isFile()) {
                if (csvFile.getName().equals(csvFileName)) {
                    counter = csvService.loadData(csvFile);
                    break;
                }

            }
        }
        assertEquals("Wrong number of loaded users!", counter, expected);
    }

    @Test
    public void loadUserCsvData() {
        loadCsvData("users.csv", userCsvService, 11);
    }

    @Test
    public void loadEventCsvData() {
        loadCsvData("events.csv", eventCsvService, 21);
    }
}