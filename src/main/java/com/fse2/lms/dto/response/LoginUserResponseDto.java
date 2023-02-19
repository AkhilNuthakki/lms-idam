package com.fse2.lms.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Date;

@Builder(builderMethodName = "buildLoginUserResponseDtoWith")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginUserResponseDto {

    private String userEmailId;

    private String userName;

    private String userRole;

    private String token;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date tokenExpirationDate;


}
