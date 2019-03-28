package wctc.edu.eligrow;

import java.sql.*;

public class CreateStoreDB
{
    public CreateStoreDB()
    {

        try
        {
            // Create a named constant for the URL.
            // NOTE: This value is specific for Java DB.
            final String DB_URL = "jdbc:derby:CamoDB;create=true";

            // Create a connection to the database.
            Connection conn =
                    DriverManager.getConnection(DB_URL);

            // If the DB already exists, drop the tables.
            dropTables(conn);

            // Build the Coffee table.
            buildCoffeeTable(conn);

            // Build the Customer table.
            buildCustomerTable(conn);

            // Close the connection.
            conn.close();
        } catch (Exception e)
        {
            System.out.println("Error Creating the Camo Table");
            System.out.println(e.getMessage());
        }

    }

    /**
     * The dropTables method drops any existing
     * in case the database already exists.
     */
    public static void dropTables(Connection conn)
    {
        System.out.println("Checking for existing tables.");

        try
        {
            // Get a Statement object.
            Statement stmt = conn.createStatement();

            try
            {
                // Drop the Customer table.
                stmt.execute("DROP TABLE Customer");
                System.out.println("Customer table dropped.");
            } catch (SQLException ex)
            {
                // No need to report an error.
                // The table simply did not exist.
            }

            try
            {
                // Drop the Coffee table.
                stmt.execute("DROP TABLE Products");
                System.out.println("Catalog table dropped.");
            } catch (SQLException ex)
            {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex)
        {
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * The buildCoffeeTable method creates the
     * Coffee table and adds some rows to it.
     */
    public static void buildCoffeeTable(Connection conn)
    {
        try
        {
            // Get a Statement object.
            Statement stmt = conn.createStatement();

            // Create the table.
            stmt.execute("CREATE TABLE Products (" +
                    "Description CHAR(100), " +
                    "ProdNum CHAR(10) NOT NULL PRIMARY KEY, " +
                    "Price DOUBLE, " +
                    "ImgUrl CHAR(100) )");

            // Insert row #1.
            stmt.execute("INSERT INTO Products VALUES ( " +
                    "'Blue Sapphire Image Headlamp', " +
                    "'001', " +
                    "59.99, " +
                    "'https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/LED-headlamp.jpg/1200px-LED-headlamp.jpg' )");

            // Insert row #1.
            stmt.execute("INSERT INTO Products VALUES ( " +
                    "'Lizard Gear Black Mamba', " +
                    "'002', " +
                    "14.99, " +
                    "'https://upload.wikimedia.org/wikipedia/commons/8/85/550Cord_Bracelet.JPG' )");

            // Insert row #2.
            stmt.execute("INSERT INTO Products VALUES ( " +
                    "'Toronto Blue Bird Knife', " +
                    "'003', " +
                    "99.99, " +
                    "'https://svgsilh.com/svg_v2/159519.svg' )");

            System.out.println("Catalog table created.");
        } catch (SQLException ex)
        {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * The buildCustomerTable method creates the
     * Customer table and adds some rows to it.
     */
    public static void buildCustomerTable(Connection conn)
    {
        try
        {
            // Get a Statement object.
            Statement stmt = conn.createStatement();

            // Create the table.
            stmt.execute("CREATE TABLE Customer" +
                    "( CustomerNumber CHAR(10) NOT NULL PRIMARY KEY, " +
                    "  Name CHAR(25)," +
                    "  Address CHAR(25)," +
                    "  City CHAR(12)," +
                    "  State CHAR(2)," +
                    "  Zip CHAR(5) )");

            // Add some rows to the new table.
            stmt.executeUpdate("INSERT INTO Customer VALUES" +
                    "('C-001', 'Ezra Lowe', '17 N. Main Street'," +
                    " 'Asheville', 'NC', '55515')");

            stmt.executeUpdate("INSERT INTO Customer VALUES" +
                    "('C-002', 'Christian Lewis'," +
                    " '110 E. Main Street'," +
                    " 'Canton', 'NC', '55555')");

            stmt.executeUpdate("INSERT INTO Customer VALUES" +
                    "('C-003', 'Byron Drake', '101 Center Plaza'," +
                    " 'Waynesville', 'NC', '55516')");

            System.out.println("Customer table created.");
        } catch (SQLException ex)
        {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

}