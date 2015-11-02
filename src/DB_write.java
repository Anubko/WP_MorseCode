//STEP 1. Import required packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DB_write {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   Scanner user_input = new Scanner(System.in);
   String fn = null, ln = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      //INSERT INTO `MorseCode`.`users` (`first_name`, `last_name`) VALUES ('wayne', 'seymour');
      
      stmt = conn.createStatement();
      ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM MorseCode.users");

      // Get the number of rows from the result set
      resultSet.next();
      int rowcount = resultSet.getInt(1);
      int user_id = rowcount+1;
      
      System.out.println("Inserting records into the table...");
      System.out.println("Taking user input...");
      
      System.out.println("Enter first name:");
      fn = user_input.next();
      
      System.out.println("Enter last name:");
      ln = user_input.next();
      
      stmt = conn.createStatement();
      
      //String sql = 	"INSERT INTO MorseCode.users VALUES ('"+ user_id +"', '"+ fn +"', '"+ ln +"')";
      String sql = 	"INSERT INTO MorseCode.users (first_name, last_name) VALUES ('"+ fn +"', '"+ ln +"')";
      
      stmt.executeUpdate(sql);
      
      System.out.println("Records inserted succesfully.");

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
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end JDBCExample