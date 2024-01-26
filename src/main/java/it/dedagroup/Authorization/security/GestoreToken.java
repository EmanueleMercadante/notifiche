package it.dedagroup.Authorization.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.dedagroup.Authorization.enums.Ruolo;
import it.dedagroup.Authorization.model.Utente;
import it.dedagroup.Authorization.repository.UtenteRepository;
@Service
public class GestoreToken {

    @Autowired
    UtenteRepository utenteRepository;

    @Value("${jwt.secret}")
    private String miaKey;

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(miaKey.getBytes());
    }

    public String generaToken(Utente utente){
        long durata = 1000L * 60 * 60 * 24; 					//un giorno
        return Jwts.builder().claims().add("ruolo", utente.getRuolo())
                .subject(utente.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + durata))
                .and().signWith(getKey()).compact();
    }

    private Claims getClaims(String token){
        JwtParser parser = Jwts.parser().verifyWith(getKey()).build();
        Jws<Claims> jws = parser.parseSignedClaims(token);
        Claims claims = jws.getPayload();
        return claims;
    }

    private <T> T getValue(Function<Claims, T> function, String token){
        return function.apply(getClaims(token));
    }

    public String getUsername(String token){
        return getValue(c-> c.getSubject(), token);
    }

    public LocalDateTime getIssuedAt(String token){
        return getValue(Claims::getIssuedAt, token)
                .toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public LocalDateTime getExpirationTime(String token){
        return getValue(Claims::getExpiration, token)
                .toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public Ruolo getRuolo(String token){
        return getClaims(token).get("ruolo", Ruolo.class);
    }

    public Utente getUtente(String token){
        return utenteRepository.findByEmail(getUsername(token))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
