package com.OAuth2.clientApp.GoogleOAuth2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;


/*    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal OAuth2User principal) {
        model.addAttribute("name", principal.getAttribute("name"));
        model.addAttribute("email", principal.getAttribute("email"));
        model.addAttribute("picture", principal.getAttribute("picture")); // Google provides profile picture
        return "dashboard";
    }*/

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal OAuth2User principal,
                            OAuth2AuthenticationToken authenticationToken) {
        model.addAttribute("name", principal.getAttribute("name"));
        model.addAttribute("email", principal.getAttribute("email"));
        model.addAttribute("picture", principal.getAttribute("picture"));

        // Show access token (not production safe, just for debug/learning)
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(authenticationToken.getAuthorizedClientRegistrationId(),
                        authenticationToken.getName());
        model.addAttribute("accessToken", client.getAccessToken().getTokenValue());

        return "dashboard";
    }

}
