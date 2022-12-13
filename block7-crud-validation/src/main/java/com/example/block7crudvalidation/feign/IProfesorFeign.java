package com.example.block7crudvalidation.feign;

import com.example.block7crudvalidation.controller.dto.ProfesotOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8080", name = "profesorFeign")
public interface IProfesorFeign {
    @GetMapping("profesor/{id}")
    ProfesotOutputDto getProfesorById(@PathVariable String id, @RequestParam(value = "outputType", defaultValue = "simple") String outputType);
}
