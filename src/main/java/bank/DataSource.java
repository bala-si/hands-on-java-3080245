package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  
  // connect to data source
  public static Connection connect(){
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;

    try{
      connection = DriverManager.getConnection(db_file);
      System.out.println("We are connected");
    }catch(SQLException e){
      e.printStackTrace();
    }

    return connection;    
  }

  // connect customer from data source
  public static Customer getCustomer(String userName){
    String sql = "select * from customers where username = ?";
    Customer customer = null;
    // try with the resources allows us to pass closable resaurce,
    // Java will automatically close that resource
    // after the tyr block has been completed
    try(
      Connection connection = connect();
      // object for handling the sql statement
      PreparedStatement statement = connection.prepareStatement(sql);
      ){
        // set the value for placeholders with query
        statement.setString(1, userName);
        // execute query
        try(ResultSet resultSet = statement.executeQuery()){
          customer = new Customer(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("userName"),
            resultSet.getString("password"),
            resultSet.getInt("account_id"));
        }
    }
    catch(SQLException e){
      e.printStackTrace();
    }
    
    return customer;


  }

  public static void main(String[] args){
    Customer customer = getCustomer("twest8o@friendfeed.com");
    System.out.println(customer.getName());
  }
}
