package alexuuport;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    @Test
    void constructorShouldCreateDefaultCategories() {
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
    void getOperationShouldReturnEmptyListInitially() {
        Wallet wallet = new Wallet();

        List<Operation> operations = wallet.getOperation();

        assertNotNull(operations);
        assertTrue(operations.isEmpty());
    }

    @Test
    void countBalanceShouldReturnZeroWhenNoOperations() {
        Wallet wallet = new Wallet();

        assertEquals(0.0, wallet.countBalance());
    }

    @Test
    void countBalanceShouldIncreaseForIncomeOperation() {
        Wallet wallet = new Wallet();
        Category salary = new Category("Зарплата");

        wallet.getOperation().add(
                new Operation(true, salary, 5000.0, LocalDate.now())
        );

        assertEquals(5000.0, wallet.countBalance());
    }

    @Test
    void countBalanceShouldDecreaseForExpenseOperation() {
        Wallet wallet = new Wallet();
        Category food = new Category("Еда");

        wallet.getOperation().add(
                new Operation(false, food, 1500.0, LocalDate.now())
        );

        assertEquals(-1500.0, wallet.countBalance());
    }

    @Test
    void countBalanceShouldHandleMultipleOperationsCorrectly() {
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