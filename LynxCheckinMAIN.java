import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class LynxCheckinMAIN {
    public static Scanner numscan = new Scanner(System.in);
    public static Scanner wordscan = new Scanner(System.in);

    public static void main(String[] args) {

        String destination = "placeholder";
        Boolean flightEligible = true;
        Boolean loop = true; // standard looper variable just ignore it.
        String lynxID = "placeholder";
        String confirmed = "placeholder";
        String seatChoice;
        int checkinMethod;
        String blackList[] = Files.loadStringArr("/Users/mikhails/Desktop/FINAL CS/LynxCheckin/theBlacklist.txt");
        String flightDepartures[] = Files.loadStringArr("/Users/mikhails/Desktop/FINAL CS/LynxCheckin/Flights.txt");
        String flightNumbers[] = Files.loadStringArr("/Users/mikhails/Desktop/FINAL CS/LynxCheckin/FlightNum.txt");

        while (loop == true) {

            System.out.println("                              ~~LynxAir Check-In~~");

            System.out
                    .println("Greetings passenger! Welcome to Lynx Air Calgary. We are eager to have you fly with us.");
            System.out.println("Please select a method you would like to use to check into your upcoming flight");
            System.out.println();
            System.out.println();
            System.out.println("1. LynxID[First and Last Name]");
            System.out.println();
            System.out.println("2. LynxFN[Your flight number (No LynxID Frequent Flyer Account. Book new flight.)]");
            System.out.println();

            checkinMethod = numscan.nextInt();

            if (checkinMethod == 1) {
                lynxID = lynxID(lynxID);
                confirmed = flight(destination, flightDepartures, flightNumbers);
                seatChoice = seatSelection(confirmed, lynxID);
                flightEligible = securityScreening(lynxID, confirmed, blackList);

                int foundIndex;
                foundIndex = -1;
                for (int i = 0; i < blackList.length; i++) {

                    if (blackList[i].equalsIgnoreCase(lynxID)) {
                        foundIndex = i;
                        break;
                    }

                }

                if ((foundIndex == -1) && (flightEligible == true)) {
                    System.out.println("TSA Passed");
                    System.out.println("Enjoy your flight! Printing boarding pass...");
                    outputString("                      ~~~LynxAir~~~ \n  \n Name: " + lynxID + "       "
                            + "FlightNum/Destination:" + confirmed + "\n \n" + "Seat #:" + seatChoice);
                } else if ((foundIndex > -1) && (flightEligible == false)) {
                    outputString(
                            "                      ~~~LynxAir~~~  \n \n ERROR: Blacklisted. Visit an attendant with this ticket.");
                } else {
                    System.out.println("You are not flight eligible.");
                    System.out.println();
                }

            } else if (checkinMethod == 2) {
                confirmed = flight(destination, flightDepartures, flightNumbers);
                lynxID = newFlyer();
                seatChoice = seatSelection(confirmed, lynxID);
                flightEligible = securityScreening(confirmed, lynxID, blackList);

                int foundIndex;
                foundIndex = -1;
                for (int i = 0; i < blackList.length; i++) {

                    if (blackList[i].contains(lynxID)) {
                        foundIndex = i;
                        break;
                    }

                }

                System.out.println(foundIndex);
                System.out.println(flightEligible);
                if ((foundIndex == -1) && (flightEligible == true)) {
                    System.out.println("TSA Passed");
                    System.out.println("Enjoy your flight! Printing boarding pass...");
                    outputString("                      ~~~LynxAir~~~ \n  \n Name: " + lynxID + "       "
                            + "FlightNum/Destination:" + confirmed + "\n \n" + "Seat #:" + seatChoice);
                } else if ((foundIndex > -1) && (flightEligible == false)) {
                    outputString(
                            "                      ~~~LynxAir~~~  \n \n ERROR: Blacklisted. Visit an attendant with this ticket.");
                } else {
                    System.out.println("Goodbye.");
                    System.out.println();
                }

            }

            else {
                System.out.println("ERROR: Select valid check-in method, or check departures");
                checkinMethod = numscan.nextInt();
            }
        }

    }// end main

    public static String lynxID(String LynxID) {

        String firstName;
        String lastName;

        System.out.println("                              ~~LynxAir Check-In~~");
        System.out.println(
                "Thank you for returning to fly with us! Please provide us with your first name and last name.");
        System.out.println();
        firstName = wordscan.nextLine();
        System.out.println("Now your last.");
        lastName = wordscan.nextLine();

        System.out.println();
        System.out.println("Welcome, " + firstName + " " + lastName);

        LynxID = (firstName + " " + lastName);

        return LynxID;

    }

    public static String flight(String confirm, String[] flightList, String[] flightnum) {

        System.out.println();
        System.out.println("Please select your/a departing flight");

        System.out.println("                         ~~LynxAir Check-In Departures~~");

        for (int i = 0; i < flightList.length; i++) {
            System.out.println(i + ". " + flightnum[i] + " " + flightList[i]);
        }

        int userChoice = numscan.nextInt();
        String flightconfirm = "N/A";

        if (userChoice <= flightList.length) {
            System.out.println("Thank you for selecting your flight to " + flightList[userChoice]);
            flightconfirm = (flightnum[userChoice] + " " + flightList[userChoice]);
        } else {
            System.out.println("ERROR: flight unavailable enter again");
            userChoice = numscan.nextInt();
        }

        return flightconfirm;

    }// end flight()

    public static String seatSelection(String confirmation, String ID) {

        System.out.println();
        String selection = "PLACEHOLDER";

        System.out.println("                         ~~LynxAir Check-In Seat Selection~~");
        System.out.println();
        System.out.println(
                "Hello " + ID + ", welcome to our seat selection, please choose one of our available options for");
        System.out.println("your flight to " + confirmation);

        if (confirmation.contains("L100")) {
            System.out.println("   Seat Selection");
            System.out.println("____________________");
            System.out.println("XX 1B 1C -- 1D 1E 1F");
            System.out.println("2A XX 2C -- 2D 2E 2F");
            System.out.println("XX 3B 3C -- 3D XX 3F");
            System.out.println("4A 4B 4C -- XX 4E 4F");
            System.out.println("5A 5B 5C -- 5D 5E 5F");
            System.out.println();

            selection = wordscan.nextLine();

            if (selection.equalsIgnoreCase("1A") || selection.equalsIgnoreCase("2B") || selection.equalsIgnoreCase("3A")
                    || selection.equalsIgnoreCase("3E") || selection.equalsIgnoreCase("3D")) {
                System.out.println("UNAVAILABLE: Select new seat.");
                selection = wordscan.nextLine();
            } else {
                System.out.println("Your seat has been selected: " + selection);
                return selection;
            }

        } else if (confirmation.contains("L101")) {
            System.out.println("   Seat Selection");
            System.out.println("____________________");
            System.out.println("XX 1B XX -- 1D 1E 1F");
            System.out.println("2A XX 2C -- 2D 2E 2F");
            System.out.println("3A 3B 3C -- 3D XX 3F");
            System.out.println("XX XX 4C -- 4D 4E 4F");
            System.out.println("5A 5B 5C -- 5D 5E 5F");
            System.out.println();

            selection = wordscan.nextLine();

            if (selection.equalsIgnoreCase("1A") || selection.equalsIgnoreCase("1C") || selection.equalsIgnoreCase("2B")
                    || selection.equalsIgnoreCase("3E") || selection.equalsIgnoreCase("4A")
                    || selection.equalsIgnoreCase("4B")) {
                System.out.println("UNAVAILABLE: Select new seat.");
                selection = wordscan.nextLine();
            } else {
                System.out.println("Your seat has been selected: " + selection);
                return selection;
            }

        } else if (confirmation.contains("L102")) {
            System.out.println("   Seat Selection");
            System.out.println("____________________");
            System.out.println("1A 1B 1C -- 1D 1E 1F");
            System.out.println("2A 2B 2C -- 2D 2E 2F");
            System.out.println("3A XX 3C -- 3D 3E 3F");
            System.out.println("4A 4B 4C -- 4D 4E 4F");
            System.out.println("5A 5B 5C -- 5D 5E 5F");
            System.out.println();

            selection = wordscan.nextLine();

            if (selection.equalsIgnoreCase("3B")) {
                System.out.println("UNAVAILABLE: Select new seat.");
                selection = wordscan.nextLine();
            } else {
                System.out.println("Your seat has been selected: " + selection);
                return selection;
            }

        } else if (confirmation.contains("L103")) {
            System.out.println("   Seat Selection");
            System.out.println("____________________");
            System.out.println("1A XX 1C -- 1D 1E 1F");
            System.out.println("2A XX 2C -- XX XX XX");
            System.out.println("3A 3B 3C -- 3D 3E 3F");
            System.out.println("4A 4B 4C -- 4D XX 4F");
            System.out.println("5A 5B 5C -- 5D 5E 5F");
            System.out.println();

            selection = wordscan.nextLine();

            if (selection.equalsIgnoreCase("1B") || selection.equalsIgnoreCase("4E") || selection.equalsIgnoreCase("2B")
                    || selection.equalsIgnoreCase("2D") || selection.equalsIgnoreCase("2E")
                    || selection.equalsIgnoreCase("2F")) {
                System.out.println("UNAVAILABLE: Select new seat.");
                selection = wordscan.nextLine();
            } else {
                System.out.println("Your seat has been selected: " + selection);
                return selection;
            }

        } else if (confirmation.contains("L104")) {
            System.out.println("   Seat Selection");
            System.out.println("____________________");
            System.out.println("XX XX 1C -- 1D XX 1F");
            System.out.println("XX 2B XX -- 2D 2E 2F");
            System.out.println("XX 3B 3C -- XX 3E XX");
            System.out.println("4A XX 4C -- 4D 4E 4F");
            System.out.println("5A 5B XX -- 5D 5E 5F");
            System.out.println();

            selection = wordscan.nextLine();

            if (selection.equalsIgnoreCase("1A") || selection.equalsIgnoreCase("1B") || selection.equalsIgnoreCase("2A")
                    || selection.equalsIgnoreCase("2C") || selection.equalsIgnoreCase("3A")
                    || selection.equalsIgnoreCase("3D") || selection.equalsIgnoreCase("3F")
                    || selection.equalsIgnoreCase("4B") || selection.equalsIgnoreCase("5C")) {
                System.out.println("UNAVAILABLE: Select new seat.");
                selection = wordscan.nextLine();
            } else {
                System.out.println("Your seat has been selected: " + selection);
                return selection;
            }

        }

        return selection;

    } // end seatSelection()

    public static boolean securityScreening(String passengerName, String destination, String[] blacklist) {

        boolean travelEligible = true;
        String q1 = "no";
        String q2 = "no";
        String q3 = "no";

        System.out.println("                         ~~Transport Canada Background Check~~");
        System.out.println();
        System.out.println("Hello " + passengerName + ", before you depart we need to perform a background check,");
        System.out.println("security check, and covid safety check. This is to ensure everybody's flight safety.");
        System.out.println();

        System.out.println("COVID 19 PRE DEPARTURE:");
        System.out.println(
                "1. Have your and or, anyone travelling in your party recently been exposed too, tested or fell ill to Covid-19 (answer: YES || NO");
        q1 = wordscan.nextLine();

        if (q1.equalsIgnoreCase("yes")) {
            System.out.println(
                    "Sorry, you are not eligible for flight at this time, visit a customer service agent for refund");
            travelEligible = false;
            return travelEligible;
        } else if (q1.equalsIgnoreCase("no")) {
            travelEligible = true;
            System.out.println();
            System.out.println("2. What are your reasons for travel?");
            q2 = wordscan.nextLine();

            if (q2.contains("vacation") || q2.contains("Vacation")) {
                System.out.println(
                        "Sorry, you are not eligible for flight at this time due to Covid Restrictions, visit a customer service agent for refund");
                travelEligible = false;
                return travelEligible;
            } else {
                System.out.println("Thank you for going through our covid screening.");
                travelEligible = true;
            }
        }

        System.out.println();
        System.out.println("Are you carrying any illegal possessions YES OR NO");
        System.out.println("Firearms");
        System.out.println("Non-perscription drugs");
        System.out.println("Liquids over 5oz");

        q3 = wordscan.nextLine();
        if (q3.equalsIgnoreCase("yes")) {

            for (int i = 0; i < blacklist.length; i++) {
                if (blacklist[i].equals("PLACEHOLDER")) {
                    blacklist[i] = passengerName;
                    break;
                } // try getting onboard now you pesky passengers

            }

            System.out.println("ERROR: You are now added to the airline blacklist");
            System.out.println("Security is en route to this checkpoint, goodbye");

            travelEligible = false;
            return travelEligible;
        } else {

            if (travelEligible) {

                for (int i = 0; i < blacklist.length; i++) {
                    if (blacklist[i].contains(passengerName)) {
                        travelEligible = false;
                    }
                }
            } else {
                System.out.println("Thank you for completing our background check!");
                travelEligible = true;
            }
        }

        return travelEligible;

    }// end securityScreening()

    public static String newFlyer() {
        System.out.println("                         ~~LynxAir LynxID Registration~~");
        System.out.println();
        System.out.println(
                "Hello! Thank you for choosing to fly with LynxAir. To embark on your journey \n LynxAir requires guests to register for a LynxID to assist with security. \n Please enter your First, then Last name.");
        String fName = wordscan.nextLine();
        System.out.println("Now your last.");
        String lName = wordscan.nextLine();

        String newLynxID = (fName + " " + lName);
        return newLynxID;

    }

    public static void outputString(String message) {
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(frame, message, "My Message Window", 2);
        // 0 - stop sign, 1 - information, 2 - exclamation mark, 3 - question mark
        System.out.println(message);
    } // end outputString()



} // end class

// LynxAir Check-in System (CS20 Final Project)
// By Mikhail Shteinbach. Gr 11 Dr E.P. Scarlett


