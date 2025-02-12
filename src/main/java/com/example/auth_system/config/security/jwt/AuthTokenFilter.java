package com.example.auth_system.config.security.jwt;

import com.example.auth_system.component.Helper;
import com.example.auth_system.config.security.services.UserDetailsServiceImpl;
import com.example.auth_system.dto.user.UserDetailsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = Helper.parseJwt(request);
            if (jwt != null) {
                Claims claims = jwtUtils.getAllClaimsFromToken(jwt);
                ObjectMapper objectMapper = new ObjectMapper();

                String claimsString = objectMapper.writeValueAsString(claims.get("userDetails", Object.class));
                UserDetailsDto userDetailsDto = objectMapper.readValue(claimsString, UserDetailsDto.class);

                GrantedAuthority authority = new SimpleGrantedAuthority(userDetailsDto.getRoleDto().getRoleName());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetailsDto, null, Collections.singleton(authority)
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JsonProcessingException e) {
            logger.error("Cannot set user authentication by token - JsonProcessingException", e);
        } catch (ExpiredJwtException e) {
            logger.error("Cannot set user authentication by token - ExpiredJwtException", e);
        } catch (MalformedJwtException e) {
            logger.error("Cannot set user authentication by token - MalformedJwtException", e);
        } catch (io.jsonwebtoken.security.SecurityException e) {
            logger.error("Cannot set user authentication by token - SecurityException", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Cannot set user authentication by token - UnsupportedJwtException", e);
        } catch (IllegalArgumentException e) {
            logger.error("Cannot set user authentication by token - IllegalArgumentException", e);
        }

        filterChain.doFilter(request, response);
    }
}
