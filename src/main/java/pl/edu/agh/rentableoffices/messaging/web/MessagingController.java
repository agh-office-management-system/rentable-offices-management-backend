package pl.edu.agh.rentableoffices.messaging.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "Endpointy dla wiadomości między użytkownikami",tags = SwaggerTags.MESSAGE)
public class MessagingController {
    private final MessagingService messagingService;

    @PostMapping("/send")
    @PreAuthorize("permitAll()")
    @ApiOperation("Wysyłanie wiadomości")
    public ResponseDto<Void> sendMessage(@ApiParam("Żądanie utworzenia wiadomości") @RequestBody @Valid CreateMessageCommand command) throws ReceiverNotFound {
        messagingService.sendMessage(command);
        return ResponseDto.success();
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    @ApiOperation("Pobranie wiadomości użytkownika")
    public ResponseDto<List<MessageDto>> getUserMessages(@ApiParam("Nazwa użytkownika") @PathVariable String username) throws MessageNotFound {
        return ResponseDto.success(messagingService.getUserMessages(username));
    }

    @GetMapping("/{id}")
    //TODO preauthorize
    @ApiOperation("Pobranie pojedynczej wiadomości")
    public ResponseDto<MessageDto> getMessage(@ApiParam("Id wiadomości") @PathVariable Long id) throws MessageNotFound {
        return ResponseDto.success(messagingService.getMessage(id));
    }

    @PutMapping("/{id}/markAsRead")
    //TODO preauthorize
    @ApiOperation("Oznacz wiadomość jako przeczytaną")
    public ResponseDto<Void> markAsRead(@ApiParam("Id wiadomości") @PathVariable Long id) throws MessageNotFound {
        messagingService.markAsRead(id);
        return ResponseDto.success();
    }

}
