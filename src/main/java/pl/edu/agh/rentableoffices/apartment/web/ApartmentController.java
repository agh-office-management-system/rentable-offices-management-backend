package pl.edu.agh.rentableoffices.apartment.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.apartment.dto.ApartmentDto;
import pl.edu.agh.rentableoffices.apartment.dto.CreateApartmentCommand;
import pl.edu.agh.rentableoffices.apartment.dto.UpdateApartmentCommand;
import pl.edu.agh.rentableoffices.apartment.service.ApartmentDetailsService;
import pl.edu.agh.rentableoffices.apartment.service.CreateApartmentService;
import pl.edu.agh.rentableoffices.apartment.service.UpdateApartmentService;
import pl.edu.agh.rentableoffices.common.ResponseDto;

@RestController
@RequestMapping("/api/apartment")
@RequiredArgsConstructor
public class ApartmentController {
    private final CreateApartmentService createApartmentService;
    private final UpdateApartmentService updateApartmentService;
    private final ApartmentDetailsService apartmentDetailsService;

    @GetMapping("/{id}")
    public ResponseDto<ApartmentDto> get(@PathVariable Long id) {
        return ResponseDto.success(apartmentDetailsService.get(id));
    }

    @PostMapping
    public ResponseDto<Long> create(@RequestBody CreateApartmentCommand request) {
        return ResponseDto.success(createApartmentService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseDto<Void> update(@PathVariable Long id, @RequestBody UpdateApartmentCommand request) {
        updateApartmentService.update(id, request);
        return ResponseDto.success();
    }
}
