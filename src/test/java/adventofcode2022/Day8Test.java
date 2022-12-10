package adventofcode2022;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import adventofcode2022.Day8.ForestMap;

public class Day8Test {

    @Test
    public void test() {
        String input = """
                30373
                25512
                65332
                33549
                35390
                """;
        ForestMap forestMap = Day8.ForestMap.parse(input);
        Assertions.assertThat(forestMap.numTreesVisible()).isEqualTo(21);
        Assertions.assertThat(forestMap.maxScenicScore()).isEqualTo(8);
    }

}
