package masternamebattler.Util.UserInput;

import java.util.ArrayList;
import java.util.Scanner;

import masternamebattler.GameManager;
import masternamebattler.Console.ConsoleManager;
import masternamebattler.Util.UtilConstants;

import java.util.Collections;
import java.util.NoSuchElementException;

public  class UserInput{
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputIntValidValues(ArrayList<Integer> validValues){
        Collections.sort(validValues);
        int inputInt = 0;
        do{
            inputInt = inputInt();
            if(validValues.contains(inputInt)){
                break;
            }
            GameManager.consoleManager.addLogText(UtilConstants.INVALID_RANGE_MESSAGE);
        }while(!validValues.contains(inputInt));
        return inputInt;
    }

    public static String inputString(){
        String inputString = "";
        while(inputString.isEmpty()){
            try {
                inputString = scanner.nextLine();
            } catch (NoSuchElementException e) {
                GameManager.consoleManager.addLogText(UtilConstants.NO_ELEMENT_MESSAGE);
            } catch (IllegalStateException e) {
                GameManager.consoleManager.addLogText(UtilConstants.SCANNER_CLOSED_MESSAGE);
                break;
            }

            if(inputString.isEmpty()){
                GameManager.consoleManager.addLogText(UtilConstants.NO_ELEMENT_MESSAGE);
            }
        }
        return inputString;
    }

    public static int inputInt(){
        int inputInt = 0;
        boolean validInput = false;
        do{
            String input = inputString();
            try {
                inputInt = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                GameManager.consoleManager.addLogText(UtilConstants.INVALID_INTEGER_MESSAGE);
            }
        } while(!validInput);
        return inputInt;
    }

    public static void waitForEnter() {
        GameManager.consoleManager.addLogText(UtilConstants.WAIT_ENTER);
        scanner.nextLine();
    }

    public static void closeScanner() {
        scanner.close();
    }
}
