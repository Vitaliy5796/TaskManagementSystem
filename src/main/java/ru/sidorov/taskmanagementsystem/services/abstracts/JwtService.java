package ru.sidorov.taskmanagementsystem.services.abstracts;


import jakarta.servlet.http.HttpServletRequest;
import ru.sidorov.taskmanagementsystem.models.entities.User;

public interface JwtService {
    /**
     * Token generation for login and put login as token payload
     * @param login user login
     * @return
     */
    String generateToken(String login);

    /**
     * Token validation
     * @param token income token
     * @return
     */
    boolean validateToken(String token);

    /**
     * Get login from token payload
     * @param token income token
     * @return
     */
    String getLoginFromToken(String token);

    /**
     * Get user from token payload login
     * @param token income token
     * @return
     */
    User getUserFromToken(String token);

    /**
     * Get token from servlet request
     * @param request servlet request
     * @return
     */
    String getTokenFromRequest(HttpServletRequest request);
}
