package be.vanmanen.feestjanbien.gasten;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
class BevestigingService {
    private final BevestigingRepository bevestigingRepository;

    public BevestigingService(BevestigingRepository bevestigingRepository) {
        this.bevestigingRepository = bevestigingRepository;
    }

    @Transactional
    long create(NieuweBevestiging nieuweBevestiging) {
        var bevestiging = new Bevestiging(0, nieuweBevestiging.voornaam(), nieuweBevestiging.familienaam(), nieuweBevestiging.eetMee(), nieuweBevestiging.opmerkingen());
        return bevestigingRepository.create(bevestiging);
    }
}
