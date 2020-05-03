package pl.edu.agh.rentableoffices.tenant.exception;

import pl.edu.agh.rentableoffices.common.BusinessException;

public class SurveyNotFoundException extends BusinessException {
    public SurveyNotFoundException(Long id) {
        super("SURVEY_NOT_FOUND", new Object[]{id});
    }
}
