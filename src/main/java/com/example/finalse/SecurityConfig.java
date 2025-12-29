package com.example.finalse;

import com.example.finalse.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http, UserServiceImpl userService) throws Exception {
                http.exceptionHandling(
                                exception -> exception.accessDeniedPage("/forbidden"));

                http.authorizeHttpRequests(
                                authorize -> authorize
                                                .requestMatchers("/sign-in", "/entering", "/sign-up", "/registration")
                                                .anonymous()
                                                .requestMatchers("/sign-out", "/change-password", "/save-password",
                                                                "/profile")
                                                .authenticated()
                                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                                .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
                                                .requestMatchers("/css/**", "/js/**").permitAll()
                                                .requestMatchers("/api/users/login", "/api/users/registration")
                                                .permitAll()
                                                .requestMatchers("/api/**").authenticated()
                                                .anyRequest().permitAll())
                                .formLogin(
                                                login -> login
                                                                .loginProcessingUrl("/entering")
                                                                .defaultSuccessUrl("/profile")
                                                                .loginPage("/sign-in")
                                                                .failureUrl("/sign-in?error")
                                                                .usernameParameter("user_email")
                                                                .passwordParameter("user_password"))
                                .logout(
                                                logout -> logout
                                                                .logoutSuccessUrl("/sign-in?logout")
                                                                .logoutUrl("/sign-out"))
                                .httpBasic(basic -> {
                                })
                                .userDetailsService(userService)
                                .csrf(AbstractHttpConfigurer::disable);

                return http.build();
        }
}
