package src;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day3 {
    public static void main(String[] args) {
        StringBuilder corruptedMemory = new StringBuilder();
        int mulTotal = 0;

        // Read the corrupted memory
        try {
            Scanner myReader = new Scanner(new File("src/main/resources/day3.txt"));
            while (myReader.hasNextLine()) {
                corruptedMemory.append(myReader.nextLine());
            }
        } catch (java.io.FileNotFoundException e) {
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

        // Part 1 Answer - 166905464
        System.out.println("Multiply Total: " + mulTotal);
        assertEquals(166905464, mulTotal);

        mulTotal = 0;

        // Create the mul(A,B) regex
        matcher = pattern.matcher(corruptedMemory.toString().replaceAll("don't\\(\\).*?(do\\(\\)|$)", ""));

        // Find matches
        while (matcher.find()) {
            int valueA = Integer.parseInt(matcher.group(1));
            int valueB = Integer.parseInt(matcher.group(2));
            mulTotal += valueA * valueB;
        }

        // Part 2 Answer - 72948684
        System.out.println("Multiply Total: " + mulTotal);
        assertEquals(72948684, mulTotal);
    }
}
