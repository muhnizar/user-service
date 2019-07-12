package id.co.homecredit.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource-server-rest-api";
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String SECURED_PATTERN = "/api/**";
    private static final String VALIDATED_TOKEN= "/oauth/token";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
                http.csrf().disable()
//                .authorizeRequests().antMatchers("/**").permitAll();
                .authorizeRequests().antMatchers("/hello","/login","/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
                .antMatchers(HttpMethod.GET, SECURED_PATTERN).access(SECURED_READ_SCOPE)
                .antMatchers(HttpMethod.GET, VALIDATED_TOKEN).access(SECURED_READ_SCOPE);
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
//                .anyRequest().access(SECURED_READ_SCOPE);
                http.headers().frameOptions().disable();
//                http.addFilterAfter( new TransactionFilter(), BasicAuthenticationFilter.class);

    }
}