package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Day5 {
    int middleNumberCount;
    int incorrectMiddleNumberCount;

    public Day5() {
        HashMap<Integer, ArrayList<Integer>> rules = new HashMap<>();
        ArrayList<ArrayList<Integer>> updates = new ArrayList<>();
        ArrayList<ArrayList<Integer>> validUpdates = new ArrayList<>();
        ArrayList<ArrayList<Integer>> invalidUpdates = new ArrayList<>();

        // Read the rules and updates
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day5.txt"))) {
            boolean readingRules = true;
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    readingRules = false;
                    continue;
                }

                // Read the rules
                if (readingRules) {
                    String[] values = line.split("\\|");
                    int valueA = Integer.parseInt(values[0]);
                    int valueB = Integer.parseInt(values[1]);
                    rules.computeIfAbsent(valueA, k -> new ArrayList<>()).add(valueB);
                    continue;
                }

                // Read the updates
                updates.add(Arrays.stream(line.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toCollection(ArrayList::new)));
                validUpdates.add(Arrays.stream(line.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toCollection(ArrayList::new)));
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }

        // Remove invalid updates
        for (ArrayList<Integer> update : updates) {
            outerLoop:
            for (int i = 0; i < update.size(); i++) {
                for (int j = i + 1; j < update.size(); j++) {
                    if (!rules.get(update.get(i)).contains(update.get(j))) {
                        validUpdates.remove(update);
                        invalidUpdates.add(update);
                        break outerLoop;
                    }
                }
            }
        }

        // Count the middle number of every valid update
        middleNumberCount = validUpdates.stream()
                .mapToInt(update -> update.get((update.size() - 1) / 2))
                .sum();

        // Revalidate the invalid updates
        ArrayList<ArrayList<Integer>> revalidatedUpdates = new ArrayList<>();
        for (ArrayList<Integer> invalidUpdate : invalidUpdates) {
            HashMap<Integer, ArrayList<Integer>> invalidRules = new HashMap<>();
            for (Integer value : invalidUpdate) {
                // Filter the rule pairings
                ArrayList<Integer> rulesForValue = new ArrayList<>();
                for (Integer rule : rules.get(value)) {
                    if (invalidUpdate.contains(rule)) {
                        rulesForValue.add(rule);
                    }
                }

                // Filter the rules
                invalidRules.put(value, rulesForValue);
            }

            // Reverse the hashmap and use the number of pairings as the key
            HashMap<Integer, Integer> lengthPairings = new HashMap<>();
            for (Integer value : invalidRules.keySet()) {
                lengthPairings.put(invalidRules.get(value).size(), value);
            }

            ArrayList<Integer> newUpdate = new ArrayList<>();
            for (int i = 0; i < invalidUpdate.size(); i++) {
                newUpdate.add(lengthPairings.get(i));
            }
            revalidatedUpdates.add(newUpdate);
        }

        // Count the middle number of the revalidated updates
        incorrectMiddleNumberCount = revalidatedUpdates.stream()
                .mapToInt(update -> update.get((update.size() - 1) / 2))
                .sum();
    }

    public int getMiddleNumberCount() {
        return middleNumberCount;
    }

    public int getIncorrectMiddleNumberCount() {
        return incorrectMiddleNumberCount;
    }
}
