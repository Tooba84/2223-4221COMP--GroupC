import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

/**
 * frontdesk Class
 * Class that represents a frontdesk
 */
public class frontdesk extends Person
{

    final String header = Constants.RECORD_LIST_HEADER;
    private String identificationNumber;      // System user ID number
    private String address;                   // System user contact address
    private String phoneNumber;               // System user phone number
    private String mail;                      // System user e-mail

    /**
     * Class constructor without parameters
     */
    public frontdesk()
    {
        this.personType="";
        this.firstName="";
        this.lastName="";
    }

    /**
     * Class constructor specifying objects to create
     * @param personType
     * @param firstName
     * @param lastName
     */
    public frontdesk(String personType,String firstName, String lastName)
    {
        this.personType=personType;
        this.firstName=firstName;
        this.lastName=lastName;
    }


    /**
     * Save the frontdesk's reservation check-in and check-out records to given csv file.
     * @param header the String to record file header
     * @param roomNo the integer to room no
     */
    public void saveTheRecords(String header,int roomNo)
    {

        try {
            FileWriter fw = new FileWriter(Constants.CHECK_IN_CHECK_OUT_LIST_FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(header);

            pw.print(getRoomDetail(roomNo));
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            pw.println(dateFormat.format(date));

            pw.flush();
            pw.close();

        } catch(Exception E){
            System.out.println("Exception Caught : " + E);
        }
    }

    /**
     *
     * This method shows the reserved rooms details in the hotel.
     */
    public void showReservedRooms()
    {
        for (int i = 0; i < roomList.size(); i++)
        {
            System.out.println(roomList.get(i).toString());
        }
    }

    /**
     * Check the room state,according to the room no
     * @param roomNo the integer to room no
     * @return If the process is successful,return true.Otherwise,return false
     */
    public boolean checkRoomStatus(int roomNo)
    {

        for (int i = 0; i < roomList.size(); i++)
        {
            if (roomList.get(i).getRoomNo() == roomNo)
            {
                return true;
            }
        }

        return false;
    }


    /**
     *
     * @param roomNo the integer to reserved room no.
     * @return the String for according to the given room no,reserved room details,
     */
    public String getRoomDetail(int roomNo)
    {
        String roomDetail;

        for(int i=0; i<roomList.size(); ++i)
        {
            if (roomList.get(i).getRoomNo() == roomNo)
            {
                roomDetail = roomList.get(i).toString();
                return roomDetail;
            }
        }

        return null;
    }

    /**
     * This method saves the reservation records to given csv file.
     */
    public void saveTheCustomerRecord()
    {

        try {
            FileWriter fw = new FileWriter(Constants.CUSTOMER_RECORD_LIST_FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(Constants.RECORD_LIST_HEADER);

            for (int i = 0; i < roomList.size(); i++)
            {
                pw.println(roomList.get(i).toString());
            }

            pw.flush();
            pw.close();
        } catch(Exception E){
            System.out.println("Exception Caught : " + E);
        }
    }


    /**
     * frontdesk Check out operation to Customer
     */
    public boolean checkOutOperation()
    {
        int roomNo;
        Scanner input = new Scanner(System.in);

        System.out.println("\n*** Reserved Rooms In the Hotel *** \n");
        System.out.println(header);
        showReservedRooms();

        System.out.println("\n> Enter the room no :");
        roomNo = input.nextInt();

        // Check to operation according to room no
        if (checkRoomStatus(roomNo) == true)
        {
            System.out.println("\nCheck-Out operation is succesfull !!\n");
            System.out.println(header+", Check-Out Date");

            System.out.print(getRoomDetail(roomNo));
            getDate();

            // Save the check-out details in the CheckInRecords.csv file.
            saveTheRecords(header+", Check-Out Date",roomNo);
            return true;
        }

        else {
            System.out.println("Check-out operation is not successfull !");
            return  false;
        }
    }

    /**
     * Get date information to frontdesk check-in and check-out operation
     */
    public void getDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }

    /**
     * frontdesk Check in operation
     */
    public boolean checkInOperation()
    {
        int roomNo;
        Scanner input = new Scanner(System.in);

        System.out.println("\n*** Reserved Rooms In the Hotel *** \n");
        System.out.println(header);
        showReservedRooms();

        System.out.println("\n> Enter the room no :");
        roomNo = input.nextInt();
        if(checkRoomStatus(roomNo)==true)
        {
            System.out.println("\nCheck-in operation is succesfull !!\n");
            System.out.println(header+", Check-in Date");

            System.out.print(getRoomDetail(roomNo));
            getDate();

            // Save the check-in details in the CheckInRecords.csv file
            saveTheRecords(header+", Check-in Date", roomNo);
            return true;

        }
        else {
            System.out.println("Check-in operation is not successfull !");
            return false;
        }
    }


    /**
     * Get Customer information,create a Customer object.Then,create a room object according to the given Customer details.
     * @return new room object
     */
    public Rooms newRoomObject()
    {

        String roomType;
        int roomNo,nightStay,roomPrice;

        Scanner input = new Scanner(System.in);

        System.out.println("What is the Customer name ?");
        firstName = input.next();

        System.out.println("What is the Customer surname ?");
        lastName = input.next();

        System.out.println("What is the Customer identification number ?");
        identificationNumber = input.next();

        System.out.println("What is the Customer contact address ?");
        address = input.next();

        System.out.println("What is the Customer phone number ?");
        phoneNumber = input.next();

        System.out.println("What is the Customer e-mail ?");
        mail = input.next();

        Customer g1 = new Customer("frontdesk",firstName,lastName,
                identificationNumber,address,phoneNumber,mail);

        System.out.println("\n> Select the room type \n- Luxury\n- Suit\n- Deluxe\n ");
        roomType = input.next();
        System.out.println("\n> Enter the room no\n- Luxury - 1\n- Suit -   2\n- Deluxe - 3\n");
        roomNo = input.nextInt();

        if(checkRoomStatus(roomNo)==true)
        {
            System.out.println("The room is already reserved !!");
            System.out.println("\n> Enter the Customer room no <\n- Luxury - 1\n- Suit - 2\n- Deluxe - 3\n");
            roomNo = input.nextInt();
        }

        System.out.println("Enter the night stay");
        nightStay=input.nextInt();
        System.out.println("Enter the room price ( £ )\n- Luxury - 300\n- Suit -   200\n- Deluxe - 100\n");
        roomPrice=input.nextInt();

        return new Rooms(g1,roomType,roomNo,nightStay,roomPrice);
    }


    /**
     * frontdesk Operation
     *
     * Reservation
     * Reservation Cancelling
     * Check-in
     * Check-out
     * @throws Exception if the an operation is fail
     */
    @Override
    public void personOperations() throws Exception
    {
        try {

            int operationChoice,roomNo;

            Scanner input = new Scanner(System.in);

            Customer g3 = new Customer("Customer", "M", "Abdullah",
                    "8230396749959",
                    "Pak", "03025556979", "mabdullahsaleh@gmail.com");


            Rooms newRoomReservation3 = new Rooms(g3,"Suit",
                    2, 5, 200);

               // Other Customer reservation operations
            reservation(newRoomReservation3);

            System.out.println("*** frontdesk Operations ***\nEnter 1 to Reservation");
            System.out.println("Enter 2 to Reservation Cancellation");
            System.out.println("Enter 3 to Check-In");
            System.out.println("Enter 4 to Check-Out");

            operationChoice = input.nextInt();

            if (operationChoice == 1)
            {
                if (true == reservation(newRoomObject()))
                {
                    System.out.println("\nYour reservation operation is successful !!\n");
                    saveTheCustomerRecord();   // Save the Customer reservation
                    showReservedRooms();    // Show the updated reserved rooms details
                }
            }

            // Reservation cancelling operation
            else if(operationChoice == 2)
            {
                System.out.println("\n*** Reserved Rooms In the Hotel *** \n");
                System.out.println(header);
                showReservedRooms();
                System.out.println("\nEnter Customer room no");
                roomNo = input.nextInt();

                if (true == cancelReservation(roomNo))
                {
                    cancelReservation(roomNo);
                    System.out.println("Reservation cancelling operation is successful !!");
                    System.out.println("\n*** Updated Reserved Rooms In the Hotel ** \n");
                    System.out.println(header);
                    showReservedRooms();    // Show the reserved rooms details
                    saveTheCustomerRecord();   // Save the updated rooms details
                }
                else
                {
                    System.out.println("Invalid Room No !!");
                }
            }

            // Check in operation
            else if(operationChoice == 3)
            {
               checkInOperation();
            }

            // Check out operation
            else if (operationChoice == 4)
            {
                checkOutOperation();
            }
            else
            {
                System.out.println("Wrong operation choice !!");
            }


        } catch (Exception E)
        {
            System.out.println(">>>>> An Exception Caught: " + E);
        }

    }

}
