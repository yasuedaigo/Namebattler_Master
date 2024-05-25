package masternamebattler.Chara;

public class CharaConstants {

    public static final String ATTACK_MESSAGE = "%sの攻撃！";
    public static final String DAMAGE_MESSAGE = "%sに%dのダメージ！";
    public static final String DOWN_MESSAGE = "%sは力尽きた...！";
    public static final String CRITICAL_MESSAGE = "会心の一撃！";
    public static final String USE_MAGIC_MESSAGE = "%sは%sを使った！";
    public static final String PARALYSIS_MESSAGE = "%sは麻痺して動けなかった！";
    public static final String POISON_DAMAGE_MESSAGE = "%sは毒によりダメージを受けた！";
    public static final String INVALID_NAME_MESSAGE = "入力が正しくありません";
    public static final String SET_CONDITION_MESSAGE = "%sは%sにかかった！";
    public static final String SHOW_HP_MESSAGE = "%s HP:%d";

    public static final int MIN_DAMAGE = 0;
    public static final int DOWN_HP = 0;
    public static final int HP_INDEX = 0;
    public static final int MP_INDEX = 1;
    public static final int STR_INDEX = 2;
    public static final int DEF_INDEX = 3;
    public static final int AGI_INDEX = 4;
    public static final int LUCK_INDEX = 5;

    public static int MAX_LUCK_STATUS = 255;

    
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
