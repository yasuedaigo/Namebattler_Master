package namebattler;

import namebattler.chara.Chara;
import java.util.Scanner;

public class GameManager {
    private static final String GET_INPUT_NAME_MESSAGE = "プレイヤー%sの名前を入力してください : ";
    private static final String BATTLE_START_MESSAGE = "=== バトル開始 ===";
    private static final String NEXT_TURN_MESSAGE = "- 次のターン -";
    private static final String PARTITION_LINE = "---";
    private static final String PLAYER_STATUS_MESSAGE = "%s: %s(HP %d)";
    private static final String RESULT_MESSAGE = "%sの勝利！！";
    private static final String BLANK_LINE = "";
    private Scanner scan = new Scanner(System.in);

    public static enum Teams {
        Player,
        Enemy
    };

    public void start() {
        Chara player = makeChara(Teams.Player);
        Chara enemy = makeChara(Teams.Enemy);
        player.showStatsu();
        enemy.showStatsu();
        showBlankLine();
        showBattleStartMessage();
        while (!isFinish(player, enemy)) {
            player.showStatsu();
            enemy.showStatsu();
            player.attack(enemy);
            if (enemy.getIsLive()) {
                enemy.attack(enemy);
            }
            if (!isFinish(player, enemy)) {
                showTurnFinishMessage(player, enemy);
            }
        }
        showResult(player, enemy);
    }

    private Chara makeChara(Teams team) {
        String name = this.getInputName(team);
        return new Chara(name, team);
    }

    private String getInputName(Teams team) {
        String name = "";
        while (isBlank(name)) {
            showGetInputNameMessage(team);
            name = scan.nextLine();
        }
        return name;
    }

    private void showGetInputNameMessage(Teams team) {
        System.out.print(String.format(GET_INPUT_NAME_MESSAGE, team.toString()));
    }

    private void showBattleStartMessage() {
        System.out.println(BATTLE_START_MESSAGE);
    }

    private void showNextTurnMessage() {
        System.out.println(NEXT_TURN_MESSAGE);
    }

    private void showBlankLine() {
        System.out.println(BLANK_LINE);
    }

    private void showPartitionLine() {
        System.out.println(PARTITION_LINE);
    }

    private void showPlayerStatusMessage(Chara chara) {
        System.out.println(String.format(PLAYER_STATUS_MESSAGE,
                chara.getTeam().toString(), chara.getName(), chara.getHp()));
    }

    private void showResultMessage(Chara chara) {
        System.out.print(String.format(RESULT_MESSAGE, chara.getName()));
    }

    private void showTurnFinishMessage(Chara player, Chara enemy) {
        showBlankLine();
        showNextTurnMessage();
        showPlayerStatusMessage(player);
        showPlayerStatusMessage(enemy);
        showBlankLine();
        showPartitionLine();
    }

    private boolean isBlank(String name) {
        return name.length() == 0;
    }

    private boolean isFinish(Chara player, Chara enemy) {
        if (player.isDown()) {
            return true;
        }
        if (enemy.isDown()) {
            return true;
        }
        return false;
    }

    private void showResult(Chara player, Chara enemy) {
        Chara winner = getWinner(player, enemy);
        showResultMessage(winner);
    }

    private Chara getWinner(Chara player, Chara enemy) {
        if (player.isDown()) {
            return enemy;
        }
        return player;
    }
}