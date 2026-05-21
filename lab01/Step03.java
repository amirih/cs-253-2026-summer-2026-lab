package lab01;

public class Step03 {
    // Step 03: constructors, fields, and encapsulation
    // encapsulation: hiding internal details and providing public methods to access
    // them
    static class BankAccount {
        private String owner;
        private double balance;

        BankAccount(String owner, double openingBalance) {
            this.owner = owner;
            this.balance = openingBalance;
        }

        String getOwner() {
            return owner;
        }

        double getBalance() {
            return balance;
        }

        void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }

        boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("Ava", 100.0);
        account.deposit(50.0);
        account.withdraw(30.0);

        System.out.println(account.getOwner() + " has $" + account.getBalance());
    }
}
