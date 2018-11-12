package beans.springmvc.configuration;


import beans.security.CustomAccessDeniedHandler;
import beans.security.CustomAuthenticationFailureHandler;
import beans.security.CustomLogoutSuccessHandler;
import beans.services.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;



@Configuration
@EnableWebSecurity
@ComponentScan("beans")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user1").password("pass1").roles("REGISTERED_USER")
            .and()
            .withUser("user2").password("pass2").roles("REGISTERED_USER")
            .and()
            .withUser("admin").password("admin123").roles("BOOKING_MANAGER");
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/admin/**").hasRole("BOOKING_MANAGER")
            .antMatchers("/index").hasAnyRole("REGISTERED_USER", "BOOKING_MANAGER")
            .antMatchers("/anonymous*").anonymous()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/user_login")
                .defaultSuccessUrl("/index", true)
                .failureHandler(authenticationFailureHandler())
            .and()
                .logout()
                .logoutUrl("/user_logout")
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(logoutSuccessHandler());
    }


    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public MyUserDetailsService myUserDetailsService() {
        return new MyUserDetailsService();
    }

}
