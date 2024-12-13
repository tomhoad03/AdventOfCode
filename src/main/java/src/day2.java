package src;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day2 {
    public static void main(String[] args) {
        enum DIRECTION {INCREASING, DECREASING, NONE}
        int safeReportCount = 0;

        // Read the input reports
        try {
            Scanner myReader = new Scanner(new File("src/main/resources/day2.txt"));
            while (myReader.hasNextLine()) {
                List<Integer> levels = Arrays.stream(myReader.nextLine().split(" "))
                        .map(Integer::parseInt)
                        .toList();

                boolean safeReport = true;
                int currentLevel = levels.getFirst();
                DIRECTION reportDirection = DIRECTION.NONE;

                // Determine the direction and differences are safe
                for (int i = 1; i < levels.size(); i++) {
                    int diff = levels.get(i) - currentLevel;

                    // Check the difference is within safe limits
                    if (Math.abs(diff) == 0 || Math.abs(diff) > 3) {
                        safeReport = false;
                        break;
                    }

                    // Check the direction is consistent
                    if (reportDirection == DIRECTION.NONE) {
                        reportDirection = (diff > 0) ? DIRECTION.INCREASING : DIRECTION.DECREASING;
                    } else if ((reportDirection == DIRECTION.INCREASING && diff < 0) ||
                            (reportDirection == DIRECTION.DECREASING && diff > 0)) {
                        safeReport = false;
                        break;
                    }

                    currentLevel = levels.get(i);
                }
                if (safeReport) {
                    safeReportCount++;
                }
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found!");
        }

        // Part 1 Answer - 680
        System.out.println("Safe report count: " + safeReportCount);
        assertEquals(680, safeReportCount);
    }
}
