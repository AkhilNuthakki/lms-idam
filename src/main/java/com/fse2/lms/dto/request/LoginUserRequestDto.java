package com.fse2.lms.dto.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder(builderMethodName = "buildLoginUserRequestDtoWith")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginUserRequestDto {

    @NotBlank(message = "User email is required")
    @Email(message = "User email should be valid")
    private String userEmailId;

    @NotBlank(message = "Password is required")
    private String password;
}
