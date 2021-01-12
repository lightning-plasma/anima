# anima - Spring Boot Application Sample

Spring BootのシンプルなCRUD アプリケーション  
CとRを実装済

## 技術要素

- Spring Boot 2.4
- JdbcTemplate
- ControllerAdvice
- Thymeleaf
- Gradle
- MySQL

## application 起動

### mysql 起動

```shell
$ cd mysql
$ docker-compose up -d --build
$ mysql -u jung -h 127.0.0.1 -p
```

### browser access

- http://localhost:8080/anima/books
- http://localhost:8080/anima/books/9784422114361
- http://localhost:8080/anima/books/new
