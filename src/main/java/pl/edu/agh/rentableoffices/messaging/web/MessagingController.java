package pl.edu.agh.rentableoffices.messaging.web;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.configuration.SwaggerTags;
import pl.edu.agh.rentableoffices.messaging.dto.CreateMessageCommand;
import pl.edu.agh.rentableoffices.messaging.dto.MessageDto;
import pl.edu.agh.rentableoffices.messaging.exception.MessageNotFound;
import pl.edu.agh.rentableoffices.messaging.exception.ReceiverNotFound;
import pl.edu.agh.rentableoffices.messaging.service.MessagingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/message",produces = "application/json")
@RequiredArgsConstructor
public class MessagingController {
    private final MessagingService messagingService;

    @PostMapping("/send")
    @PreAuthorize("permitAll()")
    public ResponseDto<Void> sendMessage(@RequestBody @Valid CreateMessageCommand command) throws ReceiverNotFound {
        messagingService.sendMessage(command);
        return ResponseDto.success();
    }

    @GetMapping("/user/{user}")
    @PreAuthorize("#user == authentication.principal.username")
    public ResponseDto<List<MessageDto>> getUserMessages(@PathVariable String user) throws MessageNotFound {
        return ResponseDto.success(messagingService.getUserMessages(user));
    }

    @GetMapping("/{id}")
    //TODO preauthorize
    public ResponseDto<MessageDto> getMessage(@PathVariable Long id) throws MessageNotFound {
        return ResponseDto.success(messagingService.getMessage(id));
    }

    @PutMapping("/{id}/markAsRead")
    //TODO preauthorize
    public ResponseDto<Void> markAsRead(@PathVariable Long id) throws MessageNotFound {
        messagingService.markAsRead(id);
        return ResponseDto.success();
    }

}
