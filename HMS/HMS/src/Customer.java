import java.util.ArrayList;
import java.util.Scanner;

/**
 * Customer Class
 */
public class Customer extends Person {

    private String IdentificationNumber;
    private String address;
    private String phoneNumber;
    private String eMail;

    // Hotel Customers
    private ArrayList<Person> hotelCustomers = new ArrayList<>();

    /**
     * Class constructor without parameter
     */
    public Customer()
    {
        this.personType="";
        this.firstName="";
        this.lastName="";
    }

    /**
     * Class constructor specifying objects to create
     * Initializes a Customer object with all properties speicified.
     * @param pType             person type
     * @param name              first name
     * @param surname           last name
     * @param ID                identification number
     * @param contactAddress    contact address
     * @param phone             phone number
     * @param mail              e-mail
     */
    public Customer(String pType,String name, String surname, String ID, String contactAddress,
                 String phone, String mail)
    {
        this.personType = pType;
        this.firstName = name;
        this.lastName =  surname;
        setContactInfo(ID,contactAddress,phone,mail);
    }

    /**
     *
     * @param ID ID to set
     * @param contactAddress contactAddress to set
     * @param phone phone to set
     * @param mail mail to set
     */
    public void setContactInfo(String ID,String contactAddress, String phone, String mail)
    {
        IdentificationNumber = ID;
        address = contactAddress;
        phoneNumber = phone;
        eMail = mail;
    }

    /**
     * @return Customer identification number
     */
    public String getIdentificationNumber()
    {
        return IdentificationNumber;
    }

    /**
     * @return Customer contact address
     */
    public String getAddress()
    {
        return address;
    }


    /**
     *
     * @return Customer phone number
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     *
     * @return Customer e-mail
     */
    public String geteMail()
    {
        return eMail;
    }

    /**
     * Show hotel Customers
     */
    public void showHotelCustomers()
    {
        for (int i = 0; i < hotelCustomers.size(); i++)
        {
            System.out.println(hotelCustomers.get(i).toString());
        }
    }

    /**
     * Get room details,according to the Customer surname
     * @param CustomerSurname the String to Customer surname
     */
    public String getRoomDetailToCustomer(String CustomerSurname)
    {

        for(int i=0; i<roomList.size(); ++i)
        {
            if (roomList.get(i).getPersonObject().getPersonLastName().equals(CustomerSurname))
            {
                return  roomList.get(i).toString();
            }
        }

        return null;
    }

    /**
     * toString() method override
     * @return the String to Customer details
     */
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",super.toString(),getIdentificationNumber(),getAddress(),
                getPhoneNumber(),geteMail());
    }


    /**
     * Customer operations
     * If the Customer press 1,will be able to reservation
     * If the Customer press 2,will be able to reservation cancelling
     * @throws Exception if the an operation is fail.
     */


    @Override
    public void personOperations() throws Exception
    {

        try {
                int operationChoice;
                String firstName, lastName, identificationNumber, address, phoneNumber, mail;
                String roomType;
                int roomNo, nightStay, roomPrice;

                Scanner input = new Scanner(System.in);

                // Other hotel Customer records
                Customer g2 = new Customer("Customer", "Abdullah", "Saleh",
                        "8230396749959",
                        "Pakistan", "03025556979", "m.abdullahsaleh@gmail.com");

                Rooms newRoomReservation2 = new Rooms(g2, "Deluxe",
                        3, 2, 100);


                // Reservation process to other Customers
                reservation(newRoomReservation2);

                // Show the reserved rooms in the hotel
                // showReservedRooms();

                hotelCustomers.add(g2);
                // Show the list of hotel Customers
                // showHotelCustomers();

                System.out.println("Enter 1 to Reservation");
                System.out.println("Enter 2 to Reservation Cancellation");
                operationChoice = input.nextInt();

                // Reservation
                if (operationChoice == 1)
                {
                    System.out.println("What is your name ?");
                    firstName = input.next();

                    System.out.println("What is your surname ?");
                    lastName = input.next();

                    System.out.println("What is your identification number ?");
                    identificationNumber = input.next();

                    if (!identificationNumber.matches("\\d{13}")) {
                        System.out.println("Invalid identification number! Please enter a 13-digit identification number.");
                        identificationNumber = input.next();
                    }

                    System.out.println("What is your contact address ?");
                    address = input.next();

                    System.out.println("What is your phone number ?");
                    phoneNumber = input.next();

                    if (!phoneNumber.matches("\\d{11}")) {
                        System.out.println("Invalid phone number! Please enter an 11-digit phone number.");
                        phoneNumber = input.next();
                    }

                    System.out.println("What is your e-mail ?");
                    mail = input.next();

                    if (!mail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                        System.out.println("Invalid e-mail! Please enter a valid e-mail address.");
                        mail = input.next();
                    }

                    Customer g1 = new Customer("Customer", firstName, lastName,
                            identificationNumber, address, phoneNumber, mail);

                    System.out.println("\n> Select your room type < \n- 1.Luxury\n- 2.Suit\n- 3.Deluxe\n ");
                    roomType = input.next();
                    
                    switch (roomType.toLowerCase()) {
                        case "luxury":
                            // code to execute if roomType is "1"
                            break;
                        case "suit":
                            // code to execute if roomType is "2"
                            break;
                        case "deluxe":
                            // code to execute if roomType is "3"
                            break;
                        default:
                            System.out.println("Invalid room type! Please enter a valid room type.");
                            roomType = input.next();
                            break;
                    }
                    System.out.println("\n> Enter your room no <\n- Luxury - 1\n- Suit -   2\n- Deluxe - 3\n");
                    roomNo = input.nextInt();

                    switch (roomNo) {
                        case 1:
                            // code to execute if roomNo is 1
                            break;
                        case 2:
                            // code to execute if roomNo is 2
                            break;
                        case 3:
                            // code to execute if roomNo is 3
                            break;
                        default:
                            System.out.println("Invalid room number! Please enter a valid room number.");
                            roomNo = input.nextInt();
                            break;
                    }

                    if(checkRoomStatus(roomNo)==true)
                    {
                        System.out.println("The room is already reserved !!");
                        System.out.println("\n> Enter your room no <\n- Luxury - 1\n- Suit - 2\n- Deluxe - 3\n");
                        roomNo = input.nextInt();
                    }


                    System.out.println("\n> Enter your the night stay <");
                    nightStay = input.nextInt();

                    System.out.println("> Enter the room price ( £ ) <\n- Luxury - 300\n- Suit   - 200\n- Deluxe - 100\n");
                    roomPrice = input.nextInt();

                    switch (roomType.toLowerCase()) {
                        case "luxury":
                            if (roomPrice!= 300) {
                                System.out.println("Invalid room price!");
                                roomPrice = input.nextInt();
                            }
                            break;
                        case "suit":
                            if (roomPrice!= 200) {
                                System.out.println("Invalid room price!");
                                roomPrice = input.nextInt();
                            }
                            break;
                        case "deluxe":
                            if (roomPrice!= 100) {
                                System.out.println("Invalid room price!");
                                roomPrice = input.nextInt();
                            }
                            break;
                        default:
                            System.out.println("Invalid room type!");
                            roomType = input.next();
                            roomPrice = input.nextInt();
                            break;
                    }


                    Rooms newRoomReservation1 = new Rooms(g1, roomType, roomNo, nightStay, roomPrice);

                    if (true == reservation(newRoomReservation1))
                    {
                        System.out.println("\nYour reservation operation is successful !!\n");
                        System.out.println("**** Your reservation detail ****");
                        System.out.println(Constants.RECORD_LIST_HEADER);
                        System.out.println(getRoomDetail(roomNo));
                    }
                }

                //Reservation cancelling
                if (operationChoice == 2)
                {
                    System.out.println("Enter your surname ");
                    lastName = input.next();

                    System.out.printf("\n%s\n", Constants.RECORD_LIST_HEADER);
                    System.out.println(getRoomDetailToCustomer(lastName));

                    System.out.println("\nEnter your room number ");
                    roomNo = input.nextInt();

                    if (true == cancelReservation(roomNo))
                    {
                        System.out.println("Reservation cancelling operation is successful !!");
                    }
                    else
                    {
                        System.out.println("Invalid Room No !!");
                    }
                }

            saveTheCustomerRecord();   // Save the records to an csv file


        } catch (Exception E)
        {
            System.out.println("An Exception Caught: " + E);
        }

    }


}
