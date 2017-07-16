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
    private JTextPane helpContentTextPane;//�ı���
    private JScrollPane scrollPane;

    public HelpDialog() {
	this.initComponent();//��ʼ�����
    }

    private void initComponent() {
	this.helpContentTextPane = new JTextPane();
	this.helpContentTextPane.setEditable(false);//����Ϊ���ɱ༭
	this.helpContentTextPane.setContentType("text/html;charset=utf-8");
	try {
	    this.helpContentTextPane.setText(FileUtil.readFileToString(Config.HELP_FILE_PATH));//��ȡ�����ļ�
	} catch (IOException e) {
	    e.printStackTrace();//�������쳣�����
	}

	this.scrollPane = new JScrollPane(this.helpContentTextPane);
	this.scrollPane.setAutoscrolls(true);

	Container c = this.getContentPane();
	c.add(this.scrollPane, BorderLayout.CENTER);//�߽粼�֣����м�

	this.setTitle("Help");//���ñ���
	this.setIconImage(new ImageIcon(Config.LOGO_IMG).getImage());//����LOGO
	this.setSize(Config.HELP_DIALOG_WIDTH, Config.HELP_DIALOG_HEIGHT);//�����Ի���ܴ�С
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

}