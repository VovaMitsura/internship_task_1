package org.example.util;

import org.example.model.Book;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AttributeStatisticTest {

  @Test
  void get_stat_by_attribute_occurrence_should_return_map_with_occurrences() {
    // given
    AttributeStatistic<Book> attributeStatistic = new AttributeStatistic<>();

    // when
    Map<String, Integer> result = attributeStatistic.getStatByAttributeOccurrence(books, "author");

    // then
    assertEquals(3, result.size());
    assertEquals(3, result.get("Author1"));
  }

  List<Book> books = List.of(
    new Book("Title1", "Author1", 100, "Genre1"),
    new Book("Title2", "Author2", 200, "Genre2"),
    new Book("Title3", "Author1", 300, "Genre3"),
    new Book("Title4", "Author4", 400, "Genre4"),
    new Book("Title5", "Author1", 500, "Genre5")
  );
}