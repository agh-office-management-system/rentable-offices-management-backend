package pl.edu.agh.rentableoffices.repair;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.rentableoffices.common.ResponseDto;

@RestController
@RequestMapping("/api/repair")
@RequiredArgsConstructor
public class RepairController {
    private final RepairService repairService;
    /*
    Zgłoszenie wykonania naprawy przez pracownika
Informacja o tym  trafia do pracownika administracji, który akceptuje wykonanie prac.
Jeżeli realizacja została odrzucona przez pracownika administracji, informacja o odmowie trafia do robotnika.
Jeśli realizacja została zaakceptowana przez pracownika administracji prośba jest przekazywana do najemcy zgłaszającego incydent.
 Najemca decyduje o akceptacji wykonania prac.
 Jeżeli najemca akceptuje wykonane prace, zgłoszenie zostaje zamknięte.
 Jeżeli najemca odmawia odbioru, informacja trafia do pracownika administracji, który decyduje czy odmowa jest zasadna.
  Jeżeli odmowa nie jest zasadna zlecenie jest zamykane.
  Jeżeli odmowa zostanie uznana za zasadną, informacja o odmowie trafia do robotnika.
     */
    /*
    Po wykonaniu naprawy robotnik zgłasza zakończenie prac.
    TODO - Role Robotnik
     */
    public ResponseDto<Void> reportRepair() {
        return ResponseDto.success();
    }
    /*
    Generowanie raportu prac wykonanych przez pracownika
Pracownik administracji wysyła żądanie wygenerowania raportu prac wykonanych przez robotnika. System sprawdza stan prac wykonanych przez robotnika i generuje raport. Raport jest odsyłany do autora żądania.

     */
}
