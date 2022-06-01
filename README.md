# Задание
Написание автоматизированных тестов для [The Rick and Morty API](https://rickandmortyapi.com/)

Применены Cucumber, JUnit, Maven, Rest Assured, Allure

# Описание и ссылки на основные классы
[class CucumberRunnerTest](src/test/java/runner/CucumberRunnerTest.java) - основной класс для запуска  всех сценариев

# Запуск тестов
mvn clean test

# Построение локально отчетов
mvn allure:serve

# Входные данные
задаются в файле [application.properties](src/test/resources/application.properties)
