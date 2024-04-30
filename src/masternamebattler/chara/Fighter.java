package masternamebattler.chara;

import masternamebattler.Constants;

/**
 * 戦士クラス
 */
public class Fighter extends Player {
    // 戦士のステータスの最大値、最小値
    public static final int FIGHTER_MIN_HP = 100;
    public static final int FIGHTER_MAX_HP = 300;
    public static final int FIGHTER_MIN_MP = 0;
    public static final int FIGHTER_MAX_MP = 0;
    public static final int FIGHTER_MIN_STR = 30;
    public static final int FIGHTER_MAX_STR = 100;
    public static final int FIGHTER_MIN_DEF = 30;
    public static final int FIGHTER_MAX_DEF = 100;
    public static final int FIGHTER_MIN_LUCK = 1;
    public static final int FIGHTER_MAX_LUCK = 100;
    public static final int FIGHTER_MIN_AGI = 1;
    public static final int FIGHTER_MAX_AGI = 50;
    public static final String DISPLAY_NAME = "戦士";
    
    /**
     * コンストラクタ
     * @param team プレイヤーの所属するチーム
     */
    public Fighter(Constants.Teams team) {
        super(team);
    }

    /**
     * ステータスを計算してセットする
     */
    @Override
    public void setStatsu(){
        this.hp = calcStatus(this.name, FIGHTER_MAX_HP, FIGHTER_MIN_HP,HP_INDEX);
        this.mp = calcStatus(this.name, FIGHTER_MAX_MP, FIGHTER_MIN_MP, MP_INDEX);
        this.str = calcStatus(this.name, FIGHTER_MAX_STR, FIGHTER_MIN_STR, STR_INDEX);
        this.def = calcStatus(this.name, FIGHTER_MAX_DEF, FIGHTER_MIN_DEF, DEF_INDEX);
        this.agi = calcStatus(this.name, FIGHTER_MAX_AGI, FIGHTER_MIN_AGI, AGI_INDEX);
        this.luck = calcStatus(this.name, FIGHTER_MAX_LUCK, FIGHTER_MIN_LUCK, LUCK_INDEX);
    }

    /**
     * @return 戦士の職業名
     */
    @Override
    public String getDisplayJobName() {
        return DISPLAY_NAME;
    }
}