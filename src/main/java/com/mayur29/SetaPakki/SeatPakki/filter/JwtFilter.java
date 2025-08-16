package com.mayur29.SetaPakki.SeatPakki.filter;

import com.mayur29.SetaPakki.SeatPakki.entity.User;
import com.mayur29.SetaPakki.SeatPakki.service.UserDetailsImpl;
import com.mayur29.SetaPakki.SeatPakki.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsImpl userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String userName="";
        String jwt="";
        if(authorization!=null&&authorization.startsWith("Bearer")){
            jwt = authorization.substring(7);
            userName = jwtUtils.extractUserName(jwt);
        }

        if (userName!=null && !userName.isEmpty()){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            if (jwtUtils.validateToken(jwt)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request,response);
    }
}
