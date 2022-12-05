package adventofcode2022;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import adventofcode2022.Day5.CrateStacks;

public class Day5Test {

    @Test
    public void testPartOne() {
        String input = """
                    [D]
                [N] [C]
                [Z] [M] [P]
                 1   2   3

                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
                """;
        CrateStacks crateStack = Day5.CrateStacks.parse(input);
        assertThat(crateStack.stacks).hasSize(3);
        assertThat(crateStack.instructions).hasSize(4);
        crateStack.run(9000);

        assertThat(crateStack.getTopCratesString()).isEqualTo("CMZ");
    }

}
