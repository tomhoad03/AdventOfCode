package src;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    public static void main(String[] args) {
        Day1 day1 = new Day1();
        assertEquals(2196996, day1.getTotalDistance());
        assertEquals(23655822, day1.getSimilarityScore());

        Day2 day2 = new Day2();
        assertEquals(680, day2.getSafeReportCount());
        assertEquals(710, day2.getToleratedSafeReportCount());

        Day3 day3 = new Day3();
        assertEquals(166905464, day3.getMulTotal());
        assertEquals(72948684, day3.getDoMulTotal());

        Day4 day4 = new Day4();
        assertEquals(2532, day4.getXmasSearchCount());
        assertEquals(1941, day4.getMasSearchCount());
    }
}
