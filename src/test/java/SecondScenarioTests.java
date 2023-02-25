import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SecondScenarioTests {

    @Test
    void test1() {
        String json = "{userName: mailo," +
                " password: Automation@!@123}";
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(json);
        given().contentType(ContentType.JSON)
                .body(jsonObject)
                .post("https://bookstore.toolsqa.com/Account/v1/User")
                .then().body("username", equalTo("mailo")).body("books.size()", (equalTo(0)));

    }

    @Test
    void test2() {
        String json = "{userName: mailo," +
                " password: Automation@!@123}";
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(json);
        String result = given().contentType(ContentType.JSON)
                .body(jsonObject)
                .post("https://bookstore.toolsqa.com/Account/v1/Authorized").thenReturn()
                .body().asString();
        Assert.assertEquals(result, "false");


    }

    @Test
    void test3() {
        String json = "{userName: mailo," +
                " password: Automation@!@123}";
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(json);
        given().contentType(ContentType.JSON)
                .body(jsonObject)
                .post("https://bookstore.toolsqa.com/Account/v1/GenerateToken").thenReturn()
                .then().body("status", equalTo("Success")).body("result", (equalTo("User authorized successfully.")));

    }

    @Test
    void test4() {
        String json = "{userName: mailo," +
                " password: Automation@!@123}";
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(json);
        String result = given().contentType(ContentType.JSON)
                .body(jsonObject)
                .post("https://bookstore.toolsqa.com/Account/v1/Authorized").thenReturn()
                .body().asString();
        Assert.assertEquals(result, "true");

    }
}
