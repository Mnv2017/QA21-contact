package com.telran.contact.api.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.telran.contact.api.dto.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpClientTests {

    @Test
    public void loginHttpClientTest() throws IOException {
        String email = "mmm@mail.ru";
        String password = "Mm$123456";

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString("{\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute();

        String responseJson = response.returnContent().asString();
        System.out.println("*** " + responseJson);
        JsonElement element = JsonParser.parseString(responseJson);
        JsonElement token = element.getAsJsonObject().get("token");
        System.out.println("##### " + token.getAsString());
    }

    @Test
    public void loginHttpClientTestWithDto() throws IOException {
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("mmm@mail.ru")
                .password("Mm$123456")
                .build();
        Gson gson = new Gson();
        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString(gson.toJson(requestDto), ContentType.APPLICATION_JSON)
                .execute();
        String responseJson = response.returnContent().asString();
        AuthResponseDto responseDto = gson.fromJson(responseJson, AuthResponseDto.class);
        System.out.println("******* Вход выполнен! token: " + responseDto.getToken());
    }

    @Test
    public void loginHttpClientNegativeTestWithDto() throws IOException {

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("mmm@mail.ru")
                .password("Mm123456")// в пароле нет спецсимвола
                .build();
        Gson gson = new Gson();
        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString(gson.toJson(requestDto), ContentType.APPLICATION_JSON)
                .execute();
        HttpResponse httpResponse = response.returnResponse();
        System.out.println(httpResponse);
        System.out.println(httpResponse.getStatusLine().getStatusCode());

        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        AuthErrorDto errorDto = gson.fromJson(stringBuilder.toString(), AuthErrorDto.class);
        System.out.println(errorDto.getCode());
        System.out.println(errorDto.getMessage());
        System.out.println(errorDto.getDetails());
    }
}
