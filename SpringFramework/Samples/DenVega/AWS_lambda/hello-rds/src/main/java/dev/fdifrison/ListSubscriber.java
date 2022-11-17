package dev.fdifrison;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// in this example we don't need the aws context and the logger, so we don't implement aws requestHandler
public class ListSubscriber {

    private final DataSourceProperties db;

    public ListSubscriber() {
        // we need also a no arg constructor because aws has no way to pass arguments
        // calling this, we will automatically set a request for environment variables
        this.db = new DataSourceProperties();
    }

    public ListSubscriber(DataSourceProperties db) {
        this.db = db;
    }

    public List<String> handleRequest() {
        List<String> subscribers;

        try (Connection conn = DriverManager.getConnection(db.getJdbcUrl(), db.getUser(), db.getPassword())) {
            subscribers = new ArrayList<>();
            if (!conn.isValid(0)) {
                System.out.println("Unable to connect to: " + db.getJdbcUrl());
                System.exit(0);
            }
            PreparedStatement select = conn.prepareStatement("select * from subscribers");
            ResultSet rs = select.executeQuery();

            while ((rs.next())) {
                String email = rs.getString("email");
                System.out.println(email);
                subscribers.add(email);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subscribers;
    }
}
