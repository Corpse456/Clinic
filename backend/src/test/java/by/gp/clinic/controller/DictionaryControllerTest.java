package by.gp.clinic.controller;

import by.gp.clinic.AbstractSpringMvcTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DictionaryControllerTest extends AbstractSpringMvcTest {

    private static final String URL = "/dictionary";

    @Test
    public void getDictionaryDataTest() {
        final MvcResult result = getQuery(URL);
        final String answer = getContentAsString(result);

        assertEquals(200, result.getResponse().getStatus());
        assertNotNull(answer);
    }
}