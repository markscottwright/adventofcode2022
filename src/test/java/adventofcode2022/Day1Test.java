package adventofcode2022;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day1Test {

    private static final String INPUT = """
            1000
            2000
            3000

            4000

            5000
            6000

            7000
            8000
            9000

            10000
            """;

    @Test
    public void testPart1() {
        long biggestIndividualTotal = Day1.getBiggestIndividualTotal(INPUT);
        Assertions.assertThat(biggestIndividualTotal).isEqualTo(24000);
    }

    @Test
    public void testPart2() {
        long part2 = Day1.getBiggestThreeTotal(INPUT);
        Assertions.assertThat(part2).isEqualTo(45000);
    }
}
