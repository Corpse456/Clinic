package by.gp.clinic.controller;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dbo.AbstractDbo;
import by.gp.clinic.dto.AbstractDto;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractControllerTest extends AbstractSpringMvcTest {

    private static final String SEARCH = "/search";
    private static final String ID = "id";

    Long addEntityCheck() {
        return addEntityCheck(getDtoMock());
    }

    Long addEntityCheck(final AbstractDto dto) {
        final MvcResult result = postQuery(getUrl(), dto);

        final JSONObject answer = getJsonFormString(getContent(result));
        final Long id = getLongFromJson(answer, ID);

        assertNotNull(id);
        assertEquals(200, result.getResponse().getStatus());
        return id;
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

    <N extends AbstractDto> void findEntitiesTest(final TypeReference<List<N>> typeReference) {
        MvcResult result = getQuery(getUrl() + SEARCH);
        final List<N> list = getListOfObjectsFromResult(result, typeReference);
        assertNotNull(list);
    }

    protected abstract JpaRepository<? extends AbstractDbo, Long> getRepository();

    protected abstract AbstractDto getDtoMock();

    protected abstract String getUrl();
}
