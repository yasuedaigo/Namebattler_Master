package masternamebattler.Console;

import masternamebattler.GameConstants;
import masternamebattler.GlobalConstants;
import masternamebattler.Party;
import masternamebattler.Chara.Player;

import java.util.List;
import java.util.ArrayList;
import masternamebattler.GameConstants.Teams;
import java.util.stream.Collectors;

/**
 * コンソール表示を管理するクラス
 */
public class ConsoleManager {

    // ログのテキストリスト
    public List<String> logList = new ArrayList<>();

    // ベースのコンポーネント
    ConsoleComponent baseComponent = new ConsoleComponent();
    // 1行目のコンポーネント
    ConsoleComponent topComponent = new ConsoleComponent();
    // プレイヤーのチームタグコンポーネント
    ConsoleComponent playerTeamTagComponent = new ConsoleComponent();
    // プレイヤーチームのベースコンポーネント
    ConsoleComponent playerTeamBaseComponent = new ConsoleComponent();
    // 仕切りコンポーネント
    ConsoleComponent partitinComponent = new ConsoleComponent();
    // 敵チームのベースコンポーネント
    ConsoleComponent enemyTeamBaseComponent = new ConsoleComponent();
    // 敵のチームタグコンポーネント
    ConsoleComponent enemyTeamTagComponent = new ConsoleComponent();
    // プレイログのコンポーネント
    ConsoleComponent playLogComponent = new ConsoleComponent();

    /**
     * コンストラクタ
     * コンポーネントを構築
     * パーティションのテキストを設定
     */
    public ConsoleManager() {
        buildComponent();

        partitinComponent.setText(GlobalConstants.PARTITION);
        topComponent.setText(ConsoleConstants.TITLE_MESSAGE);
        playerTeamTagComponent.setText("Player");
        enemyTeamTagComponent.setText("ENEMY");
    }

    /**
     * コンポーネントを構築
     */
    public void buildComponent() {
        // それぞれのチームベースコンポーネントを構築
        buildTeamBaseComponent(playerTeamBaseComponent);
        buildTeamBaseComponent(enemyTeamBaseComponent);

        // 1行目1列目から順にコンポーネントを追加
        int row = 0;
        int column = 0;
        // 1行目
        baseComponent.addComponent(topComponent, row, column);
        row++;
        // 2行目
        baseComponent.addComponent(playerTeamTagComponent, row, column);
        row++;
        // 3行目
        baseComponent.addComponent(playerTeamBaseComponent, row, column);
        row++;
        // 4行目
        baseComponent.addComponent(partitinComponent, row, column);
        row++;
        // 5行目
        baseComponent.addComponent(enemyTeamBaseComponent, row, column);
        row++;
        // 6行目
        baseComponent.addComponent(enemyTeamTagComponent, row, column);
        row++;
        // 7行目
        baseComponent.addComponent(partitinComponent, row, column);
        row++;
        // 8行目
        baseComponent.addComponent(playLogComponent, row, column);
    }

    /**
     * 全てのプレイヤーコンポーネントにプレイヤー情報を設定
     * 
     * @param party
     */
    public void setPlayerToPlayerComponent(Party party) {
        List<ConsoleComponent> playerComponentList = playerTeamBaseComponent.components.get(0);
        List<ConsoleComponent> enemyComponentList = enemyTeamBaseComponent.components.get(0);
        List<Player> members = party.getMembers();

        // 1つ1つのプレイヤーコンポーネントにプレイヤー情報を設定
        for (int i = 0; i < GameConstants.NUMBER_OF_TEAM_MEMBERS; i++) {
            List<Player> playerList = members.stream().filter(p -> p.getTeam() == Teams.PLAYER)
                    .collect(Collectors.toList());

            Player player = playerList.get(i);
            if (player != null) {
                playerComponentList.get(i).setPlayer(player);
            }
        }
        // 敵のプレイヤーコンポーネントにプレイヤー情報を設定
        for (int i = 0; i < GameConstants.NUMBER_OF_TEAM_MEMBERS; i++) {
            List<Player> enemyList = members.stream().filter(p -> p.getTeam() == Teams.ENEMY)
                    .collect(Collectors.toList());
            Player enemy = enemyList.get(i);

            if (enemy != null) {
                enemyComponentList.get(i).setPlayer(enemy);
            }
        }
    }

    /**
     * 印字処理。コンソールをクリアしてから全てのコンポーネントにテキストを設定して印字
     * 
     * @param party
     */
    public void printLog() {
        clearConsole();
        baseComponent.print(ConsoleConstants.START_ROW, ConsoleConstants.START_COLUMN, ConsoleConstants.MAX_WIDTH);
    }

    /**
     * ログテキストを追加
     * 
     * @param log
     */
    public void addLogText(String log) {
        logList.add(log);

        // ログの行数が最大行数を超えた場合、最初の行を削除
        if (logList.size() > ConsoleConstants.MAX_LOG_LINES) {
            logList.remove(0);
        }

        // ログリストを改行コードで連結してplayLogComponentに設定
        StringBuilder logText = new StringBuilder();
        for (String logEntry : logList) {
            logText.append(logEntry);
            logText.append(ConsoleConstants.LINE_BREAK);
        }

        playLogComponent.setText(logText.toString());
        printLog();
    }

    /**
     * プレイヤーチームのベースコンポーネントを構築
     * 
     * @param teamBaseConsoleComponent
     */
    public void buildTeamBaseComponent(ConsoleComponent teamBaseConsoleComponent) {
        // 行を保持する変数。0で初期化
        int row = 0;
        
        // チームメンバーの数分繰り返す
        for (int i = 0; i < GameConstants.NUMBER_OF_TEAM_MEMBERS; i++) {
            // プレイヤーコンポーネントを作成し、teamBaseConsoleComponentに追加
            PlayerComponent playerComponent = new PlayerComponent();
            teamBaseConsoleComponent.addComponent(playerComponent, row, i);
        }
    }

    /**
     * コンソールをクリアしてカーソルをホーム位置に移動
     */
    public void clearConsole() {
        System.out.print(ConsoleConstants.ANSI_RESET_CONSOLE);
    }

    public void printPlayerDiff() {

    }
}
