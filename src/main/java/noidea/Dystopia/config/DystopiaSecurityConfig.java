package noidea.Dystopia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DystopiaSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        String encodedPassword = passwordEncoder().encode("password"); // Шифруем пароль
        manager.createUser(User.withDefaultPasswordEncoder().username("Томат").password("кетчуп").roles("USER").build());
//        manager.createUser(User.withUsername("star@google.bim").password(encodedPassword).roles("USER").build());
        return manager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(requests -> requests


                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/**").hasRole("USER")
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/api/registration").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())


                .formLogin((form) -> form.loginPage("/login-form").permitAll())
                .logout((logout) -> logout.logoutUrl("/exit"))
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }



//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder(10);
//    }
}
