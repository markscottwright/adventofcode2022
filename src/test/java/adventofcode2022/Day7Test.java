package adventofcode2022;

import java.util.HashMap;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day7Test {

    @Test
    public void test() {
        HashMap<String, Long> directorySizes = Day7.parseScript("""
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
                $ cd a
                $ ls
                dir e
                29116 f
                2557 g
                62596 h.lst
                $ cd e
                $ ls
                584 i
                $ cd ..
                $ cd ..
                $ cd d
                $ ls
                4060174 j
                8033020 d.log
                5626152 d.ext
                7214296 k
                """);
        HashMap<String, Long> sizesWithChildren = Day7.sizesWithChildren(directorySizes);
        long totalSizeOfMatchingDirectories = sizesWithChildren.values().stream().filter(s -> s < 100000)
                .mapToLong(v -> v).sum();
        Assertions.assertThat(totalSizeOfMatchingDirectories).isEqualTo(95437);
    }

}
