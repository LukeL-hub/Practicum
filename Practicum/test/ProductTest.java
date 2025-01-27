import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testConstructorAndGetters() {
        Product product = new Product("000001", "Laptop", "A high-performance laptop", 999.99);
        assertEquals("000001", product.getID());
        assertEquals("Laptop", product.getName());
        assertEquals("A high-performance laptop", product.getDescription());
        assertEquals(999.99, product.getCost());
    }

    @Test
    void testSetters() {
        Product product = new Product("000001", "Laptop", "A high-performance laptop", 999.99);
        product.setName("Desktop");
        product.setDescription("A powerful desktop");
        product.setCost(799.99);

        assertEquals("Desktop", product.getName());
        assertEquals("A powerful desktop", product.getDescription());
        assertEquals(799.99, product.getCost());
    }

    @Test
    void testSetCostValidation() {
        Product product = new Product("000001", "Laptop", "A high-performance laptop", 999.99);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setCost(-50.00);
        });
        assertEquals("Cost must be non-negative.", exception.getMessage());
    }

    @Test
    void testToString() {
        Product product = new Product("000001", "Laptop", "A high-performance laptop", 999.99);
        assertEquals("Laptop (000001): A high-performance laptop - $999.99", product.toString());
    }

    @Test
    void testEquals() {
        Product product1 = new Product("000001", "Laptop", "A high-performance laptop", 999.99);
        Product product2 = new Product("000001", "Laptop", "A high-performance laptop", 999.99);
        Product product3 = new Product("000002", "Desktop", "A powerful desktop", 799.99);

        assertEquals(product1, product2);
        assertNotEquals(product1, product3);
    }

    @Test
    void testToCSV() {
        Product product = new Product("000001", "Laptop", "A high-performance laptop", 999.99);
        assertEquals("000001,Laptop,A high-performance laptop,999.99", product.toCSV());
    }

    @Test
    void testToJSON() {
        Product product = new Product("000001", "Laptop", "A high-performance laptop", 999.99);
        String expectedJSON = "{\"ID\":\"000001\", \"name\":\"Laptop\", \"description\":\"A high-performance laptop\", \"cost\":999.99}";
        assertEquals(expectedJSON, product.toJSON());
    }

    @Test
    void testToXML() {
        Product product = new Product("000001", "Laptop", "A high-performance laptop", 999.99);
        String expectedXML = "<Product><ID>000001</ID><Name>Laptop</Name><Description>A high-performance laptop</Description><Cost>999.99</Cost></Product>";
        assertEquals(expectedXML, product.toXML());
    }
}