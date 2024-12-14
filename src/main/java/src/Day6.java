package src;

import java.io.*;
import java.util.Arrays;

public class Day6 {
    int distinctCount = 1;
    int obstructionCount = 0;

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
        Character[][] grid2 = Arrays.stream(grid)
                .map(Character[]::clone)
                .toArray(Character[][]::new);

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

        // Find the number of distinct positions
        distinctPositions(guardI, guardJ, gridHeight, gridWidth, grid, direction);

        // Find the number of obstructions
        obstructions(guardI, guardJ, gridHeight, gridWidth, grid2, direction);
    }

    private void distinctPositions(int guardI, int guardJ, int gridHeight, int gridWidth, Character[][] grid, DIRECTION direction) {
        while (guardI != 0 && guardJ != 0 && guardI != gridHeight - 1 && guardJ != gridWidth - 1) {
            grid[guardI][guardJ] = 'X';

            if (direction == DIRECTION.NORTH) {
                if (grid[guardI - 1][guardJ] != 'X') {
                    distinctCount++;
                }
                if (guardI - 2 >= 0 && grid[guardI - 2][guardJ] == '#') {
                    direction = DIRECTION.EAST;
                }
                guardI--;
            } else if (direction == DIRECTION.EAST) {
                if (grid[guardI][guardJ + 1] != 'X') {
                    distinctCount++;
                }
                if (guardJ + 2 < grid[0].length && grid[guardI][guardJ + 2] == '#') {
                    direction = DIRECTION.SOUTH;
                }
                guardJ++;
            } else if (direction == DIRECTION.SOUTH) {
                if (grid[guardI + 1][guardJ] != 'X') {
                    distinctCount++;
                }
                if (guardI + 2 < grid.length && grid[guardI + 2][guardJ] == '#') {
                    direction = DIRECTION.WEST;
                }
                guardI++;
            } else {
                if (grid[guardI][guardJ - 1] != 'X') {
                    distinctCount++;
                }
                if (guardJ - 2 >= 0 && grid[guardI][guardJ - 2] == '#') {
                    direction = DIRECTION.NORTH;
                }
                guardJ--;
            }
        }
    }

    private void obstructions(int guardI, int guardJ, int gridHeight, int gridWidth, Character[][] grid, DIRECTION direction) {
        grid[guardI][guardJ] = '^';
        for (int i = guardI + 1; i < grid.length; i++) {
            if (grid[i][guardJ] == '#') {
                break;
            } else if (grid[i][guardJ] == '.') {
                grid[i][guardJ] = '^';
            }
        }

        while (guardI != 0 && guardJ != 0 && guardI != gridHeight - 1 && guardJ != gridWidth - 1) {
            if (direction == DIRECTION.NORTH) {
                if (guardI - 2 >= 0 && grid[guardI - 2][guardJ] == '#') {
                    grid[guardI - 1][guardJ] = 'X';
                    direction = DIRECTION.EAST;
                    for (int j = guardJ - 1; j >= 0; j--) {
                        if (grid[guardI - 1][j] == '#') {
                            break;
                        } else if (grid[guardI - 1][j] == '.') {
                            grid[guardI - 1][j] = '>';
                        }
                    }
                } else if (grid[guardI - 1][guardJ] == '>') {
                    grid[guardI - 1][guardJ] = '+';
                    obstructionCount++;
                }
                if (grid[guardI][guardJ] == '.') {
                    grid[guardI][guardJ] = '^';
                }
                guardI--;
            } else if (direction == DIRECTION.EAST) {
                if (guardJ + 2 < grid[0].length && grid[guardI][guardJ + 2] == '#') {
                    grid[guardI][guardJ + 1] = 'X';
                    direction = DIRECTION.SOUTH;
                    for (int i = guardI - 1; i >= 0; i--) {
                        if (grid[i][guardJ + 1] == '#') {
                            break;
                        } else if (grid[i][guardJ + 1] == '.') {
                            grid[i][guardJ + 1] = 'V';
                        }
                    }
                } else if (grid[guardI][guardJ + 1] == 'V') {
                    grid[guardI][guardJ + 1] = '+';
                    obstructionCount++;
                }
                if (grid[guardI][guardJ] == '.') {
                    grid[guardI][guardJ] = '>';
                }
                guardJ++;
            } else if (direction == DIRECTION.SOUTH) {
                if (guardI + 2 < grid.length && grid[guardI + 2][guardJ] == '#') {
                    grid[guardI + 1][guardJ] = 'X';
                    direction = DIRECTION.WEST;
                    for (int j = guardJ + 1; j < grid[0].length; j++) {
                        if (grid[guardI + 1][j] == '#') {
                            break;
                        } else if (grid[guardI + 1][j] == '.') {
                            grid[guardI + 1][j] = '<';
                        }
                    }
                } else if (grid[guardI + 1][guardJ] == '<') {
                    grid[guardI + 1][guardJ] = '+';
                    obstructionCount++;
                }
                if (grid[guardI][guardJ] == '.') {
                    grid[guardI][guardJ] = 'V';
                }
                guardI++;
            } else {
                if (guardJ - 2 >= 0 && grid[guardI][guardJ - 2] == '#') {
                    grid[guardI][guardJ - 1] = 'X';
                    direction = DIRECTION.NORTH;
                    for (int i = guardI + 1; i < grid.length; i++) {
                        if (grid[i][guardJ - 1] == '#') {
                            break;
                        } else if (grid[i][guardJ - 1] == '.') {
                            grid[i][guardJ - 1] = '^';
                        }
                    }
                } else if (grid[guardI][guardJ - 1] == '^') {
                    grid[guardI][guardJ - 1] = '+';
                    obstructionCount++;
                }
                if (grid[guardI][guardJ] == '.') {
                    grid[guardI][guardJ] = '<';
                }
                guardJ--;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Day6-X.txt"))) {
            for (int i = 0; i < gridHeight; i++) {
                for (int j = 0; j < gridWidth; j++) {
                    writer.write(grid[i][j]);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    public int getDistinctCount() {
        return distinctCount;
    }

    public int getObstructionCount() {
        return obstructionCount;
    }

    enum DIRECTION {
        NORTH, SOUTH, EAST, WEST
    }
}
