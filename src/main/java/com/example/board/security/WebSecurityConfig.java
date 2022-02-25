package com.example.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 해당 메소드 아래는 각 경로에 따른 권한을 지정할 수 있다.
                .authorizeRequests()
                // 로그인 권한은 누구나, resources 파일도 모든 권한
                .antMatchers("/", "/bHome").permitAll()
                // 로그인된 사용자가 요청을 수행할 때 필요하다
                // 만약 사용자가 인증되지 않았다면, spring security 필터는 요청을 잡아내고 사용자를 로그인 페이지로 redirect 해준다.
                .anyRequest().authenticated()
                .and()
                // 로그인 폼으로 로그인 성공시 이동경로 설정 가능하다.
                // 로그인 폼의 아이디, 패스워드는 username, password로 맞춰야한다.
                .formLogin()
                // 로그인이 수행될 경로
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
