package src;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdventTest {
    @Test
    void Day1_Test() {
        long startTime = System.nanoTime();
        Day1 day1 = new Day1();
        long stopTime = System.nanoTime();
        System.out.println("Day 1 execution time: " + ((stopTime - startTime) / 1000000000D));
        assertEquals(2196996, day1.getTotalDistance());
        assertEquals(23655822, day1.getSimilarityScore());
    }

    @Test
    void Day2_Test() {
        long startTime = System.nanoTime();
        Day2 day2 = new Day2();
        long stopTime = System.nanoTime();
        System.out.println("Day 2 execution time: " + ((stopTime - startTime) / 1000000000D));
        assertEquals(680, day2.getSafeReportCount());
        assertEquals(710, day2.getToleratedSafeReportCount());
    }

    @Test
    void Day3_Test() {
        long startTime = System.nanoTime();
        Day3 day3 = new Day3();
        long stopTime = System.nanoTime();
        System.out.println("Day 3 execution time: " + ((stopTime - startTime) / 1000000000D));
        assertEquals(166905464, day3.getMulTotal());
        assertEquals(72948684, day3.getDoMulTotal());
    }

    @Test
    void Day4_Test() {
        long startTime = System.nanoTime();
        Day4 day4 = new Day4();
        long stopTime = System.nanoTime();
        System.out.println("Day 4 execution time: " + ((stopTime - startTime) / 1000000000D));
        assertEquals(2532, day4.getXmasSearchCount());
        assertEquals(1941, day4.getMasSearchCount());
    }

    @Test
    void Day5_Test() {
        long startTime = System.nanoTime();
        Day5 day5 = new Day5();
        long stopTime = System.nanoTime();
        System.out.println("Day 5 execution time: " + ((stopTime - startTime) / 1000000000D));
        assertEquals(4957, day5.getMiddleNumberCount());
        assertEquals(6938, day5.getIncorrectMiddleNumberCount());
    }

    @Test
    void Day6_Test() {
        long startTime = System.nanoTime();
        Day6 day6 = new Day6();
        long stopTime = System.nanoTime();
        System.out.println("Day 6 execution time: " + ((stopTime - startTime) / 100000000D));
        assertEquals(5177, day6.getDistinctCount());
        // assertEquals(1686, day6.getObstructionCount());
    }

    @Test
    void Day7_Test() {
        long startTime = System.nanoTime();
        Day7 day7 = new Day7();
        long stopTime = System.nanoTime();
        System.out.println("Day 7 execution time: " + ((stopTime - startTime) / 1000000000D));
        assertEquals(2299996598890L, day7.getTotalCalibrationResult());
        assertEquals(362646859298554L, day7.getConcatTotalCalibrationResult());
    }

    @Test
    void Day8_Test() {
        long startTime = System.nanoTime();
        Day8 day8 = new Day8();
        long stopTime = System.nanoTime();
        System.out.println("Day 8 execution time: " + ((stopTime - startTime) / 1000000000D));
        assertEquals(256, day8.getUniqueAntinodes());
    }
}
