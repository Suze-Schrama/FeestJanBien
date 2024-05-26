package be.vanmanen.feestjanbien.gasten;

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

    private record VoornaamFamilienaam(String voornaam, String familieNaam) {
        VoornaamFamilienaam(Bevestiging bevestiging) {
        this(bevestiging.getVoornaam(), bevestiging.getFamilienaam());
    }}

    @PostMapping("bevestigingen")
    long create(@RequestBody NieuweBevestiging nieuweBevestiging){
        return bevestigingService.create(nieuweBevestiging);
    }

    @GetMapping("bevestigingen")
    Stream<VoornaamFamilienaam>findAll(){
        return bevestigingService.findAll()
                .stream()
                .map(bevestiging -> new VoornaamFamilienaam(bevestiging));
    }
}
