package by.gp.clinic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import static java.util.Objects.requireNonNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest()
public abstract class AbstractTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    protected MvcResult postQuery(final String url, final Object object) {
        try {
            return mockMvc.perform(post(url)
                                       .content(requireNonNull(toJson(object)))
                                       .contentType(APPLICATION_JSON)).andReturn();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected MvcResult postQuery(final String url) {
        try {
            return mockMvc.perform(post(url)).andReturn();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected MvcResult patchQuery(final String url, final Object object) {
        try {
            return mockMvc.perform(patch(url)
                                       .content(requireNonNull(toJson(object)))
                                       .contentType(APPLICATION_JSON)).andReturn();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected MvcResult getQuery(final String url) {
        try {
            return mockMvc.perform(get(url)).andReturn();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected String getContent(final MvcResult result) {
        try {
            return result.getResponse().getContentAsString();
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    protected String toJson(final Object object) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected HashMap fromJson(final String object) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(object.getBytes(), HashMap.class);
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
