## Ввести авторизацию на основе URL и/или доменных сущностей
### Цель: научиться защищать приложение с помощью полноценной авторизации и разграничением прав доступа
### Результат: полноценное приложение с безопасностью на основе Spring Security
### Описание задания:
Настроить в приложении авторизацию на основе доменных сущностей и методов сервиса. 
Рекомендации по выполнению:
Не рекомендуется выделять пользователей с разными правами в разные классы - т.е. просто один класс пользователя.
В случае авторизации на основе доменных сущностей и PostgreSQL не используйте GUID для сущностей.
Написать тесты контроллеров, которые проверяют, что все необходимые ресурсы действительно защищены.

Настроен доступ на чтение 