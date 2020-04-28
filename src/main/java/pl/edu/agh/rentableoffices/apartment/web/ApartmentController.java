package pl.edu.agh.rentableoffices.apartment.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.apartment.dto.ApartmentDto;
import pl.edu.agh.rentableoffices.apartment.dto.CreateApartmentCommand;
import pl.edu.agh.rentableoffices.apartment.dto.UpdateApartmentCommand;
import pl.edu.agh.rentableoffices.apartment.service.ApartmentDetailsService;
import pl.edu.agh.rentableoffices.apartment.service.CreateApartmentService;
import pl.edu.agh.rentableoffices.apartment.service.UpdateApartmentService;

@RestController
@RequestMapping("/api/apartment")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApartmentController {
    private final CreateApartmentService createApartmentService;
    private final UpdateApartmentService updateApartmentService;
    private final ApartmentDetailsService apartmentDetailsService;

    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(apartmentDetailsService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateApartmentCommand request) {
        return ResponseEntity.ok(createApartmentService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateApartmentCommand request) {
        updateApartmentService.update(id, request);
        return ResponseEntity.ok(null);
    }
}
