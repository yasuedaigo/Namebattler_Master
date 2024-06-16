package masternamebattler.Chara;

public class CharaConstants {

    //攻撃したとき
    public static final String ATTACK_MESSAGE = "%sの攻撃！";
    //ダメージを受けたとき
    public static final String DAMAGE_MESSAGE = "%sに%dのダメージ！";
    //倒れたとき
    public static final String DOWN_MESSAGE = "%sは力尽きた...！";
    //会心の一撃がでたとき
    public static final String CRITICAL_MESSAGE = "会心の一撃！";
    //魔法を使ったとき
    public static final String USE_MAGIC_MESSAGE = "%sは%sを使った！";
    //麻痺で攻撃できなかったとき
    public static final String PARALYSIS_MESSAGE = "%sは麻痺して動けなかった！";
    //毒ダメージを受けたとき
    public static final String POISON_DAMAGE_MESSAGE = "%sは毒によりダメージを受けた！";
    //状態異常にかかったとき
    public static final String SET_CONDITION_MESSAGE = "%sは%sにかかった！";
    //HPを表示するとき
    public static final String SHOW_HP_MESSAGE = "%sのHPは%dです。";
    //回復メッセージ
    public static final String HEAL_MESSAGE = "%sは%s回復した";

    //ダメージの最小値（ダメージがマイナスにならない）
    public static final int MIN_DAMAGE = 0;
    //ダウンするHP（0以下でダウン）
    public static final int DOWN_HP = 0;
    //Playerのステータスのインデックス
    public static final int HP_INDEX = 0;
    public static final int MP_INDEX = 1;
    public static final int STR_INDEX = 2;
    public static final int DEF_INDEX = 3;
    public static final int AGI_INDEX = 4;
    public static final int LUCK_INDEX = 5;

    //LUCKの最大値
    public static int MAX_LUCK_STATUS = 255;

    //Fighterのステータスの最大値、最小値、表示名
    public static class Fighter {
        public static final int MIN_HP = 100;
        public static final int MAX_HP = 300;
        public static final int MIN_MP = 0;
        public static final int MAX_MP = 0;
        public static final int MIN_STR = 30;
        public static final int MAX_STR = 100;
        public static final int MIN_DEF = 30;
        public static final int MAX_DEF = 100;
        public static final int MIN_LUCK = 1;
        public static final int MAX_LUCK = 255;
        public static final int MIN_AGI = 1;
        public static final int MAX_AGI = 50;
        public static final String DISPLAY_NAME = "戦士";
    }

    //Ninjaのステータスの最大値、最小値、表示名
    public static class Ninja {
        public static final int MIN_HP = 1;
        public static final int MAX_HP = 255;
        public static final int MIN_MP = 0;
        public static final int MAX_MP = 0;
        public static final int MIN_STR = 1;
        public static final int MAX_STR = 255;
        public static final int MIN_DEF = 1;
        public static final int MAX_DEF = 255;
        public static final int MIN_LUCK = 255;
        public static final int MAX_LUCK = 255;
        public static final int MIN_AGI = 255;
        public static final int MAX_AGI = 255;
        public static final String DISPLAY_NAME = "忍者";
    }

    //Priestのステータスの最大値、最小値、表示名
    public static class Priest {
        public static final int MIN_HP = 80;
        public static final int MAX_HP = 200;
        public static final int MIN_MP = 20;
        public static final int MAX_MP = 50;
        public static final int MIN_STR = 10;
        public static final int MAX_STR = 70;
        public static final int MIN_DEF = 10;
        public static final int MAX_DEF = 70;
        public static final int MIN_LUCK = 1;
        public static final int MAX_LUCK = 255;
        public static final int MIN_AGI = 20;
        public static final int MAX_AGI = 60;
        public static final String DISPLAY_NAME = "僧侶";
    }

    //Wizardのステータスの最大値、最小値、表示名
    public static class Wizard {
        public static final int MIN_HP = 50;
        public static final int MAX_HP = 150;
        public static final int MIN_MP = 30;
        public static final int MAX_MP = 80;
        public static final int MIN_STR = 1;
        public static final int MAX_STR = 50;
        public static final int MIN_DEF = 1;
        public static final int MAX_DEF = 50;
        public static final int MIN_LUCK = 1;
        public static final int MAX_LUCK = 255;
        public static final int MIN_AGI = 20;
        public static final int MAX_AGI = 60;
        public static final String DISPLAY_NAME = "魔導士";
    }
}
