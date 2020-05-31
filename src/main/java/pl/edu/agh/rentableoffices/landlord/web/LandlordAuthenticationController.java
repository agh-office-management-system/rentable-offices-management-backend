package pl.edu.agh.rentableoffices.landlord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.rentableoffices.authentication.AuthenticationService;
import pl.edu.agh.rentableoffices.authentication.LoginDto;
import pl.edu.agh.rentableoffices.authentication.PrincipalDto;

@RestController
@RequestMapping("/api/landlords")
@RequiredArgsConstructor
public class LandlordAuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<PrincipalDto> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        LoginDto loginDto = authenticationService.loginLandlord(authHeader);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, loginDto.getToken())
                .body(loginDto.getPrincipalDto());
    }
}
