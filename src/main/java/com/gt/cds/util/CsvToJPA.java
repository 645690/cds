package com.gt.cds.util;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class CsvToJPA {

    public static List readCSVtoBeans(String file, Class models) throws IOException {
        Resource resource = new ClassPathResource(file);
        FileReader fileReader = new FileReader(resource.getFile());
        List beanList = new CsvToBeanBuilder(fileReader)
                .withType(models).build().parse();
        fileReader.close();
        return beanList;
    }
}
