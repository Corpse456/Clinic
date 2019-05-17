package by.gp.clinic.service.schedule;

import by.gp.clinic.AbstractSpringMvcTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TimeTableCreatingServiceTest extends AbstractSpringMvcTest {

    @Autowired
    TimeTableCreatingService timeTableCreatingService;

    @Test
    public void executeTest() {
        timeTableCreatingService.execute();
    }
}