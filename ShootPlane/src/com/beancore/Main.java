package com.beancore;
import com.beancore.ui.MainFrame;//����MainFrame��

public class Main 
{
    public static void main(String args[]) throws InterruptedException 
    {
	MainFrame mainFrame;
	try {
	    mainFrame = new MainFrame();//����MainFrame����
	    mainFrame.loadGame();//����loadgame��������
		}
	catch (Exception e) 
		{//�����쳣
	    e.printStackTrace();
		}
    }
}
