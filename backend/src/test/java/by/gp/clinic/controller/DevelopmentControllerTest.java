package by.gp.clinic.controller;

import by.gp.clinic.AbstractSpringMvcTest;
import org.junit.jupiter.api.Test;

public class DevelopmentControllerTest extends AbstractSpringMvcTest {

    @Test
    public void fillData() {
        postQuery("/development/fill");
    }
}
