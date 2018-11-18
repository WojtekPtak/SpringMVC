package beans.services.csv;


import beans.models.Auditorium;
import beans.services.AuditoriumService;
import com.fasterxml.jackson.databind.MappingIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.CsvFileReader;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.StreamSupport;


@Service
public class AuditoriumCsvService implements CsvService {

    Logger log = LoggerFactory.getLogger(AuditoriumCsvService.class);

    @Autowired
    CsvFileReader csvFileReader;
    @Autowired
    AuditoriumService auditoriumService;

    public int loadData(File csvFile) {
        int counter = auditoriumService.getAuditoriums().size();
        try (MappingIterator<AuditoriumBuilder> reader = csvFileReader.open(csvFile, AuditoriumBuilder.class)) {
            StreamSupport
                    .stream(Spliterators.spliteratorUnknownSize(reader, Spliterator.ORDERED), false)
                    .forEach( csv -> auditoriumService.add(csv.build()));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return auditoriumService.getAuditoriums().size() - counter;
    }

    private static class AuditoriumBuilder {
        private String name;
        private Integer seatsNumber;
        private String vipSeats;

        public void setName(String name) {
            this.name = name;
        }
        public void setSeatsNumber(Integer seatsNumber) {
            this.seatsNumber = seatsNumber;
        }
        public void setVipSeats(String vipSeats) {
            this.vipSeats = vipSeats;
        }

        public Auditorium build() {
            List<Integer> vipSeatsList = new ArrayList();
            Arrays.stream(vipSeats.split(","))
                .mapToInt(Integer::parseInt)
                .forEach( vipSeatsList::add);
            return new Auditorium(name, seatsNumber, vipSeatsList);
        }
    }
}
