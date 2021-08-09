package com.cos.jwt.config;

import com.cos.jwt.filter.MyFilter1;
import com.cos.jwt.filter.MyFilter3;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //내가 만든 필터가 security보다 빠르게 동작하려면 security에 등록을 해야한다.
        http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class); //BasicAuthenticationFilter 동작되기전 filter가 걸림.
        //csrf 막아줌
        http.csrf().disable();

        // Session을 사용하지 않겠다는 의미.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter) //내 server는 cors 정책에서 벗어날 수 있다. @CrossOrigin(인증X), 시큐리티 필터에 등록 인증(O)
                .formLogin().disable()  //JWT server이기에 formlogin을 하지 않겠다는의미
                .httpBasic().disable()
                .authorizeRequests()

                .antMatchers("/api/v1/user/**")
                // user쪽으로는 USER뿐 아니라 MANAGER, ADMIN 도 접속허용을 한다.
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")

                .antMatchers("/api/v1/manager/**")
                // manager쪽으로는 MANAGER, ADMIN 만 접속허용을 한다.
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")

                .antMatchers("/api/v1/admin/**")
                // admin쪽으로는 ADMIN 만 접속허용을 한다.
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest(); // <- 위의 요청 외에 다른 요청은 권한없이 들어갈 수 있다.




    }
}
