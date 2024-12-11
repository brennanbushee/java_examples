package parallel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class LongestWordParallelExample {

    public static void main(String[] args) {
        Path filePath = Paths.get("data/article.txt");

        AtomicReference<String> longestWord = new AtomicReference<>("");

        try (Stream<String> lines = Files.lines(filePath).parallel()) {
            lines.forEach(line -> {
                String longestInLine = Arrays.stream(line.replaceAll("^[\\W\\s]", "").split("\\s+|-"))
                        .max(String::compareTo)
                        .orElse("");
                // Atomic update to ensure thread safety when updating the longest word
                longestWord.updateAndGet(current ->
                        longestInLine.length() > current.length() ? longestInLine : current);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Longest word: " + longestWord.get());
    }
}

