package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    int mulTotal;
    int doMulTotal;

    public Day3() {
        StringBuilder corruptedMemory = new StringBuilder();

        // Read the corrupted memory
        try (Scanner scanner = new Scanner(new File("src/main/resources/Day3.txt"))) {
            while (scanner.hasNextLine()) {
                corruptedMemory.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
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
