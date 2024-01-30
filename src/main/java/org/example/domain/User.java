package org.example.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    @Builder.Default private static Integer sequence = 0;

    {
        sequence++;
    }

    private String name;
    private String password;
    private String email;
    private Integer age;
    @Builder.Default private Integer id = sequence;
}

