package org.example.mockproject_052024_group1.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Utility class for JWT token generation and validation.
 *
 * <p>
 * This service class provides methods for generating JWT access tokens based on user information
 * and validating these tokens.
 * </p>
 *
 * @author Chien Ma
 * @version 1.0
 */
@Service
public class JwtUtils {

    @Value("${security.jwt.token.secret-key}")
    private String SECRET_KEY;

    @Value("${security.jwt.token.accessTokenValidityMs}")
    private long accessTokenValidityMs;

    private SecretKey getHmacShaSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generates a JWT access token for the specified user.
     *
     * @param userDetails The user for whom the access token is generated.
     * @return The generated JWT access token.
     */
    public String generateAccessToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessTokenValidityMs);

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getHmacShaSigningKey())
                .compact();
    }

    /**
     * Validates the specified JWT access token.
     *
     * @param token       The JWT access token to validate.
     * @param userDetails The user against whom the token is validated.
     * @return True if the token is valid for the user, false otherwise.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Extracts the username from the specified JWT access token.
     *
     * @param token The JWT access token from which to extract the username.
     * @return The username extracted from the token.
     */
    public String extractUsername(String token) {
        return Jwts.parser().verifyWith(getHmacShaSigningKey()).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    /**
     * Checks if the specified JWT access token is expired.
     *
     * @param token The JWT access token to check for expiration.
     * @return True if the token is expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        Date expiryDate = Jwts.parser().verifyWith(getHmacShaSigningKey()).build()
                .parseSignedClaims(token).getPayload().getExpiration();
        return expiryDate.before(new Date());
    }
}

