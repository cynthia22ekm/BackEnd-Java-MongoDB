package com.example.demo.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

//References
//https://www.youtube.com/watch?v=x980d_aFJ2s(Auto generate id in MOngoDB using spring boot)

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="User")
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String phone;
}
