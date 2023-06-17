import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    //just
    //just2
    static int maxBurgerStock = 50;
    static int burgersAvailable = 50;
    static Scanner scanner = new Scanner(System.in);
    static String[] queue1 = {"X", "X"};
    static String[] queue2 = {"X", "X", "X"};
    static String[] queue3 = {"X", "X", "X", "X", "X"};

    String[] allCustomers = {};
    //X – Not Occupied O – Occupied
    static String[] queue1Customers = {"X", "X"};
    static String[] queue2Customers = {"X", "X", "X"};
    static String[] queue3Customers = {"X", "X", "X", "X", "X"};

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    100 or VFQ: View all Queues
                    101 or VEQ: View all Empty Queues.
                    102 or ACQ: Add customer to a Queue.
                    103 or RCQ: Remove a customer from a Queue. (From a specific location)
                    104 or PCQ: Remove a served customer.
                    105 or VCS: View Customers Sorted in alphabetical order (Do not use library sort routine)
                    106 or SPD: Store Program Data into file.
                    107 or LPD: Load Program Data from file.
                    108 or STK: View Remaining burgers Stock.
                    109 or AFS: Add burgers to Stock.
                    999 or EXT: Exit the Program.""");
            String action = scanner.next();
            switch (action) {
                case "100", "VQF" -> printQueue();
                case "101", "VEQ" -> printEmptyQueues();
                case "102", "ACQ" -> addACustomer();
                case "103", "RCQ" -> removeACustomerSpecific();
                case "104", "PCQ" -> removeAServedCustomer();
                case "105", "VCS" -> {
                    System.out.println("105");
                }
                case "106", "SPD" -> {
                    System.out.println("106");
                }
                case "107", "LPD" -> {
                    System.out.println("107");
                }
                case "108", "STK" -> System.out.println(burgersAvailable);
                case "109", "AFS" -> addBurgers();
                case "999", "EXT" -> isRunning = false;
                default -> System.out.println("enter a valid action");
            }

        }

    }

    //Queue related methods
    public static void printQueue() {
        System.out.println("*".repeat(16) + "\n" + "*   cashiers   *" + "\n" + "*".repeat(16));
        for (int i = 0; i <= 5; i++) {
            if (i < 2) System.out.print("  " + queue1[i] + " ");
            if (i < 3) {
                if (i == 2) System.out.print("       " + queue2[i] + "   ");
                else System.out.print("   " + queue2[i] + "   ");
            }
            if (i < 5) {
                if (i >= 3) System.out.print("            " + queue3[i] + " ");
                else System.out.print(" " + queue3[i] + " ");
            }
            System.out.println();
        }
    }

    public static void printEmptyQueues(){

        System.out.println("*".repeat(16) + "\n" + "*   cashiers   *" + "\n" + "*".repeat(16));

        for (int i = 0; i <= 5; i++) {
            if (i < 2 && arrayConverterAndXFinder(queue1)) System.out.print("  " + queue1[i] + " ");
            else System.out.print("    ");
            if (i < 3 && arrayConverterAndXFinder(queue2)) System.out.print("   " + queue2[i] + "   ");
            else System.out.print("       ");
            if (i < 5 && arrayConverterAndXFinder(queue3)) System.out.print(" " + queue3[i] + " ");
            System.out.println();
        }
    }
    public static void UpdateQueueAdding(int i, int queueNum) {/// this method changes the position of queue from "X" to "O".
        if (queueNum == 1) queue1[i] = "O";
        else if (queueNum == 2) queue2[i] = "O";
        else if (queueNum == 3) queue3[i] = "O";
    }

    //customer related methods
    public static void addCustomers(String name, int queueNum) {// this method checks if we can add a customer and adding a customer.
        if (queueNum == 1 && arrayConverterAndXFinder(queue1Customers)) CustomerRelated(queueNum, queue1Customers, name);
        else if (queueNum == 1) System.out.println("Queue 1 is full.");
        else if (queueNum == 2 && arrayConverterAndXFinder(queue2Customers)) CustomerRelated(queueNum, queue2Customers, name);
        else if (queueNum == 2) System.out.println("Queue 2 is full.");
        else if (queueNum == 3 && arrayConverterAndXFinder(queue3Customers)) CustomerRelated(queueNum, queue3Customers, name);
        else if (queueNum == 3) System.out.println("Queue 3 is full. ");
    }

    public static void CustomerRelated(int queueNum, String[] Queue, String name) {//this method update the changes when adding a customer.
        for (int i = 0; i < Queue.length; i++) {
            if (Objects.equals(Queue[i], "X")) {
                Queue[i] = name;
                UpdateQueueAdding(i, queueNum);
                removeBurgers();
                break;
            }
        }
    }

    public static void addACustomer() {//this method prompts the details and verify the detailes
        System.out.println("Enter a queue number: ");
        String queueNum = scanner.next();
        System.out.println("Enter the Name of the customer: ");
        String name = scanner.next();
        if (!isInteger(queueNum, 4)) {
            System.out.println("Enter valid Queue number ");
        } else if (isInteger(queueNum, 4)) {
            addCustomers(name, Integer.parseInt(queueNum));
        }
    }


    public static void removeACustomerSpecific(){
        System.out.println("Enter the Queue number :");
        String queueNum = scanner.next();
        if (!isInteger(queueNum,4)) {
            System.out.println("Enter valid Queue number ");
        }else if (Objects.equals(queueNum, String.valueOf(1))){
            System.out.println("Enter which position you wanted to remove ");
            String position = scanner.next();
            if (isInteger(position,3)){
                removeACustomer(Integer.parseInt(position),Integer.parseInt(queueNum));
            }else {
                System.out.println("position not found");
            }
        }else if (Objects.equals(queueNum, String.valueOf(2))){
            System.out.println("Enter which position you wanted to remove ");
            String position = scanner.next();
            if (isInteger(position,4)){
                removeACustomer(Integer.parseInt(position),Integer.parseInt(queueNum));
            }else {
                System.out.println("position not found");
            }
        }else if (Objects.equals(queueNum, String.valueOf(3))){
            System.out.println("Enter which position you wanted to remove ");
            String position = scanner.next();
            if (isInteger(position,6)){
                removeACustomer(Integer.parseInt(position),Integer.parseInt(queueNum));
            }else {
                System.out.println("position not found");
            }
        }
    }
    public static void removeAServedCustomer(){
        System.out.println("Enter a queue number: ");
        String queueNum = scanner.next();
        if (isInteger(queueNum,4)){
            removeACustomer(1, Integer.parseInt(queueNum));
            removeBurgers();
        }else {
            System.out.println("Enter a valid queue number: ");
        }
    }
    public static void removeACustomer(int position, int queueNum) {//moving the array when removing a customer.
        String[] queueCustomers;
        String[] queue;
        switch (queueNum) {
            case 1 -> {
                queueCustomers = queue1Customers;
                queue = queue1;
            }
            case 2 -> {
                queueCustomers = queue2Customers;
                queue = queue2;
            }
            case 3 -> {
                queueCustomers = queue3Customers;
                queue = queue3;
            }
            default -> {
                return;
            }

        }
        queueCustomers[position - 1] = "X";
        for (int i = position - 1; i < queueCustomers.length - 1; i++) {
            queueCustomers[i] = queueCustomers[i + 1];
        }
        for (int i = position - 1; i < queue.length - 1; i++) {
            queue[i] = queue[i + 1];
        }
        queue[queue.length - 1] = "X";
    }

    //burger related methods

    public static void addBurgers(){
        System.out.println("how many burgers need to add to the stock?");
        int numOfBurgers= scanner.nextInt();
        if ((numOfBurgers+burgersAvailable)<=maxBurgerStock) burgersAvailable+=numOfBurgers;
        else if((numOfBurgers+burgersAvailable)>maxBurgerStock){
            int maxBurgers= maxBurgerStock-burgersAvailable;
            System.out.println("Only possible to store "+maxBurgers+ " burgers");
        }
    }
    public static void removeBurgers(){
        burgersAvailable -= 5;
    }
    //other methods
    private static boolean isInteger(String x ,int maxValue) {
        boolean i = false;
        try {
            int y= Integer.parseInt(x);
            if(y<maxValue &&y>0 ){
                i =true;
            }
            return i;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean arrayConverterAndXFinder(String[] array) {
        List<String> list = Arrays.asList(array);
        return list.contains("X");
    }
}