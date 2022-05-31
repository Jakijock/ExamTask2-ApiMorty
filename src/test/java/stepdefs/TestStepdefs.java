package stepdefs;

import configuration.Configuration;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Character;
import org.apache.hc.core5.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TestStepdefs extends Configuration {

    public static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri(getConfigurationFile("rickAndMortyUrl"))
                    .setContentType(ContentType.JSON)
                    .addFilter(new AllureRestAssured())
                    .build();

    @Когда("^найден персонаж Морти Смит$")
    public Character findCharacter() throws IOException {
        Character character =
                given().spec(REQ_SPEC)
                        .param("name", getConfigurationFile("characterName"))
                        .when()
                        .get("/character")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .body().jsonPath().getList("results", Character.class).get(0);
        return character;
    }

    @Тогда("находим последний эпизод с персонажем")
    public int getLastEpisodeOfChatacter() throws IOException {
        int colEpisodeCharacter = (new JSONArray(findCharacter().getEpisode()).length() - 1);
        int numberLastEpisodeWithCharacter = Integer.parseInt(new JSONArray(findCharacter().getEpisode()).get(colEpisodeCharacter).toString().replaceAll("[^0-9]", ""));
        return numberLastEpisodeWithCharacter;
    }

    @И("находим последнего персонажа в этом эпизоде")
    public int getLastCharacterOnEpisode() throws IOException {
        Response lastEpisodeWithCharacterInfo =
                given().spec(REQ_SPEC)
                        .when()
                        .get("/episode/" + getLastEpisodeOfChatacter())
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .response();
        int colCharacterOnEpisode = (new JSONObject(lastEpisodeWithCharacterInfo.getBody().asString()).getJSONArray("characters").length() - 1);
        int lastCharacterOnEpisode = Integer.parseInt(new JSONObject(lastEpisodeWithCharacterInfo.getBody().asString()).getJSONArray("characters").get(colCharacterOnEpisode).toString().replaceAll("[^0-9]", ""));
        return lastCharacterOnEpisode;
    }

    @Тогда("сравниваем местонахождение этого персонажа и Морти Смита")
    public void checkCharactersLocation() throws IOException {
        Response lastPersonOnEpisodeInfo =
                given().spec(REQ_SPEC)
                        .when()
                        .get("/character/" + getLastCharacterOnEpisode())
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .response();
        String location = (new JSONObject(lastPersonOnEpisodeInfo.getBody().asString()).getJSONObject("location").get("name").toString());
        Assert.assertEquals("Разное местонахождение", findCharacter().getLocation().getName(), location);

    }

    @Тогда("сравниваем рассу этого персонажа и Морти Смита")
    public void checkCharactersSpecies() throws IOException {
        Response lastPersonOnEpisodeInfo =
                given().spec(REQ_SPEC)
                        .when()
                        .get("/character/" + getLastCharacterOnEpisode())
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .response();
        String species = (new JSONObject(lastPersonOnEpisodeInfo.getBody().asString()).get("species").toString());
        Assert.assertEquals("Разные рассы", findCharacter().getSpecies(), species);
    }
}
