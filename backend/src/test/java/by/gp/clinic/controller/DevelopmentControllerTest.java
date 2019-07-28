package by.gp.clinic.controller;

import by.gp.clinic.AbstractSpringMvcTest;
import org.junit.Ignore;
import org.junit.Test;

public class DevelopmentControllerTest extends AbstractSpringMvcTest {

    @Test
    @Ignore
    public void fillData() {
        postQuery("/development/fill");
    }
}