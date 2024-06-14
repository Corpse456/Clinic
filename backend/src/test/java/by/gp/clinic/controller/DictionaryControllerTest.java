package by.gp.clinic.controller;

import by.gp.clinic.AbstractSpringMvcTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DictionaryControllerTest extends AbstractSpringMvcTest {

    private static final String URL = "/dictionary";

    @Test
    public void getDictionaryDataTest() {
        final var result = getQuery(URL);
        final var answer = getContentAsString(result);

        assertEquals(200, result.getResponse().getStatus());
        assertNotNull(answer);
    }
}
