package adventofcode2022;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

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

    /**
     * Return lines in resource named "classname.txt" as stream of strings
     */
    public static Stream<String> inputAsLinesStream(Class<?> callingClass) {
        return new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(inputAsString(callingClass).getBytes()))).lines();
    }

}
