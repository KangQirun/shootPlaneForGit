package com.beancore.ui;

import java.awt.BorderLayout;//����BorderLayout������
import java.awt.Container;//����������
import java.awt.Dimension;//��Ļ����
import java.awt.Toolkit;
import java.awt.event.ActionEvent;//�¼�����
import java.awt.event.ActionListener;//�¼�����
import java.io.IOException;//��������쳣
import java.util.ArrayList;//����
import java.util.Collections;//���뼯����
import java.util.Date;//��������
import java.util.List;

import javax.sound.sampled.LineUnavailableException;//��Ƶ�쳣
import javax.sound.sampled.UnsupportedAudioFileException;//��Ƶ�쳣
import javax.swing.Box;//���벼���ࣨ����/���ң�
import javax.swing.BoxLayout;//���벼���ࣨ�����ϱ��У�
import javax.swing.ImageIcon;//����ͼƬ��
import javax.swing.JFrame;//����swing���
import javax.swing.JLabel;//��ǩ
import javax.swing.JOptionPane;
import javax.swing.JPanel;//���ư�

import com.beancore.config.Config;
import com.beancore.config.ImageConstants;
import com.beancore.entity.Score;//����
import com.beancore.util.FileUtil;//��İ��е�����Ҫ����
import com.beancore.util.ImageLoader;//ͼƬ������
import com.beancore.util.Images;//����ͼƬ��
import com.beancore.util.SoundPlayer;//����������

public class MainFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;//�汾����
    private ImageLoader imgLoader;

    private GameLoadingPanel gameLoadingPanel;
    private GamePlayingPanel gamePlayingPanel;

    private PopupMenuPanel popupMenuPanel;//�˵����
    private Top10ScorePanel popupScorePanel;//������ǰʮ���
    private HelpDialog helpDialog;//�����Ի�

    private SoundPlayer achievementSoundPlayer;

    private List<Score> scoreList;//�����б���

    public MainFrame() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
	try {
	    this.scoreList = FileUtil.readScore(Config.SCORE_FILE);//ͨ����������
	} catch (Exception e) {
	    this.scoreList = new ArrayList<Score>();//��
	}
	this.loadImage();//����ͼƬ
	this.initSoundPlayer();//��ʼ������������
	this.initComponents();//��ʼ�����
	this.setBackgroundImage();//���ñ���ͼƬ
    }

    /**
     * Load the image part from the whole image, shoot_background.png shoot.png
     * are both extracted from the weixin apk file
     * */
    private void loadImage() throws IOException {
    //����ͼƬ��ͨ����ĸͼ�Ͻ�ȡ��ͼ���
	this.imgLoader = new ImageLoader(Config.SHOOT_BACKGROUND_IMG);
	//��SHOOT_BACKGROUND_IMGΪĸͼ
	Images.GAME_LOADING_IMG1 = this.imgLoader.getImage(ImageConstants.GAME_LOADING_PLANE_1_POS_X,
		ImageConstants.GAME_LOADING_PLANE_1_POS_Y, ImageConstants.GAME_LOADING_PLANE_1_WIDTH,
		ImageConstants.GAME_LOADING_PLANE_1_HEIGHT);//��Ϸ����ͼƬ1
	Images.GAME_LOADING_IMG2 = this.imgLoader.getImage(ImageConstants.GAME_LOADING_PLANE_2_POS_X,		ImageConstants.GAME_LOADING_PLANE_2_POS_Y, ImageConstants.GAME_LOADING_PLANE_2_WIDTH,
		ImageConstants.GAME_LOADING_PLANE_2_HEIGHT);//��Ϸ����ͼƬ2
	Images.GAME_LOADING_IMG3 = this.imgLoader.getImage(ImageConstants.GAME_LOADING_PLANE_3_POS_X,
		ImageConstants.GAME_LOADING_PLANE_3_POS_Y, ImageConstants.GAME_LOADING_PLANE_3_WIDTH,
		ImageConstants.GAME_LOADING_PLANE_3_HEIGHT);//��Ϸ����ͼƬ3

	Images.SHOOT_BACKGROUND_IMG = this.imgLoader.getImage(ImageConstants.GAME_BACKGROUND_IMG_POS_X,
		ImageConstants.GAME_BACKGROUND_IMG_POS_Y, ImageConstants.GAME_BACKGROUND_IMG_WIDTH,
		ImageConstants.GAME_BACKGROUND_IMG_HEIGHT);//����ͼƬ

	Images.GAME_LOADING_TEXT_IMG = this.imgLoader.getImage(ImageConstants.GAME_LOADING_TEXT_IMG_POS_X,
		ImageConstants.GAME_LOADING_TEXT_IMG_POS_Y, ImageConstants.GAME_LOADING_TEXT_IMG_WIDTH,
		ImageConstants.GAME_LOADING_TEXT_IMG_HEIGHT);

	this.imgLoader = new ImageLoader(Config.SHOOT_IMG);//��SHOOT_IMGΪ�ڶ���ĸͼ
	Images.YELLOW_BULLET_IMG = this.imgLoader.getImage(ImageConstants.YELLOW_BULLET_POS_X,
		ImageConstants.YELLOW_BULLET_POS_Y, ImageConstants.YELLOW_BULLET_WIDTH,
		ImageConstants.YELLOW_BULLET_HEIGHT);//��ɫ�ӵ�
	Images.BLUE_BULLET_IMG = this.imgLoader.getImage(ImageConstants.BLUE_BULLET_POS_X,
		ImageConstants.BLUE_BULLET_POS_Y, ImageConstants.BLUE_BULLET_WIDTH, ImageConstants.BLUE_BULLET_HEIGHT);//��ɫ�ӵ�
	Images.MY_PLANE_IMG = this.imgLoader.getImage(ImageConstants.MY_PLANE_POS_X, ImageConstants.MY_PLANE_POS_Y,
		ImageConstants.MY_PLANE_WIDTH, ImageConstants.MY_PLANE_HEIGHT);//����
	Images.MY_PLANE_FLYING_IMG = this.imgLoader.getImage(ImageConstants.MY_PLANE_FLYING_POS_X,
		ImageConstants.MY_PLANE_FLYING_POS_Y, ImageConstants.MY_PLANE_FLYING_WIDTH,
		ImageConstants.MY_PLANE_FLYING_HEIGHT);//���ڷ��еı���
	Images.SMALL_PLANE_IMG = this.imgLoader.getImage(ImageConstants.SMALL_PLANE_POS_X,
		ImageConstants.SMALL_PLANE_POS_Y, ImageConstants.SMALL_PLANE_WIDTH, ImageConstants.SMALL_PLANE_HEIGHT);//С�ɻ�
	Images.BIG_PLANE_IMG = this.imgLoader.getImage(ImageConstants.BIG_PLANE_POS_X, ImageConstants.BIG_PLANE_POS_Y,
		ImageConstants.BIG_PLANE_WIDTH, ImageConstants.BIG_PLANE_HEIGHT);//��ɻ�
	Images.BOSS_PLANE_IMG = this.imgLoader.getImage(ImageConstants.BOSS_PLANE_POS_X,
		ImageConstants.BOSS_PLANE_POS_Y, ImageConstants.BOSS_PLANE_WIDTH, ImageConstants.BOSS_PLANE_HEIGHT);//Boss�ɻ�
	Images.BOMB_IMG = this.imgLoader.getImage(ImageConstants.BOMB_POS_X, ImageConstants.BOMB_POS_Y,
		ImageConstants.BOMB_WIDTH, ImageConstants.BOMB_HEIGHT);//ը��
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
		ImageConstants.SMALL_PLANE_ASHED_HEIGHT);//С�ɻ�����

	Images.BIG_PLANE_FIGHTING_IMG = this.imgLoader.getImage(ImageConstants.BIG_PLANE_FIGHTING_POS_X,
		ImageConstants.BIG_PLANE_FIGHTING_POS_Y, ImageConstants.BIG_PLANE_FIGHTING_WIDTH,
		ImageConstants.BIG_PLANE_FIGHTING_HEIGHT);
	Images.BIG_PLANE_HITTED_IMG = this.imgLoader.getImage(ImageConstants.BIG_PLANE_HITTED_POS_X,
		ImageConstants.BIG_PLANE_HITTED_POS_Y, ImageConstants.BIG_PLANE_HITTED_WIDTH,
		ImageConstants.BIG_PLANE_HITTED_HEIGHT);//���д�ɻ�
	Images.BIG_PLANE_BADDLY_WOUNDED_IMG = this.imgLoader.getImage(ImageConstants.BIG_PLANE_BADDLY_WOUNDED_POS_X,
		ImageConstants.BIG_PLANE_BADDLY_WOUNDED_POS_Y, ImageConstants.BIG_PLANE_BADDLY_WOUNDED_WIDTH,
		ImageConstants.BIG_PLANE_BADDLY_WOUNDED_HEIGHT);//��ɻ�������
	Images.BIG_PLANE_KILLED_IMG = this.imgLoader.getImage(ImageConstants.BIG_PLANE_KILLED_POS_X,
		ImageConstants.BIG_PLANE_KILLED_POS_Y, ImageConstants.BIG_PLANE_KILLED_WIDTH,
		ImageConstants.BIG_PLANE_KILLED_HEIGHT);//��ɻ�����
	Images.BIG_PLANE_ASHED_IMG = this.imgLoader.getImage(ImageConstants.BIG_PLANE_ASHED_POS_X,
		ImageConstants.BIG_PLANE_ASHED_POS_Y, ImageConstants.BIG_PLANE_ASHED_WIDTH,
		ImageConstants.BIG_PLANE_ASHED_HEIGHT);//��ɻ�����

	Images.BOSS_PLANE_FIGHTING_IMG = this.imgLoader.getImage(ImageConstants.BOSS_PLANE_FIGHTING_POS_X,
		ImageConstants.BOSS_PLANE_FIGHTING_POS_Y, ImageConstants.BOSS_PLANE_FIGHTING_WIDTH,
		ImageConstants.BOSS_PLANE_FIGHTING_HEIGHT);//Boss�ɻ�ս��
	Images.BOSS_PLANE_HITTED_IMG = this.imgLoader.getImage(ImageConstants.BOSS_PLANE_HITTED_POS_X,
		ImageConstants.BOSS_PLANE_HITTED_POS_Y, ImageConstants.BOSS_PLANE_HITTED_WIDTH,
		ImageConstants.BOSS_PLANE_HITTED_HEIGHT);//Boss�ɻ�������
	Images.BOSS_PLANE_BADDLY_WOUNDED_IMG = this.imgLoader.getImage(ImageConstants.BOSS_PLANE_BADDLY_WOUNDED_POS_X,
		ImageConstants.BOSS_PLANE_BADDLY_WOUNDED_POS_Y, ImageConstants.BOSS_PLANE_BADDLY_WOUNDED_WIDTH,
		ImageConstants.BOSS_PLANE_BADDLY_WOUNDED_HEIGHT);//Boss�ɻ�������
	Images.BOSS_PLANE_KILLED_IMG = this.imgLoader.getImage(ImageConstants.BOSS_PLANE_KILLED_POS_X,
		ImageConstants.BOSS_PLANE_KILLED_POS_Y, ImageConstants.BOSS_PLANE_KILLED_WIDTH,
		ImageConstants.BOSS_PLANE_KILLED_HEIGHT);//Boss�ɻ�����
	Images.BOSS_PLANE_ASHED_IMG = this.imgLoader.getImage(ImageConstants.BOSS_PLANE_ASHED_POS_X,
		ImageConstants.BOSS_PLANE_ASHED_POS_Y, ImageConstants.BOSS_PLANE_ASHED_WIDTH,
		ImageConstants.BOSS_PLANE_ASHED_HEIGHT);//Boss�ɻ�����

	Images.SCORE_IMG = this.imgLoader.getImage(ImageConstants.SCORE_IMG_POS_X, ImageConstants.SCORE_IMG_POS_Y,
		ImageConstants.SCORE_IMG_WIDTH, ImageConstants.SCORE_IMG_HEIGHT);//����

	this.imgLoader = new ImageLoader(Config.FONT_IMG);//������ĸͼ
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
	this.setIconImage(new ImageIcon(Config.LOGO_IMG).getImage());//���ô���ͼ��Logo
	this.setSize(Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT);//���ô�С
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//��ȡ��Ļ����
	this.setBounds((d.width - Config.MAIN_FRAME_WIDTH) / 2, (d.height - Config.MAIN_FRAME_HEIGHT) / 2,
		Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT);//���ñ߽��С��λ��
	this.setResizable(false);//���ò��������С��
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initSoundPlayer() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	achievementSoundPlayer = new SoundPlayer(Config.ACHIEVEMENT_AUDIO);
	//���ù��췽��������Ƶ
    }

    private void setBackgroundImage() {
	ImageIcon bgImgIcon = new ImageIcon(Images.SHOOT_BACKGROUND_IMG);
	JLabel bgLabel = new JLabel(bgImgIcon);//��ΪJLabel
	this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));//
	//getLayeredPane()���ش˴���� layeredPane ����add��ָ���������ӵ���������β��
	bgLabel.setBounds(0, 0, bgImgIcon.getIconWidth(), bgImgIcon.getIconHeight());
	//���ñ߽�
	((JPanel) this.getContentPane()).setOpaque(false);
	//���ص���container������Ҫת��ΪJpanel
    }

    private void popupMenuPanel() {
	Container c = this.getContentPane();//���ش˴���� contentPane ����
	c.removeAll();//���������Ƴ��������
	this.repaint();//�ػ������ˢ��
	if (this.popupMenuPanel == null) {
	    this.popupMenuPanel = new PopupMenuPanel(this);
	}
	BoxLayout boxLayout = new BoxLayout(c, BoxLayout.Y_AXIS);//��������
	c.setLayout(boxLayout);
	c.add(Box.createVerticalGlue());//��������glue���
	c.add(this.popupMenuPanel);
	c.add(Box.createVerticalGlue());
	this.validate();//��֤�����������������������ʾ
    }

    public void loadGame() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	Container c = this.getContentPane();
	c.removeAll();//�Ƴ��������
	this.repaint();//�ػ��������
	if (this.gameLoadingPanel == null) {//
	    this.gameLoadingPanel = new GameLoadingPanel();
	}

	BoxLayout boxLayout = new BoxLayout(c, BoxLayout.Y_AXIS);//��������
	c.setLayout(boxLayout);
	c.add(Box.createVerticalGlue());//����glue��������Ʋ���
	c.add(this.gameLoadingPanel);
	c.add(Box.createVerticalGlue());
	this.gameLoadingPanel.loadingGame();//������Ϸ����ͼƬ
	this.startGame();//�����ʼ���ò���ʼ��Ϸ
    }

    private void startGame() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	Container c = this.getContentPane();
	c.removeAll();
	this.repaint();//����ˢ��
	BorderLayout borderLayout = new BorderLayout();
	c.setLayout(borderLayout);
	this.gamePlayingPanel = new GamePlayingPanel();
	c.add(this.gamePlayingPanel, BorderLayout.CENTER);//��gamePlayingPanel��������
	this.gamePlayingPanel.startGame();//���÷�����ʼ��Ϸ
	long startTime = System.currentTimeMillis();//��ȡ��ǰʱ��
	while (this.gamePlayingPanel.getMyPlane().isAlive()) {//���������
	    try {
		Thread.sleep(Config.GAME_PANEL_REPAINT_INTERVAL);//�ڵл��ػ��ڼ��߳�˯��
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	//����������
	long endTime = System.currentTimeMillis();//�����Ժ���Ϊ��λ�ĵ�ǰʱ�䡣
	// add to score list
	this.addScore(this.gamePlayingPanel.getScore(), endTime - startTime);
	int option = JOptionPane.showConfirmDialog(this, "Game Over, Score:" + this.gamePlayingPanel.getScore()
		+ ", Start Again?", "Game Over", JOptionPane.YES_NO_OPTION);//����ȷ���Ƿ��ٴο�ʼ
	switch (option) {
	case JOptionPane.YES_OPTION://ѡ��YES�����¼�����Ϸ
	    loadGame();
	    break;
	case JOptionPane.NO_OPTION://ѡ��NO�������Ϸ
	    stopGame();
	    break;
	}
    }
    //��÷��б�����ӵ÷ּ�¼
    private void addScore(int score, long lastMilliSeconds) throws IOException {
	Score s = new Score(new Date(System.currentTimeMillis()), score, lastMilliSeconds);
	int size = this.scoreList.size();//��ȡ��ǰ�÷ּ�¼���ϴ�С
	if (this.scoreList.contains(s)) {//����б����������¼
	    return;
	}
	if (size < Config.MAX_SCORE_COUNT) {
	    this.scoreList.add(s);//���÷ּ�¼С��10��������
	} else {
	    Score lastScore = this.scoreList.get(size - 1);//��ȡ���һ�η���
	    if (s.compareTo(lastScore) > 0) {
		this.scoreList.remove(lastScore);//�Ƴ�ԭ�������һ����¼
		this.scoreList.add(s);//��������¼�����б�
	    }
	}
	Collections.sort(this.scoreList);//�Է����б������������
	Collections.reverse(this.scoreList);//��ת�����б�˳�򣨽���
	FileUtil.writeScore(scoreList, Config.SCORE_FILE);//������д���ļ�
    }

    public void stopGame() {
	popupMenuPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {//�¼�����
	String actionCmd = e.getActionCommand();//��ȡ�¼�Դ
	if (actionCmd.equals(PopupMenuPanel.START_GAME_BUTTON)) {//��ѡ��ʼ��Ϸ
	    startGameAction();
	} else if (actionCmd.equals(PopupMenuPanel.TOP_10_SCORES_BUTTON)) {//��ѡ��top10
	    this.achievementSoundPlayer.play();//������Ӧ����
	    popupScorePanel(this.scoreList);//��ʾ
	} else if (actionCmd.equals(PopupMenuPanel.EXIT_GAME_BUTTON)) {//��ѡ���˳���Ϸ
	    exitGameAction();
	} else if (actionCmd.equals(PopupMenuPanel.HELP_BUTTON)) {//��ѡ�����
	    helpAction();
	} else if (actionCmd.equals(Top10ScorePanel.OK_BUTTON)) {//��ѡ��OK
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
	this.popupScorePanel.loadScore(sortedScoreList);//��ȡ�����б�
	BoxLayout boxLayout = new BoxLayout(c, BoxLayout.Y_AXIS);//��������
	c.setLayout(boxLayout);
	c.add(Box.createVerticalGlue());//��������glue���ճ��
	c.add(this.popupScorePanel);
	c.add(Box.createVerticalGlue());
	this.validate();
    }

    private void startGameAction() {
	new Thread(new StartGameActionClass()).start();//������Ϸ�߳�
    }

    class StartGameActionClass implements Runnable {

	@Override
	public void run() {
	    try {
		loadGame();//������Ϸ
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    private void exitGameAction() {//�˳���Ϸ�¼�����
	System.exit(0);
    }

    private void helpAction() {//�����¼�����
	if (this.helpDialog == null) {
	    this.helpDialog = new HelpDialog();
	}
	this.helpDialog.setVisible(true);
    }
}
