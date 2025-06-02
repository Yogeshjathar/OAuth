package com.OAuth2.clientApp.GoogleOAuth2.Service;

import com.OAuth2.clientApp.GoogleOAuth2.Entity.User;
import com.OAuth2.clientApp.GoogleOAuth2.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Extract details
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        String provider = userRequest.getClientRegistration().getRegistrationId(); // "google"

        // Check in DB
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    // Create new user if not exist
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setName(name);
                    newUser.setProvider(provider);
                    return userRepository.save(newUser);
                });

        return oAuth2User;
    }
}
