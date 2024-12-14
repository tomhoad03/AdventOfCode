package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day4 {
    static int[][] xmasDirections = {
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1},
            {-1, 0},
            {-1, 1}
    };
    static String searchString = "XMAS";
    int xmasSearchCount;
    int masSearchCount;

    public Day4() {
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();

        // Read the word search
        try (Scanner scanner = new Scanner(new File("src/main/resources/Day4.txt"))) {
            while (scanner.hasNextLine()) {
                grid.add(scanner.nextLine().chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toCollection(ArrayList::new)));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        // Search grid for starting character
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                // Search all the directions from a starting character for xmas
                if (grid.get(i).get(j) == 'X') {
                    for (int[] direction : xmasDirections) {
                        xmasSearchCount = checkXmasStringMatch(grid, i, j, direction[0], direction[1]) ? xmasSearchCount + 1 : xmasSearchCount;
                    }
                }

                // Search all the directions from a starting character for x-mas
                if (grid.get(i).get(j) == 'A') {
                    int mCount = 0, sCount = 0;
                    StringBuilder currentString = new StringBuilder();

                    for (int k = 1; k < 8; k = k+2) {
                        int endI = i + xmasDirections[k][0];
                        int endJ = j + xmasDirections[k][1];

                        // Check boundaries before accessing the grid
                        if (endI >= 0 && endI < grid.size() && endJ >= 0 && endJ < grid.getFirst().size()) {
                            char current = grid.get(endI).get(endJ);
                            if (current == 'M') {
                                mCount++;
                                currentString.append(current);
                            } else if (current == 'S') {
                                sCount++;
                                currentString.append(current);
                            }
                        }
                    }
                    if (mCount == 2 && sCount == 2 && !currentString.toString().equals("SMSM") && !currentString.toString().equals("MSMS")) {
                        masSearchCount++;
                    }
                }
            }
        }
    }

    private static boolean checkXmasStringMatch(ArrayList<ArrayList<Character>> grid, int i, int j, int di, int dj) {
        // Check out of bounds
        int endI = i + (searchString.length() - 1) * di;
        int endJ = j + (searchString.length() - 1) * dj;
        if (endI < 0 || endI >= grid.size() || endJ < 0 || endJ >= grid.getFirst().size()) {
            return false;
        }

        // Create a found string
        StringBuilder foundString = new StringBuilder();
        for (int k = 0; k < searchString.length(); k++) {
            foundString.append(grid.get(i + (k * di)).get(j + (k * dj)));
        }

        // Check if the string is found
        return foundString.toString().equals(searchString);
    }

    public int getXmasSearchCount() {
        return xmasSearchCount;
    }

    public int getMasSearchCount() {
        return masSearchCount;
    }
}