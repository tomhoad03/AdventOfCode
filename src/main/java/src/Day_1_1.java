package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Answer - 2196996
public class Day_1_1 {
    public static void main(String[] args) {
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();

        // Read the input lists
        try {
            Scanner myReader = new Scanner(new File("src/main/resources/day1/input_lists.csv"));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                listA.add(Integer.valueOf(data.split(",")[0]));
                listB.add(Integer.valueOf(data.split(",")[1]));
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
        System.out.println("Total distance: " + totalDistance);
    }
}
