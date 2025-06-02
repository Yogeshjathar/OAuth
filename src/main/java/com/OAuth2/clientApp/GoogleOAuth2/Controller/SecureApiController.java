package com.OAuth2.clientApp.GoogleOAuth2.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureApiController {

    @GetMapping("/api/secure")
    public String securedMessage() {
        return "🔒 This is a secure endpoint. You're authenticated!";
    }
}
