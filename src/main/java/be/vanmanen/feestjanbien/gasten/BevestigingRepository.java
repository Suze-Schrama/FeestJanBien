package be.vanmanen.feestjanbien.gasten;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class BevestigingRepository {
    private final JdbcClient jdbcClient;

    BevestigingRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    List<Bevestiging>findAll(){
        var sql = """
                SELECT id, voornaam, familienaam, eetMee, opmerkingen
                FROM bevestigingen
                ORDER BY familienaam, voornaam
                """;
        return jdbcClient.sql(sql)
                .query(Bevestiging.class)
                .list();
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
