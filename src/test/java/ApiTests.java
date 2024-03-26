import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class ApiTests {

    @DisplayName("Получение данных пользователя")
    @Test
    void getUserTestuser2() {
        RestAssured.baseURI = "http://qa-stand-login.inzhenerka.tech/api";
        given()
                .queryParam("API_KEY", "API_KEY123")
                .when()
                .get("/users/testuser2")
                .then()
                .log().all()
                .statusCode(200)
                .body("username", equalTo("testuser2"));
    }

    @DisplayName("Admin: Получение данных")
    @Test
    void getAdminUser() {
        RestAssured.baseURI = "http://qa-stand-login.inzhenerka.tech/api";
        given()
                .queryParam("API_KEY", "API_KEY123")
                .when()
                .get("/users/admin")
                .then()
                .log().all()
                .statusCode(200)
                .body("username", equalTo("admin"));
    }

    @DisplayName("Обновление данных пользователя")
    @Test
    void updateUserTestuser2() {
        String requestBody = """
    {
        "admin": false,
        "age": 45,
        "description": "Привет! Я - счастливый студент ИнженеркаТех и я сегодня изучил REST API",
        "jobtitle": "Старший Тестировщик",
        "name": "Владимир Тестировович",
        "password": "password123",
        "username": "testuser2"
    }
    """;

        RestAssured.baseURI = "http://qa-stand-login.inzhenerka.tech/api";
        given()
                .contentType("application/json")
                .queryParam("API_KEY", "API_KEY123")
                .body(requestBody)
                .when()
                .put("/users/testuser2")
                .then()
                .log().all()
                .statusCode(200); // Убедитесь, что сервер поддерживает обновление данных и корректно обрабатывает PUT запросы
    }

    @DisplayName("Создание нового пользователя")
    @Test
    void createNewUser() {
        String requestBody = """
    {
        "admin": false,
        "age": 45,
        "description": "Привет! Я - студент ИнженеркаТех",
        "jobtitle": "Старший Тестировщик",
        "name": "Владимир Тестировович",
        "password": "password123",
        "username": "testuser2"
    }
    """;

        RestAssured.baseURI = "http://qa-stand-login.inzhenerka.tech/api";
        given()
                .contentType("application/json")
                .queryParam("API_KEY", "API_KEY123")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(200); // Убедитесь, что сервер поддерживает создание новых пользователей и корректно обрабатывает POST запросы
    }


}