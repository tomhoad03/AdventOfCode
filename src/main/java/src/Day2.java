package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day2 {
    int safeReportCount = 0;
    int toleratedSafeReportCount = 0;

    public Day2() {
        // Read the input reports
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day2.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<Integer> levels = Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toCollection(ArrayList::new));

                if (isReportSafe(levels)) {
                    safeReportCount++;
                    continue;
                }

                // Check for tolerated safe reports
                for (int i = 0; i < levels.size(); i++) {
                    ArrayList<Integer> faultedLevels = new ArrayList<>(levels);
                    // noinspection SuspiciousListRemoveInLoop
                    faultedLevels.remove(i);

                    if (isReportSafe(faultedLevels)) {
                        toleratedSafeReportCount++;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }
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

    public int getSafeReportCount() {
        return safeReportCount;
    }

    public int getToleratedSafeReportCount() {
        return safeReportCount + toleratedSafeReportCount;
    }

    enum DIRECTION {INCREASING, DECREASING, NONE}
}
