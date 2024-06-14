package by.gp.clinic.service;

import by.gp.clinic.dbo.VerificationTokenDbo;
import by.gp.clinic.repository.UserRepository;
import by.gp.clinic.repository.VerificationTokenRepository;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenAuthenticationService {

    private static final long EXPIRATION_TIME = 2_592_000_000L; // Month

    private static final String SECRET = "D90#11%fhBpP";

    private static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    private final UserRepository userRepository;

    private final VerificationTokenRepository tokenRepository;

    private final Cache<String, VerificationTokenDbo> cache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(120, TimeUnit.MINUTES)
            .build();

    @Transactional
    public void addAuthentication(final HttpServletResponse response, final String alias, final String roles) {
        final var userID = userRepository.getIdByAlias(alias);
        final var JWT = encode(alias, userID.toString(), roles);

        updateToken(JWT);
        response.addHeader(HEADER_STRING, JWT);
    }

    public Authentication getAuthentication(final HttpServletRequest request) {
        final var token = request.getHeader(HEADER_STRING);
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        if (getVerificationTokenDbo(token) == null) {
            throw new io.jsonwebtoken.security.SignatureException(
                    "This token can't be trusted as it was marked as logged out");
        }
        // parse the token.
        final String subject;
        try {
            subject = decode(token);
        } catch (MalformedJwtException e) {
            throw new io.jsonwebtoken.security.SignatureException("Wrong token format");
        }
        final var user = subject.split(":")[0];
        final var roles = Arrays.asList(subject.substring(subject.lastIndexOf(':') + 1).split(","));

        return user != null
                ? new UsernamePasswordAuthenticationToken(user, null, roles.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
                : null;
    }

    @Transactional
    public void logout(final String token) {
        tokenRepository.deleteByToken(token);
        cache.invalidate(token);
    }

    private void updateToken(final String JWT) {
        final var verificationToken = tokenRepository.findByToken(JWT).orElseGet(() -> {
            final var verificationTokenDbo = new VerificationTokenDbo();
            verificationTokenDbo.setToken(JWT);
            return verificationTokenDbo;
        });
        verificationToken.setExpiryDate(LocalDate.now().plusMonths(1));
        tokenRepository.save(verificationToken);
        cache.put(JWT, verificationToken);
    }

    private static String encode(final String... fields) {
        return TOKEN_PREFIX + " " + Jwts.builder()
                .subject(String.join(":", fields))
                .expiration(new Date(System.currentTimeMillis() + TokenAuthenticationService.EXPIRATION_TIME))
                .signWith(KEY)
                .compact();
    }

    private static String decode(final String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token.replace(TOKEN_PREFIX, ""))
                .getPayload()
                .getSubject();
    }

    private VerificationTokenDbo getVerificationTokenDbo(final String token) {
        return getFromCache(token).orElseGet(() -> {
            final var byToken = tokenRepository.findByToken(token);
            if (byToken.isPresent()) {
                cache.put(token, byToken.get());
                return byToken.get();
            }
            return null;
        });
    }

    private Optional<VerificationTokenDbo> getFromCache(final String token) {
        return Optional.ofNullable(cache.getIfPresent(token));
    }
}
