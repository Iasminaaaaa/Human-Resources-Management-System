package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class BazaDate {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    /**
     * Metoda creează baza de date dacă nu există
     */
    public static void creareBazaDate() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            statement.executeUpdate(sql);
            System.out.println("Baza de date '" + DATABASE_NAME + "' a fost creată cu succes!");

        } catch (SQLException e) {
            System.out.println("Eroare la crearea bazei de date: " + e.getMessage());
        }
    }

    /**
     * Metoda realizează conectarea la baza de date
     * @return o conexiune la baza de date sau null dacă apare o eroare
     */
    public static Connection conectareBD() {
        String url = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
        
        try {
            Connection connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static void main(String[] args) {
        creareBazaDate(); // Crează baza de date dacă nu există
    }
}
