import java.util.Scanner;

/**
 * Hotel Management System test class
 *
 *
 */
public class ManagementSystemTest {

    public static void main(String args[]) throws Exception
    {
        try {

            String whichPerson;
            Scanner input = new Scanner(System.in);

            System.out.println("\n*** WELCOME ***");
            System.out.println("Are you a Customer ?\n Y/y or N/n");

            whichPerson = input.next(); // Get the system user type

            // Customer operation
            // Reservation
            // Reservation Cancelling
            if (whichPerson.equals("Y") || whichPerson.equals("y"))
            {
                Person personObject = new Customer();          // processed polymorphically
                personObject.personOperations();
            }

            // frontdesk Operation
            // Reservation
            // Reservation Cancelling
            // Check-in
            // Check-out
            else if (whichPerson.equals("N") || whichPerson.equals("n"))
            {
                Person personObject = new frontdesk();   // processed polymorphically
                personObject.personOperations();
            }
            else {
                System.out.println("Invalid Choice !");
            }

        } catch(Exception E){

            System.out.println("An Exception Caught : " + E);
        }

    } // END MAIN FUNCTION
} // END CLASS
