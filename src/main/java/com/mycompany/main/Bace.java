package com.mycompany.main;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Bace extends JFrame implements ActionListener
{
  //パネル
  private JPanel mainTitleP, titleImageP, titleP, titleButtonP; //タイトル画面用パネル
  private JPanel kaihatsusyaP, mainKaihatsusyaP, kaihatsusyaImageP; //開発者画面用パネル
  private JPanel selectP, imageP; //メニュー画面用パネル
  private JPanel sekigaeMainP, sekigaeTitleP, controlSekigaeButtonP, startSekigaeP, backSekigaeP; //席替え画面用パネル
  private JPanel sekigaeFinishP; //席替え完了画面用パネル
  private JPanel mainSiryouP, makeSiryouP, backSiryouButtonP; //使用資料画面用パネル
  private JPanel mainSiyouP, siyouCommentP; //仕様画面用パネル
  private JPanel mainSettingP, settingControlP, settingBackImageP; //設定画面用パネル

  //ラベル
  private JLabel titleL, buttonL, imageL, exitButtonL; //タイトル画面用ラベル
  private JLabel backImageL; //メニュー画面用ラベル
  private JLabel sekigaeTitleL, startSekigaeButtonL, backSekigaeButtonL; //席替え画面用ラベル
  private JLabel[] chairButtonL; //席替え完了画面用ラベル
  private JLabel kaihatsusyaImageL, makeL; //開発者画面用ラベル
  private JLabel siryouTitleL, makeSiryouCommentL, makeImageL, backSiryouButtonL; //使用資料画面用ラベル
  private JLabel makeSiyouL, backSiyouButtonL; //仕様画面用ラベル
  private JLabel settingBackImageL; //設定画面用ラベル

  //ボタン
  private JButton titleB, exitB; //タイトル画面用ボタン
  private JButton menuButton1, menuButton2, menuButton3, menuButton4, menuButton5, menuButton6; //メニュー画面用ボタン
  private JButton startSekigaeB, backSekigaeB; //席替え画面用ボタン
  private JButton[] chairB; private JButton filesDeleteB; //席替え完了画面用ボタン
  private JButton backKaihatsusyaB; //開発者画面用ボタン
  private JButton backSiryouB; //使用資料画面ボタン
  private JButton backSiyouB; //仕様画面用ボタン
  private JButton settingRowAndColumB, settingNamesB, backSettingB; //設定画面用ボタン
  
  //マップ
  private Map<Integer, String> studentsM;
  
  //配列
  int[] random;

  /*
  * コンストラクタ
  */
  public Bace()
  {
    setTitle("タイトル");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    add(makeTitlePanel());
    setVisible(true);
  }

  /*
  * タイトル画面を表示
  */
  public JPanel makeTitlePanel()
  {
    mainTitleP = new JPanel();
    mainTitleP.setLayout(new BorderLayout());

    titleP = new JPanel();
    titleP.setBackground(Color.LIGHT_GRAY);
    titleL = new JLabel("席替えソフト Ver1.0");
    titleL.setFont(new Font("Monospaced", Font.BOLD, 50));
    titleP.add(titleL);

    titleImageP = new JPanel();
    titleImageP.setLayout(new BorderLayout());
    imageL = new JLabel(new ImageIcon(("title.jpg")));
    titleImageP.add(imageL, BorderLayout.CENTER);

    titleButtonP = new JPanel();
    titleButtonP.setLayout(new GridLayout(1, 2));

    titleB = new JButton();
    titleB.setBackground(Color.WHITE);
    titleB.addActionListener(this);
    buttonL = new JLabel("開始");
    buttonL.setForeground(Color.BLACK);
    buttonL.setFont(new Font("Monospaced", Font.BOLD, 50));
    titleB.add(buttonL);
    
    exitB = new JButton();
    exitB.setBackground(Color.BLACK);
    exitB.addActionListener(this);
    exitButtonL = new JLabel("終了");
    exitButtonL.setForeground(Color.WHITE);
    exitButtonL.setFont(new Font("Monospaced", Font.BOLD, 50));
    exitB.add(exitButtonL);
    
    titleButtonP.add(exitB);
    titleButtonP.add(titleB);

    mainTitleP.add(titleP, BorderLayout.NORTH);
    mainTitleP.add(titleImageP, BorderLayout.CENTER);
    mainTitleP.add(titleButtonP, BorderLayout.SOUTH);

    return mainTitleP;
  }

  /*
  * メニュー画面を表示
  */
  public JPanel menuPanel()
  {
    //全体のパネル設定
    mainTitleP = new JPanel();
    mainTitleP.setLayout(new GridLayout(1, 2));

    //背景に表示する画像の設定
    imageP = new JPanel();
    imageP.setLayout(new BorderLayout());
    backImageL = new JLabel(new ImageIcon("kyoushitsu.jpg"));
    imageP.add(backImageL, BorderLayout.CENTER);

    //席替えボタンとラベルの設定
    menuButton1 = makeButton("席替え");
    menuButton1.addActionListener(this);

    //開発者についてボタンとラベルの設定
    menuButton2 = makeButton("開発者について");
    menuButton2.addActionListener(this);

    //使用資料ボタンとラベルの設定
    menuButton3 = makeButton("引用資料集");
    menuButton3.addActionListener(this);

    //仕様についてボタンとラベルの設定
    menuButton4 = makeButton("仕様について");
    menuButton4.addActionListener(this);

    //設定ボタンとラベルの設定
    menuButton5 = makeButton("設定");
    menuButton5.addActionListener(this);

    //タイトルに戻るボタンとラベルの設定
    menuButton6 = makeButton("タイトルに戻る");
    menuButton6.addActionListener(this);

    //パネルの設定と配置
    selectP = new JPanel();
    selectP.setLayout(new GridLayout(6, 1));
    selectP.add(menuButton1);
    selectP.add(menuButton2);
    selectP.add(menuButton3);
    selectP.add(menuButton4);
    selectP.add(menuButton5);
    selectP.add(menuButton6);

    //パネルを貼り付ける
    mainTitleP.add(imageP);
    mainTitleP.add(selectP);

    return mainTitleP;
  }

  /*
  * 席替え画面を表示
  */
  public JPanel sekigaePanel()
  {
    sekigaeMainP = new JPanel();
    sekigaeMainP.setLayout(new BorderLayout());

    sekigaeTitleP = new JPanel();
    sekigaeTitleP.setLayout(new FlowLayout(FlowLayout.CENTER));
    sekigaeTitleP.setBackground(Color.ORANGE);

    sekigaeTitleL = new JLabel("席替え");
    sekigaeTitleL.setFont(new Font("Monospaced", Font.BOLD, 40));
    sekigaeTitleP.add(sekigaeTitleL);

    controlSekigaeButtonP = new JPanel();
    controlSekigaeButtonP.setLayout(new GridLayout(1, 2));

    startSekigaeP = new JPanel();
    startSekigaeP.setLayout(new FlowLayout(FlowLayout.CENTER));
    startSekigaeButtonL = new JLabel("席替え開始");
    startSekigaeButtonL.setFont(new Font("Monospaced", Font.BOLD, 35));
    startSekigaeB = new JButton();
    startSekigaeB.addActionListener(this);
    startSekigaeB.add(startSekigaeButtonL);
    startSekigaeP.add(startSekigaeB);

    backSekigaeP = new JPanel();
    backSekigaeButtonL = new JLabel("メニュー画面に戻る");
    backSekigaeButtonL.setFont(new Font("Monospaced", Font.BOLD, 35));
    backSekigaeB = new JButton();
    backSekigaeB.addActionListener(this);
    backSekigaeB.add(backSekigaeButtonL);
    backSekigaeP.add(backSekigaeB);

    controlSekigaeButtonP.add(backSekigaeP);
    controlSekigaeButtonP.add(startSekigaeP);


    sekigaeMainP.add(sekigaeTitleP, BorderLayout.NORTH);
    sekigaeMainP.add(controlSekigaeButtonP, BorderLayout.SOUTH);

    return sekigaeMainP;
  }
  
  /*
  * 席替え後の画面を表示
  */
  public JPanel sekigaeFinishPanel(int row, int colum, int removeChairs)
  {
      int chairs = row * colum - removeChairs;
      //乱数を生成
      random = new int[chairs];
      int i, j;
      for(i = 0; i < chairs; i++)
      {
        do
        {
            random[i] = new Random().nextInt(chairs);
            for(j = 0; j < i; j++)
            {
                if(random[i] == random[j])
                {
                    break;
                }
            }
        }while(i != j);
      }
      
      sekigaeFinishP = new JPanel();
      sekigaeFinishP.setLayout(new GridLayout(row, colum));
      chairB = new JButton[chairs];
      chairButtonL = new JLabel[chairs];
      studentsData();
      
      for(i = 0; i < chairs; i++)
      {
          sekigaeFinishP.add(makeChairs(i));
      }
            
      return sekigaeFinishP;
  }
  
  //席替えで使用する「席」を作る関数
  public JButton makeChairs(int number)
  {
    chairB[number] = new JButton();
    chairButtonL[number] = new JLabel(studentsM.get(random[number]));
    chairButtonL[number].setFont(new Font("Monospaced", Font.PLAIN, 30));
    chairB[number].add(chairButtonL[number]);
    return chairB[number];
  }

  /*
  * 開発者画面を表示
  */
  public JPanel kaihatsusyaPanel()
  {
    //全体パネルの設定
    mainKaihatsusyaP = new JPanel();
    mainKaihatsusyaP.setLayout(new GridLayout(1, 2));

    //開発者パネルの設定
    kaihatsusyaP = new JPanel();
    kaihatsusyaP.setBackground(Color.BLACK);
    kaihatsusyaP.setLayout(new GridLayout(5, 1));

    setLabel(" 開発:PG_Katan");
    setLabel(" Twitter:@PG_katan_kun");
    setLabel(" 開発者サイト:http://katankun.html.xdomain.jp/katan.html");
    setLabel(" ソフトの問題はこちらまで→katanprdaisuki@gmail.com");

    //開発者イラストパネルの設定
    kaihatsusyaImageP = new JPanel();
    kaihatsusyaImageP.setLayout(new BorderLayout());
    kaihatsusyaImageL = new JLabel(new ImageIcon("pgkatan.jpg"));
    kaihatsusyaImageP.add(kaihatsusyaImageL, BorderLayout.CENTER);

    //戻るボタンについての設定
    backKaihatsusyaB = new JButton("メニュー画面に戻る");
    backKaihatsusyaB.addActionListener(this);
    backKaihatsusyaB.setFont(new Font("Monospaced", Font.BOLD, 40));
    kaihatsusyaP.add(backKaihatsusyaB, BorderLayout.SOUTH);

    //フレームにコメントと画像を貼り付け
    mainKaihatsusyaP.add(kaihatsusyaP);
    mainKaihatsusyaP.add(kaihatsusyaImageP);

    return mainKaihatsusyaP;
  }

  /*
  * 引用資料集画面を表示
  */
  public JPanel siryouPanel()
  {
    mainSiryouP = new JPanel();
    mainSiryouP.setLayout(new GridLayout(5, 1));

    siryouTitleL = new JLabel("-----引用資料集-----");
    siryouTitleL.setFont(new Font("Monospaced", Font.ITALIC, 50));


    backSiryouB = new JButton();
    backSiryouB.addActionListener(this);
    backSiryouButtonP = new JPanel();
    backSiryouButtonP.setLayout(new BorderLayout());
    backSiryouButtonL = new JLabel("メニュー画面に戻る");
    backSiryouButtonL.setFont(new Font("Monospaced", Font.BOLD, 25));
    backSiryouB.add(backSiryouButtonL);
    backSiryouButtonP.add(backSiryouB, BorderLayout.EAST);

    mainSiryouP.add(siryouTitleL);
    mainSiryouP.add(makeSiryouPanel("title2.jpg", " タイトル画面:https://www.irasutoya.com/2020/01/blog-post_68.html"));
    mainSiryouP.add(makeSiryouPanel("kyoushitsu2.jpg", " メニュー画面:https://www.pixiv.net/users/698864"));
    mainSiryouP.add(makeSiryouPanel("setting2.png", " 設定画面:https://illustrain.com/?p=29700"));
    mainSiryouP.add(backSiryouButtonP);

    return mainSiryouP;
  }

  /*
  * 仕様画面を表示
  */
  public JPanel makeSiyouPanel()
  {
    mainSiyouP = new JPanel();
    mainSiyouP.setLayout(new BorderLayout());

    siyouCommentP = new JPanel();
    siyouCommentP.setLayout(new GridLayout(7, 1));
    siyouCommentP.add(makeComment("-----本ソフトの仕様-----"));
    siyouCommentP.add(makeComment("・画面を縮小、拡大時に背景画像が消える(再度そのページを開いていただければ直るかと)"));
    siyouCommentP.add(makeComment("-----本ソフト使用上の注意-----"));
    siyouCommentP.add(makeComment("画面複製のうえ使用すること(画面拡張でも使用は出来ますが、バグる可能性があります)"));

    backSiyouB = new JButton();
    backSiyouB.addActionListener(this);
    backSiyouButtonL = new JLabel("メニュー画面に戻る");
    backSiyouButtonL.setFont(new Font("Monospaced", Font.BOLD, 50));
    backSiyouB.add(backSiyouButtonL);

    mainSiyouP.add(siyouCommentP, BorderLayout.WEST);
    mainSiyouP.add(backSiyouB, BorderLayout.SOUTH);

    return mainSiyouP;
  }

  /*
  * 設定画面を表示
  */
  public JPanel makeSettingPanel()
  {
    mainSettingP = new JPanel();
    mainSettingP.setLayout(new GridLayout(1, 2));

    settingBackImageP = new JPanel();
    settingBackImageL = new JLabel(new ImageIcon("setting.png"));
    settingBackImageP.add(settingBackImageL);

    settingControlP = new JPanel();
    settingControlP.setLayout(new GridLayout(3, 1));

    settingRowAndColumB = makeSettingButton("席の場所を設定");
    settingRowAndColumB.addActionListener(this);
    settingControlP.add(settingRowAndColumB);

    settingNamesB = makeSettingButton("学生の名前を登録");
    settingNamesB.addActionListener(this);
    settingControlP.add(settingNamesB);

    backSettingB = makeSettingButton("メニュー画面に戻る");
    backSettingB.addActionListener(this);
    settingControlP.add(backSettingB);

    mainSettingP.add(settingBackImageP);
    mainSettingP.add(settingControlP);

    return mainSettingP;
  }
  
  
  //学生の名前と番号をハッシュマップに登録する
  public void studentsData()
  {
    StoreData s = new StoreData();
    int studentsNumbers = s.getDataOfNameFile();
    String[] datas = new String[studentsNumbers];
      
    Path nameFileRelativeP = Paths.get("studentsNameList.txt");
    Path nameFileAbsoluteP = nameFileRelativeP.toAbsolutePath();
    //テキストファイルから学生の番号と名前を読み込む
    try{
        File file = new File(nameFileAbsoluteP.toString());
        if(checkBeforeReadfile(file))
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = null;
            int i = 0;
            while((str = br.readLine()) != null)
            {
                datas[i] = str;
                i ++;
            }
            br.close();
          }
    }catch(FileNotFoundException error1){
        error1.printStackTrace();
    }catch(IOException error2){
        error2.printStackTrace();
    }
      
      //ハッシュアップに情報を入れる
      studentsM = new HashMap<>();
      for(int i = 0; i < studentsNumbers; i++)
      {
        studentsM.put(i, datas[i]);
      }
  }
  
  //学生の名前ファイルから情報の個数を引き出す関数
  public void searchFileInformationsOfNameFile()
  {
      Path nameFileRelativeP = Paths.get("studentsNameList.txt");
      Path nameFileAbsoluteP = nameFileRelativeP.toAbsolutePath();
      try{
          File file = new File(nameFileAbsoluteP.toString());
          if(checkBeforeReadfile(file))
          {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    int dataCounter = 0;
                    while((br.readLine()) != null)
                    {
                        dataCounter += 1;
                    }
                    br.close();
                    StoreData s = new StoreData();
                    s.setDataOfNameFile(dataCounter);
          }
          }catch(FileNotFoundException error3){
                error3.printStackTrace();
          }catch(IOException error4){
                error4.printStackTrace();
          }
  }
  
  //席に関するファイルから情報の個数を調べる関数
  public void searchFileInformationOfChairsPlaceFile()
  {
      Path chairsPlaceFileRelativeP = Paths.get("rowAndColumAndRemoveNumber.txt");
      Path chairsPlaceFileAbsoluteP = chairsPlaceFileRelativeP.toAbsolutePath();
      try{   
          File file = new File(chairsPlaceFileAbsoluteP.toString());
          if(checkBeforeReadfile(file))
          {
              BufferedReader br = new BufferedReader(new FileReader(file));
              int dataCounter = 0;
              while((br.readLine()) != null)
              {
                      dataCounter += 1;
              }
              br.close();
              StoreData s = new StoreData();
              s.setDataOfChairPlaceFile(dataCounter);
          }
       }catch(FileNotFoundException error5){
                error5.printStackTrace();
       }catch(IOException error6){
                error6.printStackTrace();
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

  //コメントラベル作成用の汎用型関数
  public JLabel makeComment(String comment)
  {
    makeSiyouL = new JLabel(comment);
    makeSiyouL.setFont(new Font("Monospaced", Font.BOLD, 18));
    return makeSiyouL;
  }


  //各資料の名前とコメントを含んだパネルを作成する汎用型関数
  public JPanel makeSiryouPanel(String siryouImageName, String siryouLabel)
  {
    makeSiryouP = new JPanel();
    makeSiryouP.setLayout(new GridLayout(1, 2));
    makeImageL = new JLabel(new ImageIcon(siryouImageName));
    makeSiryouCommentL = makeComment(siryouLabel);
    makeSiryouP.add(makeImageL);
    makeSiryouP.add(makeSiryouCommentL);

    return makeSiryouP;
  }


  //ラベル用の汎用型関数
  public void setLabel(String comment)
  {
    makeL = new JLabel(comment);
    makeL.setForeground(Color.WHITE);
    makeL.setFont(new Font("Monospaced", Font.BOLD, 22));
    kaihatsusyaP.add(makeL);
  }

  //メニューで使用する各ボタンを設定する汎用型関数
  public JButton makeButton(String buttonName)
  {
    JLabel buttonLabel = new JLabel(buttonName);
    JButton makeB = new JButton();
    buttonLabel.setFont(new Font("Monospaced", Font.BOLD, 50));
    makeB.add(buttonLabel);
    return makeB;
  }

  //設定画面で使用する各ボタンの汎用型関数
  public JButton makeSettingButton(String settingButtonName)
  {
    JLabel makeSettingButtonL = new JLabel(settingButtonName);
    makeSettingButtonL.setFont(new Font("Monospaced", Font.BOLD, 60));
    JButton makeSettingB = new JButton();
    makeSettingB.add(makeSettingButtonL);
    return makeSettingB;
  }


  /*
  * ボタンが押された際の動作
  */
  @Override
  public void actionPerformed(ActionEvent event)
  {
    if(event.getSource() == titleB) //始めるボタンの場合
    {
      mainTitleP.removeAll();
      setTitle("メニュー");
      add(menuPanel());
      setVisible(true);
    }
    else if(event.getSource() == exitB) //ソフトを終了するボタンの場合
    {
      dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    else if(event.getSource() == menuButton1) //席替えの場合
    {
      mainTitleP.removeAll();
      setTitle("席替え");
      add(sekigaePanel());
      setVisible(true);
    }
    else if(event.getSource() == menuButton2) //開発者についての場合
    {
      mainTitleP.removeAll();
      setTitle("開発者について");
      add(kaihatsusyaPanel());
      setVisible(true);
    }
    else if(event.getSource() == menuButton3) //使用資料の場合
    {
      mainTitleP.removeAll();
      setTitle("使用資料");
      add(siryouPanel());
      setVisible(true);
    }
    else if(event.getSource() == menuButton4) //仕様についての場合
    {
      mainTitleP.removeAll();
      setTitle("仕様について");
      add(makeSiyouPanel());
      setVisible(true);
    }
    else if(event.getSource() == menuButton5) //設定についての場合
    {
      mainTitleP.removeAll();
      setTitle("設定");
      add(makeSettingPanel());
      setVisible(true);
    }
    else if(event.getSource() == menuButton6)
    {
      mainTitleP.removeAll();
      setTitle("タイトル");
      add(makeTitlePanel());
      setVisible(true);
    }
    else if(event.getSource() == startSekigaeB)
    {
        setTitle("席替え完了");
        
        searchFileInformationsOfNameFile();
        searchFileInformationOfChairsPlaceFile();
        
        StoreData s = new StoreData();
        int chairDataNumbers = s.getDataOfChairPlaceFile();
        int[] datas = new int[chairDataNumbers];
      
        Path chairPlaceFileRelativeP = Paths.get("rowAndColumAndRemoveNumber.txt");
        Path chairPlaceFileAbsoluteP = chairPlaceFileRelativeP.toAbsolutePath();
        try{
            File file = new File(chairPlaceFileAbsoluteP.toString());
            if(checkBeforeReadfile(file))
            {
              BufferedReader br = new BufferedReader(new FileReader(file));
              String str;
              int i = 0;
              while((str = br.readLine()) != null)
              {
                  datas[i] = Integer.parseInt(str);
                  i ++;
              }
              br.close();
            }
        }catch(FileNotFoundException error1){
          error1.printStackTrace();
        }catch(IOException error2){
          error2.printStackTrace();
        }
        
        //席を出力する関数に情報を与える(datas[0]:行, datas[1]:列 datas[2]:除外する席の数)
        sekigaeMainP.add(sekigaeFinishPanel(datas[0], datas[1], datas[2]));
        
        controlSekigaeButtonP.removeAll();
        
        controlSekigaeButtonP.setLayout(new GridLayout(1, 3));
        filesDeleteB = new JButton();
        filesDeleteB.setBackground(Color.RED);
        filesDeleteB.addActionListener(this);
        JLabel filesDeleteButtonL = new JLabel("ファイル削除");
        filesDeleteButtonL.setFont(new Font("Monospaced", Font.BOLD, 35));
        filesDeleteB.add(filesDeleteButtonL);
        
        controlSekigaeButtonP.add(backSekigaeB);
        controlSekigaeButtonP.add(startSekigaeB);
        controlSekigaeButtonP.add(filesDeleteB);
        
        setVisible(true);
    }
    else if(event.getSource() == filesDeleteB)
    {
        controlSekigaeButtonP.removeAll();
        controlSekigaeButtonP.add(backSekigaeB);
        setVisible(true);
        
        Path nameFileRelativeP = Paths.get("studentsNameList.txt");
        Path nameFileAbsoluteP = nameFileRelativeP.toAbsolutePath();
        Path chairPlaceFileRelativeP = Paths.get("rowAndColumAndRemoveNumber.txt");
        Path chairPlaceFileAbsoluteP = chairPlaceFileRelativeP.toAbsolutePath();
        File studentsNameListFile = new File(nameFileAbsoluteP.toString());
        File chairPlacesFile = new File(chairPlaceFileAbsoluteP.toString());
        
        studentsNameListFile.delete();
        chairPlacesFile.delete();
    }
    else if(event.getSource() == settingRowAndColumB)
    {
       new SettingChairPlaces();
    }
    else if(event.getSource() == settingNamesB)
    {
      new SettingNamesPage();
    }
    else if(event.getSource() == startSekigaeB)
    {
      setTitle("席替え中");
    }
    else if(event.getSource() == backSekigaeB)
    {
      sekigaeMainP.removeAll();
      setTitle("メニュー");
      add(menuPanel());
      setVisible(true);
    }
    else if(event.getSource() == backKaihatsusyaB)
    {
      mainKaihatsusyaP.removeAll();
      setTitle("メニュー");
      add(menuPanel());
      setVisible(true);
    }
    else if(event.getSource() == backSiryouB)
    {
      mainSiryouP.removeAll();
      setTitle("メニュー");
      add(menuPanel());
      setVisible(true);
    }
    else if(event.getSource() == backSiyouB)
    {
      mainSiyouP.removeAll();
      setTitle("メニュー");
      add(menuPanel());
      setVisible(true);
    }
    else if(event.getSource() == backSettingB)
    {
      mainSettingP.removeAll();
      setTitle("メニュー");
      add(menuPanel());
      setVisible(true);
    }
  }
}
