package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day2 {
    public static void main(String[] args) {
        int safeReportCount = 0;
        int toleratedSafeReportCount = 0;

        // Read the input reports
        try {
            Scanner myReader = new Scanner(new File("src/main/resources/day2.txt"));
            while (myReader.hasNextLine()) {
                ArrayList<Integer> levels = Arrays.stream(myReader.nextLine().split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toCollection(ArrayList::new));

                if (isReportSafe(levels)) {
                    safeReportCount++;
                    continue;
                }

                // Check for tolerated safe reports
                for (int i = 0; i < levels.size(); i++) {
                    ArrayList<Integer> faultedLevels = new ArrayList<>(levels);
                    faultedLevels.remove(i);

                    if (isReportSafe(faultedLevels)) {
                        toleratedSafeReportCount++;
                        break;
                    }
                }
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found!");
        }

        // Part 1 Answer - 680
        System.out.println("Safe report count: " + safeReportCount);
        assertEquals(680, safeReportCount);

        // Part 2 Answer - 680
        System.out.println("Tolerated safe report count: " + (safeReportCount + toleratedSafeReportCount));
        assertEquals(710, safeReportCount + toleratedSafeReportCount);
    }

    private static boolean isReportSafe(ArrayList<Integer> levels) {
        int currentLevel = levels.getFirst();
        DIRECTION reportDirection = DIRECTION.NONE;

        // Determine the direction and differences are safe
        for (int i = 1; i < levels.size(); i++) {
            int diff = levels.get(i) - currentLevel;

            // Check the difference is within safe limits
            if (Math.abs(diff) == 0 || Math.abs(diff) > 3) {
                return false;
            }

            // Check the direction is consistent
            if (reportDirection == DIRECTION.NONE) {
                reportDirection = (diff > 0) ? DIRECTION.INCREASING : DIRECTION.DECREASING;
            } else if ((reportDirection == DIRECTION.INCREASING && diff < 0) ||
                    (reportDirection == DIRECTION.DECREASING && diff > 0)) {
                return false;
            }

            currentLevel = levels.get(i);
        }
        return true;
    }

    enum DIRECTION {INCREASING, DECREASING, NONE}
}
