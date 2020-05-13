package pl.edu.agh.rentableoffices.tenant.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO generic
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnswerDto {
    private String code;
    private Object value;
}
