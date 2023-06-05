package com.example.springboot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class RandomController {
    @GetMapping("/random")
    public ResponseEntity<String> random() {
        boolean random = new Random().nextBoolean();
        if (random) {
            return ResponseEntity.ok().body("200 OK");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 Bad Request");
        }

    }
}
