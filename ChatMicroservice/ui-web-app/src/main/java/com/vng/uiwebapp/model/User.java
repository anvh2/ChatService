package com.vng.uiwebapp.model;

import com.google.errorprone.annotations.FormatString;
import com.vng.apigateway.WebClientServiceOuterClass;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private String fullname;
    private String password;
    private String confirm;
    private String email;
    private String gender;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String birthday;
}
