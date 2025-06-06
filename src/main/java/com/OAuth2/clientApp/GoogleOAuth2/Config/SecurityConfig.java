package com.OAuth2.clientApp.GoogleOAuth2.Config;

import com.OAuth2.clientApp.GoogleOAuth2.Service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(); // Enables OAuth2 login
//
//        return http.build();

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/dashboard","/api/**").authenticated()
                        .anyRequest().permitAll()
                )
//                .csrf(csrf -> csrf.disable())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true) // Redirect to dashboard after login
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // Plug in DB logic
                        )
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt() // Enable JWT token validation for resource server
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login").permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/access-denied") // 🔥 custom access denied page
                );

        return http.build();
    }
}
