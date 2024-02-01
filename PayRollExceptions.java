
import java.util.Scanner;

public class PayRollExceptions {

        public static void main(String[] args) throws Exception {

                ProductionWorker pw = new ProductionWorker();
                Scanner input = new Scanner(System.in);

                System.out.print("Enter the employee's name: ");
                String name = input.nextLine();

                System.out.print("Enter employee number, (ex. 999-M): ");
                String employee_num = input.nextLine();

                System.out.print("Enter the employee's hourly rate: ");
                int payrate = input.nextInt();

                System.out.print("Enter the number of hours the employee has worked: ");
                int noOfhrs = input.nextInt();

                try {
                        pw.checkingName(name);
                        pw.CheckingID(employee_num);
                        pw.checkingPayrate(payrate);
                        pw.CheckingNumHours(noOfhrs);
                        System.out.println("Employees name: " + name);
                        System.out.println("ID: " + employee_num);
                        System.out.println("Hourly Rate: $" + new Float(payrate));
                        System.out.println("Hours: " + noOfhrs+" hrs ");
                        System.out.println("Gross Pay: $" + new Float(noOfhrs * payrate));
                } catch (Exception e) {
                        System.out.print(e.getMessage());
                }

        }

}

class ProductionWorker {

        public void CheckingID(String ID) throws Exception {
                String regex = "[0-9][0-9][0-9]-[A-M]";
                if(!(ID.matches(regex))) {
                        throw new Exception("Error: Numericals in ID must be between 0-9 and letters must be between A-M");
                }

        }

        public void CheckingNumHours(int hrs) throws Exception {

                if (hrs < 0 || hrs > 84) {
                        throw new Exception("Error: Hours Cannot be negative or greater than 84");

                }

        }

        void checkingPayrate(int payrate) throws Exception {
                if (payrate < 0 || payrate > 25) {
                        throw new Exception("Error: Hourly Rate Cannot be negative or greater than 25");
                }

        }

        void checkingName(String name) throws Exception {
                if (name == "") {
                        throw new Exception("Error: Name cannot be empty");
                }
        }
}
