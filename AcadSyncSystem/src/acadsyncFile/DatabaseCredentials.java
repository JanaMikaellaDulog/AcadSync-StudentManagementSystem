package acadsyncFile;

public class DatabaseCredentials {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/acadsyncdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Jana@8154";

    // Static getter methods to retrieve the credentials
    public static String getDBURL() {
        return DB_URL;
    }

    public static String getDBUSER() {
        return DB_USER;
    }

    public static String getDBPASS() {
        return DB_PASSWORD;
    }
}