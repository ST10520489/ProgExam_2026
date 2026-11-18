import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void GetTotalProductSales_WithValidData_ReturnCorrectValue() {
        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int[][] sales = {{25, 18, 20,}, {15, 10, 12}, {18, 15, 20}};

        Product product = new Product(products, sales);
        int expectedTotal = 153;
        int actualTotal = product.GetTotalProductSold();

        assertEquals(expectedTotal, actualTotal);
    }
    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue(){
        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int[][] sales = {{25, 18, 20,}, {15, 10, 12}, {18, 15, 20}};

        Product product = new Product(products, sales);
        double expectedAverage = 17.0;
        double actualAverage = product.GetAverageProductSold();

        assertEquals(expectedAverage, actualAverage, 0.01);

    }
}
