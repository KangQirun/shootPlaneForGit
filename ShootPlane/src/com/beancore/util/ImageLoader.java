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

    public ImageLoader(String imagePath) throws IOException {//图片加载器
	sourceImg = ImageIO.read(new File(imagePath));

    }

    public Image getImage(int posX, int posY, int width, int height) {//裁剪子图
	BufferedImage targetImg = this.sourceImg.getSubimage(posX, posY, width, height);
	//按矩形区域		裁剪子图，X、Y:左上角横纵坐标      后两者表示图片的长宽
	Image img = new ImageIcon(targetImg).getImage();//返回图片
	return img;
    }

}
