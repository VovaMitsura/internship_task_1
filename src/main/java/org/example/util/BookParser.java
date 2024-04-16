package org.example.util;

import org.example.model.Book;

import java.nio.file.Path;
import java.util.List;

public interface BookParser {
  List<Book> parseDirectory(String directory);
  List<Book> parseFile(Path path);
}
