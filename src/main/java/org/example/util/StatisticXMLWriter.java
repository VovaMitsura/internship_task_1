package org.example.util;

import lombok.AllArgsConstructor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@AllArgsConstructor
public class StatisticXMLWriter {
  private static final String FILE_NAME = "result/statistic_by_%s.xml";
  private static final String XML_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
  private static final String STATISTICS_OPEN_TAG = "<statistics>\n";
  private static final String STATISTICS_CLOSE_TAG = "</statistics>";
  private static final String ITEM_TAG = "<item>\n<value>%s</value>\n<count>%d</count>\n</item>\n";

  private final Logger logger = Logger.getLogger(StatisticXMLWriter.class.getName());

  public void write(String attribute, Map<String, Integer> items) {
    String fileName = String.format(FILE_NAME, attribute);
    File file = new File(fileName);
    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }

    try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {
      fileWriter.write(XML_VERSION);
      fileWriter.write(STATISTICS_OPEN_TAG);

      for (Map.Entry<String, Integer> entry : items.entrySet()) {
        String itemXml = String.format(ITEM_TAG, entry.getKey(), entry.getValue());
        fileWriter.write(itemXml);
      }

      fileWriter.write(STATISTICS_CLOSE_TAG);
    } catch (IOException e) {
      logger.warning("Error while writing to file");
      throw new RuntimeException(e);
    }

    logger.info("Statistic saved to " + fileName);
  }
}
