package alexuuport;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
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
        LocalDate date = LocalDate.of(2024, 5, 3);
        Category category = new Category("Зарплата");

        Operation operation = new Operation(true, category,5000.0, date);

        operation.printOperation();

        String output = outContent.toString();

        assertTrue(output.contains("Тип операции: Доход"));
        assertTrue(output.contains("Сумма операции: 5000.0"));
        assertTrue(output.contains("Категория: Зарплата"));
        assertTrue(output.contains("Дата совершения операции: 03-05-2024"));
    }
}
