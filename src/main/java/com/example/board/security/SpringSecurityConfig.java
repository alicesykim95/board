package com.example.board.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                // 페이지 권한 설정
                .authorizeRequests()
                // 권한 없이 누구나 접근 가능한 페이지 설정
                .antMatchers("/board/bHome", "/board/bLogin", "/board/bJoin").permitAll()

                // 로그인 설정
                .and()
                .formLogin()
                .loginPage("/board/bLogin")
                // 로그인 성공 시 이동할 URL
                .defaultSuccessUrl("/board/bList", true)
                .permitAll()

                // 로그아웃 설정
                .and()
                .logout()
                // 로그아웃의 기본 URL /logout이 아닌 다른 URL로 재정의한다.
                .logoutRequestMatcher(new AntPathRequestMatcher("/board/bLogout"))
                // 로그아웃 성공 시 이동할 URL
                .logoutSuccessUrl("/board/bHome")
                // 세션을 초기화하는 작업
                .invalidateHttpSession(true);
    }


    // css나 이미지 파일 등의 경우 인증이 되지 않은 상태에서도 보여져야 한다.
    // 때문에 이 경우 별도로 WebSecurity 하나를 인자로 갖는 configure를 오버라이딩해서 예외 처리할 수 있다.
    // 예외 처리를 하지 않으면 인증을 진행해야 하기 때문에 화면에서 깨진다.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/css/**","/templetes/board/**");
    }


}
