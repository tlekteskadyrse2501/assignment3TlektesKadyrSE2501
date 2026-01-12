import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        // 1. Создание объектов и работа со списками
        Donor donor1 = new Donor("Ali", "ali@mail.com");
        Donor donor2 = new Donor("Dana", "dana@mail.com");

        Charity charity1 = new Charity("Help Children", "Helping children in Kazakhstan");
        Charity charity2 = new Charity("Green Earth", "Environmental protection");

        Donation donation1 = new Donation(donor1, charity1, 10000);
        Donation donation2 = new Donation(donor2, charity1, 15000);

        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(donation1);
        donations.add(donation2);

        // Полиморфизм / Вывод инфо
        donor1.printInfo();
        donor2.printInfo();

        // Фильтрация
        System.out.println("\nFiltered (amount > 12000):");
        for (Donation d : donations) {
            if (d.getAmount() > 12000) {
                System.out.println(d);
            }
        }

        // Сортировка
        Collections.sort(donations);
        System.out.println("\nSorted donations:");
        for (Donation d : donations) {
            System.out.println(d);
        }

        // 2. РАБОТА С БАЗОЙ ДАННЫХ
        System.out.println("\n--- Database Operations ---");
        try {
            // АВТОМАТИЧЕСКОЕ СОЗДАНИЕ ТАБЛИЦ (если их еще нет)
            DonorDAO.createTables();

            Donor dbDonor = new Donor("Database User", "db@mail.com");

            // Вызов методов вашего DAO
            DonorDAO.insertDonor(dbDonor);
            DonorDAO.readDonors();

            // Мы используем ID 1 для примера обновления
            DonorDAO.updateDonorEmail(1, "updated@mail.com");

        } catch (Exception e) {
            System.err.println("Ошибка при работе с БД: " + e.getMessage());
            e.printStackTrace();
        }

    } // Конец метода main
} // Конец класса Main