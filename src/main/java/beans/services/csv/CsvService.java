package beans.services.csv;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/1/2016
 * Time: 7:32 PM
 */
public interface CsvService {

    int loadData(File csvFile);

}
