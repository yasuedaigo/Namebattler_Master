package masternamebattler.Chara;

import masternamebattler.GameConstants;
import masternamebattler.GameManager;
import masternamebattler.Tactics.Tactics;

/**
 * 職業のひとつ「戦士」
 */
public class Fighter extends Player {
    // 表示名
    public static final String DISPLAY_NAME = CharaConstants.Fighter.DISPLAY_NAME;

    /**
     * コンストラクタ
     * 
     * @param name          プレイヤーの名前
     * @param team          プレイヤーの所属するチーム
     * @param characterType 職業を表す列挙子
     * @param tactics       作戦を表す列挙子
     */
    public Fighter(String name, GameConstants.Teams team, CharacterType characterType, Tactics tactics) {
        super(name, team, characterType, tactics);
    }

    /**
     * 名前からステータスを計算してセットする
     */
    @Override
    protected void setStatsu(String name) {
        this.hp = calcStatus(this.name, CharaConstants.Fighter.MAX_HP, CharaConstants.Fighter.MIN_HP,
                CharaConstants.HP_INDEX);
        this.mp = calcStatus(this.name, CharaConstants.Fighter.MAX_MP, CharaConstants.Fighter.MIN_MP,
                CharaConstants.MP_INDEX);
        this.str = calcStatus(this.name, CharaConstants.Fighter.MAX_STR, CharaConstants.Fighter.MIN_STR,
                CharaConstants.STR_INDEX);
        this.def = calcStatus(this.name, CharaConstants.Fighter.MAX_DEF, CharaConstants.Fighter.MIN_DEF,
                CharaConstants.DEF_INDEX);
        this.agi = calcStatus(this.name, CharaConstants.Fighter.MAX_AGI, CharaConstants.Fighter.MIN_AGI,
                CharaConstants.AGI_INDEX);
        this.luck = calcStatus(this.name, CharaConstants.Fighter.MAX_LUCK, CharaConstants.Fighter.MIN_LUCK,
                CharaConstants.LUCK_INDEX);
    }

    /**
     * 攻撃処理
     * 麻痺判定を行い、麻痺した場合は攻撃できない
     * 麻痺していない場合は通常攻撃を行う
     * 
     * @param enemy 攻撃対象のプレイヤー
     */
    @Override
    public void attack() {
        if (isIncapacitationForParalysis()) {
            GameManager.consoleManager.addLogText(String.format(CharaConstants.PARALYSIS_MESSAGE, this.name));
            return;
        }
        super.normalAttack();
    }

    /**
     * @return 職業名
     */
    @Override
    public String getDisplayJobName() {
        return DISPLAY_NAME;
    }
}