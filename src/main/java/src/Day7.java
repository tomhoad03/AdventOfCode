package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day7 {
    long totalCalibrationResult;
    long concatTotalCalibrationResult;

    public Day7() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day7.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                long testValue = Long.parseLong(line.split(": ")[0]);
                ArrayList<Integer> levels = Arrays.stream(line.split(": ")[1].split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toCollection(ArrayList::new));

                if (levels.size() == 1) {
                    if (testValue == levels.getFirst()) {
                        totalCalibrationResult += testValue;
                        concatTotalCalibrationResult += testValue;
                    }
                    continue;
                }

                ArrayList<String> operators = generateTernaryValues(levels.size() - 1);
                boolean addedBinary = false, addedTernary = false;

                for (String operator : operators) {
                    long value = getValue(operator, levels);

                    if (testValue == value && !operator.contains("2") && !addedBinary) {
                        totalCalibrationResult += testValue;
                        addedBinary = true;
                    }
                    if (testValue == value && !addedTernary) {
                        concatTotalCalibrationResult += testValue;
                        addedTernary = true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    private static long getValue(String operator, ArrayList<Integer> levels) {
        long value = levels.getFirst();

        for (int i = 0; i < operator.length(); i++) {
            if (operator.charAt(i) == '1') {
                value += levels.get(i + 1);
            } else if (operator.charAt(i) == '0') {
                value *= levels.get(i + 1);
            } else if (operator.charAt(i) == '2') {
                value = Long.parseLong(String.valueOf(value) + levels.get(i + 1));
            }
        }
        return value;
    }

    public static ArrayList<String> generateTernaryValues(int length) {
        int maxValue = (int) Math.pow(3, length);
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < maxValue; i++) {
            String ternaryValue = String.format("%" + length + "s", Integer.toString(i, 3)).replace(' ', '0');
            result.add(ternaryValue);
        }
        return result;
    }

    public long getTotalCalibrationResult() {
        return totalCalibrationResult;
    }

    public long getConcatTotalCalibrationResult() {
        return concatTotalCalibrationResult;
    }
}
