package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.tenant.dto.CreateSurveyCommand;
import pl.edu.agh.rentableoffices.tenant.dto.SubmitSurveyCommand;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TenantSurveyService {
    /*

Najemcy, których dotyczy utworzona ankieta otrzymują powiadomienie.
Jeżeli najemca zdecyduje się wypełnić ankietę, do pracownika administracji trafia powiadomienie o wypełnionej ankiecie i raport z wynikami.
Jeżeli najemca odrzuci ankietę do pracownika administracji trafia informacja o odmowie.
 */
    //TODO
    public Long createSurvey(CreateSurveyCommand command) {
        return 1L;
    }

    public void submitSurvey(Long id, SubmitSurveyCommand command) {

    }

    public void rejectSurvey(Long id) {

    }
}
