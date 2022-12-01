package adventofcode2022;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Util {

    /**
     * Return contents of resource named "classname.txt" as text
     */
    public static String inputAsString(Class<?> callingClass) {
        String dayName = callingClass.getSimpleName().toLowerCase();
        try {
            return new String(callingClass.getResourceAsStream("/" + dayName + ".txt").readAllBytes(),
                    StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
