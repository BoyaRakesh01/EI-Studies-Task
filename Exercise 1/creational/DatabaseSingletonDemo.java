class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private String connection;

    private DatabaseConnectionManager() {}

    public static synchronized DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public String connect(String connectionString) {
        if (connection == null) {
            System.out.println("Connecting to database with: " + connectionString);
            connection = "Connected: " + connectionString;
        }
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            System.out.println("Disconnecting from database");
            connection = null;
        } else {
            System.out.println("No active connection to disconnect");
        }
    }
}

public class DatabaseSingletonDemo {
    public static void main(String[] args) {
        DatabaseConnectionManager manager1 = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager manager2 = DatabaseConnectionManager.getInstance();

        System.out.println(manager1 == manager2);

        String connection1 = manager1.connect("mysql://localhost:3306/mydb");
        System.out.println(connection1);

        String connection2 = manager2.connect("mysql://localhost:3306/mydb");
        System.out.println(connection2);

        manager1.disconnect();
        manager2.disconnect();
    }
}
