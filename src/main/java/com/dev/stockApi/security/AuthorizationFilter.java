package com.dev.stockApi.security;
import com.dev.stockApi.domain.RequestContext;
import com.dev.stockApi.domain.Token;
import com.dev.stockApi.domain.TokenData;
import com.dev.stockApi.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.dev.stockApi.constant.Constants.PUBLIC_ROUTES;
import static com.dev.stockApi.domain.ApiAuthentication.authenticated;
import static com.dev.stockApi.enumeration.TokenType.ACCESS;
import static com.dev.stockApi.enumeration.TokenType.REFRESH;
import static com.dev.stockApi.utils.RequestUtils.handleErrorResponse;
import static java.util.Arrays.asList;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var accessToken = jwtService.extractToken(request, ACCESS.getValue());
            if (accessToken.isPresent() && jwtService.getTokenData(accessToken.get(), TokenData::isValid)) {
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(accessToken.get(), request));
                RequestContext.setUserId(jwtService.getTokenData(accessToken.get(), TokenData::getUser).getId());
            } else {
                var refreshToken = jwtService.extractToken(request, REFRESH.getValue());
                if(refreshToken.isPresent() && jwtService.getTokenData(refreshToken.get(), TokenData::isValid)) {
                    var user = jwtService.getTokenData(refreshToken.get(), TokenData::getUser);
                    SecurityContextHolder.getContext().setAuthentication(getAuthentication(jwtService.createToken(user, Token::getAccess), request));
                    jwtService.addCookie(response, user, ACCESS);
                    RequestContext.setUserId(user.getId());
                } else {
                    SecurityContextHolder.clearContext();
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            handleErrorResponse(request, response, exception);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        //var shouldNotFilter = request.getMethod().equalsIgnoreCase(OPTIONS.name()) || asList(PUBLIC_ROUTES).contains(request.getRequestURI());
        var shouldNotFilter = request.getMethod().equalsIgnoreCase(OPTIONS.name()) || asList(PUBLIC_ROUTES).contains(request.getRequestURI());
        if(shouldNotFilter) { RequestContext.setUserId(0L); }
        return shouldNotFilter;
    }

    private Authentication getAuthentication(String token, HttpServletRequest request) {
        var authentication = authenticated(jwtService.getTokenData(token, TokenData::getUser), jwtService.getTokenData(token, TokenData::getAuthorities));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }
}