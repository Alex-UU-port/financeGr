package alexuuport;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void constructorAndGettersShouldReturnCorrectValues() {
        LocalDate date = LocalDate.of(2024, 1, 10);
        Category category = new Category("Еда", 1000);

        Operation operation = new Operation(false, category, 150.50, date);

        assertFalse(operation.getIsIncome());
        assertEquals(150.50, operation.getAmount());
        assertEquals(category, operation.getCategory());
        assertEquals(date, operation.getDate());
    }

    @Test
    void printOperationShouldPrintCorrectOutput() {
        Category category = new Category("Еда");
        Operation operation = new Operation(false, category, 500, LocalDate.of(2024, 1, 10));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));

        operation.printOperation();

        String output = out.toString(StandardCharsets.UTF_8);

        assertTrue(output.contains("Еда"));
        assertTrue(output.contains("500"));
        assertTrue(output.contains("10-01-2024"));
    }
}