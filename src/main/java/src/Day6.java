package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day6 {
    int distinctCount = 1;

    public Day6() {
        int gridHeight = 0, gridWidth = 0;

        // Find the grid size
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day6.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                gridWidth = line.length();
                gridHeight++;
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }


        Character[][] grid = new Character[gridHeight][gridWidth];

        // Read the word search
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day6.txt"))) {
            String line;
            gridHeight = 0;
            while ((line = reader.readLine()) != null) {
                for (int j = 0; j < line.length(); j++) {
                    grid[gridHeight][j] = line.charAt(j);
                }
                gridHeight++;
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }

        int guardI = -1, guardJ = -1;
        DIRECTION direction = DIRECTION.NORTH;

        // Find the guard
        outerLoop:
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                if (grid[i][j] == '^') {
                    guardI = i;
                    guardJ = j;
                    break outerLoop;
                }
            }
        }

        while (guardI != 0 && guardJ != 0 && guardI != gridHeight - 1 && guardJ != gridWidth - 1) {
            grid[guardI][guardJ] = 'X';

            if (direction == DIRECTION.NORTH) {
                if (grid[guardI - 1][guardJ] != 'X') {
                    distinctCount++;
                }
                if (guardI - 2 >= 0 && grid[guardI - 2][guardJ] == '#') {
                    grid[guardI - 1][guardJ] = '>';
                    direction = DIRECTION.EAST;
                } else {
                    grid[guardI - 1][guardJ] = '^';
                }
                guardI--;
            } else if (direction == DIRECTION.EAST) {
                if (grid[guardI][guardJ + 1] != 'X') {
                    distinctCount++;
                }
                if (guardJ + 2 < grid[0].length && grid[guardI][guardJ + 2] == '#') {
                    grid[guardI][guardJ + 1] = 'V';
                    direction = DIRECTION.SOUTH;
                } else {
                    grid[guardI][guardJ + 1] = '>';
                }
                guardJ++;
            } else if (direction == DIRECTION.SOUTH) {
                if (grid[guardI + 1][guardJ] != 'X') {
                    distinctCount++;
                }
                if (guardI + 2 < grid.length && grid[guardI + 2][guardJ] == '#') {
                    grid[guardI + 1][guardJ] = '<';
                    direction = DIRECTION.WEST;
                } else {
                    grid[guardI + 1][guardJ] = 'V';
                }
                guardI++;
            } else {
                if (grid[guardI][guardJ - 1] != 'X') {
                    distinctCount++;
                }
                if (guardJ - 2 >= 0 && grid[guardI][guardJ - 2] == '#') {
                    grid[guardI][guardJ - 1] = '^';
                    direction = DIRECTION.NORTH;
                } else {
                    grid[guardI][guardJ - 1] = '<';
                }
                guardJ--;
            }
        }
    }

    public int getDistinctCount() {
        return distinctCount;
    }

    enum DIRECTION {
        NORTH, SOUTH, EAST, WEST
    }
}
