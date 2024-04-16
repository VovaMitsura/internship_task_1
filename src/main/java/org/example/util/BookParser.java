package org.example.util;

import org.example.model.Book;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface for parsing books from directory or file
 */
public interface BookParser {
  /**
   * Method to parse directory and return list of books
   *
   * @param directory directory to parse
   * @return list of books parsed from directory
   */
  List<Book> parseDirectory(String directory);

  /**
   * Method to parse file and return list of books
   *
   * @param path path to file
   * @return list of books parsed from file
   */
  List<Book> parseFile(Path path);
}
