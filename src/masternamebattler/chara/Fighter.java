package masternamebattler.Chara;

import masternamebattler.GameConstants;
import masternamebattler.Tactics.Tactics;

/**
 * 戦士クラス
 */
public class Fighter extends Player {
    // 戦士のステータスの最大値、最小値
    public static final String DISPLAY_NAME = CharaConstants.Fighter.DISPLAY_NAME;
    
    /**
     * コンストラクタ
     * @param team プレイヤーの所属するチーム
     */
    public Fighter(String name,GameConstants.Teams team,CharacterType characterType,Tactics tactics) {
        super(name,team,characterType,tactics);
    }

    /**
     * ステータスを計算してセットする
     */
    @Override
    public void setStatsu(String name){
        this.hp = calcStatus(this.name, CharaConstants.Fighter.MAX_HP, CharaConstants.Fighter.MIN_HP, CharaConstants.HP_INDEX);
        this.mp = calcStatus(this.name, CharaConstants.Fighter.MAX_MP, CharaConstants.Fighter.MIN_MP, CharaConstants.MP_INDEX);
        this.str = calcStatus(this.name, CharaConstants.Fighter.MAX_STR, CharaConstants.Fighter.MIN_STR, CharaConstants.STR_INDEX);
        this.def = calcStatus(this.name, CharaConstants.Fighter.MAX_DEF, CharaConstants.Fighter.MIN_DEF, CharaConstants.DEF_INDEX);
        this.agi = calcStatus(this.name, CharaConstants.Fighter.MAX_AGI, CharaConstants.Fighter.MIN_AGI, CharaConstants.AGI_INDEX);
        this.luck = calcStatus(this.name, CharaConstants.Fighter.MAX_LUCK, CharaConstants.Fighter.MIN_LUCK, CharaConstants.LUCK_INDEX);
    }

    /**
     * @return 戦士の職業名
     */
    @Override
    public String getDisplayJobName() {
        return DISPLAY_NAME;
    }
}