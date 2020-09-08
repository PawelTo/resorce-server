package pl.pawel.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Profile("resource-server")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.addFilterAfter()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //.requestMatchers(unsecuredRequestMatcher()).permitAll()
                //.anyRequest().permitAll()
                .antMatchers("/**").authenticated()
                //.anyRequest().denyAll()
                .and()
                /*Configuration for opaqueToken
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::opaqueToken)*/
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        ;
    }
}
