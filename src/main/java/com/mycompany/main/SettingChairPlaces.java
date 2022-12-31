package com.mycompany.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class SettingChairPlaces extends JFrame implements ActionListener{
    //パネル
    private JPanel settingChairPlacesP;
    private JPanel settingChairPlacesAboveP;
    private JPanel settingChairPlacesUnderP;
    private JPanel settingChairPlacesTitleP;
    private JPanel descriptionLabeblP;
    private JPanel aboveTextFieldsP;
    private JPanel underTextFieldP;
    private JPanel buttonsP1, buttonsP2;
    private JPanel removeChairsP;
    private JPanel textAreaLeftP, textAreaRightP, textAreaP;
    private JPanel removeChairsTitleP;
    private JPanel cautionP;
    
    //ラベル
    private JLabel settingChairPlacesTitleL;
    private JLabel descriptionL;
    private JLabel rowL, columL;
    private JLabel removeChairsL, removeChairsTitleL;
    private JLabel makeButtonL;
    private JLabel cautionL1, cautionL2, cautionL3;
    private JLabel finishL;
    
    //ボタン
    private JButton exitB, decideB, backB, registerB;
    private JButton makeB;
    private JButton finishB;
    
    //テキストフィールド
    private JTextField rowFi, columFi;
    private JTextField removeChairsFi;
    
    //テキストエリア
    private JTextArea rowArea;
    private JTextArea columArea;
    
    //整数
    private int removeChairs;
    
    /*
    * コンストラクタ
    */
    public SettingChairPlaces(){
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setTitle("席の場所を設定");
        add(makeSettingChairPlacesPanel());
        setVisible(true);
    }
    
    /*
    * 席の行と列、不要な席の個数を指定する画面を表示
    */
    public JPanel makeSettingChairPlacesPanel()
    {
        //メインのパネル
        settingChairPlacesP = new JPanel();
        settingChairPlacesP.setLayout(new GridLayout(4, 1));
        
        //上部のパネル
        settingChairPlacesAboveP = new JPanel();
        settingChairPlacesAboveP.setLayout(new BorderLayout());
        
        //上部のパネルの一番上の部分
        settingChairPlacesTitleP = new JPanel();
        settingChairPlacesTitleP.setLayout(new FlowLayout(FlowLayout.CENTER));
        settingChairPlacesTitleP.setBackground(Color.BLACK);
        settingChairPlacesTitleL = new JLabel("行と列を指定してください");
        settingChairPlacesTitleL.setForeground(Color.WHITE);
        settingChairPlacesTitleL.setFont(new Font("Monospaced", Font.BOLD, 40));
        settingChairPlacesTitleP.add(settingChairPlacesTitleL);
        settingChairPlacesAboveP.add(settingChairPlacesTitleP, BorderLayout.NORTH);
        
        //行と列を指定する部分
        aboveTextFieldsP = new JPanel();
        aboveTextFieldsP.setLayout(new FlowLayout(FlowLayout.CENTER));
        //行について
        rowL = new JLabel("行: ");
        rowL.setFont(new Font("Monospaced", Font.BOLD, 40));
        rowFi = new JTextField(3);
        rowFi.setFont(new Font("Monospaced", Font.BOLD, 40));
        //列について
        columL = new JLabel("列: ");
        columL.setFont(new Font("Monospaced", Font.BOLD, 40));
        columFi = new JTextField(3);
        columFi.setFont(new Font("Monospaced", Font.BOLD, 40));
        //パネルに追加
        aboveTextFieldsP.add(rowL);
        aboveTextFieldsP.add(rowFi);
        aboveTextFieldsP.add(columL);
        aboveTextFieldsP.add(columFi);
        settingChairPlacesAboveP.add(aboveTextFieldsP, BorderLayout.CENTER);
        
        //下部のパネル
        settingChairPlacesUnderP = new JPanel();
        settingChairPlacesUnderP.setLayout(new BorderLayout());
        
        //下部のパネルの一番上の部分
        descriptionLabeblP = new JPanel();
        descriptionLabeblP.setLayout(new FlowLayout(FlowLayout.CENTER));
        descriptionLabeblP.setBackground(Color.BLACK);
        descriptionL = new JLabel("上記で設定した中で除外する席はいくつありますか");
        descriptionL.setForeground(Color.WHITE);
        descriptionL.setFont(new Font("Monospaced", Font.BOLD, 40));
        descriptionLabeblP.add(descriptionL);
        settingChairPlacesUnderP.add(descriptionLabeblP, BorderLayout.NORTH);
        
        //除外する席の数を指定する部分
        underTextFieldP = new JPanel();
        underTextFieldP.setLayout(new FlowLayout(FlowLayout.CENTER));
        removeChairsFi = new JTextField(3);
        removeChairsFi.setFont(new Font("Monospaced", Font.BOLD, 40));
        removeChairsL = new JLabel("個");
        removeChairsL.setFont(new Font("Monospaced", Font.BOLD, 40));
        underTextFieldP.add(removeChairsFi);
        underTextFieldP.add(removeChairsL);
        settingChairPlacesUnderP.add(underTextFieldP, BorderLayout.CENTER);
        
        
        //最下部のボタン
        buttonsP1 = new JPanel();
        buttonsP1.setLayout(new GridLayout(1, 2));
        exitB = makeButton("閉じる", Color.BLUE);
        exitB.addActionListener(this);
        decideB = makeButton("決定", Color.RED);
        decideB.addActionListener(this);
        buttonsP1.add(exitB);
        buttonsP1.add(decideB);
        
        
        //上部と下部のパネルを追加
        settingChairPlacesP.add(settingChairPlacesAboveP);
        settingChairPlacesP.add(settingChairPlacesUnderP);
        
        //注意文追加
        cautionL1= new JLabel("※注意");
        cautionL1.setFont(new Font("Monospaced", Font.BOLD, 40));
        cautionL2 = new JLabel("必ず全ての枠に数値を入力してください(バグります)");
        cautionL2.setFont(new Font("Monospaced", Font.BOLD, 30));
        cautionL3 = new JLabel("rowAndColumNumber.txtがディレクトリ内に存在しないことを確認してください");
        cautionL3.setFont(new Font("Monospaced", Font.BOLD, 30));
        
        cautionP = new JPanel();
        cautionP.setBackground(Color.YELLOW);
        cautionP.setLayout(new GridLayout(3, 1));
        cautionP.add(cautionL1);
        cautionP.add(cautionL2);
        cautionP.add(cautionL3);
        settingChairPlacesP.add(cautionP);
        
        //ボタンを追加
        settingChairPlacesP.add(buttonsP1);
        
        return settingChairPlacesP;
    }
    
    /*
    * 不要な席の場所を指定する画面
    */
    public JPanel makeRemoveChairsPanel()
    {
        removeChairsP = new JPanel();
        removeChairsP.setLayout(new BorderLayout());
        
        removeChairsTitleP = new JPanel();
        removeChairsTitleP.setLayout(new FlowLayout(FlowLayout.CENTER));
        removeChairsTitleL = new JLabel("除外する席の行と列を指定してください(右が行,左が列)");
        removeChairsTitleL.setFont(new Font("Monospaced", Font.BOLD, 40));
        removeChairsTitleP.add(removeChairsTitleL);
        
        textAreaP = new JPanel();
        textAreaP.setLayout(new GridLayout(1, 2));
        
        textAreaLeftP = new JPanel();
        rowArea = new JTextArea(removeChairs, 3);
        rowArea.setFont(new Font("Monospaced", Font.BOLD, 30));
        JScrollPane scrollpaneLeft = new JScrollPane(rowArea);
        
        textAreaRightP = new JPanel();
        columArea = new JTextArea(removeChairs, 3);
        columArea.setFont(new Font("Monospaced", Font.BOLD, 30));
        JScrollPane scrollpaneRight = new JScrollPane(columArea);
        
        buttonsP2 = new JPanel();
        buttonsP2.setLayout(new GridLayout(1, 2));
        backB = makeButton("戻る", Color.BLUE);
        backB.addActionListener(this);
        registerB = makeButton("登録",  Color.GREEN);
        registerB.addActionListener(this);
        buttonsP2.add(backB);
        buttonsP2.add(registerB);
        
        textAreaP.add(scrollpaneLeft);
        textAreaP.add(scrollpaneRight);
        
        removeChairsP.add(removeChairsTitleP, BorderLayout.NORTH);
        removeChairsP.add(textAreaP, BorderLayout.CENTER);
        removeChairsP.add(buttonsP2, BorderLayout.SOUTH);
        
        return removeChairsP;
    }
    
    
    //ボタンを作成する汎用型関数
    public JButton makeButton(String buttonName, Color buttonColor)
    {
        makeB = new JButton();
        makeB.setBackground(buttonColor);
        makeButtonL = new JLabel(buttonName);
        makeButtonL.setFont(new Font("Monospaced", Font.BOLD, 50));
        makeButtonL.setLayout(new FlowLayout(FlowLayout.CENTER));
        makeButtonL.setForeground(Color.WHITE);
        makeB.add(makeButtonL);
        return makeB;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == decideB)
        {
            removeChairs = Integer.parseInt(removeChairsFi.getText());
            settingChairPlacesP.removeAll();
            setTitle("除外する席の場所を指定");
            add(makeRemoveChairsPanel());
            setVisible(true);
        }
        else if(event.getSource() == backB)
        {
            removeChairsP.removeAll();
            setTitle("席の場所を設定");
            add(makeSettingChairPlacesPanel());
            setVisible(true);
        }
        else if(event.getSource() == registerB)
        {
            setTitle("登録完了");
            
            removeChairsTitleP.removeAll();
            textAreaP.removeAll();
            buttonsP2.removeAll();
            
            textAreaP.setLayout(new FlowLayout(FlowLayout.CENTER));
            finishL = new JLabel("設定が完了しました");
            finishL.setFont(new Font("Monospaced", Font.BOLD, 50));
            textAreaP.add(finishL);
            
            buttonsP2.setLayout(new FlowLayout(FlowLayout.CENTER));
            finishB = makeButton("終了", Color.ORANGE);
            finishB.addActionListener(this);
            buttonsP2.add(finishB);
            setVisible(true);
            
            Path relativeP2 = Paths.get("rowAndColumAndRemoveNumber.txt");
            Path absoluteP2 = relativeP2.toAbsolutePath();
            try{
                Files.createFile(absoluteP2);
            }catch(IOException error1){
                error1.printStackTrace();
            }
            
            try{
                File file = new File(absoluteP2.toString());
                if(checkBeforeWriteFile(file))
                {
                    FileWriter fileWriter = new FileWriter(file);
                    
                    fileWriter.write(rowFi.getText()); //行
                    fileWriter.write("\r\n");
                    fileWriter.write(columFi.getText()); //列
                    fileWriter.write("\r\n");
                    fileWriter.write(removeChairsFi.getText()); //取り除く席の数
                    fileWriter.write("\r\n");
                    
                    for(int i = 0; i < removeChairs; i++)
                    {
                        int start1 = rowArea.getLineStartOffset(i);
                        int end1 = rowArea.getLineEndOffset(i);
                        fileWriter.write(rowArea.getText(start1, end1 - start1));            
                        int start2 = columArea.getLineStartOffset(i);
                        int end2 = columArea.getLineEndOffset(i);
                        if(i == removeChairs - 1)
                        {
                            fileWriter.write("\r\n");
                        }
                        fileWriter.write(columArea.getText(start2, end2 - start2));   
                    }
                    fileWriter.close();
                }
            }catch(IOException error2){
                error2.printStackTrace();
            }catch(BadLocationException error3)
            {
                error3.printStackTrace();
            }
            
        }
        else if(event.getSource() == exitB)
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
