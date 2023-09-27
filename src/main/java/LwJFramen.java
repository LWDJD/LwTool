import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static java.awt.Toolkit.getDefaultToolkit;

public class LwJFramen extends JFrame {
    static boolean quiverRestric = false;//用于存储是否抖动冷却

    public JFrame mainWin() throws InterruptedException {
        Dimension screenSize = getDefaultToolkit().getScreenSize();//获取分辨率
        //设置窗口的大小和位置
        int winWidth = screenSize.width*3/12;//为分辨率的分数
        int winHeight = screenSize.height*3/6;//为分辨率的分数
        int winX = screenSize.width / 2 - winWidth / 2;
        int winY = screenSize.height / 2 - winHeight / 2;
        setBounds(winX,winY,winWidth,winHeight);//设置窗口大小和位置

        Container container = getContentPane();
        setTitle("老王工具箱");// 设置窗口标题
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口关闭同时关闭程
        //设置窗口的图标
        String imageUrl = "src/main/resources/img/20220506140228.jpg";//设置读取路径
        ImageIcon imageIcon = new ImageIcon(imageUrl);//读取图片
        Image image = imageIcon.getImage();//存入变量
        setIconImage(image);//设置为图标
        //设置按钮
        int blX=20;
        int blY=20;
        JButton button = new JButton("点击我！");// 创建一个JButton对象
        button.setBounds(10,10,10,10);
        button.addActionListener(e -> {
            if (!quiverRestric) {
                try {
                    new LwJFramen().quiver(Main.mainJFrame);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        container.add(button);

        setVisible(true);//显示窗口
        return this;
    }
    //窗口抖动
    public void quiver(JFrame jFrame) throws InterruptedException {
        Point a = jFrame.getLocationOnScreen();
        int winX = a.x;
        int winY = a.y;
        jFrame.setLocation(winX+3,winY-3);
        Thread.sleep(40);
        jFrame.setLocation(winX-4,winY-4);
        Thread.sleep(40);
        jFrame.setLocation(winX+4,winY-4);
        Thread.sleep(40);
        jFrame.setLocation(winX-4,winY+4);
        Thread.sleep(40);
        jFrame.setLocation(winX+4,winY-4);
        Thread.sleep(40);
        jFrame.setLocation(winX-3,winY+3);
        Thread.sleep(40);
        jFrame.setLocation(winX,winY);
        new LwJFramen().quiverCooling(270);//启动冷却
    }
    //抖动冷却
    public void quiverCooling(int CoolingTime){
        Thread thread = new Thread(() -> {
            quiverRestric=true;
            try {
                Thread.sleep(CoolingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            quiverRestric=false;
        });
        thread.start();
    }
}
