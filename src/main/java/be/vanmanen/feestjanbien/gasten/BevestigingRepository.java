package be.vanmanen.feestjanbien.gasten;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
class BevestigingRepository {
    private final JdbcClient jdbcClient;

    BevestigingRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    void create(Bevestiging bevestiging){
        var sql = """
                INSERT INTO bevestigingen (voornaam, familienaam, eetMee, opmerking)
                VALUES (?, ?, ?, ?)
                """;
        jdbcClient.sql(sql).params(bevestiging.getVoornaam(), bevestiging.getFamilienaam(), bevestiging.getEetMee(), bevestiging.getOpmerking()).update();
    }
}
