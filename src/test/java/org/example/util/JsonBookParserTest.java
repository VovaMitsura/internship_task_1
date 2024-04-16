package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonBookParserTest {

  ObjectMapper objectMapper = new ObjectMapper();
  static final String EXISTING_DIRECTORY = "src/test/resources";
  static final String NON_EXISTING_DIRECTORY = "src/test/resources/unknown";

  @Test
  void parse_directory_should_return_list () {
    // given
    var parser = new JsonBookParser(objectMapper);
    // when
    var books = parser.parseDirectory(EXISTING_DIRECTORY);
    // then
    Assertions.assertNotNull(books);
    Assertions.assertNotNull(books.get(0));
  }

  @Test
  void parse_directory_should_throw_exception_when_directory_not_exits () {
    // given
    var parser = new JsonBookParser(objectMapper);
    // when
    // then
    Assertions.assertThrows(IllegalArgumentException.class, () -> parser.parseDirectory(NON_EXISTING_DIRECTORY));
  }
}