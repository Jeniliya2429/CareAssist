package com.evaluation.HireTrack.config;


import com.evaluation.HireTrack.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final UserService userService;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Spring needs this for POST,PUT & DELETE
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.POST,"/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/auth/login").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/auth//user-details").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/jobs").hasAuthority("EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/api/applications").hasAuthority("SEEKER")
                        .requestMatchers(HttpMethod.GET, "/api/my-applications").hasAuthority("SEEKER")
                        .requestMatchers(HttpMethod.GET, "/api/jobs").hasAnyAuthority("SEEKER", "EMPLOYER")
                        .anyRequest().authenticated()

                );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.httpBasic(Customizer.withDefaults()); //i am telling Spring that i am using Basic Auth technique
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider(userService);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
