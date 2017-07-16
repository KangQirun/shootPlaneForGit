package com.beancore.ui;

import java.awt.BorderLayout;//导入BorderLayout布局类
import java.awt.Container;//导入容器类
import java.awt.Dimension;//屏幕参数
import java.awt.Toolkit;
import java.awt.event.ActionEvent;//事件处理
import java.awt.event.ActionListener;//事件监听
import java.io.IOException;//输入输出异常
import java.util.ArrayList;//导入
import java.util.Collections;//导入集合类
import java.util.Date;//导入日期
import java.util.List;

import javax.sound.sampled.LineUnavailableException;//音频异常
import javax.sound.sampled.UnsupportedAudioFileException;//视频异常
import javax.swing.Box;//导入布局类（上下/左右）
import javax.swing.BoxLayout;//导入布局类（东西南北中）
import javax.swing.ImageIcon;//导入图片类
import javax.swing.JFrame;//导入swing框架
import javax.swing.JLabel;//标签
import javax.swing.JOptionPane;
import javax.swing.JPanel;//控制板

import com.beancore.config.Config;
import com.beancore.config.ImageConstants;
import com.beancore.entity.Score;//分数
import com.beancore.util.FileUtil;//别的包中的类需要导入
import com.beancore.util.ImageLoader;//图片载入器
import com.beancore.util.Images;//导入图片类
import com.beancore.util.SoundPlayer;//声音播放器

public class MainFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;//版本控制
    private ImageLoader imgLoader;

    private GameLoadingPanel gameLoadingPanel;
    private GamePlayingPanel gamePlayingPanel;

    private PopupMenuPanel popupMenuPanel;//菜单面板
    private Top10ScorePanel popupScorePanel;//分数榜前十面板
    private HelpDialog helpDialog;//帮助对话

    private SoundPlayer achievementSoundPlayer;

    private List<Score> scoreList;//分数列表集合

    public MainFrame() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
	try {
	    this.scoreList = FileUtil.readScore(Config.SCORE_FILE);//通过类名调用
	} catch (Exception e) {
	    this.scoreList = new ArrayList<Score>();//？
	}
	this.loadImage();//加载图片
	this.initSoundPlayer();//初始化声音播放器
	this.initComponents();//初始化组件
	this.setBackgroundImage();//设置背景图片
    }

    /**
     * Load the image part from the whole image, shoot_background.png shoot.png
     * are both extracted from the weixin apk file
     * */
    private void loadImage() throws IOException {
    //载入图片。通过从母图上截取子图完成
	this.imgLoader = new ImageLoader(Config.SHOOT_BACKGROUND_IMG);
	//以SHOOT_BACKGROUND_IMG为母图
	Images.GAME_LOADING_IMG1 = this.imgLoader.getImage(ImageConstants.GAME_LOADING_PLANE_1_POS_X,
		ImageConstants.GAME_LOADING_PLANE_1_POS_Y, ImageConstants.GAME_LOADING_PLANE_1_WIDTH,
		ImageConstants.GAME_LOADING_PLANE_1_HEIGHT);//游戏加载图片1
	Images.GAME_LOADING_IMG2 = this.imgLoader.getImage(ImageConstants.GAME_LOADING_PLANE_2_POS_X,		ImageConstants.GAME_LOADING_PLANE_2_POS_Y, ImageConstants.GAME_LOADING_PLANE_2_WIDTH,
		ImageConstants.GAME_LOADING_PLANE_2_HEIGHT);//游戏加载图片2
	Images.GAME_LOADING_IMG3 = this.imgLoader.getImage(ImageConstants.GAME_LOADING_PLANE_3_POS_X,
		ImageConstants.GAME_LOADING_PLANE_3_POS_Y, ImageConstants.GAME_LOADING_PLANE_3_WIDTH,
		ImageConstants.GAME_LOADING_PLANE_3_HEIGHT);//游戏加载图片3

	Images.SHOOT_BACKGROUND_IMG = this.imgLoader.getImage(ImageConstants.GAME_BACKGROUND_IMG_POS_X,
		ImageConstants.GAME_BACKGROUND_IMG_POS_Y, ImageConstants.GAME_BACKGROUND_IMG_WIDTH,
		ImageConstants.GAME_BACKGROUND_IMG_HEIGHT);//背景图片

	Images.GAME_LOADING_TEXT_IMG = this.imgLoader.getImage(ImageConstants.GAME_LOADING_TEXT_IMG_POS_X,
		ImageConstants.GAME_LOADING_TEXT_IMG_POS_Y, ImageConstants.GAME_LOADING_TEXT_IMG_WIDTH,
		ImageConstants.GAME_LOADING_TEXT_IMG_HEIGHT);

	this.imgLoader = new ImageLoader(Config.SHOOT_IMG);//以SHOOT_IMG为第二张母图
	Images.YELLOW_BULLET_IMG = this.imgLoader.getImage(ImageConstants.YELLOW_BULLET_POS_X,
		ImageConstants.YELLOW_BULLET_POS_Y, ImageConstants.YELLOW_BULLET_WIDTH,
		ImageConstants.YELLOW_BULLET_HEIGHT);//黄色子弹
	Images.BLUE_BULLET_IMG = this.imgLoader.getImage(ImageConstants.BLUE_BULLET_POS_X,
		ImageConstants.BLUE_BULLET_POS_Y, ImageConstants.BLUE_BULLET_WIDTH, ImageConstants.BLUE_BULLET_HEIGHT);//蓝色子弹
	Images.MY_PLANE_IMG = this.imgLoader.getImage(ImageConstants.MY_PLANE_POS_X, ImageConstants.MY_PLANE_POS_Y,
		ImageConstants.MY_PLANE_WIDTH, ImageConstants.MY_PLANE_HEIGHT);//本机
	Images.MY_PLANE_FLYING_IMG = this.imgLoader.getImage(ImageConstants.MY_PLANE_FLYING_POS_X,
		ImageConstants.MY_PLANE_FLYING_POS_Y, ImageConstants.MY_PLANE_FLYING_WIDTH,
		ImageConstants.MY_PLANE_FLYING_HEIGHT);//正在飞行的本机
	Images.SMALL_PLANE_IMG = this.imgLoader.getImage(ImageConstants.SMALL_PLANE_POS_X,
		ImageConstants.SMALL_PLANE_POS_Y, ImageConstants.SMALL_PLANE_WIDTH, ImageConstants.SMALL_PLANE_HEIGHT);//小飞机
	Images.BIG_PLANE_IMG = this.imgLoader.getImage(ImageConstants.BIG_PLANE_POS_X, ImageConstants.BIG_PLANE_POS_Y,
		ImageConstants.BIG_PLANE_WIDTH, ImageConstants.BIG_PLANE_HEIGHT);//大飞机
	Images.BOSS_PLANE_IMG = this.imgLoader.getImage(ImageConstants.BOSS_PLANE_POS_X,
		ImageConstants.BOSS_PLANE_POS_Y, ImageConstants.BOSS_PLANE_WIDTH, ImageConstants.BOSS_PLANE_HEIGHT);//Boss飞机
	Images.BOMB_IMG = this.imgLoader.getImage(ImageConstants.BOMB_POS_X, ImageConstants.BOMB_POS_Y,
		ImageConstants.BOMB_WIDTH, ImageConstants.BOMB_HEIGHT);//炸弹
	Images.CAUGHT_BOMB_IMG = this.imgLoader.getImage(ImageConstants.CAUGHT_BOMB_POS_X,
		ImageConstants.CAUGHT_BOMB_POS_Y, ImageConstants.CAUGHT_BOMB_WIDTH, ImageConstants.CAUGHT_BOMB_HEIGHT);
	Images.DOUBLE_LASER_IMG = this.imgLoader.getImage(ImageConstants.DOUBLE_LASER_POS_X,
		ImageConstants.DOUBLE_LASER_POS_Y, ImageConstants.DOUBLE_LASER_WIDTH,
		ImageConstants.DOUBLE_LASER_HEIGHT);

	Images.SMALL_PLANE_FIGHTING_IMG = this.imgLoader.getImage(ImageConstants.SMALL_PLANE_FIGHTING_POS_X,
		ImageConstants.SMALL_PLANE_FIGHTING_POS_Y, ImageConstants.SMALL_PLANE_FIGHTING_WIDTH,
		ImageConstants.SMALL_PLANE_FIGHTING_HEIGHT);
	Images.SMALL_PLANE_KILLED_IMG = this.imgLoader.getImage(ImageConstants.SMALL_PLANE_KILLED_POS_X,
		ImageConstants.SMALL_PLANE_KILLED_POS_Y, ImageConstants.SMALL_PLANE_KILLED_WIDTH,
		ImageConstants.SMALL_PLANE_KILLED_HEIGHT);
	Images.SMALL_PLANE_ASHED_IMG = this.imgLoader.getImage(ImageConstants.SMALL_PLANE_ASHED_POS_X,
		ImageConstants.SMALL_PLANE_ASHED_POS_Y, ImageConstants.SMALL_PLANE_ASHED_WIDTH,
		ImageConstants.SMALL_PLANE_ASHED_HEIGHT);//小飞机击毁

	Images.BIG_PLANE_FIGHTING_IMG = this.imgLoader.getImage(ImageConstants.BIG_PLANE_FIGHTING_POS_X,
		ImageConstants.BIG_PLANE_FIGHTING_POS_Y, ImageConstants.BIG_PLANE_FIGHTING_WIDTH,
		ImageConstants.BIG_PLANE_FIGHTING_HEIGHT);
	Images.BIG_PLANE_HITTED_IMG = this.imgLoader.getImage(ImageConstants.BIG_PLANE_HITTED_POS_X,
		ImageConstants.BIG_PLANE_HITTED_POS_Y, ImageConstants.BIG_PLANE_HITTED_WIDTH,
		ImageConstants.BIG_PLANE_HITTED_HEIGHT);//击中大飞机
	Images.BIG_PLANE_BADDLY_WOUNDED_IMG = this.imgLoader.getImage(ImageConstants.BIG_PLANE_BADDLY_WOUNDED_POS_X,
		ImageConstants.BIG_PLANE_BADDLY_WOUNDED_POS_Y, ImageConstants.BIG_PLANE_BADDLY_WOUNDED_WIDTH,
		ImageConstants.BIG_PLANE_BADDLY_WOUNDED_HEIGHT);//大飞机严重损坏
	Images.BIG_PLANE_KILLED_IMG = this.imgLoader.getImage(ImageConstants.BIG_PLANE_KILLED_POS_X,
		ImageConstants.BIG_PLANE_KILLED_POS_Y, ImageConstants.BIG_PLANE_KILLED_WIDTH,
		ImageConstants.BIG_PLANE_KILLED_HEIGHT);//大飞机死亡
	Images.BIG_PLANE_ASHED_IMG = this.imgLoader.getImage(ImageConstants.BIG_PLANE_ASHED_POS_X,
		ImageConstants.BIG_PLANE_ASHED_POS_Y, ImageConstants.BIG_PLANE_ASHED_WIDTH,
		ImageConstants.BIG_PLANE_ASHED_HEIGHT);//大飞机击毁

	Images.BOSS_PLANE_FIGHTING_IMG = this.imgLoader.getImage(ImageConstants.BOSS_PLANE_FIGHTING_POS_X,
		ImageConstants.BOSS_PLANE_FIGHTING_POS_Y, ImageConstants.BOSS_PLANE_FIGHTING_WIDTH,
		ImageConstants.BOSS_PLANE_FIGHTING_HEIGHT);//Boss飞机战斗
	Images.BOSS_PLANE_HITTED_IMG = this.imgLoader.getImage(ImageConstants.BOSS_PLANE_HITTED_POS_X,
		ImageConstants.BOSS_PLANE_HITTED_POS_Y, ImageConstants.BOSS_PLANE_HITTED_WIDTH,
		ImageConstants.BOSS_PLANE_HITTED_HEIGHT);//Boss飞机被击中
	Images.BOSS_PLANE_BADDLY_WOUNDED_IMG = this.imgLoader.getImage(ImageConstants.BOSS_PLANE_BADDLY_WOUNDED_POS_X,
		ImageConstants.BOSS_PLANE_BADDLY_WOUNDED_POS_Y, ImageConstants.BOSS_PLANE_BADDLY_WOUNDED_WIDTH,
		ImageConstants.BOSS_PLANE_BADDLY_WOUNDED_HEIGHT);//Boss飞机严重损坏
	Images.BOSS_PLANE_KILLED_IMG = this.imgLoader.getImage(ImageConstants.BOSS_PLANE_KILLED_POS_X,
		ImageConstants.BOSS_PLANE_KILLED_POS_Y, ImageConstants.BOSS_PLANE_KILLED_WIDTH,
		ImageConstants.BOSS_PLANE_KILLED_HEIGHT);//Boss飞机死亡
	Images.BOSS_PLANE_ASHED_IMG = this.imgLoader.getImage(ImageConstants.BOSS_PLANE_ASHED_POS_X,
		ImageConstants.BOSS_PLANE_ASHED_POS_Y, ImageConstants.BOSS_PLANE_ASHED_WIDTH,
		ImageConstants.BOSS_PLANE_ASHED_HEIGHT);//Boss飞机击毁

	Images.SCORE_IMG = this.imgLoader.getImage(ImageConstants.SCORE_IMG_POS_X, ImageConstants.SCORE_IMG_POS_Y,
		ImageConstants.SCORE_IMG_WIDTH, ImageConstants.SCORE_IMG_HEIGHT);//分数

	this.imgLoader = new ImageLoader(Config.FONT_IMG);//第三张母图
	Images.X_MARK_IMG = this.imgLoader.getImage(ImageConstants.X_MARK_POS_X, ImageConstants.X_MARK_POS_Y,
		ImageConstants.X_MARK_WIDTH, ImageConstants.X_MARK_HEIGHT);
	Images.NUMBER_0_IMG = this.imgLoader.getImage(ImageConstants.NUMBER_0_POS_X, ImageConstants.NUMBER_0_POS_Y,
		ImageConstants.NUMBER_0_WIDTH, ImageConstants.NUMBER_0_HEIGHT);
	Images.NUMBER_1_IMG = this.imgLoader.getImage(ImageConstants.NUMBER_1_POS_X, ImageConstants.NUMBER_1_POS_Y,
		ImageConstants.NUMBER_1_WIDTH, ImageConstants.NUMBER_1_HEIGHT);
	Images.NUMBER_2_IMG = this.imgLoader.getImage(ImageConstants.NUMBER_2_POS_X, ImageConstants.NUMBER_2_POS_Y,
		ImageConstants.NUMBER_2_WIDTH, ImageConstants.NUMBER_2_HEIGHT);
	Images.NUMBER_3_IMG = this.imgLoader.getImage(ImageConstants.NUMBER_3_POS_X, ImageConstants.NUMBER_3_POS_Y,
		ImageConstants.NUMBER_3_WIDTH, ImageConstants.NUMBER_3_HEIGHT);
	Images.NUMBER_4_IMG = this.imgLoader.getImage(ImageConstants.NUMBER_4_POS_X, ImageConstants.NUMBER_4_POS_Y,
		ImageConstants.NUMBER_4_WIDTH, ImageConstants.NUMBER_4_HEIGHT);
	Images.NUMBER_5_IMG = this.imgLoader.getImage(ImageConstants.NUMBER_5_POS_X, ImageConstants.NUMBER_5_POS_Y,
		ImageConstants.NUMBER_5_WIDTH, ImageConstants.NUMBER_5_HEIGHT);
	Images.NUMBER_6_IMG = this.imgLoader.getImage(ImageConstants.NUMBER_6_POS_X, ImageConstants.NUMBER_6_POS_Y,
		ImageConstants.NUMBER_6_WIDTH, ImageConstants.NUMBER_6_HEIGHT);
	Images.NUMBER_7_IMG = this.imgLoader.getImage(ImageConstants.NUMBER_7_POS_X, ImageConstants.NUMBER_7_POS_Y,
		ImageConstants.NUMBER_7_WIDTH, ImageConstants.NUMBER_7_HEIGHT);
	Images.NUMBER_8_IMG = this.imgLoader.getImage(ImageConstants.NUMBER_8_POS_X, ImageConstants.NUMBER_8_POS_Y,
		ImageConstants.NUMBER_8_WIDTH, ImageConstants.NUMBER_8_HEIGHT);
	Images.NUMBER_9_IMG = this.imgLoader.getImage(ImageConstants.NUMBER_9_POS_X, ImageConstants.NUMBER_9_POS_Y,
		ImageConstants.NUMBER_9_WIDTH, ImageConstants.NUMBER_9_HEIGHT);
    }

    private void initComponents() {
	this.setTitle("Shoot Plane - jemygraw@gmail.com");
	this.setIconImage(new ImageIcon(Config.LOGO_IMG).getImage());//设置窗口图标Logo
	this.setSize(Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT);//设置大小
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕参数
	this.setBounds((d.width - Config.MAIN_FRAME_WIDTH) / 2, (d.height - Config.MAIN_FRAME_HEIGHT) / 2,
		Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT);//设置边界大小和位置
	this.setResizable(false);//设置不可最大最小化
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initSoundPlayer() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	achievementSoundPlayer = new SoundPlayer(Config.ACHIEVEMENT_AUDIO);
	//调用构造方法播放音频
    }

    private void setBackgroundImage() {
	ImageIcon bgImgIcon = new ImageIcon(Images.SHOOT_BACKGROUND_IMG);
	JLabel bgLabel = new JLabel(bgImgIcon);//作为JLabel
	this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));//
	//getLayeredPane()返回此窗体的 layeredPane 对象。add将指定的组件添加到此容器的尾部
	bgLabel.setBounds(0, 0, bgImgIcon.getIconWidth(), bgImgIcon.getIconHeight());
	//设置边界
	((JPanel) this.getContentPane()).setOpaque(false);
	//返回的是container对象，需要转化为Jpanel
    }

    private void popupMenuPanel() {
	Container c = this.getContentPane();//返回此窗体的 contentPane 对象
	c.removeAll();//从容器中移除所有组件
	this.repaint();//重绘组件，刷新
	if (this.popupMenuPanel == null) {
	    this.popupMenuPanel = new PopupMenuPanel(this);
	}
	BoxLayout boxLayout = new BoxLayout(c, BoxLayout.Y_AXIS);//纵向排列
	c.setLayout(boxLayout);
	c.add(Box.createVerticalGlue());//创建纵向glue组件
	c.add(this.popupMenuPanel);
	c.add(Box.createVerticalGlue());
	this.validate();//验证此容器及其所有子组件以显示
    }

    public void loadGame() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	Container c = this.getContentPane();
	c.removeAll();//移除所有组件
	this.repaint();//重绘所有组件
	if (this.gameLoadingPanel == null) {//
	    this.gameLoadingPanel = new GameLoadingPanel();
	}

	BoxLayout boxLayout = new BoxLayout(c, BoxLayout.Y_AXIS);//按纵向布置
	c.setLayout(boxLayout);
	c.add(Box.createVerticalGlue());//创建glue组件以完善布局
	c.add(this.gameLoadingPanel);
	c.add(Box.createVerticalGlue());
	this.gameLoadingPanel.loadingGame();//加载游戏加载图片
	this.startGame();//各项初始配置并开始游戏
    }

    private void startGame() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	Container c = this.getContentPane();
	c.removeAll();
	this.repaint();//界面刷新
	BorderLayout borderLayout = new BorderLayout();
	c.setLayout(borderLayout);
	this.gamePlayingPanel = new GamePlayingPanel();
	c.add(this.gamePlayingPanel, BorderLayout.CENTER);//将gamePlayingPanel置于中央
	this.gamePlayingPanel.startGame();//调用方法开始游戏
	long startTime = System.currentTimeMillis();//获取当前时间
	while (this.gamePlayingPanel.getMyPlane().isAlive()) {//当本机存活
	    try {
		Thread.sleep(Config.GAME_PANEL_REPAINT_INTERVAL);//在敌机重绘期间线程睡眠
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	//若本机死亡
	long endTime = System.currentTimeMillis();//返回以毫秒为单位的当前时间。
	// add to score list
	this.addScore(this.gamePlayingPanel.getScore(), endTime - startTime);
	int option = JOptionPane.showConfirmDialog(this, "Game Over, Score:" + this.gamePlayingPanel.getScore()
		+ ", Start Again?", "Game Over", JOptionPane.YES_NO_OPTION);//请求确认是否再次开始
	switch (option) {
	case JOptionPane.YES_OPTION://选择YES则重新加载游戏
	    loadGame();
	    break;
	case JOptionPane.NO_OPTION://选择NO则结束游戏
	    stopGame();
	    break;
	}
    }
    //向得分列表中添加得分记录
    private void addScore(int score, long lastMilliSeconds) throws IOException {
	Score s = new Score(new Date(System.currentTimeMillis()), score, lastMilliSeconds);
	int size = this.scoreList.size();//获取当前得分记录集合大小
	if (this.scoreList.contains(s)) {//如果列表包含这条记录
	    return;
	}
	if (size < Config.MAX_SCORE_COUNT) {
	    this.scoreList.add(s);//若得分记录小于10则继续添加
	} else {
	    Score lastScore = this.scoreList.get(size - 1);//获取最后一次分数
	    if (s.compareTo(lastScore) > 0) {
		this.scoreList.remove(lastScore);//移除原本的最后一条记录
		this.scoreList.add(s);//将本条记录加入列表
	    }
	}
	Collections.sort(this.scoreList);//对分数列表进行排序（升序）
	Collections.reverse(this.scoreList);//翻转分数列表顺序（降序）
	FileUtil.writeScore(scoreList, Config.SCORE_FILE);//将分数写入文件
    }

    public void stopGame() {
	popupMenuPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {//事件处理
	String actionCmd = e.getActionCommand();//获取事件源
	if (actionCmd.equals(PopupMenuPanel.START_GAME_BUTTON)) {//若选择开始游戏
	    startGameAction();
	} else if (actionCmd.equals(PopupMenuPanel.TOP_10_SCORES_BUTTON)) {//若选择top10
	    this.achievementSoundPlayer.play();//播放相应音乐
	    popupScorePanel(this.scoreList);//显示
	} else if (actionCmd.equals(PopupMenuPanel.EXIT_GAME_BUTTON)) {//若选择退出游戏
	    exitGameAction();
	} else if (actionCmd.equals(PopupMenuPanel.HELP_BUTTON)) {//若选择帮助
	    helpAction();
	} else if (actionCmd.equals(Top10ScorePanel.OK_BUTTON)) {//若选择OK
	    this.popupMenuPanel();
	}
    }

    private void popupScorePanel(List<Score> sortedScoreList) {
	Container c = this.getContentPane();
	c.removeAll();
	this.repaint();
	if (this.popupScorePanel == null) {
	    this.popupScorePanel = new Top10ScorePanel(this);
	}
	this.popupScorePanel.loadScore(sortedScoreList);//获取分数列表
	BoxLayout boxLayout = new BoxLayout(c, BoxLayout.Y_AXIS);//纵向排列
	c.setLayout(boxLayout);
	c.add(Box.createVerticalGlue());//加入纵向glue组件粘合
	c.add(this.popupScorePanel);
	c.add(Box.createVerticalGlue());
	this.validate();
    }

    private void startGameAction() {
	new Thread(new StartGameActionClass()).start();//开启游戏线程
    }

    class StartGameActionClass implements Runnable {

	@Override
	public void run() {
	    try {
		loadGame();//加载游戏
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    private void exitGameAction() {//退出游戏事件处理
	System.exit(0);
    }

    private void helpAction() {//帮助事件处理
	if (this.helpDialog == null) {
	    this.helpDialog = new HelpDialog();
	}
	this.helpDialog.setVisible(true);
    }
}
