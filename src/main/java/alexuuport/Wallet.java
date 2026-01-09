package alexuuport;

import java.util.ArrayList;
import java.util.List;


public class Wallet {

    private List<Operation> operations = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    //private double balance = countBalance();

    public Wallet() {

        //в случае создания нового пользователя, несколько категорий добавляются по умолчанию
        Category salary = new Category("Зарплата");
        Category bonus = new Category("Бонус");
        Category food = new Category("Еда", 10000);
        Category medicine = new Category("Медицина", 10000);

        categories.add(salary);
        categories.add(bonus);
        categories.add(food);
        categories.add(medicine);

    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Operation> getOperation() {
        return operations;
    }

    public double countBalance() {
        double balance = 0;
        for (Operation operation : operations) {
            if (operation.getIsIncome()) {
                balance += operation.getAmount();
            } else {
                balance -= operation.getAmount();
            }
        }
        return balance;
    }

}
