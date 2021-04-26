package hu.nive.ujratervezes.kepesitovizsga.dogs;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DogManager {
    private List<Dog> dogs = new ArrayList<>();

    public DogManager() {
        loadDogs();
    }

    private void loadDogs() {
        try (BufferedReader bf = Files.newBufferedReader(Path.of("src/main/resources/dogs.csv"))) {
            String line = bf.readLine();
            while ((line = bf.readLine()) != null) {
                String[] result = line.split(";");
                dogs.add(new Dog(result[1],result[4],result[5]));
            }
        } catch (IOException e) {
            throw new IllegalStateException("file error");
        }
    }
    public String getCountryByExactDogSpecies(String s) {
        return dogs.stream()
                .filter(dog -> dog.getName().equals(s))
                .findAny()
                .map(Dog::getCountry)
                .orElseThrow(() -> new IllegalArgumentException("No such dog name found."));
    }

    public List<Dog> getDogsInAlphabeticalOrderByName() {
        return dogs.stream()
                .sorted(Comparator.comparing(Dog::getName))
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getDogStatistics() {
        return dogs.stream()
                .collect(Collectors.toMap(Dog::getCountry,v -> 1, Integer::sum));
    }
}
