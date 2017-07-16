package com.beancore.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * This class is used to load the picture of each item from a whole picture
 * */
public class ImageLoader {

    private BufferedImage sourceImg;

    public ImageLoader(String imagePath) throws IOException {//ͼƬ������
	sourceImg = ImageIO.read(new File(imagePath));

    }

    public Image getImage(int posX, int posY, int width, int height) {//�ü���ͼ
	BufferedImage targetImg = this.sourceImg.getSubimage(posX, posY, width, height);
	//����������		�ü���ͼ��X��Y:���ϽǺ�������      �����߱�ʾͼƬ�ĳ���
	Image img = new ImageIcon(targetImg).getImage();//����ͼƬ
	return img;
    }

}
