package org.example.util;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Item {
  private String value;
  private int count;
}
