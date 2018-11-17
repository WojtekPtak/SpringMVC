package beans.services.csv;

import beans.models.Event;
import beans.services.EventService;
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
public class EventCsvService implements CsvService {

    Logger log = LoggerFactory.getLogger(EventCsvService.class);

    @Autowired
    CsvFileReader csvFileReader;
    @Autowired
    EventService eventService;

    public int loadData(File csvFile) {
        int counter = eventService.getAll().size();
        try (MappingIterator<Event> reader = csvFileReader.open(csvFile, Event.class)) {
            StreamSupport
                    .stream(Spliterators.spliteratorUnknownSize(reader, Spliterator.ORDERED), false)
                    .forEach(eventService::create);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return eventService.getAll().size() - counter;
    }

}
