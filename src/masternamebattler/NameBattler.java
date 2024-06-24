package masternamebattler;

import masternamebattler.Util.UserInput.UserInput;

/**
 * 
 * メインクラス
 */
public class NameBattler {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.start();
        UserInput.closeScanner();
    }
}