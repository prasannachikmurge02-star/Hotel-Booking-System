import java.util.*;
import java.io.FileWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class HotelSystem {

    // -------- Encapsulation --------
    private String customerName;
    private String customerAddress;
    private String aadharNumber;
    private int age;

    Scanner sc = new Scanner(System.in);

    // -------- Constructor --------
    HotelSystem() {
        System.out.println("    Book Your Hotel Now...!!");
        System.out.println("----- Area You Want Stay --------");
        System.out.println("1. Ravet");
        System.out.println("2. Chinchwad");
        System.out.println("3. Hinjewadi");
        System.out.println("4. Baner");
    }

    // -------- Hotel Menu --------
    void showHotels() {
        System.out.println("----- Hotels In That Area --------");
        System.out.println("1. Sayaji");
        System.out.println("2. Green Tree");
        System.out.println("3. Cherish");
        System.out.println("4. NeoWood");
    }

    void welcomeHotel(int choice, String area) {
        switch (choice) {
            case 1: System.out.println("Welcome to Sayaji in " + area); break;
            case 2: System.out.println("Welcome to Green Tree in " + area); break;
            case 3: System.out.println("Welcome to Cherish in " + area); break;
            case 4: System.out.println("Welcome to NeoWood in " + area); break;
            default: System.out.println("Invalid Hotel");
        }
    }

    // -------- Customer Input --------
    void getCustomerDetails() {
        sc.nextLine();

        System.out.println("Enter Name:");
        customerName = sc.nextLine();

        System.out.println("Enter Address:");
        customerAddress = sc.nextLine();

        System.out.println("Enter Aadhar:");
        aadharNumber = sc.nextLine();

        System.out.println("Enter Age:");
        age = sc.nextInt();
    }

    // -------- Getters --------
    public String getCustomerName() { return customerName; }
    public String getCustomerAddress() { return customerAddress; }
    public String getAadharNumber() { return aadharNumber; }
    public int getAge() { return age; }

    // -------- Main --------
    public static void main(String[] args) {

        HotelSystem h = new HotelSystem();
        Scanner sc = h.sc;

        try {
            FileWriter fw = new FileWriter("hotelman.txt", true);

            int finalBill = 0;

            // -------- AREA --------
            int areaChoice = sc.nextInt();
            String area = "";

            switch (areaChoice) {
                case 1: area = "Ravet"; break;
                case 2: area = "Chinchwad"; break;
                case 3: area = "Hinjewadi"; break;
                case 4: area = "Baner"; break;
                default: area = "Unknown";
            }

            System.out.println("Selected Area: " + area);

            // -------- HOTEL --------
            h.showHotels();
            int hotelChoice = sc.nextInt();
            h.welcomeHotel(hotelChoice, area);

            // -------- CUSTOMER --------
            h.getCustomerDetails();

            if (h.getAge() < 18) {
                System.out.println("Not eligible");
                return;
            }

            // -------- ROOM --------
            System.out.println("Room Types:");
            System.out.println("1. Luxury (2000)");
            System.out.println("2. Semi-Luxury (1500)");
            System.out.println("3. AC (1000)");
            System.out.println("4. Non-AC (700)");

            int roomChoice = sc.nextInt();
            int roomPrice = 0;
            String roomType = "";

            switch (roomChoice) {
                case 1: roomType = "Luxury"; roomPrice = 2000; break;
                case 2: roomType = "Semi-Luxury"; roomPrice = 1500; break;
                case 3: roomType = "AC"; roomPrice = 1000; break;
                case 4: roomType = "Non-AC"; roomPrice = 700; break;
            }

            // -------- MEAL --------
            System.out.println("Meal Types:");
            System.out.println("1. Combo (1000)");
            System.out.println("2. Breakfast + Dinner (800)");
            System.out.println("3. Non-Veg (1200)");

            int mealChoice = sc.nextInt();
            int mealPrice = 0;
            String mealType = "";

            switch (mealChoice) {
                case 1: mealType = "Combo"; mealPrice = 1000; break;
                case 2: mealType = "Breakfast + Dinner"; mealPrice = 800; break;
                case 3: mealType = "Non-Veg"; mealPrice = 1200; break;
            }

            // -------- MEMBERS --------
            System.out.println("Enter number of members:");
            int members = sc.nextInt();

            // -------- DAYS --------
            System.out.println("Enter number of days to stay:");
            int days = sc.nextInt();

            // -------- BILLING --------
            int total = (roomPrice + mealPrice) * members * days;
            int gst = total * 18 / 100;
            finalBill = total + gst;

            // -------- DATE --------
            LocalDateTime checkIn = LocalDateTime.now();
            LocalDateTime checkOut = checkIn.plusDays(days);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            // -------- DISPLAY BILL --------
            System.out.println("\n===== BILL =====");
            System.out.println("Check-In: " + checkIn.format(format));
            System.out.println("Check-Out: " + checkOut.format(format));
            System.out.println("Customer: " + h.getCustomerName());
            System.out.println("Area: " + area);
            System.out.println("Room: " + roomType);
            System.out.println("Meal: " + mealType);
            System.out.println("Members: " + members);
            System.out.println("Days: " + days);
            System.out.println("Base Amount: " + total);
            System.out.println("GST (18%) : " + gst);
            System.out.println("Final Bill: " + finalBill);

            // -------- PAYMENT --------
            sc.nextLine();
            System.out.println("Enter Payment Mode (online/offline):");
            String paymentMode = sc.nextLine();

            if (paymentMode.equalsIgnoreCase("online")) {
                System.out.println("Enter UPI ID:");
                String upi = sc.nextLine();
                fw.write("Payment: Online, UPI: " + upi + "\n");
            } else {
                fw.write("Payment: Offline\n");
            }

            // -------- FILE WRITE --------
            fw.write("\nName: " + h.getCustomerName());
            fw.write("\nAddress: " + h.getCustomerAddress());
            fw.write("\nAadhar: " + h.getAadharNumber());
            fw.write("\nCheck-In: " + checkIn.format(format));
            fw.write("\nCheck-Out: " + checkOut.format(format));
            fw.write("\nDays: " + days);
            fw.write("\nFinal Bill: " + finalBill);
            fw.write("\n-------------------------\n");

            fw.close();

            System.out.println("Booking Successful!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}