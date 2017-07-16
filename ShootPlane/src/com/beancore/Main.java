package com.beancore;
import com.beancore.ui.MainFrame;//导入MainFrame类

public class Main 
{
    public static void main(String args[]) throws InterruptedException 
    {
	MainFrame mainFrame;
	try {
	    mainFrame = new MainFrame();//创建MainFrame对象
	    mainFrame.loadGame();//调用loadgame（）方法
		}
	catch (Exception e) 
		{//捕获异常
	    e.printStackTrace();
		}
    }
}
