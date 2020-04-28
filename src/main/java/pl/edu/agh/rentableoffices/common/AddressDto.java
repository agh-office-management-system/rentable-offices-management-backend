package pl.edu.agh.rentableoffices.common;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressDto {
    @NotEmpty
    private String number;

    private String street;

    @NotEmpty
    private String city;

    @NotEmpty
    private String postalCode;

    @Builder.Default
    private String country = "PL";
}
