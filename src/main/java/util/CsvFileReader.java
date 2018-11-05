package util;


import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

@Component
public class CsvFileReader {
    private static final Logger log = LoggerFactory.getLogger(CsvFileReader.class);

    private final CsvMapper mapper;
    private final CsvSchema schema;

    public CsvFileReader() {
        mapper = new CsvMapper();
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        mapper.enable(CsvParser.Feature.TRIM_SPACES);
        mapper.registerModule(new JavaTimeModule());

        schema = CsvSchema.emptySchema().withHeader();
    }

    public <T> MappingIterator<T> open(File inputFile, Class<T> clazz) {

        System.out.println();
        try {
            File file = inputFile.getAbsoluteFile();
            if(!file.exists()) {
                throw new RuntimeException("File does not exist " + file);
            }
            if(file.length()==0L) {
                throw new RuntimeException("File " + file + " is empty!");
            }

            return mapper
                .readerFor(clazz)
                .with(schema)
                .readValues(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}