# Учебный проект. Тестирование API
[Документация API](https://code.s3.yandex.net/qa-automation-engineer/java/cheatsheets/paid-track/diplom/api-documentation.pdf).

Стэк:
- Java 11
- JUnit 4.13.2
- Allure 2.22.2
- RestAssured 5.3.1
- GSON 2.10.1
## Что было сделано
1. Написаны все тесты, указанные в задании.
2. В тестах проверяется тело и код ответа.
3. Все тесты независимы.
4. Нужные тестовые данные создаются перед тестом и удаляются после того, как он выполнится.
5. Сделан Allure-отчёт. Отчёт добавлен в пул-реквест.
## Тест-кейсы
### Создание пользователя:
- создать уникального пользователя;
- создать пользователя, который уже зарегистрирован;
- создать пользователя и не заполнить одно из обязательных полей.
### Логин пользователя:
- логин под существующим пользователем,
- логин с неверным логином и паролем.
### Изменение данных пользователя:
- с авторизацией,
- без авторизации,
Для обеих ситуаций нужно проверить, что любое поле можно изменить. Для неавторизованного пользователя — ещё и то, что система вернёт ошибку.
### Создание заказа:
- с авторизацией,
- без авторизации,
- с ингредиентами,
- без ингредиентов,
- с неверным хешем ингредиентов.
### Получение заказов конкретного пользователя:
- авторизованный пользователь,
- неавторизованный пользователь.
