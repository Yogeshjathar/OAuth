package com.OAuth2.clientApp.GoogleOAuth2.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/profile")
    public Map<String, Object> getProfile(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }
}

