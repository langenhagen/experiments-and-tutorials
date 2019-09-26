# Gradle Playground
- author: langenhagen
- version: 2018-04-13

initial example taken from: https://spring.io/guides/gs/gradle/


## Gradle commands

gradle tasks
gradle tasks --all      # shows also \"other tasks\"
gradle wrapper          # creates you a gradle wrapper
gradle project          # information about the gradle projects
gradle dependencies     # shows all transient dependencies of your project


./gradlew assemble  # will build your artifacts
./gradlew build  # will build your artifacts and do additional checks (probably does assemble, lint and test)
./gradlew build --dry-run
./gradlew run
