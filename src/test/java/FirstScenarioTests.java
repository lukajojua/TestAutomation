import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FirstScenarioTests {

    @Test
    void test1() {
        String json = "{userName: automation," +
                " password: Automation@!@123}";
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(json);
        given().contentType(ContentType.JSON)
                .body(jsonObject)
                .post("https://bookstore.toolsqa.com/Account/v1/User")
                .then().body("code", equalTo("1204")).body("message", equalTo("User exists!"));


    }

    @Test
    void test2() {
        String json = "{userName: automation," +
                " password: 'Auto@2'}";
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(json);
        given().contentType(ContentType.JSON)
                .body(jsonObject)
                .post("https://bookstore.toolsqa.com/Account/v1/User")
                .then().body("code", equalTo("1300")).body("message", equalTo("Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer."));

    }

    @Test
    void test3() {
        String json = "{userName: automation," +
                " password:'' }";
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(json);
        given().contentType(ContentType.JSON)
                .body(jsonObject)
                .post("https://bookstore.toolsqa.com/Account/v1/User")
                .then().body("code", equalTo("1200")).body("message", equalTo("UserName and Password required."));

    }
}
