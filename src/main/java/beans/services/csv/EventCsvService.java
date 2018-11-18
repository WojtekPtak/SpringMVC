package beans.services.csv;

import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Rate;
import beans.services.AuditoriumService;
import beans.services.EventService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.MappingIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import util.CsvFileReader;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
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
    @Autowired
    AuditoriumService auditoriumService;

    public int loadData(File csvFile) {
        int counter = eventService.getAll().size();
        try (MappingIterator<EventBuilder> reader = csvFileReader.open(csvFile, EventBuilder.class)) {
            StreamSupport
                    .stream(Spliterators.spliteratorUnknownSize(reader, Spliterator.ORDERED), false)
                    .forEach(e -> eventService.create(e.build(auditoriumService)));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return eventService.getAll().size() - counter;
    }


    private static class EventBuilder {
        private String name;
        private Rate rate;
        private double basePrice;
        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime dateTime;

        //@JsonProperty("auditorium")
        private String auditoriumName;

        public void setName(String name) {
            this.name = name;
        }
        public void setRate(Rate rate) {
            this.rate = rate;
        }
        public void setBasePrice(double basePrice) {
            this.basePrice = basePrice;
        }
        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }
        public void setAuditorium(String auditorium) {
            this.auditoriumName = auditorium;
        }

        public Event build(AuditoriumService auditoriumService) {
            List<Auditorium> testwp = auditoriumService.getAuditoriums();
            Auditorium auditorium = auditoriumService.getByName(auditoriumName);
            if(auditorium == null) {
                throw new IllegalStateException(String.format("Auditorium '%s' used in CSV file doesn't exist!", auditoriumName));
            }
            return new Event(name, rate, basePrice, dateTime, auditorium);
        }
    }
}
