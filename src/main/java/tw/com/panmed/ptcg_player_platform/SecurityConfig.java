package tw.com.panmed.ptcg_player_platform;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableAutoConfiguration(
    exclude = {LiquibaseAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
        .oauth2Login(Customizer.withDefaults()).csrf(csrf -> csrf.disable());
    http.headers(
        headers -> headers.httpStrictTransportSecurity(hstsCustomizer -> hstsCustomizer.disable()));

    return http.build();
  }

}
