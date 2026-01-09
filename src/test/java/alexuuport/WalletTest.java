package alexuuport;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    @Test
    void constructor_shouldCreateDefaultCategories() {
        Wallet wallet = new Wallet();

        List<Category> categories = wallet.getCategories();

        assertNotNull(categories);
        assertEquals(4, categories.size());

        assertTrue(categories.contains(new Category("Зарплата")));
        assertTrue(categories.contains(new Category("Бонус")));
        assertTrue(categories.contains(new Category("Еда")));
        assertTrue(categories.contains(new Category("Медицина")));
    }

    @Test
    void getOperation_shouldReturnEmptyListInitially() {
        Wallet wallet = new Wallet();

        List<Operation> operations = wallet.getOperation();

        assertNotNull(operations);
        assertTrue(operations.isEmpty());
    }

    @Test
    void countBalance_shouldReturnZeroWhenNoOperations() {
        Wallet wallet = new Wallet();

        assertEquals(0.0, wallet.countBalance());
    }

    @Test
    void countBalance_shouldIncreaseForIncomeOperation() {
        Wallet wallet = new Wallet();
        Category salary = new Category("Зарплата");

        wallet.getOperation().add(
                new Operation(true, salary, 5000.0, LocalDate.now())
        );

        assertEquals(5000.0, wallet.countBalance());
    }

    @Test
    void countBalance_shouldDecreaseForExpenseOperation() {
        Wallet wallet = new Wallet();
        Category food = new Category("Еда");

        wallet.getOperation().add(
                new Operation(false, food, 1500.0, LocalDate.now())
        );

        assertEquals(-1500.0, wallet.countBalance());
    }

    @Test
    void countBalance_shouldHandleMultipleOperationsCorrectly() {
        Wallet wallet = new Wallet();

        wallet.getOperation().add(
                new Operation(true, new Category("Зарплата"), 10000.0, LocalDate.now())
        );
        wallet.getOperation().add(
                new Operation(false, new Category("Еда"), 3000.0, LocalDate.now())
        );
        wallet.getOperation().add(
                new Operation(false, new Category("Медицина"), 2000.0, LocalDate.now())
        );

        assertEquals(5000.0, wallet.countBalance());
    }
}