package it.uniroma2.ispw.utils;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import it.uniroma2.ispw.utils.exception.ItemNotFoundException;
import it.uniroma2.ispw.utils.exception.SystemException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class CSVManager {
    private CSVManager() {
    }

    public static String getCsvDir()  {
        try (InputStream input = ConnectionDB.class.getClassLoader().getResourceAsStream("config.properties")) {
            if(input == null) throw new ItemNotFoundException("Directory base non trovata");

            Properties properties = new Properties();
            properties.load(input);
            return properties.getProperty("csv.dir");

        } catch (IOException | ItemNotFoundException e) {
            System.out.println(e.getMessage());
            return null;

        }
    }

    public static void closeCsvReader (CSVReader csvReader) throws SystemException {
            if (csvReader != null) {
                try {
                    csvReader.close();

                } catch (Exception e) {
                    throw new SystemException(e.getMessage());
                }
            }
        }
            public static void closeCsvWriter (CSVWriter csvWriter) throws SystemException {
                if (csvWriter != null) {
                    try {
                        csvWriter.close();

                    } catch (Exception e) {
                        throw new SystemException(e.getMessage());
                    }
                }
            }


}


