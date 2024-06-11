package be.vanmanen.feestjanbien.gasten;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record NieuweBevestiging(@NotBlank @Size(max = 20) String voornaam, @NotBlank @Size(max = 20) String familienaam,
                         @NotBlank @Size(max = 9) String eetMee, @Size(max = 300) String opmerkingen) {
}
