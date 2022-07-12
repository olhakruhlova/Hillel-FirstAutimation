package API.restassured;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PetDto;
import dto.PetOrderDto;
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
    private Long petId;

    @Test
    @SneakyThrows
    public void createPet() {
        PetDto requestPet = PetDto.builder()
                .status("available")
                .name("Barsik")
                .build();

        //request creating pet
       petId = RestAssured
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
                .getLong("id");

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

    @Test(dependsOnMethods = "createPet")
    @SneakyThrows
    public void createOrderForPetPurchase() {
        PetOrderDto requestOrderForPetStore = PetOrderDto.builder()
                .id(1)
                .petId(petId)
                .quantity(1)
                .shipDate("2022-07-09T15:36:13.584Z")
                .status("placed")
                .complete(true)
                .build();

        Long orderID = RestAssured
                .given()
                .spec(requestSpecification)
                .body(new ObjectMapper().writeValueAsString(requestOrderForPetStore))
                .when()
                .post("/store/order" )
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getLong("id");

        JsonPath jsonResponseOrderPet = RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .get("/store/order/" + orderID)
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .jsonPath();

        PetOrderDto responsOrderForPetStore = new ObjectMapper().readValue(jsonResponseOrderPet.prettify(), PetOrderDto.class);

        Assert.assertEquals(requestOrderForPetStore.getQuantity(), responsOrderForPetStore.getQuantity());
        Assert.assertEquals(requestOrderForPetStore.getStatus(), responsOrderForPetStore.getStatus());
        Assert.assertEquals(requestOrderForPetStore.getPetId(), responsOrderForPetStore.getPetId());


    }
}
