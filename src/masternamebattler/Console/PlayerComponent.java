package masternamebattler.Console;

import java.util.ArrayList;
import java.util.List;
import masternamebattler.Chara.Player;

public class PlayerComponent extends ConsoleComponent {

    List<List<ConsoleComponent>> components = new ArrayList<>();
    ConsoleComponent nameComponent = new ConsoleComponent();
    ConsoleComponent statusComponent = new ConsoleComponent();
    ConsoleComponent statusChangeComponent = new ConsoleComponent();
    ConsoleComponent ConditionComponent = new ConsoleComponent();
    Player beforPlayer;
    String text = "";

    public PlayerComponent() {
        int row = 0;
        int column = 0;
        addComponent(nameComponent, row, column);
        row++;
        addComponent(statusComponent, row, column);
        addComponent(statusChangeComponent, row, column + 1);
        row++;
        addComponent(ConditionComponent, row, column);
    }

    @Override
    public void setText(Player player) {
        setTextForNameComponent(player);
        setTextForStatusComponent(player);
        setTextForStatusChangeComponent(player);
        setTextForConditionComponent(player);
        beforPlayer = player;
    }

    private void setTextForNameComponent(Player player) {
        text = String.format("%s (%s) ", player.getName(), player.getDisplayJobName());
        nameComponent.setText(text);
    }

    private void setTextForConditionComponent(Player player) {
        String text = String.format("isAttacked : %s\nTeam : %s\n Condition : %s\n Tactics : %s",
                player.getIsAttacked(), player.getTeam(), player.getCondition(), player.getTactics().getDisplayName());
        ConditionComponent.setText(text);
    }

    private void setTextForStatusComponent(Player player) {
        text = String.format("HP : %d\nMP : %d\nSTR : %d\nDEF : %d\nAGI : %d\nLUCK : %d\n",
                player.getHp(), player.getMp(), player.getStr(), player.getDef(), player.getAgi(), player.getLuck());
        statusComponent.setText(text);
    }

    private void setTextForStatusChangeComponent(Player player) {
        if(beforPlayer != null) {
            setDistanceText(beforPlayer.getHp(), player.getHp());
            setDistanceText(beforPlayer.getMp(), player.getMp());
            setDistanceText(beforPlayer.getStr(), player.getStr());
            setDistanceText(beforPlayer.getDef(), player.getDef());
            setDistanceText(beforPlayer.getAgi(), player.getAgi());
            setDistanceText(beforPlayer.getLuck(), player.getLuck());
        }
    }
    
    private void setDistanceText(int befrStatus, int afterStatus) {
        statusChangeComponent.clearText();
        if(!(befrStatus == afterStatus)) {
            int distance = befrStatus - afterStatus;
            statusChangeComponent.addText(String.valueOf(distance));
        }
        statusChangeComponent.addText("\n");
    }
}
