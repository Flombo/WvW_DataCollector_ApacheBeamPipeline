package Transformations;

import java.time.LocalDateTime;

public class RetrieveProcessingTimestampHelper {

    public static String retrieveTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        return now.toString();
    }

}
