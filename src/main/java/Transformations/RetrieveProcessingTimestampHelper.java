package Transformations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RetrieveProcessingTimestampHelper {

    public static String retrieveTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return now.format(format);
    }

}
