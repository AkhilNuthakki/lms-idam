package com.fse2.lms.dto.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder(builderMethodName = "buildUserRequestDtoWith")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequestDto {

    @NotBlank(message = "User email is required")
    @Email(message = "User email should be valid")
    private String userEmailId;

    @NotBlank(message = "User name is required")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should be minimum of 8 characters")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Password should be alphanumeric")
    private String password;
}
