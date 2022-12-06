package adventofcode2022;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day6Test {

    @Test
    public void testPart1() {
        Assertions.assertThat(Day6.findEndPositionOfFirstStartOfPacketMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb")).isEqualTo(7);
        Assertions.assertThat(Day6.findEndPositionOfFirstStartOfPacketMarker("bvwbjplbgvbhsrlpgdmjqwftvncz")).isEqualTo(5);
        Assertions.assertThat(Day6.findEndPositionOfFirstStartOfPacketMarker("nppdvjthqldpwncqszvftbrmjlhg")).isEqualTo(6);
        Assertions.assertThat(Day6.findEndPositionOfFirstStartOfPacketMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")).isEqualTo(10);
        Assertions.assertThat(Day6.findEndPositionOfFirstStartOfPacketMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")).isEqualTo(11);
    }

    @Test
    public void testPart2() {
        Assertions.assertThat(Day6.findEndPositionOfFirstStartOfMessageMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb")).isEqualTo(19);
        Assertions.assertThat(Day6.findEndPositionOfFirstStartOfMessageMarker("bvwbjplbgvbhsrlpgdmjqwftvncz")).isEqualTo(23);
        Assertions.assertThat(Day6.findEndPositionOfFirstStartOfMessageMarker("nppdvjthqldpwncqszvftbrmjlhg")).isEqualTo(23);
        Assertions.assertThat(Day6.findEndPositionOfFirstStartOfMessageMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")).isEqualTo(29);
        Assertions.assertThat(Day6.findEndPositionOfFirstStartOfMessageMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")).isEqualTo(26);
    }
}
