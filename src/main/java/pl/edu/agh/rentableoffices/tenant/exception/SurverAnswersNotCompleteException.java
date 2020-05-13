package pl.edu.agh.rentableoffices.tenant.exception;

import pl.edu.agh.rentableoffices.common.BusinessException;

public class SurverAnswersNotCompleteException extends BusinessException {

    public SurverAnswersNotCompleteException() {
        super("SURVEY_ANSWERS_NOT_COMPLETE");
    }
}
