/*
package by.gp.clinic.filter.todo;

import com.sumtravel.api.auth.service.security.AuthSimpleUserDetailsService;
import com.sumtravel.user.auth.service.converter.CustomAccessTokenConverter;
import com.sumtravel.user.auth.service.converter.CustomTokenEnhancer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Qualifier("authenticationManagerBean")
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final CustomAccessTokenConverter customAccessTokenConverter;
    @Value("${security.signing-key}")
    private String signingKey;
    private final AuthSimpleUserDetailsService authSimpleUserDetailsService;

    // Configure token converter
    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        final JwtAccessTokenConverter converter = new CustomTokenEnhancer();
        converter.setSigningKey(signingKey);
        converter.setAccessTokenConverter(customAccessTokenConverter); // Custom converter for get token additional info
        return converter;
    }

    // Configure token store
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    // Configure default token service for create token manually for user, authenticated via social
    @Bean
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setAuthenticationManager(authenticationManager);
        defaultTokenServices.setTokenEnhancer(tokenEnhancer());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager) // Set auth manager for auth endpoint
            .tokenStore(tokenStore())  // Set token store for auth endpoint
            .accessTokenConverter(tokenEnhancer())  // Set token converter for auth endpoint
            .userDetailsService(authSimpleUserDetailsService); // Set user details service for auth endpoint
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()"); // Set permission for get and check token
    }

    // Config for token client
    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // Set type inMemory
            .withClient("client") // Set client id
            .authorizedGrantTypes("password", "authorization_code", "refresh_token",
                                  "implicit") // Set grant types (e.g. password type need user creds for generate token)
            .autoApprove(true) // Disable manual approve
            .scopes("read", "write") // Scopes of tasks, user can do in system via token
            .secret(passwordEncoder.encode("password")); // Set client secret, hashed via password encoder
    }
}*/
