package sg.edu.nus.car_rental_chatbot.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

@Service
public class DatasetContextService {

    public String buildDatasetContext() {
        StringBuilder builder = new StringBuilder();

        for (Resource file : loadDatasetFiles()) {
            builder.append("=== ")
                    .append(file.getFilename())
                    .append(" ===\n")
                    .append(readFile(file))
                    .append("\n\n");
        }

        return builder.toString();
    }

    private Resource[] loadDatasetFiles() {
        try {
            Resource[] files = new PathMatchingResourcePatternResolver()
                    .getResources("classpath:data/*.txt");

            Arrays.sort(files, Comparator.comparing(Resource::getFilename, String.CASE_INSENSITIVE_ORDER));

            if (files.length == 0) {
                throw new RuntimeException("No dataset .txt files found in classpath:data/");
            }

            return files;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load dataset files from classpath:data/", e);
        }
    }

    private String readFile(Resource file) {
        try {
            return new String(file.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read dataset file: " + file.getFilename(), e);
        }
    }
}
