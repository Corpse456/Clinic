package by.gp.clinic.controller;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dbo.AbstractDbo;
import by.gp.clinic.dto.AbstractDto;
import by.gp.clinic.dto.PageDto;
import by.gp.clinic.repository.CustomRepository;
import by.gp.clinic.search.PageableSearchRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONObject;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractControllerTest extends AbstractSpringMvcTest {

    private static final String SEARCH = "/search";
    private static final String ID = "id";

    Long addEntity() {
        return addEntity(getDtoMock());
    }

    void addEntityWithoutAnswer() {
        addEntityWithoutAnswer(getDtoMock(), "");
    }

    void addEntityWithoutAnswer(final AbstractDto dto, final String url) {
        final MvcResult result = postQuery(getUrl() + url, dto);
        assertEquals(200, result.getResponse().getStatus());
    }

    Long addEntity(final AbstractDto dto) {
        return addEntity(dto, getUrl() + "");
    }

    Long addEntity(final AbstractDto dto, final String url) {
        final MvcResult result = postQuery(url, dto);

        final JSONObject answer = getJsonFormString(getContentAsString(result));
        final Long id = getLongFromJson(answer, ID);

        assertNotNull(id);
        assertEquals(200, result.getResponse().getStatus());
        return id;
    }

    void addEntityWithStatus(final AbstractDto dto, final int status, final String errorMessage) {
        addEntityWithStatus(dto, status, errorMessage, "");
    }

    void addEntityWithStatus(final AbstractDto dto,
                             final int status,
                             final String errorMessage,
                             final String url) {
        final MvcResult result = postQuery(getUrl() + url, dto);
        assertEquals(status, result.getResponse().getStatus());
        final HashMap answer = getObjectFromResult(getReplaced(result), HashMap.class);
        assertEquals(errorMessage, answer.get("message"));
    }

    void getEntityTest(Class<? extends AbstractDto> dtoClass, final Long id) {
        final MvcResult result = getQuery(getUrl() + "/" + id);
        final AbstractDto savedEntity = getObjectFromResult(result, dtoClass);

        assertNotNull(savedEntity);
        assertEquals(200, result.getResponse().getStatus());
    }

    void removeEntityTest(final Long id) {
        final MvcResult result = deleteQuery(getUrl() + "/" + id);

        assertEquals(200, result.getResponse().getStatus());

        final Optional byId = getRepository().findById(id);
        assertFalse(byId.isPresent());
    }

    <N extends AbstractDto> void findEntitiesTest(final PageableSearchRequest searchRequest, final TypeReference<PageDto<N>> typeReference) {
        MvcResult result = postQuery(getUrl() + SEARCH, searchRequest);
        final PageDto<N> list = getListOfObjectsFromResult(result, typeReference);
        assertNotNull(list);
    }

    private String getReplaced(final MvcResult result) {
        return getContentAsString(result).replace("[", "").replace("]", "");
    }

    protected abstract CustomRepository<? extends AbstractDbo, Long> getRepository();

    protected abstract AbstractDto getDtoMock();

    protected abstract String getUrl();
}
