package pl.edu.agh.rentableoffices.messaging.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateMessageCommand {
    //TODO remove
    @NotEmpty
    private String from;
    @NotEmpty
    private String to;
    @NotEmpty
    private String content;
}
