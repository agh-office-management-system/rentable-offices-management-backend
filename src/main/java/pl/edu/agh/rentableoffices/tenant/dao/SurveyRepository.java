package pl.edu.agh.rentableoffices.tenant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.rentableoffices.tenant.exception.SurveyNotFoundException;
import pl.edu.agh.rentableoffices.tenant.model.survey.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    default Survey get(Long id) throws SurveyNotFoundException {
        return findById(id).orElseThrow(() -> new SurveyNotFoundException(id));
    }
}
