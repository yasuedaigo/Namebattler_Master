package masternamebattler.Console;

import java.util.ArrayList;
import java.util.List;
import masternamebattler.Chara.Player;

public class ConsoleComponent {

    List<List<ConsoleComponent>> components = new ArrayList<>();
    String text = "";

    public void addComponent(ConsoleComponent component, int row, int column) {
        while (components.size() <= row) {
            components.add(new ArrayList<>());
        }

        List<ConsoleComponent> componentRow = components.get(row);
        while (componentRow.size() <= column) {
            componentRow.add(new ConsoleComponent());
        }

        componentRow.set(column, component);
    }

    public void addComponent(ConsoleComponent component) {
        ArrayList<ConsoleComponent> list = new ArrayList<>();
        list.add(component);
        components.add(list);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addText(String text) {
        this.text += text;
    }

    public void print(int startRow, int startColumn, int width) {
        if (components != null && !components.isEmpty()) {
            printComponents(startRow, startColumn, width);
            return;
        }

        if (text != null && !text.isEmpty()) {
            printText(startRow, startColumn, width);
        }
    }

    public void printComponents(int startRow, int startColumn, int width){
        int currentRow = startRow;
        for (int i = 0; i < components.size(); i++) {
            List<ConsoleComponent> componentRow = components.get(i);
            if (!componentRow.isEmpty()) {
                int widthPerComponent = width / componentRow.size();
                int maxRowCount = 0;
                for (int j = 0; j < componentRow.size(); j++) {
                    ConsoleComponent comp = componentRow.get(j);
                    comp.print(currentRow, startColumn + (j * widthPerComponent), widthPerComponent);
                    int rowCount = comp.getTotalRowCount(widthPerComponent);
                    if (rowCount > maxRowCount) {
                        maxRowCount = rowCount;
                    }
                }
                currentRow += maxRowCount;
            }
        }
    }

    public void printText(int startRow, int startColumn, int width){
        String[] lines = text.split("\n");
            for (String line : lines) {
                moveTo(startRow, startColumn);
                System.out.print(String.format("%-" + width + "s", line));
                startRow++;
            }
    }

    public int getTotalRowCount(int width) {
        int totalRows = 0;

        if (components != null && !components.isEmpty()) {
            for (List<ConsoleComponent> componentRow : components) {
                int maxRowCount = 0;
                for (ConsoleComponent comp : componentRow) {
                    if (comp != null) {
                        int rowCount = comp.getTotalRowCount(width / componentRow.size());
                        if (rowCount > maxRowCount) {
                            maxRowCount = rowCount;
                        }
                    }
                }
                totalRows += maxRowCount;
            }
        }

        if (text != null && !text.isEmpty()) {
            totalRows += text.split("\n").length;
        }

        return totalRows;
    }

    /**
     * 指定された行と列にカーソルを移動する
     * @param row 移動する行（1から始まる）
     * @param col 移動する列（0から始まる）
     */
    protected void moveTo(int row, int column) {
        System.out.print(String.format("\u001B[%d;%dH", row, column + 1));
    }

    public void setText(Player player){
        setText("失敗してます");
    }

    public void clearText(){
        text = "";
    }

    /*
    protected void setPlayerText(Player player) {
        if (player != null) {
            text = String.format("%s (%s)\n HP: %d\n MP: %d\n STR: %d\n DEF: %d\n AGI: %d\n LUCK: %d\n isLive: %b\n isAttacked: %b\n Team: %s\n Condition: %s",
                    player.getName(), player.getDisplayJobName(), player.getHp(), player.getMp(),
                    player.getStr(), player.getDef(), player.getAgi(), player.getLuck(),
                    player.getIsLive(), player.getIsAttacked(), player.getTeam(), player.getCondition() != null ? player.getCondition().getDisplayName() : "None");
        }
    }
    */
}
