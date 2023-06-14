package com.crudapp.repository.gson;

import com.crudapp.model.Label;
import com.crudapp.repository.GenericRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GsonLabelRepositoryImpl implements GenericRepository<Label, Integer> {
    private final String FILE_PATH = "labels.json";
    private final Gson GSON = new Gson();

    @Override
    public Label create(Label label) {
        List<Label> currentLabels = readLabelsFromFile();
        Integer newId = generateId(currentLabels);
        label.setId(newId);
        currentLabels.add(label);
        writeLabelsToFile(currentLabels);
        return label;
    }


    @Override
    public List<Label> readAll() {
        return readLabelsFromFile();
    }

    @Override
    public Label getById(Integer integer) {
        return readLabelsFromFile().stream().filter(l -> l.getId() == integer)
                .findFirst().orElse(null);
    }

    @Override
    public Label update(Label labelToUpdate) {
        List<Label> currentLabels = readLabelsFromFile().stream()
                .map(existingLabel -> {
                    if (existingLabel.getId() == labelToUpdate.getId()) {
                        return labelToUpdate;
                    }
                    return existingLabel;
                }).collect(Collectors.toList());

        writeLabelsToFile(currentLabels);
        return labelToUpdate;
    }

    @Override
    public boolean deleteById(Integer number) {
        List<Label> labels = readLabelsFromFile();
        labels.removeIf(l -> l.getId() == number);
        writeLabelsToFile(labels);
        return true;
    }

    private List<Label> readLabelsFromFile() {
        try (FileReader fr = new FileReader(FILE_PATH);
             Scanner scan = new Scanner(fr)) {
            Type labelType = new TypeToken<List<Label>>() {}.getType();
            return GSON.fromJson(scan.nextLine(), labelType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private Integer generateId(List<Label> labels) {
        return labels.stream().mapToInt(Label::getId).max().orElse(1);
    }

    private void writeLabelsToFile(List<Label> labels) {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            fw.write(GSON.toJson(labels));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
