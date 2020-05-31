package pl.edu.agh.rentableoffices.landlord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.landlord.dto.CreateLandlordCommand;
import pl.edu.agh.rentableoffices.landlord.service.CreateLandlordService;

@RestController
@RequestMapping("/api/landlords")
@RequiredArgsConstructor
public class LandlordController {
    private final CreateLandlordService createLandlordService;

    @PostMapping
    public ResponseDto<Long> create(@RequestBody CreateLandlordCommand command) {
        return ResponseDto.success(createLandlordService.create(command));
    }
}
