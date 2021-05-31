package test;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Category;
import pojos.PetPojo;
import pojos.Tag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class petApiValidation {

    PetPojo petPojo = new PetPojo();
    Integer id=123423;

    //Changes made for the branch
    //Created pet using petPojo
    @Test
    public void createPet(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        petPojo.setId(id);
        petPojo.setName("Max");
        petPojo.setStatus("available");

        Response response = given().header("Accept","application/json")
                .header("Content-Type","application/json")
                .body(petPojo)
                .when()
                .post("pet")
                .then()
                .statusCode(200).extract().response();

        //Validating the Id is equal the Id which I sent
        PetPojo parsedResponse = response.as(PetPojo.class);
        Assert.assertEquals(id,parsedResponse.getId());

    }

    @Test
    public void uploadImage(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        File petImage = new File("src/test/resources/goldie.jpeg");

        given()
                .multiPart("file",petImage,"multipart/form-data")
                .header("Accept","application/json")
                .when()
                .post("pet/"+id+"/uploadImage")
                .then()
                .statusCode(200).extract().response();

    }

    @Test
    public void getPet(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        Response response =  given()
                            .accept("application/json")
                            .get("pet/"+id)
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();

        PetPojo parsedResponse = response.as(PetPojo.class);
        //Validating the response
        Assert.assertEquals(id,parsedResponse.getId());
        Assert.assertEquals("Max",parsedResponse.getName());
        Assert.assertEquals("available",parsedResponse.getStatus());

    }



}
