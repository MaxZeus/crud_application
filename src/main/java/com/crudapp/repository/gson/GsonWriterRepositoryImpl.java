package com.crudapp.repository.gson;

import com.crudapp.repository.GenericRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.crudapp.model.Writer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonWriterRepositoryImpl implements GenericRepository<Writer, Integer> {
    private final String FILE_PATH = "writers.json";
    private final Gson GSON = new Gson();

    @Override
    public Writer create(Writer writer) {
        List<Writer> currentWriters = readWritersFromFile();
        Integer newId = generateId(currentWriters);
        writer.setId(newId);
        currentWriters.add(writer);
        writeWritersToFile(currentWriters);
        return writer;
    }

    @Override
    public List<Writer> readAll() {
        return readWritersFromFile();
    }

    @Override
    public Writer getById(Integer number) {
        return readWritersFromFile().stream().filter(w -> w.getId() == number)
                .findFirst().orElse(null);
    }

    @Override
    public Writer update(Writer writerToUpdate) {
        List<Writer> currentWriters = readWritersFromFile().stream()
                .map(existingWriter -> {
                   if (existingWriter.getId() == writerToUpdate.getId()) {
                       return writerToUpdate;
                   }
                   return existingWriter;
                }).collect(Collectors.toList());

        writeWritersToFile(currentWriters);
        return writerToUpdate;
    }

    @Override
    public boolean deleteById(Integer number) {
        List<Writer> writers = readWritersFromFile();
        writers.removeIf(w -> w.getId() == number);
        writeWritersToFile(writers);
        return true;
    }
    private List<Writer> readWritersFromFile() {
        try (FileReader fr = new FileReader(FILE_PATH);
             Scanner scan = new Scanner(fr)) {
            Type writerType = new TypeToken<List<Writer>>(){}.getType();
            return GSON.fromJson(scan.nextLine(), writerType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private Integer generateId(List<Writer> writers) {return writers.stream().mapToInt(Writer::getId).max().orElse(1);}

    private void writeWritersToFile(List<Writer> writers) {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            fw.write(GSON.toJson(writers));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
