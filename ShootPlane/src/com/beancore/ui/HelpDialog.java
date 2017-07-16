package com.beancore.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.beancore.config.Config;
import com.beancore.util.FileUtil;

public class HelpDialog extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextPane helpContentTextPane;//文本域
    private JScrollPane scrollPane;

    public HelpDialog() {
	this.initComponent();//初始化组件
    }

    private void initComponent() {
	this.helpContentTextPane = new JTextPane();
	this.helpContentTextPane.setEditable(false);//设置为不可编辑
	this.helpContentTextPane.setContentType("text/html;charset=utf-8");
	try {
	    this.helpContentTextPane.setText(FileUtil.readFileToString(Config.HELP_FILE_PATH));//读取帮助文件
	} catch (IOException e) {
	    e.printStackTrace();//若发生异常则输出
	}

	this.scrollPane = new JScrollPane(this.helpContentTextPane);
	this.scrollPane.setAutoscrolls(true);

	Container c = this.getContentPane();
	c.add(this.scrollPane, BorderLayout.CENTER);//边界布局，放中间

	this.setTitle("Help");//设置标题
	this.setIconImage(new ImageIcon(Config.LOGO_IMG).getImage());//设置LOGO
	this.setSize(Config.HELP_DIALOG_WIDTH, Config.HELP_DIALOG_HEIGHT);//帮助对话框架大小
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

}