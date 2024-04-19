package com.example.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfigure {
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   PersistentTokenRepository persistentTokenRepository) throws Exception {
            return http
                    .authorizeHttpRequests(conf->{
                        conf.requestMatchers("/static/**").permitAll();
                        conf.anyRequest().authenticated();
                    })
                    .formLogin(conf->{
                        conf.loginPage("/login");
                        conf.loginProcessingUrl("/dologin");
                        conf.defaultSuccessUrl("/index");
                        conf.usernameParameter("username");
                        conf.passwordParameter("password");
                        conf.permitAll();
                    })
                    .logout(cof->{
                        cof.logoutUrl("/logout");
                        cof.logoutSuccessUrl("/login");
                        cof.permitAll();
                    })
                    .csrf(AbstractHttpConfigurer::disable)
                    .rememberMe(conf->{
                        conf.rememberMeParameter("remember-me");
                        conf.tokenRepository(persistentTokenRepository);
                        conf.tokenValiditySeconds(3600*7);
                    })
                    .build();
    }
}
