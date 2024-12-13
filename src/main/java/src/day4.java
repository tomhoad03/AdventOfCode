package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day4 {
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

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        int xmasSearchCount = 0;
        int masSearchCount = 0;

        // Read the word search
        try {
            Scanner myReader = new Scanner(new File("src/main/resources/day4.txt"));
            while (myReader.hasNextLine()) {
                grid.add(myReader.nextLine().chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toCollection(ArrayList::new)));
            }
        } catch (java.io.FileNotFoundException e) {
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
                        System.out.println(currentString);
                        masSearchCount++;
                    }
                }
            }
        }

        // Part 1 Answer - 2532
        System.out.println("Xmas search Count: " + xmasSearchCount);
        assertEquals(2532, xmasSearchCount);

        // Part 2 Answer - 1941
        System.out.println("Mas search Count: " + masSearchCount);
        assertEquals(1941, masSearchCount);
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
}