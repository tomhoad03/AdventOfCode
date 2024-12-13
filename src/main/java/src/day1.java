package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class day1 {
    public static void main(String[] args) {
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();
        HashMap<Integer, Integer> mapB = new HashMap<>();

        // Read the input lists
        try {
            Scanner myReader = new Scanner(new File("src/main/resources/day1/input_lists.csv"));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int valueA = Integer.parseInt(data.split(",")[0]);
                int valueB = Integer.parseInt(data.split(",")[1]);

                listA.add(valueA);
                listB.add(valueB);

                if (mapB.containsKey(valueB)) {
                    mapB.put(valueB, mapB.get(valueB) + 1);
                } else {
                    mapB.putIfAbsent(valueB, 1);
                }
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found!");
        }

        // Sort the lists
        Collections.sort(listA);
        Collections.sort(listB);

        // Find the total distance
        int totalDistance = 0;
        for (int i = 0; i < listA.size(); i++) {
            totalDistance += Math.abs(listA.get(i) - listB.get(i));
        }

        // Part 1 Answer - 2196996
        System.out.println("Total distance: " + totalDistance);

        // Find the similarity score
        int similarityScore = 0;
        for (Integer integer : listA) {
            if (mapB.containsKey(integer)) {
                similarityScore += integer * mapB.get(integer);
            }
        }

        // Part 2 Answer - 23655822
        System.out.println("Similarity score: " + similarityScore);
    }
}
