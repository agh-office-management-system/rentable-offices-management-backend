package pl.edu.agh.rentableoffices.messaging.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.messaging.dto.CreateMessageCommand;
import pl.edu.agh.rentableoffices.messaging.dto.MessageDto;
import pl.edu.agh.rentableoffices.messaging.exception.MessageNotFound;
import pl.edu.agh.rentableoffices.messaging.exception.ReceiverNotFound;
import pl.edu.agh.rentableoffices.messaging.service.MessagingService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessagingController {
    private final MessagingService messagingService;

    @PostMapping("/send")
    //TODO -> allow all
    //TODO -> remove sender after login mechanism introduced
    public ResponseDto<Void> sendMessage(@RequestBody @Valid CreateMessageCommand command) throws ReceiverNotFound {
        messagingService.sendMessage(command);
        return ResponseDto.success();
    }

    @GetMapping("/{id}")
    public ResponseDto<MessageDto> getMessage(@PathVariable Long id) throws MessageNotFound {
        return ResponseDto.success(messagingService.getMessage(id));
    }

    @PutMapping("/{id}/markAsRead")
    public ResponseDto<Void> markAsRead(@PathVariable Long id) throws MessageNotFound {
        messagingService.markAsRead(id);
        return ResponseDto.success();
    }

}
