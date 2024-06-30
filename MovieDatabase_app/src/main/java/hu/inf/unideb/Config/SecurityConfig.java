package hu.inf.unideb.Config;

import hu.inf.unideb.Entity.MyUser;
import hu.inf.unideb.Repository.UserRepository;
import hu.inf.unideb.Service.Impl.MyUserDetailService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, MvcRequestMatcher.Builder mvc) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers(mvc.pattern("/moviePage"), mvc.pattern("/seriesPage"), mvc.pattern("/register/**")).permitAll();
                    registry.requestMatchers(mvc.pattern("/newMovie"),  mvc.pattern("/newSeries"), mvc.pattern("/users")).hasRole("ADMIN");
                    registry.requestMatchers(mvc.pattern("/plannedPage"), mvc.pattern("/plannedSeriesPage"), mvc.pattern("/completedPage"), mvc.pattern("/completedSeriesPage"),
                    mvc.pattern("/watchedSeriesPage")).hasAnyRole("USER", "ADMIN");
                    registry.anyRequest().authenticated();
                })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/login")
                            .successHandler(new AuthenticationSuccessHandler())
                            .permitAll();
                })
                .build();
    }

    @PostConstruct
    public void createDefaultAdminUser() {
        MyUser adminUser = new MyUser();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("password"));
        adminUser.setRole("ADMIN");

        if (!userRepository.findByUsername("admin").isPresent()) {
            userRepository.save(adminUser);
        }
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

