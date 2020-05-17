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
@Api(value = "Manage user messages", tags = SwaggerTags.MESSAGE)
@RequiredArgsConstructor
public class MessagingController {
    private final MessagingService messagingService;

    @PostMapping("/send")
    @ApiOperation(value = "Wyślij wiadomość")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Udało się wysłać wiadomość"),
            @ApiResponse(code = 401, message = "Użytkownik nie jest zalogowany")
    })
    @PreAuthorize("permitAll()")
    public ResponseDto<Void> sendMessage(@RequestBody @Valid CreateMessageCommand command) throws ReceiverNotFound {
        messagingService.sendMessage(command);
        return ResponseDto.success();
    }

    @GetMapping("/user/{user}")
    @ApiOperation(value = "Pobierz wiadomości użytkownika")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Udało się pobrać wiadomości"),
            @ApiResponse(code = 401, message = "Użytkownik nie jest zalogowany"),
            @ApiResponse(code = 403, message = "Użytkownik nie ma uprawnień, żeby pobrać wiadomość")
    })
    @PreAuthorize("#user == authentication.principal.username")
    public ResponseDto<List<MessageDto>> getUserMessages(@PathVariable @ApiParam("Email użytkownika") String user) throws MessageNotFound {
        return ResponseDto.success(messagingService.getUserMessages(user));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Pobierz wiadomość")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Udało się pobrać wiadomość"),
            @ApiResponse(code = 401, message = "Użytkownik nie jest zalogowany"),
            @ApiResponse(code = 403, message = "Wiadomość nie jest zaadresowana do użytkownika")
    })
    //TODO preauthorize
    public ResponseDto<MessageDto> getMessage(@ApiParam("id wiadomości") @PathVariable Long id) throws MessageNotFound {
        return ResponseDto.success(messagingService.getMessage(id));
    }

    @PutMapping("/{id}/markAsRead")
    @ApiOperation(value = "Oznacz wiadomość jako przeczytaną")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Udało się zaktualizować wiadomość"),
            @ApiResponse(code = 401, message = "Użytkownik nie jest zalogowany"),
            @ApiResponse(code = 403, message = "Wiadomość nie jest zaadresowana do użytkownika")
    })
    //TODO preauthorize
    public ResponseDto<Void> markAsRead(@ApiParam("id wiadomości") @PathVariable Long id) throws MessageNotFound {
        messagingService.markAsRead(id);
        return ResponseDto.success();
    }

}
