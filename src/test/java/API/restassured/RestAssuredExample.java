package API.restassured;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PetDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RestAssuredExample {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private RequestSpecification requestSpecification;

    @BeforeClass
    public void setUp() {
       requestSpecification = new RequestSpecBuilder()
                        .setBaseUri(BASE_URL)
                        .addHeader("Content-Type", "application/json")
                        .build();
    }

    @Test
    @SneakyThrows
    public void createPet() {
        PetDto requestPet = PetDto.builder()
                .status("available")
                .name("Barsik")
                .build();

        //request creating pet
        String petId = RestAssured
                //секція підготовки запиту
                .given()
                .spec(requestSpecification)
                //потрібно заюзати джексон, який вміє перетворювати джава обєкти в json і навпаки
                .body(new ObjectMapper().writeValueAsString(requestPet))
                //шлемо сам запит
                .when()
                .post("pet")
                //перевірка респонса
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getString("id");

        //Request getting pet
        JsonPath jsonResponsePet = RestAssured
                .given()
                .spec(requestSpecification)
                //використовуємо якщо джейсон в окремому файлі
                //.body(new File("src/test/resources/testdata.petstore/barsik.json"))
                .when()
                .get("/pet/" + petId)
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .jsonPath();

        PetDto responsePet = new ObjectMapper().readValue(jsonResponsePet.prettify(), PetDto.class);

        Assert.assertEquals(requestPet.getName(), responsePet.getName());
        Assert.assertEquals(requestPet.getStatus(), responsePet.getStatus());
    }
}
