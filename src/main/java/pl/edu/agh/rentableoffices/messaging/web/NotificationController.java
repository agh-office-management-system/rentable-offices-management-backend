package pl.edu.agh.rentableoffices.messaging.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.messaging.dto.NotificationDto;
import pl.edu.agh.rentableoffices.messaging.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
//TODO websocket or long polling?
public class NotificationController {
    private final NotificationService service;

    @GetMapping("/{username}")
    public ResponseDto<List<NotificationDto>> getNotifications(@PathVariable String username) {
        return ResponseDto.success(service.getNotifications(username));
    }
}
