import javax.swing.*;

public class Main {
    static JFrame mainJFrame = null;//用于存储主窗口
    static Thread thread = null;//用于存储线程
    public static void main(String[] args) {
        thread = new Thread(() -> {
            try {
                mainJFrame =new LwJFramen().mainWin();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
}
