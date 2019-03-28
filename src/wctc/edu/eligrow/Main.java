package wctc.edu.eligrow;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        CreateStoreDB cc = new CreateStoreDB();
        outputDB();
        addContent();
        System.out.println("==========================");
        outputDB();
    }

    public static void addContent() {
        final String DB_URL = "jdbc:derby:CamoDB";
        Statement stmt = null;
        Connection conn = null;
        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO Customer " +
                    "VALUES ('C-004', 'Ryan Lowe', '17 N. Main Street', 'Asheville', 'NC', '55515')";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
            //Clean-up environment
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }


    public static void outputDB() {
        final String DB_URL = "jdbc:derby:CamoDB";
        Statement stmt = null;
        Connection conn = null;
        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT ProdNum, Description, Price FROM Products";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                //Retrieve by column name
                String id  = rs.getString("ProdNum");
                double price = rs.getDouble("Price");
                String description = rs.getString("Description");

                //Display values
                System.out.print("ID: " + id.trim());
                System.out.print(", Price: " + price);
                System.out.println(", Description: " + description);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}

