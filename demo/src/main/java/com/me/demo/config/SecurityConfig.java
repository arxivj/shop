package com.me.demo.config;

import com.me.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 로그인 페이지 URL을 설정
                .loginPage("/members/login")
                // 로그인 성공시 이동할 URL을 설정
                .defaultSuccessUrl("/")
                // 로그인 시 사용할 파라미터 이름으로 email을 지정
                .usernameParameter("email")
                // 로그인 실패 시 이동할 URL을 설정
                .failureUrl("/members/login/error")
                .and()
                .logout()
                // 로그아웃 URL을 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                // 로그아웃 성공 시 이동할 URL을 설정
                .logoutSuccessUrl("/");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

}
