package selenium;

import org.junit.Test;

public class AddHotels extends AbstractUITest {

    @Test
    public void addHotelsTest() {
        driver.get(BASE_URL);

        String gridPath = "//*[@id='HotelGrid']/div[3]/table";
    }
}
