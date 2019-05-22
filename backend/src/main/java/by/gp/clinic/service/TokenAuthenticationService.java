package by.gp.clinic.service;

import by.gp.clinic.dbo.UserDbo;
import by.gp.clinic.dbo.VerificationTokenDbo;
import by.gp.clinic.repository.UserRepository;
import by.gp.clinic.repository.VerificationTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenAuthenticationService {

    public static final long EXPIRATION_TIME = 2_592_000_000L; // Month
    public static final long EXPIRATION_TIME_SMALL = 45_000_000L; // ~12 hours
    private static final String SECRET = "D90#11%fhBpP";
    private static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;

    public void addAuthentication(final HttpServletResponse res, final String name, final String roles) {
        final UserDbo user = userRepository.getByName(name);
        final String JWT = encode(EXPIRATION_TIME, name, user.getId().toString(), roles);
        res.addHeader(HEADER_STRING, JWT);
    }

    public static String encode(final long expirationTime, final String... fields) {
        return TOKEN_PREFIX + " " + Jwts.builder()
            .setSubject(String.join(":", fields))
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public String getUserIdFromAuthorizationToken(final String token) {
        final String[] splitToken = token.split("\\.");

        final String encodedTokenPayload = splitToken[1];
        final String decodedTokenPayload = new String(Base64.getDecoder().decode(encodedTokenPayload));
        return decodedTokenPayload.split(":")[2];
    }

    public Authentication getAuthentication(final HttpServletRequest request) {
        final String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            if (isTokenExpired(token)) {
                throw new SignatureException("This token can't be trusted as it was marked as logged out");
            }
            // parse the token.
            final String subject = decode(token);
            final String user = subject.split(":")[0];
            final List<String> roles = Arrays.asList(subject.substring(subject.lastIndexOf(':') + 1).split(","));

            return user != null
                   ? new UsernamePasswordAuthenticationToken(user, null, roles.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
                   : null;
        }
        return null;
    }

    public static String decode(final String token) {
        return Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
            .getBody()
            .getSubject();
    }

    public static Date getExpiration(final String token) {
        try {
            return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getExpiration();
        } catch (final Exception ignore) {
        }
        return null;
    }

    public void invalidateToken(final String token) {
        if (!isTokenExpired(token)) {
            final VerificationTokenDbo verificationTokenDbo = new VerificationTokenDbo();
            verificationTokenDbo.setToken(token);
            verificationTokenDbo.setExpiryDate(LocalDate.now().plusMonths(1L));
            tokenRepository.save(verificationTokenDbo);
        }
    }

    private boolean isTokenExpired(final String token) {
        return tokenRepository.existsByToken(token);
    }
}
