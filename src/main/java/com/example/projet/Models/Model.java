package com.example.projet.Models;

import com.example.projet.Views.ViewFactory;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    // Client Data Section
    private final Client client;
    private boolean clientLoginSuccessFlag;
    private final ObservableList<Transaction> latestTransactions;
    private final ObservableList<Transaction> allTransactions;
    private final Request request;
    // Admin Data Section
    private final Admin admin;
    private boolean adminLoginSuccessFlag;
    private final ObservableList<Client> clients;
    private final ObservableList<Request> requests;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        // Client Data Section
        this.clientLoginSuccessFlag = false;
        this.client = new Client("", "", "", null, null, null);
        this.latestTransactions = FXCollections.observableArrayList();
        this.allTransactions = FXCollections.observableArrayList();
        this.request = new Request("", "", "", null, null);
        // Admin Data Section
        this.admin = new Admin("");
        this.adminLoginSuccessFlag = false;
        this.clients = FXCollections.observableArrayList();
        this.requests = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    /*
    * Client Method Section
    */

    public boolean getClientLoginSuccessFlag() {
        return this.clientLoginSuccessFlag;
    }

    public void setClientLoginSuccessFlag(boolean flag) {
        this.clientLoginSuccessFlag = flag;
    }

    public Client getClient() {
        return this.client;
    }

    public Request getRequest() {
        return this.request;
    }

    public Admin getAdmin(){
        return this.admin;
    }

    public void evaluateClientCred(String pAddress, String password){
//        CheckingAccount checkingAccount;
//        SavingsAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getClientData(pAddress, password);
        try{
            if(resultSet.isBeforeFirst()){
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastNameProperty().set(resultSet.getString("LastName"));
                this.client.pAddressProperty().set(resultSet.getString("Email"));
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                this.client.dateProperty().set(date);
//                checkingAccount = getCheckingAccount(pAddress);
//                savingsAccount = getSavingsAccount(pAddress);
//                this.client.checkingAccountProperty().set(checkingAccount);
//                this.client.savingsAccountProperty().set(savingsAccount);
                this.clientLoginSuccessFlag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void prepareTransactions(ObservableList<Transaction> transactions, int limit){
        ResultSet resultSet = databaseDriver.getTransactions(this.client.pAddressProperty().get(), limit);
        try {
            while(resultSet.next()){
                String sender = resultSet.getString("Sender");
                String receiver = resultSet.getString("Receiver");
                double amount = resultSet.getDouble("Amount");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                String message = resultSet.getString("Message");
                transactions.add(new Transaction(sender, receiver, amount, date, message));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setLatestTransactions(){
        prepareTransactions(this.latestTransactions, 6);
    }

    public ObservableList<Transaction> getLatestTransactions() {
        return latestTransactions;
    }

    public void setAllTransactions(){
        prepareTransactions(this.allTransactions, -1);
    }

    public ObservableList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    /*
    * Admin Method Section
    */

    public boolean getAdminLoginSuccessFlag() {
        return this.adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(boolean flag) {
        this.adminLoginSuccessFlag = flag;
    }

    public void evaluateAdminCred(String username, String password){
        ResultSet resultSet = databaseDriver.getAdminData(username, password);
        try{
            if(resultSet.isBeforeFirst()){
                this.admin.usernameProperty().set(resultSet.getString("Username"));
                this.adminLoginSuccessFlag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public ObservableList<Request> getRequests() {
        return requests;
    }

    public void setClients() {
//        CheckingAccount checkingAccount;
//        SavingsAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getAllClientsData();
        try {
            while (resultSet.next()) {
                String fName = resultSet.getString("FirstName");
                String lName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
//                checkingAccount = getCheckingAccount(email);
//                savingsAccount = getSavingsAccount(email);
                clients.add(new Client(fName, lName, email, null, null, date));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setRequests() {
        ResultSet resultSet = databaseDriver.getAllRequestsData();
        try {
            while (resultSet.next()) {
                String sender = resultSet.getString("Sender");
                String receiver = resultSet.getString("Receiver");
                String numRequest = resultSet.getString("NumRequest");
                String[] dateParts = resultSet.getString("Date").split("-");
                String message = resultSet.getString("Message");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                requests.add(new Request(sender, receiver, numRequest, date, message));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Client> searchClient(String email){
        ObservableList<Client> searchResults = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchClient(email);
        try {
            CheckingAccount checkingAccount = getCheckingAccount(email);
            SavingsAccount savingsAccount = getSavingsAccount(email);
            String fName = resultSet.getString("FirstName");
            String lName = resultSet.getString("LastName");
            String[] dateParts = resultSet.getString("Date").split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
            searchResults.add(new Client(fName, lName, email, checkingAccount, savingsAccount, date));
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchResults;
    }

    /*
    * Utility Methods Section
    */

    public CheckingAccount getCheckingAccount(String Email) {
        CheckingAccount account = null;
        ResultSet resultSet = databaseDriver.getCheckingAccountData(Email);
        try {
            String num = resultSet.getString("AccountNumber");
            int tLimit = (int) resultSet.getDouble("TransactionLimit");
            double balance = resultSet.getDouble("Balance");
            account = new CheckingAccount(Email, num, balance, tLimit);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    public SavingsAccount getSavingsAccount(String Email) {
        SavingsAccount sAccount = null;
        ResultSet resultSet = databaseDriver.getSavingsAccountData(Email);
        try {
            String num = resultSet.getString("AccountNumber");
            double wLimit = resultSet.getDouble("WithdrawalLimit");
            double balance = resultSet.getDouble("Balance");
            sAccount = new SavingsAccount(Email, num, balance, wLimit);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sAccount;
    }

}
