package com.example.block6pathvariableheaders;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
public class Controlador {
    @PostMapping("saludo")
    public Saludo saludoDeBody(@RequestBody Saludo saludo) {
        return saludo;
    }

    @GetMapping("user/{id}")
    public long userId(@PathVariable long id) {
        return id;
    }

    @PutMapping("post")
    public HashMap<String, String> datosMandados(@RequestParam("var1") String var1, @RequestParam("var2") String var2) {
        HashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("var1", var1);
        hashMap.put("var2", var2);
        return hashMap;
    }
}
