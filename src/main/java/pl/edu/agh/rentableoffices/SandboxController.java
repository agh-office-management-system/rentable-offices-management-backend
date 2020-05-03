package pl.edu.agh.rentableoffices;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.tenant.dto.CreateTenantCommand;

//@Profile("dev")
@RestController("/api")
public class SandboxController {
    @GetMapping("/exception")
    public ResponseDto<Long> create() {
        throw new IllegalArgumentException();
    }
}
