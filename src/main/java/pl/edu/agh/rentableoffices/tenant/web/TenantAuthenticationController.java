package pl.edu.agh.rentableoffices.tenant.web;

import io.swagger.annotations.Api;
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
import pl.edu.agh.rentableoffices.configuration.SwaggerTags;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
@Api(tags = SwaggerTags.LOGIN)
public class TenantAuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<PrincipalDto> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        LoginDto loginDto = authenticationService.loginTenant(authHeader);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, loginDto.getToken())
                .body(loginDto.getPrincipalDto());
    }
}
