package org.example.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.example.model.Book;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@AllArgsConstructor
public class JsonBookParser implements BookParser {

  private final String JSON_EXTENSION = ".json";
  private final Logger logger = Logger.getLogger(JsonBookParser.class.getName());
  private ObjectMapper objectMapper;

  @Override
  public List<Book> parseDirectory(String directory) {
    Path path = Paths.get(directory);
    if (!Files.exists(path) || !Files.isDirectory(path)) {
      logger.warning("Directory not found");
      throw new IllegalArgumentException("Directory not found");
    }

    try (var folderReader = Files.list(path)) {
      return folderReader
              .filter(Files::isRegularFile)
              .filter(file -> file.toString().endsWith(JSON_EXTENSION))
              .map(this::parseFile)
              .collect(ArrayList::new, List::addAll, List::addAll);
    } catch (IOException e) {
      logger.warning("Error while reading directory");
      throw new RuntimeException(e);
    }
  }

  public List<Book> parseFile(Path path) {
    try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
      return objectMapper.readValue(reader, new TypeReference<>() {
      });
    } catch (IOException e) {
      logger.warning("Error while reading file");
      throw new RuntimeException(e);
    }
  }
}
