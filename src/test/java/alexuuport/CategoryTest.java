package alexuuport;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

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
    void constructorWithName_shouldSetNameAndZeroBudget() {
        Category category = new Category("Еда");

        assertEquals("Еда", category.getName());
        assertEquals(0.0, category.getBudget());
    }

    @Test
    void constructorWithNameAndBudget_shouldSetAllFields() {
        Category category = new Category("Транспорт", 3000.0);

        assertEquals("Транспорт", category.getName());
        assertEquals(3000.0, category.getBudget());
    }

    @Test
    void printCategory_shouldPrintCorrectOutput() {
        Category category = new Category("Развлечения", 5000.0);

        category.printCategory();

        String output = outContent.toString();

        assertTrue(output.contains("Имя категории: Развлечения"));
        assertTrue(output.contains("Месячный бюджет: 5000.0"));
    }

    // ===== equals / hashCode tests =====

    @Test
    void equals_shouldBeTrueForSameObject() {
        Category category = new Category("Еда", 1000.0);

        assertEquals(category, category);
    }

    @Test
    void equals_shouldReturnTrueForSameName() {
        Category c1 = new Category("Еда", 1000.0);
        Category c2 = new Category("Еда", 5000.0);

        assertEquals(c1, c2);
    }

    @Test
    void equals_shouldReturnFalseForDifferentName() {
        Category c1 = new Category("Еда");
        Category c2 = new Category("Транспорт");

        assertNotEquals(c1, c2);
    }

    @Test
    void equals_shouldReturnFalseForNullAndDifferentClass() {
        Category category = new Category("Еда");

        assertNotEquals(category, null);
        assertNotEquals(category, "Еда");
    }

    @Test
    void hashCode_shouldBeEqualForEqualObjects() {
        Category c1 = new Category("Еда", 1000.0);
        Category c2 = new Category("Еда", 5000.0);

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}