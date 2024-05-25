package masternamebattler.Console;

import masternamebattler.GameConstants.Teams;
import masternamebattler.Chara.Player;
import masternamebattler.Party;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleManager {
    // カラムの幅
    private static final int COLUMN_WIDTH = 20;

    // ANSIエスケープシーケンス
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CLEAR = "\u001B[2J";
    private static final String ANSI_HOME = "\u001B[H";
    private static final String ANSI_SAVE_CURSOR = "\u001B[s";
    private static final String ANSI_RESTORE_CURSOR = "\u001B[u";

    /**
     * コンソールをクリアしてカーソルをホーム位置に移動
     */
    public void clearConsole() {
        System.out.print(ANSI_CLEAR);
        System.out.print(ANSI_HOME);
    }

    /**
     * カーソル位置を保存
     */
    public void saveCursorPosition() {
        System.out.print(ANSI_SAVE_CURSOR);
    }

    /**
     * 保存されたカーソル位置を復元
     */
    public void restoreCursorPosition() {
        System.out.print(ANSI_RESTORE_CURSOR);
    }

    /**
     * 指定した行にカーソルを移動
     * @param row 行番号（1から始まる）
     */
    public void moveToRow(int row) {
        System.out.printf("\u001B[%d;1H", row);
    }

    /**
     * メッセージを指定した行に表示
     * @param row 行番号（1から始まる）
     * @param message 表示するメッセージ
     */
    public void printAtRow(int row, String message) {
        moveToRow(row);
        System.out.print(message);
    }

    /**
     * パーティーメンバーの情報を表示する
     * @param party パーティー
     */
    public static void printParty(Party party) {
        List<Player> members = party.getMembers();

        // Separate players by teams
        List<Player> playerTeamMembers = members.stream()
                .filter(player -> player.getTeam() == Teams.PLAYER)
                .collect(Collectors.toList());

        List<Player> enemyTeamMembers = members.stream()
                .filter(player -> player.getTeam() == Teams.ENEMY)
                .collect(Collectors.toList());

        // Collect player information in columns
        String[] playerColumns = getPlayerColumns(playerTeamMembers);
        String[] enemyColumns = getPlayerColumns(enemyTeamMembers);

        // Print player team
        System.out.println("PLAYER TEAM");
        for (String line : playerColumns) {
            System.out.println(line);
        }

        System.out.println("\n****************************************\n");

        // Print enemy team
        System.out.println("ENEMY TEAM");
        for (String line : enemyColumns) {
            System.out.println(line);
        }
    }

    /**
     * プレイヤー情報を列ごとに取得する
     * @param players プレイヤーリスト
     * @return 各列のプレイヤー情報
     */
    private static String[] getPlayerColumns(List<Player> players) {
        int maxLines = 11; // Number of lines of player information
        String[] columns = new String[maxLines];

        for (int i = 0; i < maxLines; i++) {
            columns[i] = "";
        }

        for (Player player : players) {
            columns[0] += padRight(player.getName() + " (" + player.getDisplayJobName() + ")", COLUMN_WIDTH);
            columns[1] += padRight(" HP: " + player.hp, COLUMN_WIDTH);
            columns[2] += padRight(" MP: " + player.mp, COLUMN_WIDTH);
            columns[3] += padRight(" STR: " + player.str, COLUMN_WIDTH);
            columns[4] += padRight(" DEF: " + player.def, COLUMN_WIDTH);
            columns[5] += padRight(" AGI: " + player.agi, COLUMN_WIDTH);
            columns[6] += padRight(" LUCK: " + player.luck, COLUMN_WIDTH);
            columns[7] += padRight(" isLive: " + player.getIsLive(), COLUMN_WIDTH);
            columns[8] += padRight(" isAttacked: " + player.getIsAttacked(), COLUMN_WIDTH);
            columns[9] += padRight(" Team: " + player.getTeam(), COLUMN_WIDTH);
            columns[10] += padRight(" Condition: " + (player.getCondition() != null ? player.getCondition().getDisplayName() : "None"), COLUMN_WIDTH);
        }

        return columns;
    }

    /**
     * 指定された幅で文字列を右に詰める
     * @param s 文字列
     * @param n 幅
     * @return 幅を揃えた文字列
     */
    private static String padRight(String s, int n) {
        int len = getStringWidth(s);
        if (len < n) {
            return s + " ".repeat(n - len);
        }
        return s;
    }

    /**
     * 文字列の幅を計算する
     * 全角文字は2として計算する
     * @param s 文字列
     * @return 幅
     */
    private static int getStringWidth(String s) {
        int width = 0;
        for (char c : s.toCharArray()) {
            if (String.valueOf(c).getBytes().length > 1) {
                width += 2;
            } else {
                width += 1;
            }
        }
        return width;
    }
}

