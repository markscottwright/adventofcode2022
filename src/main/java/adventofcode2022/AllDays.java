package adventofcode2022;

import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.lang3.time.StopWatch;

public class AllDays {
    public static void main(String[] args) {
        List<Consumer<String[]>> days = List.of(Day1::main, Day2::main, Day3::main, Day4::main);
        days.forEach(m -> {
            StopWatch stopWatch = StopWatch.createStarted();
            m.accept(args);
            System.out.println(stopWatch.formatTime());
            System.out.println();
        });
    }
}
