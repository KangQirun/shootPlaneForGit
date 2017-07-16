package com.beancore.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.beancore.entity.Score;

public class FileUtil {
    public static String readFileToString(String filePath) throws IOException {
	StringBuilder sb = new StringBuilder();////����һ�������κ��ַ����ַ���������,����Ϊ16�ַ�
	File file = new File(filePath);//�½��ļ�
	BufferedReader br = new BufferedReader(new FileReader(file));//���ļ����뻺����
	String line = null;
	while ((line = br.readLine()) != null) {//�ǿ�ʱ�����϶�ȡ�ı���
	    sb.append(new String(line.getBytes(),"utf-8")).append("\r\n");
	}
	br.close();//�رջ�����
	return sb.toString();//ת��Ϊ�ַ�����ʾ
    }
    //�������б�д���ļ�
    public static void writeScore(List<Score> scoreList, String filePath) throws FileNotFoundException, IOException {
	ObjectOutputStream objOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
	//��filepathΪ�������ļ���ת��Ϊobject��
	for (Score score : scoreList) {
	    objOutputStream.writeObject(score);//����������
	}
	objOutputStream.writeObject(null);//д��һ��null���󡣷�������ȡʶ���൱��һ����β��־λ
	objOutputStream.flush();//����
	objOutputStream.close();//�ر���
    }
    //���ļ��ж�ȡ����
    public static List<Score> readScore(String filePath) throws FileNotFoundException, IOException,
	    ClassNotFoundException {
	List<Score> scoreList = new ArrayList<Score>();
	ObjectInputStream objInputStream = new ObjectInputStream(new FileInputStream(filePath));
	Object obj = null;
	while ((obj = objInputStream.readObject()) != null) {//�ǿ�ʱ���϶�ȡ����
	    scoreList.add((Score) obj);//��������ӽ����б�
	}
	//�������û�������д��null����ô��������while��������׳��쳣��
	objInputStream.close();//�ر���
	return scoreList;
    }
}