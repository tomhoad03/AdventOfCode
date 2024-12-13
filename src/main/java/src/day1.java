package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day1 {
    public static void main(String[] args) {
        HashMap<Integer, Integer> mapB = new HashMap<>();
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();

        // Read the input lists
        try {
            Scanner myReader = new Scanner(new File("src/main/resources/day1.csv"));
            while (myReader.hasNextLine()) {
                String[] values = myReader.nextLine().split(",");
                int valueA = Integer.parseInt(values[0]);
                int valueB = Integer.parseInt(values[1]);

                listA.add(valueA);
                listB.add(valueB);

                mapB.put(valueB, mapB.getOrDefault(valueB, 0) + 1);
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found!");
        }

        // Sort the lists
        Collections.sort(listA);
        Collections.sort(listB);

        // Find the total distance
        int totalDistance = IntStream.range(0, listA.size())
                .map(i -> Math.abs(listA.get(i) - listB.get(i)))
                .sum();

        // Part 1 Answer - 2196996
        System.out.println("Total distance: " + totalDistance);
        assertEquals(2196996, totalDistance);

        // Find the similarity score
        int similarityScore = listA.stream()
                .filter(mapB::containsKey)
                .mapToInt(integer -> integer * mapB.get(integer))
                .sum();

        // Part 2 Answer - 23655822
        System.out.println("Similarity score: " + similarityScore);
        assertEquals(23655822, similarityScore);
    }
}
