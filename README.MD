# IdeaPlatform Enrollment Project

## Задание
Напишите программу на языке программирования java, которая прочитает файл tickets.json и рассчитает:
- среднее время полета между городами Владивосток и Тель-Авив
- 90-й процентиль времени полета между городами Владивосток и Тель-Авив

Программа должна вызываться из командной строки Linux, результаты должны быть представлены в текстовом виде.
В качестве результата нужно прислать ответы на поставленные вопросы и ссылку на исходный код.  

## Решение

- Парсинг JSON-файла осуществляется при помощи библиотеки Gson. Контракт ридера описан в `TicketsReader`. Есть две реализации: жадная и потоковая. Жадная подгружает все данные из файла и держит их в памяти. Потоковая вытягивает по одной записи из файла.
- Подсчет перцентиля осуществлялся при помощи `Percentile` из `apache.commons.math3`. 

## Установка и запуск

1. Копируем репозиторий:
```shell
git copy
cd ideaplatform-enrollment
```
2. Собираем jar:
```shell
./mvnw clean
./mvnw package
```
3. Запускаем jar:
```shell
java -jar target/tickets-stats.jar
```