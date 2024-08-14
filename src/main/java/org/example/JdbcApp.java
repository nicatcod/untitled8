package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class JdbcApp {
    private static final String CEDVEL_YARAT = "CREATE TABLE IF NOT EXISTS students (" +
            "id SERIAL PRIMARY KEY, " +
            "name VARCHAR(100), " +
            "surname VARCHAR(100), " +
            "age INT" +
            ");";
    private static final String TELEBE_ELAVE_ET = "INSERT INTO students (name, surname, age) VALUES (?, ?, ?);";
    private static final String ADA_GORE_TAP = "SELECT * FROM students WHERE name = ?;";
    private static final String CEDVEL = "SELECT * FROM students;";
    private static final String ID_ELAVE_AT = "UPDATE students SET name = ?, surname = ?, age = ? WHERE id = ?;";
    private static final String ID_SIL = "DELETE FROM students WHERE id = ?;";
    public static void main(String[] args) {
        Table1();
        Telebe("Nicat", "Alverdiyev", 25);
        Telebe("Fuad", "Isayev", 23);
        Telebe("Telman", "Dadaşov", 28);
        Telebe("Qədir", "İmamov", 33);
        Telebe("İzzət", "Səryev", 27);
        Optional<Student> telebe = AdaGoreTap("Nicat");
        telebe.ifPresent(System.out::println);
        List<Student> telebeler = cedvel();
        telebeler.forEach(System.out::println);
        TelebeniIdYenile(1L, new Student("Nicat", "Məmmədov", 26));
        TelebeniIdGoreSil(3L);
    }
    public static void Table1() {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres",
                "postgres",
                "1111");
             Statement stmt = conn.createStatement()) {
            stmt.execute(CEDVEL_YARAT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void Telebe(String ad, String soyad, int yas) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres",
                "postgres",
                "1111")) {
            PreparedStatement preparedStatement = conn.prepareStatement(TELEBE_ELAVE_ET);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setInt(3, yas);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Optional<Student> AdaGoreTap(String ad) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres",
                "postgres",
                "1111")) {
            PreparedStatement preparedStatement = conn.prepareStatement(ADA_GORE_TAP);
            preparedStatement.setString(1, ad);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                return Optional.of(new Student(id, name, surname, age));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public static List<Student> cedvel() {
        List<Student> telebeler = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres",
                "postgres",
                "1111")) {
            PreparedStatement preparedStatement = conn.prepareStatement(CEDVEL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                telebeler.add(new Student(id, name, surname, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return telebeler;
    }
    public static void TelebeniIdYenile(Long id, Student telebe) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres",
                "postgres",
                "1111")) {
            PreparedStatement preparedStatement = conn.prepareStatement(ID_ELAVE_AT);
            preparedStatement.setString(1, telebe.getName());
            preparedStatement.setString(2, telebe.getSurname());
            preparedStatement.setInt(3, telebe.getAge());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Tələbəni ID ilə sil
    public static void TelebeniIdGoreSil(Long id) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres",
                "postgres",
                "1111")) {
            PreparedStatement preparedStatement = conn.prepareStatement(ID_SIL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}