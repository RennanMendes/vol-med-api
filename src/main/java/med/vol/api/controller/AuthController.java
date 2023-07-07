package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.dto.login.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO) {
        var token = new UsernamePasswordAuthenticationToken(loginDTO.userName(), loginDTO.password());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
