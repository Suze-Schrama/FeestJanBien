package be.vanmanen.feestjanbien.gasten;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
class BevestigingController {
    private final BevestigingService bevestigingService;

    BevestigingController(BevestigingService bevestigingService){
        this.bevestigingService = bevestigingService;
    }

    private record VoornaamFamilienaamEetMee(String voornaam, String familienaam, String eetMee) {
        VoornaamFamilienaamEetMee(Bevestiging bevestiging) {
        this(bevestiging.getVoornaam(), bevestiging.getFamilienaam(), bevestiging.getEetMee());
    }}

    @PostMapping("bevestigingen")
    long create(@RequestBody @Valid NieuweBevestiging nieuweBevestiging){
        return bevestigingService.create(nieuweBevestiging);
    }

    @GetMapping("bevestigingen")
    Stream<VoornaamFamilienaamEetMee>findAll(){
        return bevestigingService.findAll()
                .stream()
                .map(bevestiging -> new VoornaamFamilienaamEetMee(bevestiging));
    }
}
