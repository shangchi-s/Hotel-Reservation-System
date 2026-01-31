import java.sql.*;
import java.util.Scanner;

public class HotelReservation{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String user = "root";
        String password = "root1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established");

            Statement st = connection.createStatement();
            System.out.println("Statement connected");

            Scanner sc = new Scanner(System.in);
            int choice = 0;

        
            while (choice != 4) {
                
                System.out.println("1. Insert Reservation");
                System.out.println("2. Update Reservation");
                System.out.println("3. Delete Reservation");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine();
                

                try {
                    switch (choice) {
                        case 1:
                            System.out.print("Enter guest name: ");
                            String Guest_Name = sc.nextLine();
                            System.out.print("Enter contact number: ");
                            String Contact_Number = sc.nextLine();
                            System.out.print("Enter checkout date (YYYY-MM-DD HH:MM:SS): ");
                            String Checkout = sc.nextLine();
                            

                            String R = "INSERT INTO reservations (guest_name, contact_name, checkout) " +
                            "VALUES ('" + Guest_Name + "', '" + Contact_Number + "', '" + Checkout + "')";
                            int rows = st.executeUpdate(R);
                            System.out.println(rows+"**Reservation inserted**");
                            break;

                        case 2:
                            System.out.print("Enter reservation_id to update: ");
                            int Rev_id = sc.nextInt();
                            
                            System.out.print("Enter new checkout date (YYYY-MM-DD HH:MM:SS) ");
                            String New_Checkout = sc.nextLine();

                            String updates = "UPDATE reservations SET checkout='" + New_Checkout + "' WHERE reservation_id=" + Rev_id;

                            int updated = st.executeUpdate(updates);
                            System.out.println(updated + "**Reservation updated**");
                            break;

                        case 3:
                            System.out.print("Enter reservation_id to delete: ");
                            int Del_Id = sc.nextInt();

                            String delete = "DELETE FROM reservations WHERE reservation_id=" + Del_Id;
                            int deleted = st.executeUpdate(delete);
                            System.out.println(deleted + "**Reservation deleted**");
                            break;
                        
                        case 4:
                            System.out.println("Exiting");
                            break;

                        default:
                            System.out.println("Invalid choice");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}