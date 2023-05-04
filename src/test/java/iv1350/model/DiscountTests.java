package iv1350.model;

import iv1350.dto.DiscountDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DiscountTests {
    @Test
    void calculatesTotalCorrectly() {
        Discount discount25 = new Discount(new DiscountDTO(25));
        int oldTotal = 100;
        int expected = 75;
        int actual = discount25.totalAfterDiscount(oldTotal);
        assertEquals(expected, actual);
    }
}
