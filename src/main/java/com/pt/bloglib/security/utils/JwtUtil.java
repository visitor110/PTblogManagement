package com.pt.bloglib.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    public static String SECRET;

    public static Long EXPIRATION;

    public static Long EXPIRATION_REMEMBER;

    public static String TOKEN_HEADER;

    public static String TOKEN_PREFIX;

    public static String TOKEN_TYPE;

    public static String ROLE_CLAIMS;

    @Value("${jwt.secret}")
    public void setSECRET(String SECRET) {
        JwtUtil.SECRET = SECRET;
    }

    @Value("${jwt.expiration}")
    public void setEXPIRATION(Long EXPIRATION) {
        JwtUtil.EXPIRATION = EXPIRATION;
    }

    @Value("${jwt.expiration_remember}")
    public void setExpirationRemember(Long expirationRemember) {
        EXPIRATION_REMEMBER = expirationRemember;
    }

    @Value("${jwt.token_header}")
    public void setTokenHeader(String tokenHeader) {
        TOKEN_HEADER = tokenHeader;
    }

    @Value("${jwt.token_prefix}")
    public void setTokenPrefix(String tokenPrefix) {
        TOKEN_PREFIX = tokenPrefix;
    }

    @Value("${jwt.token_type}")
    public void setTokenType(String tokenType) {
        TOKEN_TYPE = tokenType;
    }

    @Value("${jwt.role_claims}")
    public void setRoleClaims(String roleClaims) {
        ROLE_CLAIMS = roleClaims;
    }

    public static String createToken(String username, List<String> roles, boolean isRememberMe) {

        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;

        String tokenPrefix = Jwts.builder()
                .setHeaderParam("typ", TOKEN_TYPE)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .claim(ROLE_CLAIMS, String.join(",", roles))
                .setIssuer("SnailClimb")
                .setIssuedAt(new Date())
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
        String jwt = TOKEN_PREFIX + tokenPrefix;
        System.out.println(jwt);
        return jwt;
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
//                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
    }

    public static String getUsernameByToken(String token) {
        return parseToken(token).getSubject();
    }

    public static List<SimpleGrantedAuthority> getUserRolesByToken(String token) {
        String role = (String) parseToken(token)
                .get(ROLE_CLAIMS);
        return Arrays.stream(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private static boolean isTokenExpired(String token) {
        Date expiredDate = parseToken(token).getExpiration();
        return expiredDate.before(new Date());
    }
}
