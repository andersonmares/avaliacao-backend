package br.com.avaliacao.resource;

import br.com.avaliacao.security.AccountCredentials;
import br.com.avaliacao.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@CrossOrigin("${frontend}")
public class LoginResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid AccountCredentials credentials) throws Exception{

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword(),
                        Collections.emptyList()
                )
        );

        if (authentication != null) {
            final String token = TokenAuthenticationService.gerarToken(credentials.getUsername());
            return ResponseEntity.ok(token);
        } else{
            return ResponseEntity.badRequest().body("CREDENCIAIS INVALIDAS");
        }
    }

}
