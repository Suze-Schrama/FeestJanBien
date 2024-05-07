package be.vanmanen.feestjanbien.gasten;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BevestigingController {
    private final BevestigingService bevestigingService;

    BevestigingController(BevestigingService bevestigingService){
        this.bevestigingService = bevestigingService;
    }

    @PostMapping("bevestiging")
    void create(@RequestBody NieuweBevestiging nieuweBevestiging){
        bevestigingService.create(nieuweBevestiging);
    }
}
