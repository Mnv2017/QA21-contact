package com.telran.contact.api.tests;

import com.google.gson.Gson;
import com.telran.contact.api.dto.ContactErrorDto;
import com.telran.contact.api.dto.ContactRequestDto;
import com.telran.contact.api.dto.ContactResponseDto;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpClientContactTests {

    @Test
    public void addContactPositiveTestWithDto() throws IOException {
        ContactRequestDto contactRequestDto = ContactRequestDto.builder()
                .address("Dortmund")
                .description("description1")
                .email("email987@gmx.de")
                .id(0)
                .lastName("Detochkin")
                .name("Yurij")
                .phone("9911882277")
                .build();

        Gson gson = new Gson();
        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/contact")
                .bodyString(gson.toJson(contactRequestDto), ContentType.APPLICATION_JSON)
//                .setHeader("Authorization", HttpClientTests.token)
                .addHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1tbUBtYWlsLnJ1In0.QIqAfln3hQCb2whO1P-YJFyUaFr1vV_scidKYDTlGSo")
                .execute();

        HttpResponse httpResponse = response.returnResponse();

        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            ContactResponseDto responseDto = gson.fromJson(stringBuilder.toString(), ContactResponseDto.class);
            System.out.println("++++++++ Контакт создан! ID = " + responseDto.getId());
            System.out.println("==============================================");
        } else {
            ContactErrorDto errorDto = gson.fromJson(stringBuilder.toString(), ContactErrorDto.class);
            System.out.println("++++++++  Ошибка!");
            System.out.println(+errorDto.getCode());
            System.out.println(errorDto.getMessage());
            System.out.println("==============================================");
        }
    }

    @Test
    public void addContactNegativeTestWithDto() throws IOException {
        ContactRequestDto contactRequestDto = ContactRequestDto.builder()
                .address("Dortmund")
                .description("description1")
                .email("email987@gmx.de")
                .id(0)
                .lastName("Detochkin")
                .name("Yurij")
//                .phone("9911882277") // не укажем телефон
                .build();

        Gson gson = new Gson();
        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/contact")
                .bodyString(gson.toJson(contactRequestDto), ContentType.APPLICATION_JSON)
                .setHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1tbUBtYWlsLnJ1In0.QIqAfln3hQCb2whO1P-YJFyUaFr1vV_scidKYDTlGSo")
                .execute();
        HttpResponse httpResponse = response.returnResponse();

        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        ContactErrorDto errorDto = gson.fromJson(stringBuilder.toString(), ContactErrorDto.class);
        System.out.println("-------  Ошибка!");
        System.out.println(+errorDto.getCode());
        System.out.println(errorDto.getMessage());
        System.out.println("==============================================");
    }

}
