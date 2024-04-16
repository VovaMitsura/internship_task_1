# Task Description

## Overview
This task involves developing a console application in Java to parse a list of JSON files representing entities and generate statistics based on one of their attributes. The application should be capable of handling multiple attributes, with the user specifying one of them as input. Additionally, one attribute should be textual and have multiple values. The program should generate an XML file with statistics sorted by count, from highest to lowest.

## Requirements
1. **Code Structure**: Organize the code into separate classes and methods, including entities, parsing logic, console interface, statistic calculation, etc.
2. **No External Libraries**: Avoid using databases and Spring. Work with in-memory collections.
3. **Unit Tests**: Include unit tests for parsing logic and statistic calculation.
4. **Memory Efficiency**: As there might be many files with large sizes, avoid loading entire files into memory.
5. **Multi-threading**: Utilize a thread pool for parsing files, with experiments comparing performance with different numbers of threads.
6. **README**: Provide a README file describing the project's entities, examples of input and output files, and results of experiments with thread count.

## Usage
To run the application:
#### java -jar .\target\internship_task_1-1.0-SNAPSHOT.jar "full_path_to_directory" "attribute"
Example:
##### java -jar .\target\internship_task_1-1.0-SNAPSHOT.jar "C:\Users\Vova Mitsura\IdeaProjects\internship_task_1\src\main\resources" year_published
For parallel execution:
#### java -jar .\target\internship_task_1-1.0-SNAPSHOT.jar -p "full_path_to_directory" "attribute"

## Entity
``` java
 public class Book {
  private String title;
  private String author;
  private int year_published;
  private String genre;
}
```

## Output
The application generates a result directory containing an XML file named "statistics_by_%attribute.xml".

## Example
Suppose the entity is about books. A sample JSON file could be:

```json
[
  {
    "title": "1984",
    "author": "George Orwell",
    "year_published": 1949,
    "genre": "Dystopian, Political Fiction"
  },
  {
    "title": "Pride and Prejudice",
    "author": "Jane Austen",
    "year_published": 1813,
    "genre": "Romance, Satire"
  },
  {
    "title": "Romeo and Juliet",
    "author": "William Shakespeare",
    "year_published": 1597,
    "genre": "Romance, Tragedy"
  }
]
```

If the user requests statistics based on the "genre" attribute, the output XML file (statistics_by_genre.xml) might look like:
```
<statistics>
  <item>
    <value>Romance</value>
    <count>2</count>
  </item>
  <item>
    <value>Dystopian</value>
    <count>1</count>
  </item>
</statistics>
```
</statistics>

## Example of sequential and parallel execution
![image](https://github.com/VovaMitsura/internship_task_1/assets/95585344/2edf8d15-e784-4eac-8ae4-5d6d92e83509)

![image](https://github.com/VovaMitsura/internship_task_1/assets/95585344/33b58596-bf7e-43b5-abf3-6e6578960324)



