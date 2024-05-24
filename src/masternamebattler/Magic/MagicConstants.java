package masternamebattler.Magic;

public class MagicConstants {
    public static class Magic{
        public static final String CANNOTCASTMESSAGE = "%sは%sを唱えられなかった！";
        public static final String CASTMESSAGE = "%sは%sを唱えた！";
        public static final String DISPLAY_NAME = "魔法";
        public static final int CONSUMPTION_MP = 0;
        public static final int MAX_DAMAGE = 0;
        public static final int MIN_DAMAGE = 0;
    }
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
        public static final int HEAL_HP = 50;
        public static final String HEAL_MESSAGE = "%sは%s回復した";
    }
    public static class Paralysis{
        public static final String DISPLAY_NAME = "パライズ";
        public static final int CONSUMPTION_MP = 10;
        public static final int PARALYSIS_RATE = 20;
        public static final int MAX_DAMAGE = 0;
        public static final int MIN_DAMAGE = 0;
    }
    public static class Poison{
        public static final String DISPLAY_NAME = "ポイズン";
        public static final int CONSUMPTION_MP = 10;
        public static final int POISON_MAX_DAMAGE = 30;
        public static final int POISON_MIN_DAMAGE = 10;
        public static final int MAX_DAMAGE = 0;
        public static final int MIN_DAMAGE = 0;
    }
    
}