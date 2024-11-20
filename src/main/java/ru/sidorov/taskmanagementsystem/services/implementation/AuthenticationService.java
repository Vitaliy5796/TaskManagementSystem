package ru.sidorov.taskmanagementsystem.services.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.sidorov.taskmanagementsystem.config.jwt.JwtUtils;
import ru.sidorov.taskmanagementsystem.models.dto.response.JwtResponse;
import ru.sidorov.taskmanagementsystem.models.dto.user.CredentialsDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public JwtResponse authenticateAndGetToken(CredentialsDto credentialsDto, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    credentialsDto.getLogin(),
                    credentialsDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtils.generateJwtToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return new JwtResponse(userDetails.getUsername(), token);
        } catch (AuthenticationException authenticationException) {
            throw new BadCredentialsException("Invalid login or password", authenticationException);
        }
    }
}
