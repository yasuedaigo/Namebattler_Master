package masternamebattler.Console;

import masternamebattler.GameConstants;
import masternamebattler.GlobalConstants;
import masternamebattler.Party;
import masternamebattler.Chara.Player;

import java.util.List;
import java.util.ArrayList;
import masternamebattler.GameConstants.Teams;

public class ConsoleManager {
    private static final String ANSI_CLEAR = "\033[H\033[2J";
    private static final String ANSI_HOME = "\u001B[H";
    private static final int MAX_LOG_LINES = 10; // ログの最大表示行数

    public List<String> logList = new ArrayList<>();

    ConsoleComponent baseComponent = new ConsoleComponent();
    ConsoleComponent topComponent = new ConsoleComponent();
    ConsoleComponent playerTeamTagComponent = new ConsoleComponent();
    ConsoleComponent playerTeamBaseComponent = new ConsoleComponent();
    ConsoleComponent partitinComponent = new ConsoleComponent();
    ConsoleComponent enemyTeamBaseComponent = new ConsoleComponent();
    ConsoleComponent enemyTeamTagComponent = new ConsoleComponent();
    ConsoleComponent playLogComponent = new ConsoleComponent();

    public ConsoleManager(){
        buildComponent();
        partitinComponent.setText(GlobalConstants.PARTITION);
    }

    public void buildComponent() {
        buildTeamBaseComponent(playerTeamBaseComponent);
        buildTeamBaseComponent(enemyTeamBaseComponent);

        int row = 0;
        int column = 0;
        baseComponent.addComponent(topComponent, row, column);
        row++;
        baseComponent.addComponent(playerTeamTagComponent, row, column);
        row++;
        baseComponent.addComponent(playerTeamBaseComponent, row, column);
        row++;
        baseComponent.addComponent(partitinComponent, row, column);
        row++;
        baseComponent.addComponent(enemyTeamBaseComponent, row, column);
        row++;
        baseComponent.addComponent(enemyTeamTagComponent, row, column);
        row++;
        baseComponent.addComponent(partitinComponent, row, column);
        row++;
        baseComponent.addComponent(playLogComponent, row, column);
    }

    public void setTextAllComponent(Party party) {
        topComponent.setText("topComponent");
        playerTeamTagComponent.setText("Player");
        enemyTeamTagComponent.setText("ENEMY");
        setTextPlayerComponent(party);
    }

    public void setTextPlayerComponent(Party party) {
        List<ConsoleComponent> playerComponentList = playerTeamBaseComponent.components.get(0);
        List<ConsoleComponent> enemyComponentList = enemyTeamBaseComponent.components.get(0);
        for (int i = 0; i < GameConstants.NUMBER_OF_TEAM_MEMBERS; i++) {
            Player player = party.getPlayer(Teams.PLAYER, i);
            if (player != null) {
                playerComponentList.get(i).setText(player);
            }
        }
        for (int i = 0; i < GameConstants.NUMBER_OF_TEAM_MEMBERS; i++) {
            Player enemy = party.getPlayer(Teams.ENEMY, i);
            if (enemy != null) {
                enemyComponentList.get(i).setText(enemy);
            }
        }
    }

    public void printLog(Party party) {
        clearConsole();
        setTextAllComponent(party);
        baseComponent.print(1, 2, 100); // widthを適切に設定
    }

    public void addLogText(String log) {
        logList.add(log);
        if (logList.size() > MAX_LOG_LINES) {
            logList.remove(0); // 古いログを削除
        }
        StringBuilder logText = new StringBuilder();
        for (String logEntry : logList) {
            logText.append(logEntry).append("\n");
        }
        playLogComponent.setText(logText.toString());
        baseComponent.print(1, 2, 100);
    }

    public void buildTeamBaseComponent(ConsoleComponent teamBaseConsoleComponent) {
        int row = 0;
        for (int i = 0; i < GameConstants.NUMBER_OF_TEAM_MEMBERS; i++) {
            PlayerComponent playerComponent = new PlayerComponent();
            teamBaseConsoleComponent.addComponent(playerComponent, row, i);
        }
    }

    /**
     * コンソールをクリアしてカーソルをホーム位置に移動
     */
    public void clearConsole() {
        System.out.print(ANSI_CLEAR);
        System.out.print(ANSI_HOME);
    }
}
