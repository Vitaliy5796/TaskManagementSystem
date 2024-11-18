package ru.sidorov.taskmanagementsystem.services.implementation;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.sidorov.taskmanagementsystem.config.ApplicationConfig;
import ru.sidorov.taskmanagementsystem.models.entities.User;
import ru.sidorov.taskmanagementsystem.models.exception.NotFoundUserException;
import ru.sidorov.taskmanagementsystem.repositories.UserRepository;
import ru.sidorov.taskmanagementsystem.services.abstracts.JwtService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Service
public class JwtServiceImpl implements JwtService {
    private static final Logger log = LoggerFactory.getLogger(JwtServiceImpl.class);

    private final ApplicationConfig applicationConfig;
    private final UserRepository userRepository;

    public JwtServiceImpl(ApplicationConfig applicationConfig, UserRepository userRepository) {
        this.applicationConfig = applicationConfig;
        this.userRepository = userRepository;
    }

    @Override
    public String generateToken(String login) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, applicationConfig.getJwtSecret())
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(applicationConfig.getJwtSecret()).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt");
        } catch (SignatureException sEx) {
            log.error("Invalid signature");
        } catch (Exception e) {
            log.error("invalid token");
        }
        return false;
    }

    @Override
    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(applicationConfig.getJwtSecret()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    @Override
    public User getUserFromToken(String token) {
        String login = getLoginFromToken(token);
        List<User> users = userRepository.getByEmail(login);
        if (users.size() == 0) {
            throw new NotFoundUserException();
        }
        return users.get(0);
    }

    @Override
    public String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
