package masternamebattler.Console;

import java.util.ArrayList;
import java.util.List;

import masternamebattler.GlobalConstants;
import masternamebattler.Chara.Player;

/**
 * プレイヤーコンポーネントクラス
 */
public class PlayerComponent extends ConsoleComponent {

    // 名前コンポーネント
    private ConsoleComponent nameComponent = new ConsoleComponent();
    // ステータスコンポーネント
    private ConsoleComponent statusComponent = new ConsoleComponent();
    // ステータス変化コンポーネント
    private ConsoleComponent beforAfterComponent = new ConsoleComponent();
    // コンディションコンポーネント
    private ConsoleComponent ConditionComponent = new ConsoleComponent();

    private Player player;
    // 前回のプレイヤー
    private Integer beforHp;
    private Integer beforMp;

    /**
     * コンストラクタ
     * コンポーネントを構築
     */
    public PlayerComponent() {
        //1行目1列目から順にコンポーネントを追加
        int row = 0;
        int column = 0;
        //1行目(名前)
        addComponent(nameComponent, row, column);
        row++;
        //2行目(ステータスとステータス変化)
        addComponent(statusComponent, row, column);
        addComponent(beforAfterComponent, row, column + 1);
        row++;
        //3行目(コンディション)
        addComponent(ConditionComponent, row, column);
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    
    public void print(int startRow, int startColumn, int width) {
        setPlayerText();
        super.print(startRow, startColumn, width);
    }

    /**
     * プレイヤーの情報を設定する
     */
    @Override
    public void setPlayerText() {
        if(player == null) {
            return;
        }
        setTextForNameComponent();
        setTextForStatusComponent();
        setTextForBeforAfterComponent();
        setTextForConditionComponent();
        //現在のプレイヤーを保存
        beforHp = player.getHp();
        beforMp = player.getMp();
    }

    /**
     * 名前コンポーネントにテキストを設定する
     */
    private void setTextForNameComponent() {
        String text = String.format(ConsoleConstants.PlayerComponent.NAME_MESSAGE, player.getName(), player.getDisplayJobName());
        nameComponent.setText(text);
    }

    /**
     * コンディションコンポーネントにテキストを設定する
     */
    private void setTextForConditionComponent() {
        String text = String.format(ConsoleConstants.PlayerComponent.CONDITION_MESSAGE,
                player.getIsAttacked(), player.getTeam(), player.getCondition(), player.getTactics().getDisplayName());
        ConditionComponent.setText(text);
    }

    /**
     * ステータスコンポーネントにテキストを設定する
     */
    private void setTextForStatusComponent() {
        String text = String.format(ConsoleConstants.PlayerComponent.STATUS_MESSAGE,
                player.getHp(), player.getMp(), player.getStr(), player.getDef(), player.getAgi(), player.getLuck());
        statusComponent.setText(text);
    }

    /**
     * ステータス変化コンポーネントにテキストを設定する
     */
    private void setTextForBeforAfterComponent() {
        if(beforHp == null || beforMp == null) {
            return;
        }
        String text = String.format(ConsoleConstants.PlayerComponent.BEFOR_AFTER_MESSAGE,
                getDiffText(beforHp, player.getHp()),getDiffText(beforMp, player.getMp()));
        beforAfterComponent.setText(text);
    }

    /**
     * ステータスの変化を取得する
     * @param befor 変化前のステータス
     * @param after 変化後のステータス
     * @return 変化量
     */
    private String getDiffText(int befor, int after) {
        int diff = after - befor;
        if(diff == 0) {
            return GlobalConstants.BLANK;
        }
        if(diff > 0) {
            return ConsoleConstants.PLUS_SIGN + diff;
        }
        return String.valueOf(diff);
    }

}
