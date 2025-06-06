package com.example.project.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

 // DTO для авторизация пользователя
@Data
public class SignInRequest {

    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String username;

    @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;

    public @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов") @NotBlank(message = "Имя пользователя не может быть пустыми") String getUsername() {
        return username;
    }

    public @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов") @NotBlank(message = "Пароль не может быть пустыми") String getPassword() {
        return password;
    }
}
