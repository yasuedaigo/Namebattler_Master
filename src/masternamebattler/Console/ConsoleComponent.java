package masternamebattler.Console;

import java.util.ArrayList;
import java.util.List;
import masternamebattler.Chara.Player;

/**
 * コンソールコンポーネントクラス
 * ConsoleComponentの二次元リストをもつことでコンソール上に表示するコンポーネントの位置を管理する
 * 配下のコンポーネントやテキストを適切な位置に印字する
 */
public class ConsoleComponent {

    // コンポーネントの二次元リスト
    List<List<ConsoleComponent>> components = new ArrayList<>();
    // テキスト
    String text = "";

    /**
     * コンポーネントを追加する
     * 
     * @param component
     * @param row       行
     * @param column    列
     */
    public void addComponent(ConsoleComponent component, int row, int column) {
        // 行が足りない場合は追加
        while (components.size() <= row) {
            components.add(new ArrayList<>());
        }

        // 指定行のコンポーネントリストを取得
        List<ConsoleComponent> componentRow = components.get(row);
        // 列が足りない場合は追加
        while (componentRow.size() <= column) {
            componentRow.add(new ConsoleComponent());
        }

        // 指定列にコンポーネントを追加
        componentRow.set(column, component);
    }

    /**
     * コンポーネントを最終行に追加する
     * 
     * @param text
     */
    public void addComponent(ConsoleComponent component) {
        // ConsoleCmpoentのリストを作成し、受け取ったcomponentを追加
        ArrayList<ConsoleComponent> list = new ArrayList<>();
        list.add(component);
        // 作成したリストをcomponentsに追加
        components.add(list);
    }

    /**
     * テキストを設定する
     * 
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * テキストを追加する
     * 
     * @param text
     */
    public void addText(String text) {
        this.text += text;
    }

    /**
     * 印字処理
     * 
     * @param startRow    開始行
     * @param startColumn 開始列
     * @param width       幅
     */
    public void print(int startRow, int startColumn, int width) {
        // コンポーネントを持つ場合はコンポーネントの印字処理を行う。テキストの印字処理は行わない
        if (components != null && !components.isEmpty()) {
            printComponents(startRow, startColumn, width);
            return;
        }

        // テキストを持つ場合はテキストの印字処理を行う
        if (text != null && !text.isEmpty()) {
            printText(startRow, startColumn, width);
        }
    }

    /**
     * コンポーネントの印字処理
     * 
     * @param startRow    開始行
     * @param startColumn 開始列
     * @param width       幅
     */
    public void printComponents(int startRow, int startColumn, int width) {
        // startRowを保持する
        int currentRow = startRow;

        // componentsの行数分繰り返す
        for (int i = 0; i < components.size(); i++) {
            // componentsの行を取得
            List<ConsoleComponent> componentRow = components.get(i);

            if (!componentRow.isEmpty()) {
                // componentsの列数から、1つのコンポーネントの幅を計算
                int widthPerComponent = width / componentRow.size();
                // 1つの行の最大行数を保持する変数。0で初期化。
                int maxRowCount = 0;

                // componentRowの列数分繰り返す
                for (int j = 0; j < componentRow.size(); j++) {
                    ConsoleComponent comp = componentRow.get(j);
                    // 開始列に、コンポーネントの幅とコンポネントの順を掛けた値を加算して、コンポーネントの開始列を計算
                    int currentColumn = startColumn + (j * widthPerComponent);
                    // コンポーネントの印字処理をよびだす
                    comp.print(currentRow, currentColumn, widthPerComponent);
                    // コンポーネントの行数を計算して、maxRowCountと比較し、大きい方をmaxRowCountに代入
                    int rowCount = comp.getTotalRowCount(widthPerComponent);

                    //大きい方の行数をmaxRowCountに代入
                    if (rowCount > maxRowCount) {
                        maxRowCount = rowCount;
                    }
                }
                // 1つの行の最大行数を加算して、currentRowを更新
                currentRow += maxRowCount;
            }
        }
    }

    /**
     * テキストの印字処理
     * 
     * @param startRow    開始行
     * @param startColumn 開始列
     * @param width       幅
     */
    public void printText(int startRow, int startColumn, int width) {
        // テキストを改行で分割
        String[] lines = text.split(ConsoleConstants.LINE_BREAK);

        // 分割した行数分繰り返す
        for (String line : lines) {
            // 開始行と開始列にカーソルを移動し、テキストを印字
            moveTo(startRow, startColumn);

            // limneを指定幅に合わせて印字
            String format = String.format(ConsoleConstants.LEFT_ALIGN_FORMAT, width);
            System.out.print(String.format(format, line));

            // 開始行をインクリメント
            startRow++;
        }
    }

    /**
     * コンポーネントの行数を取得する
     * 
     * @param width
     * @return 印字する行数
     */
    public int getTotalRowCount(int width) {
        // 行数を保持する変数。0で初期化。
        int totalRows = 0;

        // componentsが空でない場合、componentsの行数分繰り返す
        if (components != null && !components.isEmpty()) {

            for (List<ConsoleComponent> componentRow : components) {
                // 1つの行の最大行数を保持する変数。0で初期化。
                int maxRowCount = 0;

                // componentRowの列数分繰り返す
                for (ConsoleComponent comp : componentRow) {

                    if (comp != null) {

                        // コンポーネントの行数を計算して、maxRowCountと比較し、大きい方をmaxRowCountに代入
                        int rowCount = comp.getTotalRowCount(width / componentRow.size());

                        if (rowCount > maxRowCount) {
                            maxRowCount = rowCount;
                        }
                    }
                }
                
                // 1つの行の最大行数を加算して、totalRowsを更新
                totalRows += maxRowCount;
            }
        }

        // textが空でない場合、textを改行で分割し、行数を計算
        if (text != null && !text.isEmpty()) {
            totalRows += text.split("\n").length;
        }

        return totalRows;
    }

    /**
     * 指定された行と列にカーソルを移動する
     * 
     * @param row    移動する行
     * @param column 移動する列
     */
    protected void moveTo(int row, int column) {
        System.out.print(String.format(ConsoleConstants.ANSI_MOVE_CURSOR, row, column + 1));
    }

    /**
     * PlayerComponentでオーバーライドする
     */
    public void setPlayerText() {
    }

    public void setPlayer(Player player) {
    }

    /**
     * テキストをクリアする
     */
    public void clearText() {
        text = "";
    }
}