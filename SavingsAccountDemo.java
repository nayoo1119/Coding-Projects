import java.util.Scanner;

class BankAccount {

        protected double balance;
        protected int noOfDeposits;
        protected int noOfWithdrawls;
        protected float annualInterestRate;

        BankAccount(double balance, float rate) {
                this.balance = balance;
                this.annualInterestRate = rate;
        }

        public void deposit(double amount) {
                this.balance = this.balance + amount;
                this.noOfDeposits++;
        }

        public void withdraw(double amount) {
                this.balance = this.balance - amount;
                this.noOfWithdrawls++;
        }
        public void chargeFee() {
            this.balance = this.balance - 1;
        }

        private void calcInterest() {
                float monthlyInterestRate = this.annualInterestRate / 100 / 12;
                double monthlyInterest = this.balance * monthlyInterestRate;

                this.balance = this.balance + monthlyInterest;
                this.balance = Math.round(this.balance * 100.0) / 100.0;

        }

        public void monthlyProcess() {
                calcInterest();
                this.noOfDeposits = 0;
                this.noOfWithdrawls = 0;
        }

}

public class SavingsAccountDemo extends BankAccount {

        boolean status;

        SavingsAccountDemo(double balance, float rate) {
                super(balance, rate);
        }
        public void setStatus(boolean status) {
            this.status = status;
        }

        public boolean isActive() {

                if (this.balance <= 25) {
                        this.status = false;
                } else {
                        this.status = true;
                }
                return this.status;
        }


        public void deposit(double amount) {
            super.deposit(amount);

            if(this.status == false && isActive() == true) {
                setStatus(true);
            }
        }

        public void withdraw(double amount) {

            if (isActive()) {
                if(this.balance > amount) {
                    super.withdraw(amount);

                    if (this.noOfWithdrawls > 4) {
                        System.out.println("You have exceeded monthly limit of withdrawals. Fee of $1 charged");
                        super.chargeFee();
                    }

                    if(this.status == true && isActive() == false) {
                        System.out.println("Your balance is less than minimum balance. Your account is now INACTIVE");
                        setStatus(false);
                    }
                } else {
                    System.out.println("ERROR: Transaction declined!! This transaction will cause overdraft or zero balance");
                }
            } else {
                System.out.println("ERROR: Account INACTIVE");
            }
        }

        public void monthlyProcess() {
                super.monthlyProcess();
                System.out.println("Your Balance after Monthly process is: " + this.balance);

        }

        public static void main(String[] args) {

                Scanner in = new Scanner(System.in);

                System.out.print("Enter beginning balance :$");
                double balance = in.nextDouble();

                System.out.print("Enter interest rate(whole number) :%");
                float rate = in.nextFloat();
                System.out.println();
                SavingsAccountDemo account = new SavingsAccountDemo(balance, rate);
                boolean flag = true;
                do {
                        System.out.println("Enter D to deposit");
                        System.out.println("Enter W to Withdraw");
                        System.out.println("Enter B for Balance");
                        System.out.println("Enter M for Monthly Process");
                        System.out.println("Enter E to Exit");

                        char c = in.next().charAt(0);
                        if (c == 'D') {
                                System.out.print("Enter the amount you want to Deposit :$" +"\n");
                                double amount = in.nextDouble();
                                if (amount < 0) {
                                        System.out.println("Error: Must enter positive value");
                                } else {
                                        account.deposit(amount);
                                        System.out.println();
                                }

                        } else if (c == 'W') {
                                System.out.print("Enter the amount you want to withdraw :$");
                                double amount = in.nextDouble();
                                if (amount < 0) {
                                        System.out.println("Error: Must enter positive value");
                                } else {
                                        account.withdraw(amount);
                                        System.out.println();
                                }

                        } else if (c == 'B') {
                                System.out.println("Your Balance is: " + account.balance);
                        } else if (c == 'M') {
                                account.monthlyProcess();
                        } else if (c == 'E') {
                                flag = false;
                                System.out.println("Balance : $" + account.balance);
                                System.out.println("Thank you. Bye");
                        } else {
                                System.out.println("Invalid choice. Try again");
                        }

                } while (flag == true);

                in.close();
                
        }

}