package pl.edu.agh.rentableoffices.tenant.model.survey;

import lombok.*;
import pl.edu.agh.rentableoffices.common.EntityBase;
import pl.edu.agh.rentableoffices.tenant.dto.survey.QuestionDto;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "survey")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Survey extends EntityBase {
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "survey_id")
    @Setter(value = AccessLevel.PRIVATE)
    private Set<Question> questions = new HashSet<>();

    @OneToMany
    @NotEmpty
    private Set<Tenant> target = new HashSet<>();

    public static Survey create(String name, String description, Set<QuestionDto> questions, List<Tenant> tenants) {
        Survey survey = new Survey(name, description, null, new HashSet<>(tenants));
        Set<Question> entityQuestions = questions.stream()
                .map(q -> QuestionCreator.create(survey, q))
                .collect(Collectors.toSet());
        survey.setQuestions(entityQuestions);
        return survey;
    }
}
