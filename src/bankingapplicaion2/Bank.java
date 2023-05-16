package bankingapplicaion2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Bank {
    
    private final String name;
    
    
    public Bank(String name){
        this.name = name;}
    
    public void listAccount(){
        Connection connection = BankingConnection.connec();
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM account";
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next())
                System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3));
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void OpenAccount(int accountNumber ,String accountName, double balance){
        Connection connection = BankingConnection.connec();
        String sql = "INSERT INTO account(accNumber,accName,accBalance) "
                        + "VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.setString(2, accountName);
            preparedStatement.setDouble(3, balance);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void CloseAccount(int accountNumber){
        Connection connection = BankingConnection.connec();
        String sql = "DELETE FROM account WHERE accNumber=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DepositMoney(int accountNumber ,double amount){
        Account account = getAccount(accountNumber);
        account.deposit(amount);
        Connection connection = BankingConnection.connec();
        String sql = "UPDATE account SET accBalance=? WHERE accNumber=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void WithdrawMoney(int accountNumber ,double amount){
        Account account = getAccount(accountNumber);
        account.withdraw(amount);
        Connection connection = BankingConnection.connec();
        String sql = "UPDATE account SET accBalance=? WHERE accNumber=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Account getAccount(int accountNumber){
        Account account = null;
        Connection connection = BankingConnection.connec();
        String sql = "SELECT * FROM account WHERE accNumber=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            account =new Account(result.getInt(1),result.getString(2),result.getDouble(3));
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

}
