package org.example.util;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

/**
 * Class to get statistic by attribute occurrence
 *
 * @param <E> type of resource
 */
public class AttributeStatistic<E> {

  private final Logger logger = Logger.getLogger(AttributeStatistic.class.getName());

  /**
   * Method to get statistic by attribute occurrence
   *
   * @param resources list of resources
   * @param attribute attribute to get statistic by
   * @return map with attribute values and their occurrences
   */
  public Map<String, Integer> getStatByAttributeOccurrence(List<E> resources, String attribute) {
    return resources.stream()
            .map(resource -> getAttributeValue(resource, attribute))
            .collect(Collectors.toMap(
                    value -> value,
                    value -> 1,
                    Integer::sum
            ));
  }

  /**
   * Sort statistic by value in descending order
   *
   * @param statistic map with statistic
   * @return sorted map by value
   */
  public Map<String, Integer> sortStatistic(Map<String, Integer> statistic) {
    return statistic.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
            ));
  }

  private String getAttributeValue(E resource, String attribute) {
    try {
      Field field = resource.getClass().getDeclaredField(attribute);
      field.setAccessible(true);
      return field.get(resource).toString();
    } catch (NoSuchFieldException | IllegalAccessException e) {
      logger.warning(String.format("Error while getting field %s", attribute));
    }
    return null;
  }
}
