package ru.skypro.homework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/",
            "/login", "/register", "/ads/**"
    };

    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) ->
                        authz
                                .mvcMatchers(AUTH_WHITELIST).permitAll()
                                .mvcMatchers("/ads/**", "/users/**").hasAnyRole("ADMIN", "USER")
                )
                .cors(withDefaults())
                .httpBasic(withDefaults());

        return http.build();
    }
}