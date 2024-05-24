package masternamebattler.Chara;

import masternamebattler.GameConstants;
import masternamebattler.GlobalConstants;

/**
 * 忍者クラス
 * 麻痺しない特徴を持つ
 */
public class Ninja extends Player {
    // 忍者のステータスの最大値、最小値
    public static final String DISPLAY_NAME = CharaConstants.Ninja.DISPLAY_NAME;

    /**
     * コンストラクタ
     * @param team プレイヤーの所属するチーム
     */
    public Ninja(GameConstants.Teams team) {
        super(team);
    }

    /**
     * ステータスを計算してセットする
     */
    @Override
    public void setStatsu(){
        this.hp = calcStatus(this.name, CharaConstants.Ninja.MAX_HP, CharaConstants.Ninja.MIN_HP, CharaConstants.HP_INDEX);
        this.mp = calcStatus(this.name, CharaConstants.Ninja.MAX_MP, CharaConstants.Ninja.MIN_MP, CharaConstants.MP_INDEX);
        this.str = calcStatus(this.name, CharaConstants.Ninja.MAX_STR, CharaConstants.Ninja.MIN_STR, CharaConstants.STR_INDEX);
        this.def = calcStatus(this.name, CharaConstants.Ninja.MAX_DEF, CharaConstants.Ninja.MIN_DEF, CharaConstants.DEF_INDEX);
        this.agi = calcStatus(this.name, CharaConstants.Ninja.MAX_AGI, CharaConstants.Ninja.MIN_AGI, CharaConstants.AGI_INDEX);
        this.luck = calcStatus(this.name, CharaConstants.Ninja.MAX_LUCK, CharaConstants.Ninja.MIN_LUCK, CharaConstants.LUCK_INDEX);
    }

    /**
     * 攻撃処理
     * 麻痺しないため、通常攻撃のみを行う
     */
    public void attack(Player enemy) {
        normalAttack(enemy);
    }

    /**
     * @return 表示名
     */
    @Override
    public String getDisplayJobName() {
        return DISPLAY_NAME;
    }

    /**
     * @return 麻痺しないため常にfalse
     */
    @Override
    public boolean isIncapacitationForParalysis(){
        return false;
    }
}
