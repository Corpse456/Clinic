package by.gp.clinic;

import by.gp.clinic.dto.PageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ClinicApplication.class)
@ActiveProfiles("test")
@Transactional
public abstract class AbstractSpringMvcTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void before() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    protected MvcResult postQuery(final String url, final Object object) {
        try {
            return mockMvc.perform(post(url)
                                       .content(requireNonNull(toJson(object)))
                                       .contentType(APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (final Exception e) {
            fail(e.getMessage());
            return null;
        }
    }

    @SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
    protected MvcResult postQuery(final String url) {
        try {
            return mockMvc.perform(post(url)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (final Exception e) {
            fail(e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unused")
    protected MvcResult patchQuery(final String url, final Object object) {
        try {
            return mockMvc.perform(patch(url)
                                       .content(requireNonNull(toJson(object)))
                                       .contentType(APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (final Exception e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected MvcResult getQuery(final String url) {
        try {
            return mockMvc.perform(get(url)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (final Exception e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected MvcResult deleteQuery(final String url) {
        try {
            return mockMvc.perform(delete(url)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (final Exception e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected String toJson(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected <T> T getObjectFromResult(final MvcResult result, Class<T> clazz) {
        return getObjectFromResult(getContentAsString(result), clazz);
    }

    protected String getContentAsString(final MvcResult result) {
        try {
            return result.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected <T> T getObjectFromResult(final String result, Class<T> clazz) {
        try {
            return objectMapper.readValue(result, clazz);
        } catch (final IOException e) {
            fail(e.getMessage());
            return null;
        }
    }

    protected <T> PageDto<T> getListOfObjectsFromResult(final MvcResult result,
                                                        final TypeReference<PageDto<T>> listTypeReference) {
        try {
            return objectMapper.readValue(getContentAsString(result), listTypeReference);
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
