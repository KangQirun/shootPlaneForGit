package com.beancore.config;

public class Config {
    // 配置
    public final static String LOGO_IMG = "images/logo.png";//窗口logo
    public final static String SHOOT_BACKGROUND_IMG = "images/shoot_background.png";
    //背景图片
    public final static String SHOOT_IMG = "images/shoot.png";
    public final static String FONT_IMG = "images/font.png";
    public final static String BUTTON_BG_IMG = "images/button_bg.png";//按钮背景
    public final static String BUTTON_HOVER_BG_IMG = "images/button_hover_bg.png";
    //悬浮按钮背景（深色）
    //以下为不同动作触发下音频
    public final static String ACHIEVEMENT_AUDIO = "sound/achievement.wav";
    
    public final static String SMALL_PLANE_KILLED_AUDIO = "sound/small_plane_killed.wav";
    //小飞机死亡音乐
    public final static String BIG_PLANE_KILLED_AUDIO = "sound/big_plane_killed.wav";
    //大飞机死亡音乐
    public final static String BOSS_PLANE_KILLED_AUDIO = "sound/boss_plane_killed.wav";
    //Boss飞机死亡音乐
    public final static String BOSS_PLANE_FLYING_AUDIO = "sound/boss_plane_flying.wav";
    //Boss飞机飞行音乐
    public final static String POP_WEAPON_AUDIO = "sound/pop_weapon.wav";
   
    public final static String USER_BOMB_AUDIO = "sound/use_bomb.wav";
    //使用炸弹音乐
    public final static String FIRE_BULLET_AUDIO = "sound/fire_bullet.wav";

    public final static String GAME_MUSIC_AUDIO = "sound/game_music.wav";
    public final static String GAME_OVER_AUDIO = "sound/game_over.wav";
    //游戏结束音乐
    public final static String GET_BOMB_AUDIO = "sound/get_bomb.wav";
    //获得炸弹音乐
    public final static String GET_DOUBLE_LASER_AUDIO = "sound/get_double_laser.wav";
    //获取双重激光器音乐
    public final static String BUTTON_ACTION_AUDIO = "sound/button.wav";
    //点击按钮音乐
    public static final String HELP_FILE_PATH = "help.html";//帮助文档
    public static final String SCORE_FILE = "score.dat";//分数文件名

     //主框架大小设置
    public final static int MAIN_FRAME_WIDTH = 480;
    public final static int MAIN_FRAME_HEIGHT = 852;

    //弹出式菜单面板大小设置
    public final static int POP_UP_MENU_PANEL_WIDTH = 160;
    public final static int POP_UP_MENU_PANEL_HEIGHT = 248;

    //分数面板大小设置
    public final static int POP_UP_SCORE_PANEL_WIDTH = 160;
    public final static int POP_UP_SCORE_PANEL_HEIGHT = 634;

    //帮助对话大小设置
    public final static int HELP_DIALOG_WIDTH = 800;
    public final static int HELP_DIALOG_HEIGHT = 600;

    public final static int SCORE_IMG_POS_X = 5;
    public final static int SCORE_IMG_POS_Y = 5;

    public final static int CAUGHT_BOMB_IMG_POS_X = 5;
    public final static int CAUGHT_BOMB_IMG_POS_Y = 770;

    //杀死各类飞机所对应获取的分值
    public final static int KILL_SMALL_PLANE_SCORE = 1000;
    public final static int KILL_BIG_PLANE_SCORE = 6000;
    public final static int KILL_BOSS_PLANE_SCORE = 30000;

    //杀死各类飞机所需要的击中次数
    public final static int BULLET_COUNT_TO_KILL_SMALL_PLANE = 1;
    public final static int BULLET_COUNT_TO_KILL_BIG_PLANE = 10;
    public final static int BULLET_COUNT_TO_KILL_BOSS_PLANE = 20;

    public final static int SMALL_PLANE_STATUS_CHANGE_INTERVAL = 90;
    public final static int BIG_PLANE_STATUS_CHANGE_INTERVAL = 90;
    public final static int BOSS_PLANE_STATUS_CHANGE_INTERVAL = 170;

    public final static int GAME_LOADING_INTERVAL = 600;//加载游戏间隔
    public final static int MAX_SCORE_COUNT = 10;//分数列表容量

    public final static int NUMBER_0 = 0;
    public final static int NUMBER_1 = 1;
    public final static int NUMBER_2 = 2;
    public final static int NUMBER_3 = 3;
    public final static int NUMBER_4 = 4;
    public final static int NUMBER_5 = 5;
    public final static int NUMBER_6 = 6;
    public final static int NUMBER_7 = 7;
    public final static int NUMBER_8 = 8;
    public final static int NUMBER_9 = 9;

    public final static int BOMB_MAX_HOLD_COUNT = 3;//炸弹最大储量
    public final static int ONE_BOMB = 1;
    public final static int TWO_BOMB = 2;
    public final static int THREE_BOMB = 3;

    // changeable configuration
    public static int DOUBLE_LASER_LAST_TIME = 28000;// 28 seconds

    public static int BULLET_FIRE_INTERVAL = 120;//子弹开火间隔

    public static int GAME_PANEL_REPAINT_INTERVAL = 80;

    public static int YELLOW_BULLET_MOVE_SPEED = 50;//黄色子弹速度
    public static int BLUE_BULLET_MOVE_SPEED = 50;

    public static int ENEMY_PLANE_MOVE_SPEED_MIN = 30;//最小敌机速度
    public static int ENEMY_PLANE_MOVE_SPEED_MAX = 40;//最大敌机速度

    public static int POP_WEAPON_ANIMATION_MOVE_FORWARD_SPEED = 60;//敌方武器向前运动速度
    public static int POP_WEAPON_ANIMATION_MOV_BACK_SPEED = 200;//敌方武器向后运动速度

    public static int POP_WEAPON_MOVE_SPEED = 30;//本机移动速度

    public static int POP_DOUBLE_LASER_INTERVAL = 30000;
    public static int POP_BOMBO_INTERVAL = 58000;

    public static int POP_SMALL_ENEMY_PLANE_INTERVAL = 300;
    public static int POP_BIG_ENEMY_PLANE_INTERVAL = 6000;
    public static int POP_BOSS_ENEMY_PLANE_INTERVAL = 12000;

}
