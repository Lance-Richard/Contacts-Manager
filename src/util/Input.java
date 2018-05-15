package util;

import java.util.Scanner;

public class Input {

    private Scanner scanner;

    //-----constructor------
    public Input(){
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public String getString(String prompt){
        String input;
        System.out.println(prompt);
        input = this.scanner.nextLine();
        return input;
    }

    public boolean yesNo(String prompt){
        String input;
        System.out.println(prompt);
        input = this.scanner.nextLine();
        return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
    }

    public int getInt(int min, int max) {
        System.out.println("Please, enter an integer between " + min + " and " + max + ".");
        String s = this.scanner.nextLine();
        int input;
        try {
            input = Integer.valueOf(s);
        } catch (NumberFormatException e){
            System.out.println("Must be an integer.");
            return getInt(min, max);
        }
        if (input > min && input < max) {
            return input;
        } else {
            return getInt(min, max);
        }
    }

    public int getInt() {
        System.out.println("Please, enter an integer");
        String s = this.scanner.nextLine();
        int num;
        try {
            num = Integer.valueOf(s);
        } catch (NumberFormatException e){
            System.out.println("Must be an integer.");
            return getInt();
        }
        return num;
    }

    public double getDouble(double min, double max){
        System.out.println("Please, enter a Number between " + min + " and " + max + ".");
        String s = this.scanner.nextLine();
        double input;
        try {
            input = Integer.valueOf(s);
        } catch (NumberFormatException e){
            System.out.println("Must be a Number.");
            return getDouble(min, max);
        }
        if (input > min && input < max) {
            return input;
        } else {
            return getDouble(min, max);
        }
    }

    public double getDouble() {
        System.out.println("Please, enter any number");
        String s = this.scanner.nextLine();
        double num;
        try {
            num = Double.valueOf(s);
        } catch (NumberFormatException e){
            System.out.println("Must be a Number.");
            return getDouble();
        }
        return num;
    }
}