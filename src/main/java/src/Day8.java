package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Day8 {
    int uniqueAntinodes;
    int uniqueExtendedAntinodes;

    public Day8() {
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();

        // Read the map
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Day8.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                grid.add(line.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toCollection(ArrayList::new)));
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }

        HashMap<String, ArrayList<Coordinate>> antennas = new HashMap<>();
        HashSet<Coordinate> antinodes = new HashSet<>();
        HashSet<Coordinate> extendedAntinodes = new HashSet<>();

        // Read all the antennas
        for (ArrayList<Character> row : grid) {
            for (Character character : row) {
                if (!character.equals('.')) {
                    antennas.putIfAbsent(character.toString(), new ArrayList<>());
                    antennas.get(character.toString()).add(new Coordinate(row.indexOf(character), grid.indexOf(row)));
                }
            }
        }

        // Calculate the antinode locations
        for (String key : antennas.keySet()) {
            ArrayList<Coordinate> list = antennas.get(key);
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    int dx = list.get(j).i() - list.get(i).i();
                    int dy = list.get(j).j() - list.get(i).j();
                    int count = 0;

                    // Add extended antinodes until both directions are out of bounds
                    while (true) {
                        Coordinate antinodeA = new Coordinate(list.get(i).i() - (dx * count), list.get(i).j() - (dy * count));
                        Coordinate antinodeB = new Coordinate(list.get(j).i() + (dx * count), list.get(j).j() + (dy * count));
                        int bothOutsideCheck = 0;

                        // Add antinodes if within bounds
                        if (antinodeA.i() >= 0 && antinodeA.i() < grid.size() && antinodeA.j() >= 0 && antinodeA.j() < grid.getFirst().size()) {
                            if (count == 1) {
                                antinodes.add(antinodeA);
                            }
                            extendedAntinodes.add(antinodeA);
                        } else {
                            bothOutsideCheck++;
                        }
                        if (antinodeB.i() >= 0 && antinodeB.i() < grid.size() && antinodeB.j() >= 0 && antinodeB.j() < grid.getFirst().size()) {
                            if (count == 1) {
                                antinodes.add(antinodeB);
                            }
                            extendedAntinodes.add(antinodeB);
                        } else {
                            bothOutsideCheck++;
                        }
                        if (bothOutsideCheck == 2) {
                            break;
                        }
                        count++;
                    }
                }
            }
        }

        uniqueAntinodes = antinodes.size();
        uniqueExtendedAntinodes = extendedAntinodes.size();
    }

    public int getUniqueAntinodes() {
        return uniqueAntinodes;
    }

    public int getUniqueExtendedAntinodes() {
        return uniqueExtendedAntinodes;
    }

    record Coordinate(int i, int j) { }
}
