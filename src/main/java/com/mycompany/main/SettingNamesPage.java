package com.mycompany.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SettingNamesPage extends JFrame implements ActionListener
{
  //パネル
  private JPanel settingNamesTitleP;
  private JPanel settingNamesP; //行と列を指定する画面のパネル
  private JPanel buttonsP1; //行と列画面から遷移する際のボタンを配置するためのパネル
  private JPanel buttonsP2;
  private JPanel textFieldP;
  private JPanel studentsListP;
  private JPanel nameSpaceP;
  private JPanel studentsListTitleP;
  private JPanel cautionP;

  //ラベル
  private JLabel rowAndColumTitleL;
  private JLabel makeButtonL;
  private JLabel numbersL;
  private JLabel studentsListTitleL;
  private JLabel finishL;
  private JLabel cautionL1, cautionL2, cautionL3;

  //ボタン
  private JButton makeB;
  private JButton decideB, backB, registerB, exitB;
  private JButton finishB;
  
  //テキストエリア
  private JTextArea nameSpace;

  //文字列
  private String numbersS;

  //テキストフィールド
  private JTextField numbersFi;
  
  //整数
  private int numbers;


  /*
  * コンストラクタ
  */
  public SettingNamesPage()
  {
    setSize(1200, 600);
    setLocationRelativeTo(null);
    setTitle("人数・行・列を指定");
    add(makeSettingNamesPagePanel());
    setVisible(true);
  }

  /*
  * 人数を指定する画面を表示
  */
  public JPanel makeSettingNamesPagePanel()
  {
    settingNamesP = new JPanel();
    settingNamesP.setLayout(new GridLayout(4, 1));

    settingNamesTitleP = new JPanel();
    settingNamesTitleP.setBackground(Color.BLACK);
    settingNamesTitleP.setLayout(new FlowLayout(FlowLayout.CENTER));
    rowAndColumTitleL = new JLabel("人数を指定してください");
    rowAndColumTitleL.setForeground(Color.WHITE);
    rowAndColumTitleL.setFont(new Font("Monospaced", Font.BOLD, 50));
    settingNamesTitleP.add(rowAndColumTitleL);

    numbersL = new JLabel("人数");
    numbersL.setFont(new Font("Monospaced", Font.BOLD, 60));
    numbersFi = new JTextField(5);
    numbersFi.setFont(new Font("Monospaced", Font.BOLD, 60));
    
    textFieldP = new JPanel();
    textFieldP.setLayout(new FlowLayout(FlowLayout.CENTER));
    textFieldP.add(numbersL);
    textFieldP.add(numbersFi);

    cautionL1 = new JLabel("※注意");
    cautionL1.setFont(new Font("Monospaced", Font.BOLD, 40));
    cautionL2 = new JLabel("必ず枠に数値を入力してください(バグります)");
    cautionL2.setFont(new Font("Monospaced", Font.BOLD, 30));
    cautionL3 = new JLabel("ディレクトリ内にstudentsNameList.txtが存在しないことを確認して下さい");
    cautionL3.setFont(new Font("Monospaced", Font.BOLD, 30));
    
    cautionP = new JPanel();
    cautionP.setBackground(Color.YELLOW);
    cautionP.setLayout(new GridLayout(3, 1));
    cautionP.add(cautionL1);
    cautionP.add(cautionL2);
    cautionP.add(cautionL3);
    
    buttonsP1 = new JPanel();
    buttonsP1.setLayout(new GridLayout(1, 2));

    exitB = new JButton();
    exitB = makeButton("閉じる", Color.BLUE);
    exitB.addActionListener(this);

    decideB = new JButton();
    decideB = makeButton("決定", Color.RED);
    decideB.addActionListener(this);

    buttonsP1.add(exitB);
    buttonsP1.add(decideB);

    settingNamesP.add(settingNamesTitleP);
    settingNamesP.add(textFieldP);
    settingNamesP.add(cautionP);
    settingNamesP.add(buttonsP1);

    return settingNamesP;
  }

  /*
  * 学生の名前を入力する画面
  */
  public JPanel makeStudentsListPanel(int catchNumber)
  {
    studentsListP = new JPanel();
    studentsListP.setLayout(new BorderLayout());
    
    studentsListTitleP = new JPanel();
    studentsListTitleL = new JLabel("学生の名前を登録してください。(通し番号順にフルネームで名前を一人ずつ改行しながら入力すること)");
    studentsListTitleP.add(studentsListTitleL);
    
    nameSpace = new JTextArea(catchNumber, 20);
    nameSpace.setFont(new Font("Monospaced", Font.BOLD, 16));
    
    JScrollPane scrollpane = new JScrollPane(nameSpace);
    
    nameSpaceP = new JPanel();
    nameSpaceP.setLayout(new BorderLayout());
    nameSpaceP.add(scrollpane, BorderLayout.CENTER);
    
    backB = makeButton("戻る", Color.BLUE);
    backB.addActionListener(this);
    registerB = makeButton("登録", Color.GREEN);
    registerB.addActionListener(this);
    
    buttonsP2 = new JPanel();
    buttonsP2.setLayout(new GridLayout(1, 2));
    buttonsP2.add(backB);
    buttonsP2.add(registerB);
    
    studentsListP.add(studentsListTitleP, BorderLayout.NORTH);
    studentsListP.add(nameSpaceP, BorderLayout.CENTER);
    studentsListP.add(buttonsP2, BorderLayout.SOUTH);
    
    return studentsListP;
   }

  //ボタンを作成する汎用型関数
  public JButton makeButton(String buttonName, Color colorName)
  {
    makeB = new JButton();
    makeB.setBackground(colorName);
    makeButtonL = new JLabel(buttonName);
    makeButtonL.setForeground(Color.WHITE);
    makeButtonL.setFont(new Font("Monospaced", Font.ITALIC, 40));
    makeB.add(makeButtonL);
    return makeB;
  }

  @Override
  public void actionPerformed(ActionEvent event)
  {
    if(event.getSource() == decideB) //決定ボタンが押された時の動作
    {
      numbersS = numbersFi.getText();
      numbers = Integer.parseInt(numbersS);
      setTitle("学生の名前を登録");
      settingNamesP.removeAll();
      
      add(makeStudentsListPanel(numbers));
      setVisible(true);
    }
    else if(event.getSource() == registerB) //登録ボタンが押されたときの動作
    {
        setTitle("登録完了");
        
        buttonsP2.removeAll();
        nameSpaceP.removeAll();
        studentsListTitleP.removeAll();
        
        nameSpaceP.setLayout(new FlowLayout(FlowLayout.CENTER));
        finishL = new JLabel("設定が完了しました");
        finishL.setFont(new Font("Monospaced", Font.BOLD, 50));
        nameSpaceP.add(finishL);
        
        buttonsP2.setLayout(new FlowLayout(FlowLayout.CENTER));
        finishB = makeButton("終了", Color.ORANGE);
        finishB.addActionListener(this);
        buttonsP2.add(finishB);
        setVisible(true);
        
        Path relativeP1 = Paths.get("studentsNameList.txt");
        Path absoluteP1 = relativeP1.toAbsolutePath();
        try{
            Files.createFile(absoluteP1);
        }catch(IOException error1){
            error1.printStackTrace();
        }
        
        try{
            File file = new File(absoluteP1.toString());
            if(checkBeforeWriteFile(file))
            {
                FileWriter fileWriter = new FileWriter(file);
                for(int i = 0; i < numbers; i++)
                {
                    int start = nameSpace.getLineStartOffset(i);
                    int end = nameSpace.getLineEndOffset(i);
                    fileWriter.write(nameSpace.getText(start, end - start));
                }
                fileWriter.close();
            }
        }catch(IOException error2){
                error2.printStackTrace();
        }catch(BadLocationException error3){
            error3.printStackTrace();
        }
    }
    else if(event.getSource() == backB) //戻るボタンが押された時の動作
    {
        studentsListP.removeAll();
        setTitle("人数を指定");
        add(makeSettingNamesPagePanel());
        setVisible(true);
    }
    else if(event.getSource() == exitB) //閉じるボタンが押された時の動作
    {
      dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    else if(event.getSource() == finishB)
    {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
  }
  
  private boolean checkBeforeReadfile(File file)
  {
    if (file.exists())
    {
        if (file.isFile() && file.canRead())
        {
            return true;
        }
     }
     return false;
   }
  
    private boolean checkBeforeWriteFile(File file)
    {
        if(file.exists())
        {
            if(file.isFile() && file.canWrite())
            {
                return true;
            }
        }
        return false;
    }
}