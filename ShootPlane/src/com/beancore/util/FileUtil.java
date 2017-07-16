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
	StringBuilder sb = new StringBuilder();////构造一个不带任何字符的字符串生成器,容量为16字符
	File file = new File(filePath);//新建文件
	BufferedReader br = new BufferedReader(new FileReader(file));//将文件进入缓冲流
	String line = null;
	while ((line = br.readLine()) != null) {//非空时，不断读取文本行
	    sb.append(new String(line.getBytes(),"utf-8")).append("\r\n");
	}
	br.close();//关闭缓冲流
	return sb.toString();//转化为字符串表示
    }
    //将分数列表写入文件
    public static void writeScore(List<Score> scoreList, String filePath) throws FileNotFoundException, IOException {
	ObjectOutputStream objOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
	//以filepath为名创建文件并转换为object流
	for (Score score : scoreList) {
	    objOutputStream.writeObject(score);//将分数加入
	}
	objOutputStream.writeObject(null);//写入一个null对象。方便后面读取识别，相当于一个结尾标志位
	objOutputStream.flush();//缓冲
	objOutputStream.close();//关闭流
    }
    //从文件中读取分数
    public static List<Score> readScore(String filePath) throws FileNotFoundException, IOException,
	    ClassNotFoundException {
	List<Score> scoreList = new ArrayList<Score>();
	ObjectInputStream objInputStream = new ObjectInputStream(new FileInputStream(filePath));
	Object obj = null;
	while ((obj = objInputStream.readObject()) != null) {//非空时不断读取分数
	    scoreList.add((Score) obj);//将分数添加进入列表
	}
	//如果上面没有在最后写入null，那么上面的这个while语句最后会抛出异常。
	objInputStream.close();//关闭流
	return scoreList;
    }
}