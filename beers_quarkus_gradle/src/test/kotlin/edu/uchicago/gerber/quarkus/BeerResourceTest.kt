package edu.uchicago.gerber.quarkus

import edu.uchicago.gerber.quarkus._04models.Faked
//import com.alibaba.fastjson.JSON
//import com.alibaba.fastjson.JSONObject
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Test

@QuarkusTest
class BeerResourceTest {

   //https://quarkus.io/guides/getting-started-testing

    //CREATE
    @Test
    fun  testCreateBeer() {
        val beer = Faked.genRawEntity()
        given().contentType("application/json").body(beer)
            .`when`().post("/beers")
            .then()
            .statusCode(200)
            .body("id", notNullValue())
    }
    //READ

    @Test
    fun testGetBeer() {

        given()
            .`when`().get("/beers/{id}", Faked.FAKE_ID)
        .then()
            .statusCode(200)
            .body("id", `is`(Faked.FAKE_ID));

    }
    @Test
    fun testGetBeers() {
        given()
            .`when`().get("/beers")
        .then()
            .statusCode(200)

    }

    //UPDATE


    //DELETE
    @Test
    fun testDeleteBeer() {
        given()
            .`when`().delete("/beers/{id}", Faked.FAKE_ID)
            .then()
            .statusCode(200)
            .body("id", `is`(Faked.FAKE_ID))
    }











}
