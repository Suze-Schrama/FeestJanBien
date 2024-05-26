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

import java.nio.file.Files;
import java.nio.file.Path;

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
    private final static Path TEST_RESOURCES = Path.of("src/test/resources");
    private final MockMvc mockMvc;
    private final JdbcClient jdbcClient;

    BevestigingControllerTest(MockMvc mockMvc, JdbcClient jdbcClient) {
        this.mockMvc = mockMvc;
        this.jdbcClient = jdbcClient;
    }

    @Test
    void createVoegtDeBevestigingToe() throws Exception {
        var jsonData = Files.readString(TEST_RESOURCES.resolve("correcteBevestiging.json"));
        var responseBody = mockMvc.perform(post("/bevestigingen")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData)).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcClient, BEVESTIGINGEN_TABLE, "voornaam = 'test4' AND familienaam = 'test4familienaam' AND id = " + responseBody)).isOne();
    }

    @Test
    void findAllVindtAlleBevestigingen() throws Exception {
        mockMvc.perform(get("/bevestigingen"))
                .andExpectAll(status().isOk(), jsonPath("length()")
                        .value(JdbcTestUtils.countRowsInTable(jdbcClient, BEVESTIGINGEN_TABLE)));
    }}