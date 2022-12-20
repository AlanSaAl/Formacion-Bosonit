package com.example.block11uploaddownloadfiles.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Employee {
    @Id
    @GeneratedValue
    private  int id;

    private String name;

    private MultipartFile document;
}
