package com.telran.contact.api.tests;

import com.jayway.restassured.RestAssured;
import com.telran.contact.api.dto.AuthRequestDto;
import com.telran.contact.api.dto.AuthResponseDto;
import com.telran.contact.api.dto.ContactDto;
import com.telran.contact.api.dto.GetAllContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTests {
    @BeforeMethod
    public void ensurePreconditions() {
        RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
        RestAssured.basePath = "api";
    }

    @Test
    public void loginRestAssuredTest() {
        // создаем экземпляр запроса на авторизацию, инициализируя поля с помощью builder()
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("mmm@mail.ru")
                .password("Mm$123456")
                .build();
        // создаем экземпляр ответа --"--"--
        AuthResponseDto responseDto = given()
                .contentType("application/Json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);
        System.out.println(responseDto.getToken());

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1tbUBtYWlsLnJ1In0.QIqAfln3hQCb2whO1P-YJFyUaFr1vV_scidKYDTlGSo";
        AuthResponseDto responseTokenDto = given()
                .contentType("application/Json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .body("token", equalTo(token))
                .body(containsString("token"))
//                .extract().path("token");
                .extract().response().as(AuthResponseDto.class);
        System.out.println(responseDto.getToken());

    }

    @Test
    public void getAllContactDto() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1tbUBtYWlsLnJ1In0.QIqAfln3hQCb2whO1P-YJFyUaFr1vV_scidKYDTlGSo";

        GetAllContactDto responseDto = given()
                .header("Authorization", token)
                .get("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(GetAllContactDto.class);

        for (ContactDto contact : responseDto.getContacts()
        ) {
            System.out.println(contact.getId() + ",  " + contact.getName());
            System.out.println("**************************");
        }

/*
        String responseDto = given()
                .header("Authorization", token)
                .get("contact")
                .then()
                .assertThat().statusCode(500)
                .extract().path("message");
        System.out.println(responseDto);
*/
    }

    @Test
    public void deleteContactTest() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1tbUBtYWlsLnJ1In0.QIqAfln3hQCb2whO1P-YJFyUaFr1vV_scidKYDTlGSo";
        String status = given()
                .header("Authorization", token)
                .delete("contact/18194")
                .then()
                .assertThat().statusCode(200)
                .extract().path("status");
        System.out.println(status);

    }
}
