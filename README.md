# Lentil: A Static Code Analyzer for Java

Lentil is a new experimental static code analyzer for Java.

## Requirements

- Java 21
- Maven (for developers)

# How to run

- Download one of our [releases](https://github.com/clueless-skywatcher/lentil/releases) on GitHub.
- Download the jar file for the latest release.
- Open up a terminal and navigate (cd) to the path where the lentil jar is downloaded
- Type

  ```
  $ java -jar lentil-X.X.jar <path of Java file to run on>.java -c <path of analyzer-config.json>.json
  ```

  If you already have the `analyzer-config.json` file in your current directory, you can skip adding the `-c` option. An output should look something like this:
  ```
  9 issues found
  Line 11: Annotation @Deprecated present on the same line as declaration abcd
  Line 15: Annotation @Nullable present on the same line as declaration abc
  Line 18: Annotation @Deprecated present on the same line as declaration xyz1
  Line 30: Annotation @Named present on the same line as declaration xyz1
  Line 32: Annotation @Nullable present on the same line as declaration xyz3
  Line 32: Annotation @Nullable present on the same line as declaration xyz2
  Line 32: Annotation @Named present on the same line as declaration xyz2
  Line 32: Annotation @Named present on the same line as declaration xyz3
  Line 35: Annotation @SuppressWarnings present on the same line as declaration ABCD
  ```

## Building with Maven

- Clone the repo
- Run `mvn compile assembly:single`
