package com.telran.contact.api.tests;

import com.google.gson.Gson;
import com.telran.contact.api.dto.AuthErrorDto;
import com.telran.contact.api.dto.AuthRequestDto;
import com.telran.contact.api.dto.AuthResponseDto;
import okhttp3.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHttpTests {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Test
    public void loginOkHttpTest() throws IOException {
        Gson gson = new Gson();
        //создаем экземпляр Http-клиента
        OkHttpClient client = new OkHttpClient();
        // инициализируем объект Dto валидными данными
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("mmm@mail.ru")
                .password("Mm$123456")//пароль без спецсимвола
                .build();
        // формируем тело запроса методом create, преобразуя объект Dto в формат JSON + указав ContentType JSON
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto), JSON); // сериализация в Json
        // создаем HTTP-запрос и инициализируем его значениями URL + тип (post) + body
        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody).build();

        // клиент отправляет подготовленный запрос - получаем ответ
        Response response = client.newCall(request).execute();
        // берем body ответа и преобразуем в String
        String responseJson = response.body().string();

        if (response.isSuccessful()) {
            // если код ответа 200..300 - значит, вернулся token
            AuthResponseDto responseDto = gson.fromJson(responseJson, AuthResponseDto.class); // десериализация Json в responseDto
            // выводим значение token
            System.out.println(responseDto.getToken());
        } else {
            // если вернулся код ошибки
            AuthErrorDto errorDto = gson.fromJson(responseJson, AuthErrorDto.class); // десериализация Json в errorDto
            // выводим код и сообщение об ошибке
            System.out.println(errorDto.getCode());
            System.out.println(errorDto.getMessage());

        }

    }
}
