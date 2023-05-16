package bankingapplicaion2;

import java.util.Random;
import java.util.Scanner;

public class BankingApplicaion2 {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Bank bank = new Bank("Tako Bank");

        int option = 0 ,accountNumber;
        String accountName;
        double amount ,balance = 0;
        
        while(option !=6){
            System.out.println("Main Menu");
            System.out.println("1. Display All Account");
            System.out.println("2. Open New Account");
            System.out.println("3. Cloce Existing Account");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Exit");
            System.out.println();

            System.out.print("Enter Your choice: ");      
            option = scan.nextInt();
            scan.nextLine();
            
            switch (option) {
                case 1:
                    bank.listAccount();
                    break;
                case 2:
                    accountNumber = genAccounNumber();
                    System.out.print("Enter Account Name: ");
                    accountName = scan.nextLine();
                    System.out.print("Enter Initial balance: ");
                    balance = scan.nextDouble();
                    bank.OpenAccount(accountNumber, accountName, balance);
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    scan.nextLine();
                    bank.CloseAccount(accountNumber);
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter Amount: ");
                    amount = scan.nextDouble();

                    bank.DepositMoney(accountNumber, amount);
                    break;
                case 5:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter Amount: ");
                    amount = scan.nextDouble();
                    bank.WithdrawMoney(accountNumber, amount);
                    break;
            }
           System.out.println(); 
        }
        
    }
    public static int genAccounNumber(){
        Random rand = new Random();
        int accNumber = 100000 + rand.nextInt(900000);
        return accNumber;
    }
    
}
