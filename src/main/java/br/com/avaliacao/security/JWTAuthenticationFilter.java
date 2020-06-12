package br.com.avaliacao.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);

        if (authentication == null){
            SecurityContextHolder.getContext().setAuthentication(null);
        } else {
            Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
            List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<>();
            updatedAuthorities.add(authority);
            updatedAuthorities.addAll(oldAuthorities);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            authentication.getPrincipal(),
                            authentication.getCredentials(),
                            updatedAuthorities));

//        SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
