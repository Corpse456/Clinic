package by.gp.clinic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.fail;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClinicApplication.class)
@ActiveProfiles("test")
public abstract class AbstractSpringMvcTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

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
            fail(e.getMessage());
            return null;
        }
    }

    protected MvcResult postQuery(final String url) {
        try {
            return mockMvc.perform(post(url)).andReturn();
        } catch (final Exception e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected MvcResult patchQuery(final String url, final Object object) {
        try {
            return mockMvc.perform(patch(url)
                                       .content(requireNonNull(toJson(object)))
                                       .contentType(APPLICATION_JSON)).andReturn();
        } catch (final Exception e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected MvcResult getQuery(final String url) {
        try {
            return mockMvc.perform(get(url)).andReturn();
        } catch (final Exception e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected MvcResult deleteQuery(final String url) {
        try {
            return mockMvc.perform(delete(url)).andReturn();
        } catch (final Exception e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected String getContent(final MvcResult result) {
        try {
            return result.getResponse().getContentAsString();
        } catch (final UnsupportedEncodingException e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected String toJson(final Object object) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected <T> T getObjectFromResult(final MvcResult result, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(result.getResponse().getContentAsString(), clazz);
        } catch (final IOException e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected JSONObject getJsonFormString(final String object) {
        try {
            return new JSONObject(object);
        } catch (final JSONException e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected Long getLongFromJson(final JSONObject object, final String key) {
        try {
            return object.getLong(key);
        } catch (final JSONException e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected String getStringFromJson(final JSONObject object, final String key) {
        try {
            return object.getString(key);
        } catch (final JSONException e) {
            fail(e.getMessage());
            return null;
        }
    }
}
