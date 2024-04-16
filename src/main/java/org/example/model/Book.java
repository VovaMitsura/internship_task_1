package org.example.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonAutoDetect
@JsonPropertyOrder({"title", "author", "year_published", "genre"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Book {
  private String title;
  private String author;
  private int year_published;
  private String genre;
}
