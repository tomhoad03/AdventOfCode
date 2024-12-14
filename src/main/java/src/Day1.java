package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.IntStream;

public class Day1 {
    int totalDistance;
    int similarityScore;

    public Day1() {
        HashMap<Integer, Integer> mapB = new HashMap<>();
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();

        // Read the input lists
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                int valueA = Integer.parseInt(values[0]);
                int valueB = Integer.parseInt(values[1]);

                listA.add(valueA);
                listB.add(valueB);

                mapB.put(valueB, mapB.getOrDefault(valueB, 0) + 1);
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }

        // Sort the lists
        Collections.sort(listA);
        Collections.sort(listB);

        // Find the total distance
        totalDistance = IntStream.range(0, listA.size())
                .map(i -> Math.abs(listA.get(i) - listB.get(i)))
                .sum();

        // Find the similarity score
        similarityScore = listA.stream()
                .filter(mapB::containsKey)
                .mapToInt(integer -> integer * mapB.get(integer))
                .sum();
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getSimilarityScore() {
        return similarityScore;
    }
}
