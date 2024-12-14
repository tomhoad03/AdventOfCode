package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    int mulTotal;
    int doMulTotal;

    public Day3() {
        StringBuilder corruptedMemory = new StringBuilder();

        // Read the corrupted memory
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day3.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                corruptedMemory.append(line);
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }

        // Create the mul(A,B) regex
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(corruptedMemory.toString());

        // Find matches
        while (matcher.find()) {
            int valueA = Integer.parseInt(matcher.group(1));
            int valueB = Integer.parseInt(matcher.group(2));
            mulTotal += valueA * valueB;
        }

        // Create the mul(A,B) regex
        matcher = pattern.matcher(corruptedMemory.toString().replaceAll("don't\\(\\).*?(do\\(\\)|$)", ""));

        // Find matches
        while (matcher.find()) {
            int valueA = Integer.parseInt(matcher.group(1));
            int valueB = Integer.parseInt(matcher.group(2));
            doMulTotal += valueA * valueB;
        }
    }

    public int getMulTotal() {
        return mulTotal;
    }

    public int getDoMulTotal() {
        return doMulTotal;
    }
}
