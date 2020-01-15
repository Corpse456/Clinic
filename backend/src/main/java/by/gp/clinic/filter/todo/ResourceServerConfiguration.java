//package by.gp.clinic.filter.todo;
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
//@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
//@AllArgsConstructor
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//    private static final String RESOURCE_ID = "resource_id";
//    @Override
//    public void configure(final ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(RESOURCE_ID).stateless(false); // Set resource for request is token valid
//    }
//
//    @Override
//    public void configure(final HttpSecurity http) throws Exception {
//        http
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
//            .and()
//            .authorizeRequests()
//            .antMatchers("/auth/**", "/v2/**")
//            .permitAll() // Set urls for as not required authentication
//            .anyRequest().authenticated(); // Set other request as required authentication
//    }
//}
