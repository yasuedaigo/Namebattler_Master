package masternamebattler.Magic;

/**
 * 魔法に関する定数クラス
 */
public class MagicConstants {
    //魔法の基底クラスに関する定数
    public static class Magic{
        //魔法を唱えられなかった際のメッセージ
        public static final String CANNOTCASTMESSAGE = "%sは%sを唱えられなかった！";
        //魔法を唱えた際のメッセージ
        public static final String CASTMESSAGE = "%sは%sを唱えた！";
        //表示名
        public static final String DISPLAY_NAME = "魔法";
        //消費MP
        public static final int CONSUMPTION_MP = 0;
        //最大ダメージ
        public static final int MAX_DAMAGE = 0;
        //最小ダメージ
        public static final int MIN_DAMAGE = 0;
    }

    //各魔法に関する定数
    public static class Fire{
        public static final String DISPLAY_NAME = "ファイア";
        public static final int CONSUMPTION_MP = 10;
        public static final int MAX_DAMAGE = 30;
        public static final int MIN_DAMAGE = 10;
    }
    public static class Thunder{
        public static final String DISPLAY_NAME = "サンダー";
        public static final int CONSUMPTION_MP = 20;
        public static final int MAX_DAMAGE = 35;
        public static final int MIN_DAMAGE = 15;
    }
    public static class Heal{
        public static final String DISPLAY_NAME = "ヒール";
        public static final int CONSUMPTION_MP = 20;
        //回復量
        public static final int HEAL_HP = 50;
    }
    public static class Paralysis{
        public static final String DISPLAY_NAME = "パライズ";
        public static final int CONSUMPTION_MP = 10;
        public static final int MAX_DAMAGE = 0;
        public static final int MIN_DAMAGE = 0;
    }
    public static class Poison{
        public static final String DISPLAY_NAME = "ポイズン";
        public static final int CONSUMPTION_MP = 10;
        public static final int MAX_DAMAGE = 0;
        public static final int MIN_DAMAGE = 0;
    }
    
}