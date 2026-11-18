import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductCalculationTest {
    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue(){
        ProductCalculations calc = new ProductCalculations(3500.0);
        double expectedVat = 525.0;
        double actualVat = calc.GetVat();

        assertEquals(expectedVat, actualVat, 0.0);
    }
    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue(){
        ProductCalculations calc = new ProductCalculations(3500.0);
        double expectedTotal = 4200.0;
        double actualTotal = calc.GetTotal();

        assertEquals(expectedTotal, actualTotal, 0.01);

    }
}
