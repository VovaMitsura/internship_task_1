package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.model.Book;

import java.util.List;
import java.util.logging.Logger;

public class CommandLineRunner implements Runner {

  private static final Logger logger = Logger.getLogger(CommandLineRunner.class.getName());

  @Override
  public void run(String[] args) {
    logger.info("Application started");

    if(args.length != 2) {
      logger.warning("Invalid number of arguments");
      logger.warning("Usage: java -jar app.jar <directory> <attribute>");
      throw new IllegalArgumentException("Invalid number of arguments");
    }

    var objectMapper = new ObjectMapper();
    var fileParser = new JsonBookParser(objectMapper);
    var statistic = new AttributeStatistic<Book>();
    var xmlWriter = new StatisticXMLWriter();
    var directory = args[0];
    var attribute = args[1];

    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    List<Book> books = fileParser.parseDirectory(directory);
    logger.info("Books parsed");

    var statisticMap = statistic.getStatByAttributeOccurrence(books, attribute);
    statisticMap = statistic.sortStatistic(statisticMap);
    logger.info("Statistic calculated");

    xmlWriter.write(attribute, statisticMap);

    logger.info("Application finished");
  }
}
