package masternamebattler.Console;

/**
 * コンソール処理に関する定数
 */
public class ConsoleConstants {
    // コンソール表示の最大幅
    public static final int MAX_WIDTH = 100;
    // コンソール表示の最大行数
    public static final int MAX_LOG_LINES = 10;
    // コンソール表示の開始行
    public static final int START_ROW = 5;
    // コンソール表示の開始列
    public static final int START_COLUMN = 2;

    // カーソルを指定した位置に移動する
    public static final String ANSI_MOVE_CURSOR = "\u001B[%d;%dH";
    // 改行コード
    public static final String LINE_BREAK = "\n";
    // 左寄せフォーマット
    public static final String LEFT_ALIGN_FORMAT = "%%-%ds";
    // コンソールをリセット
    public static final String ANSI_RESET_CONSOLE = "\033c";
    // タイトルメッセージ
    public static final String TITLE_MESSAGE = "ネームバトラー";
    // プラス
    public static final String PLUS_SIGN = "+";

    // プレイヤーコンポーネントに関する定数
    public static class PlayerComponent {
        public static final String NAME_MESSAGE = "%s (%s) ";
        public static final String CONDITION_MESSAGE = "isAttacked : %s\nTeam : %s\n Condition : %s\n Tactics : %s";
        public static final String STATUS_MESSAGE = "HP : %d\nMP : %d\nSTR : %d\nDEF : %d\nAGI : %d\nLUCK : %d\n";
        public static final String BEFOR_AFTER_MESSAGE = "%s\n%s\n";

    }
}
