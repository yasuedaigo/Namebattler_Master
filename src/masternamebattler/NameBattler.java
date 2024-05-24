package masternamebattler;

import java.util.Scanner;
import masternamebattler.Util.UserInput.UserInput;


public class NameBattler {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.start();
        UserInput.closeScanner();
    }
}