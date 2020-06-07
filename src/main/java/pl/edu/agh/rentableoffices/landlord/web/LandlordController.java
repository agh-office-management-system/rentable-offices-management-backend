package pl.edu.agh.rentableoffices.landlord.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.configuration.SwaggerTags;
import pl.edu.agh.rentableoffices.landlord.dto.CreateLandlordCommand;
import pl.edu.agh.rentableoffices.landlord.service.CreateLandlordService;

@RestController
@RequestMapping("/api/landlords")
@RequiredArgsConstructor
@Api(value = "Endpointy do zarządania właścicielami", tags = SwaggerTags.LANDLORD)
public class LandlordController {
    private final CreateLandlordService createLandlordService;

    @PostMapping
    @ApiOperation("Utwórz profil właściela")
    public ResponseDto<Long> create(@ApiParam("Żądanie utworzenia profilu właściela") @RequestBody CreateLandlordCommand command) {
        return ResponseDto.success(createLandlordService.create(command));
    }
}
