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

                    Coordinate antinodeA = new Coordinate(list.get(i).i() - dx, list.get(i).j() - dy);
                    Coordinate antinodeB = new Coordinate(list.get(j).i() + dx, list.get(j).j() + dy);

                    if (antinodeA.i() >= 0 && antinodeA.i() < grid.size() && antinodeA.j() >= 0 && antinodeA.j() < grid.getFirst().size()) {
                        antinodes.add(antinodeA);
                    }
                    if (antinodeB.i() >= 0 && antinodeB.i() < grid.size() && antinodeB.j() >= 0 && antinodeB.j() < grid.getFirst().size()) {
                        antinodes.add(antinodeB);
                    }
                }
            }
        }

        uniqueAntinodes = antinodes.size();
    }

    public int getUniqueAntinodes() {
        return uniqueAntinodes;
    }

    record Coordinate(int i, int j) { }
}
