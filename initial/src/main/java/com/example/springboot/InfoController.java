package com.example.springboot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @GetMapping("/info")
    public ResponseEntity<String> info() {
        String message = "Info coming soon";
        return ResponseEntity.ok().body(message);
    }
}
