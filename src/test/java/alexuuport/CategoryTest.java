package alexuuport;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

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
    void constructorWithNameShouldSetNameAndZeroBudget() {
        Category category = new Category("Еда");

        assertEquals("Еда", category.getName());
        assertEquals(0.0, category.getBudget());
    }

    @Test
    void constructorWithNameAndBudgetShouldSetAllFields() {
        Category category = new Category("Транспорт", 3000.0);

        assertEquals("Транспорт", category.getName());
        assertEquals(3000.0, category.getBudget());
    }

    @Test
    void printCategoryShouldPrintCorrectOutput() {
        Category category = new Category("Еда", 1000);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));

        category.printCategory();

        String output = out.toString(StandardCharsets.UTF_8);

        assertTrue(output.contains("Имя категории: Еда"));
        assertTrue(output.contains("Месячный бюджет: 1000"));
    }

    // ===== equals / hashCode tests =====

    @Test
    void equalsShouldBeTrueForSameObject() {
        Category category = new Category("Еда", 1000.0);

        assertEquals(category, category);
    }

    @Test
    void equalsShouldReturnTrueForSameName() {
        Category c1 = new Category("Еда", 1000.0);
        Category c2 = new Category("Еда", 5000.0);

        assertEquals(c1, c2);
    }

    @Test
    void equalsShouldReturnFalseForDifferentName() {
        Category c1 = new Category("Еда");
        Category c2 = new Category("Транспорт");

        assertNotEquals(c1, c2);
    }

    @Test
    void equalsShouldReturnFalseForNullAndDifferentClass() {
        Category category = new Category("Еда");

        assertNotEquals(category, null);
        assertNotEquals(category, "Еда");
    }

    @Test
    void hashCodeShouldBeEqualForEqualObjects() {
        Category c1 = new Category("Еда", 1000.0);
        Category c2 = new Category("Еда", 5000.0);

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}