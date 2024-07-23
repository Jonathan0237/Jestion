package com.example.projet.Models;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseDriver {
    private Connection conn;

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:Projet.db");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * Client Section
    */

    public ResultSet getClientData(String pAddress, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM clients WHERE Email='"+pAddress+"' AND Password='"+password+"';");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getTransactions(String email, int limit) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM transactions WHERE Sender ='"+email+"' OR Receiver ='"+email+"' LIMIT "+limit+";");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getRequest(String numRequest, int limit) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM request WHERE Sender ='"+numRequest+"' OR Receiver ='"+numRequest+"' LIMIT "+limit+";");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    //Method returns savings account balance
    public double getSavingsAccountBalance(String email) {
        Statement statement;
        ResultSet resultSet;
        double balance = 0;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM savingsAccounts WHERE Owner='"+email+"';");
            balance = resultSet.getDouble("Balance");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    // Method to either add or subtract from balance given operation
    public void updateBalance(String email, double amount, String operation) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM savingsAccounts WHERE Owner='"+email+"';");
            double newBalance;
            if (operation.equals("add")) {
                newBalance = resultSet.getDouble("Balance") + amount;
                statement.executeUpdate("UPDATE savingsAccounts SET Balance='"+newBalance+"' WHERE Owner='"+email+"';");
            }else {
                if (resultSet.getDouble("Balance") >= amount) {
                    newBalance = resultSet.getDouble("Balance") - amount;
                    statement.executeUpdate("UPDATE savingsAccounts SET Balance='"+newBalance+"' WHERE Owner='"+email+"';");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Creates and records new transaction
    public void newTransaction(String sender, String receiver, double amount, String message) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = this.conn.createStatement();
            LocalDate date = LocalDate.now();
            statement.executeUpdate("INSERT INTO " + "transactions (Sender, Receiver, Amount, Date, Message) " +
                    "VALUES ('"+sender+"', '"+receiver+"', '"+amount+"', '"+date+"', '"+message+"')");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void newRequest(String sender, String receiver, String numRequest, String message) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = this.conn.createStatement();
            LocalDate date = LocalDate.now();
            statement.executeUpdate("INSERT INTO " + "request (Sender, Receiver, NumRequest, Date, Message) " +
                    "VALUES ('"+sender+"', '"+receiver+"', '"+numRequest+"', '"+date+"', '"+message+"')");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*
    * Admin Section
    */

    public ResultSet getAdminData(String username, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM admins WHERE Username='"+username+"' AND Password='"+password+"';");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void createClient(String fName, String lName, String Email, String password, LocalDate date) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " + "clients (FirstName, LastName, Email, Password, Date)" +
                    "VALUES ('"+fName+"', '"+lName+"', '"+Email+"', '"+password+"', '"+date.toString()+"');");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteClient(String email) {
        try (PreparedStatement statement = this.conn.prepareStatement("DELETE FROM clients WHERE Email = ?")) {
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClient(String fName, String lName, String Email) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("UPDATE clients SET FirstName = '"+fName+"', LastName = '"+lName+"', Email = '"+Email+"' WHERE Email = '"+Email+"';");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createRequest(String fName, String lName, String num, LocalDate date, String message) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " + "request (Sender, Receiver, NumRequest, Date, Message)" +
                    "VALUES ('"+fName+"', '"+lName+"', '"+num+"', '"+date.toString()+"', '"+message+"');");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createCheckingAccount(String owner, String number, double tLimit, double balance) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " + "checkingAccounts (Owner, AccountNumber, TransactionLimit, Balance)" +
                    " VALUES ('"+owner+"', '"+number+"', '"+tLimit+"', '"+balance+"');");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createSavingsAccount(String owner, String number, double wLimit, double balance) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " + "savingsAccounts (Owner, AccountNumber, WithdrawalLimit, Balance)" +
                    " VALUES ('"+owner+"', '"+number+"', '"+wLimit+"', '"+balance+"');");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet getAllClientsData() {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM clients;");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getAllRequestsData() {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM request;");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public void depositSavings(String pAddress, double amount) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("UPDATE savingsAccounts SET Balance = "+amount+" WHERE Owner = '"+pAddress+"';");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    * Utility Methods
    */


    public ResultSet searchClient(String pAddress){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM clients WHERE Email='"+pAddress+"';");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public int getLastClientsId(){
        Statement statement;
        ResultSet resultSet;
        int id = 0;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sqlite_sequence WHERE name='clients';");
            id = resultSet.getInt("seq");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }

    public int getLastNumberId(){
        Statement statement;
        ResultSet resultSet;
        int id = 0;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sqlite_sequence WHERE name='request';");
            id = resultSet.getInt("seq");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }

    public ResultSet getCheckingAccountData(String Email) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM checkingAccounts WHERE Owner='"+Email+"';");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getSavingsAccountData(String Email) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM savingsAccounts WHERE Owner='"+Email+"';");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
