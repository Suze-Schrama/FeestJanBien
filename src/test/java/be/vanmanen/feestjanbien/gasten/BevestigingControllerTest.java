package be.vanmanen.feestjanbien.gasten;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@Transactional
@Sql("/bevestigingen.sql")
@AutoConfigureMockMvc
class BevestigingControllerTest {
    private final static String BEVESTIGINGEN_TABLE = "bevestigingen";
    private final MockMvc mockMvc;
    private final JdbcClient jdbcClient;

    BevestigingControllerTest(MockMvc mockMvc, JdbcClient jdbcClient) {
        this.mockMvc = mockMvc;
        this.jdbcClient = jdbcClient;
    }

    @Test
    void createVoegtDeBevestigingToe() throws Exception {
        var responseBody = mockMvc.perform(post("/bevestigingen").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "voornaam": "test4",
                                "familienaam": "test4familienaam",
                                "eetMee": "ja",
                                "opmerkingen": "test4opmerking"
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcClient, BEVESTIGINGEN_TABLE,
                "naam = 'test4' and id =" + responseBody)).isOne();
    }
}