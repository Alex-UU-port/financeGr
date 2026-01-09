package alexuuport;

import java.util.Objects;

public class Category {
    private String name;
    private double budget;

    public Category(String name) {
        this.name = name;
    }

    public Category() {}

    public Category(String name, double budget) {
        this.name = name;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }


    public void printCategory() {
        System.out.println("Имя категории: " + name + "\t\tМесячный бюджет: " + budget);
    }

    @Override
    public boolean equals(Object obj) {
        // 1. Если сравниваем объект сам с собой — они равны
        if (this == obj) {
            return true;
        }

        // 2. Если передан null — объекты не равны
        if (obj == null) {
            return false;
        }

        // 3. Если классы разные — объекты не равны
        // Используем getClass(), а не instanceof,
        // чтобы избежать равенства с наследниками
        if (getClass() != obj.getClass()) {
            return false;
        }

        // 4. Безопасно приводим объект к Category
        Category other = (Category) obj;

        // 5. Сравниваем значимые поля
        // В нашем случае — только name
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        // hashCode ОБЯЗАН использовать те же поля,
        // что и equals()
        // Иначе коллекции (HashMap, HashSet) будут работать неправильно
        return Objects.hash(name);
    }

}
