package pl.edu.agh.rentableoffices.messaging.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.configuration.SwaggerTags;
import pl.edu.agh.rentableoffices.messaging.dto.NotificationDto;
import pl.edu.agh.rentableoffices.messaging.service.NotificationDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@Api(value = "Endpointy do notyfikacji użytkownika", tags = SwaggerTags.NOTIFICATION)
public class NotificationController {
    private final NotificationDetailsService service;

    @GetMapping("/{username}")
    @ApiOperation("Pobierz notyfikacje użytkownika")
    public ResponseDto<List<NotificationDto>> getNotifications(@ApiParam("Nazwa użytkownika") @PathVariable String username) {
        return ResponseDto.success(service.getNotifications(username));
    }
}
