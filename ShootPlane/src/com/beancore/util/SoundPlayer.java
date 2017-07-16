package com.beancore.util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {//声音播放器
    private Clip clip;

    public SoundPlayer(String filePath) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	File file = new File(filePath);//新建文件
	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
	//获取音频流
	clip = AudioSystem.getClip();
	clip.open(audioInputStream);//播放音频
    }

    public void play() {//播放
	clip.setFramePosition(0);
	clip.start();
    }

    public void loop() {//设置循环播放
	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {//停止播放音乐，缓冲区的音频的数据资源保留
clip.stop();
    }

}
