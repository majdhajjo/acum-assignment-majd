package org.example.domain;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentRequest {
    private String name;
    private String email;
    private String address;
    private String phone;
    private String major;
}
