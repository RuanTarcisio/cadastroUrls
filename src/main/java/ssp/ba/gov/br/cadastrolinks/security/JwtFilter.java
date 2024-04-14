package ssp.ba.gov.br.cadastrolinks.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import ssp.ba.gov.br.cadastrolinks.domain.Cliente;
import ssp.ba.gov.br.cadastrolinks.domain.Usuario;
import ssp.ba.gov.br.cadastrolinks.service.ClienteService;
import ssp.ba.gov.br.cadastrolinks.service.exception.InvalidTokenException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
    private  JwtService jwtService;
    @Autowired
    private  ClienteService clienteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);

        if(token != null){
            try {
                String email = jwtService.getEmailFromToken(token);
                Cliente cliente = clienteService.buscarPorEmail(email);
                setUserAsAuthenticated(cliente.getUsuario());
            }catch (InvalidTokenException e){
                log.error("Token inválido: {} ", e.getMessage());
            }catch (Exception e){
                log.error("Erro na validação do token: {} ", e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setUserAsAuthenticated(Usuario user){
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null){
            String[] authHeaderParts = authHeader.split(" ");
            if(authHeaderParts.length == 2){
                return authHeaderParts[1];
            }
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().contains("/v1/users");
    }
}