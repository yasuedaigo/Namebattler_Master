package masternamebattler.Util.UserInput;

import java.util.ArrayList;
import java.util.Scanner;

import masternamebattler.GameManager;
import masternamebattler.Util.UtilConstants;

import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * ユーザーの入力に関する処理を行うクラス
 */
public class UserInput {
    // ユーザーの入力を受け付けるためのScannerクラスのインスタンスを生成
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * 数値のリストを受け取り、その値が入力されるまで入力を受け付ける。
     * 
     * @param validValues
     * @return 入力された数値
     */
    public static int inputIntValidValues(ArrayList<Integer> validValues) {
        // validValuesに含まれない値でinputIntを初期化する
        Collections.sort(validValues);
        int inputInt = validValues.get(0) - 1;

        do {
            inputInt = inputInt();

            // 入力された値がvalidValuesに含まれている場合、ループを抜ける
            if (validValues.contains(inputInt)) {
                break;
            }

            // 入力された値がvalidValuesに含まれていない場合、エラーメッセージを表示
            GameManager.consoleManager.addLogText(UtilConstants.INVALID_RANGE_MESSAGE);

            // 入力された値がvalidValuesに含まれている場合、ループを抜ける
        } while (!validValues.contains(inputInt));

        return inputInt;
    }

    /**
     * 標準入力で文字列を受け取る
     * 
     * @return 入力された文字列
     */
    public static String inputString() {
        // 空文字で初期化する
        String inputString = "";

        // 空文字以外の入力があるまでループ
        while (inputString.isEmpty()) {

            try {
                inputString = scanner.nextLine();
            } catch (NoSuchElementException e) {
                GameManager.consoleManager.addLogText(UtilConstants.NO_ELEMENT_MESSAGE);
            } catch (IllegalStateException e) {
                GameManager.consoleManager.addLogText(UtilConstants.SCANNER_CLOSED_MESSAGE);
                break;
            }

            // 空文字の場合、エラーメッセージを表示
            if (inputString.isEmpty()) {
                GameManager.consoleManager.addLogText(UtilConstants.NO_ELEMENT_MESSAGE);
            }
        }

        return inputString;
    }

    /**
     * 標準入力で整数を受け取る
     * 
     * @return 入力された整数
     */
    public static int inputInt() {
        // 0で初期化する
        int inputInt = 0;
        // 入力が正しいかどうか判定するフラグ
        boolean validInput = false;

        // 入力が正しくなるまでループ
        do {
            // 文字列を入力してもらい、整数に変換する。変換できたときにフラグを立てる
            String input = inputString();

            try {
                inputInt = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                // 整数に変換できなかった場合、エラーメッセージを表示
                GameManager.consoleManager.addLogText(UtilConstants.INVALID_INTEGER_MESSAGE);
            }
            
        } while (!validInput);

        return inputInt;
    }

    /**
     * 標準入力でEnterが押されるまで待つ
     */
    public static void waitForEnter() {
        // 入力待ちのメッセージを表示
        GameManager.consoleManager.addLogText(UtilConstants.WAIT_ENTER);
        // Enterが押されたら次に進む
        scanner.nextLine();
    }

    /**
     * Scannerを閉じる
     */
    public static void closeScanner() {
        scanner.close();
    }
}
