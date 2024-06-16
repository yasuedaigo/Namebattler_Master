package masternamebattler.Chara;

import masternamebattler.GameConstants;
import masternamebattler.Tactics.Tactics;

/**
 *  職業のひとつ「忍者」
 *     麻痺しない
 */
public class Ninja extends Player {
    // 表示名
    public static final String DISPLAY_NAME = CharaConstants.Ninja.DISPLAY_NAME;

    /**
     * コンストラクタ
     * @param name プレイヤーの名前
     * @param team プレイヤーの所属するチーム
     * @param characterType 職業を表す列挙子
     * @param tactics 作戦を表す列挙子
     */
    public Ninja(String name,GameConstants.Teams team,CharacterType characterType,Tactics tactics) {
        super(name,team,characterType,tactics);
    }

    /**
     * 名前からステータスを計算してセットする
     */
    @Override
    protected void setStatsu(String name){
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
    public void attack() {
        normalAttack();
    }

    /**
     * @return 職業の表示名
     */
    @Override
    public String getDisplayJobName() {
        return DISPLAY_NAME;
    }
}
