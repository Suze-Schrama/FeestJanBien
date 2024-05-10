package be.vanmanen.feestjanbien.gasten;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
class BevestigingRepository {
    private final JdbcClient jdbcClient;

    BevestigingRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    long create(Bevestiging bevestiging){
        var sql = """
                INSERT INTO bevestigingen (voornaam, familienaam, eetMee, opmerkingen)
                VALUES (?, ?, ?, ?)
                """;
        var keyHolder = new GeneratedKeyHolder();
         jdbcClient.sql(sql).params(bevestiging.getVoornaam(), bevestiging.getFamilienaam(), bevestiging.getEetMee(), bevestiging.getOpmerkingen()).update(keyHolder);
         return keyHolder.getKey().longValue();
    }
}
