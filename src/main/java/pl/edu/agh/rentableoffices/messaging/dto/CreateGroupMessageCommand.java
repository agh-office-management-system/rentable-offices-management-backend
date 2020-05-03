package pl.edu.agh.rentableoffices.messaging.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateGroupMessageCommand {
    @NotEmpty
    private String from;
    @NotEmpty
    private List<String> to;
    @NotEmpty
    private String content;
}
