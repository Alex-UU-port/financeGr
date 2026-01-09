package alexuuport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private User user;
    private Category food;
    private Category salary;

    @BeforeEach
    void setUp() {
        user = new User("test", "1234");

        food = new Category("Еда", 10000);
        salary = new Category("Зарплата");

        Wallet wallet = user.getWallet();
        wallet.getCategories().clear();
        wallet.getCategories().add(food);
        wallet.getCategories().add(salary);

        wallet.getOperation().add(
                new Operation(false, food, 5000, LocalDate.of(2025, 1, 10))
        );
        wallet.getOperation().add(
                new Operation(false, food, 3000, LocalDate.of(2025, 1, 15))
        );
        wallet.getOperation().add(
                new Operation(true, salary, 20000, LocalDate.of(2025, 1, 5))
        );
        wallet.getOperation().add(
                new Operation(false, food, 1000, LocalDate.of(2025, 2, 5))
        );
    }

    // ---------- toCountBalance ----------

    @Test
    void toCountBalance_shouldCalculateCorrectBalance() {
        double balance = Main.toCountBalance(user.getWallet().getOperation());
        assertEquals(11000, balance);
    }

    @Test
    void toCountBalance_emptyList_shouldReturnZero() {
        assertEquals(0, Main.toCountBalance(List.of()));
    }

    // ---------- toFilter(year, month) ----------

    @Test
    void toFilter_shouldReturnOperationsByYearAndMonth() {
        List<Operation> result = Main.toFilter(user, 2025, 1);

        assertEquals(3, result.size());
        assertTrue(result.stream().allMatch(
                o -> o.getDate().getYear() == 2025 &&
                        o.getDate().getMonthValue() == 1
        ));
    }

    @Test
    void toFilter_noOperationsForMonth_shouldReturnEmptyList() {
        List<Operation> result = Main.toFilter(user, 2024, 12);
        assertTrue(result.isEmpty());
    }

    // ---------- toFilter(year, month, category) ----------

    @Test
    void toFilter_withCategory_shouldReturnOnlyThatCategory() {
        List<Operation> result =
                Main.toFilter(user, 2025, 1, "Еда");

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(
                o -> o.getCategory().getName().equals("Еда")
        ));
    }

    @Test
    void toFilter_withCategory_incomeShouldBeExcluded() {
        List<Operation> result =
                Main.toFilter(user, 2025, 1, "Зарплата");

        assertEquals(1, result.size());
        assertTrue(result.get(0).getIsIncome());
    }

    @Test
    void toFilter_unknownCategory_shouldReturnEmptyList() {
        List<Operation> result =
                Main.toFilter(user, 2025, 1, "Транспорт");

        assertTrue(result.isEmpty());
    }
}