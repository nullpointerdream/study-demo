package com.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Java实现鼠标随机移动
 */
@Slf4j
public class MouseController implements Runnable {

    private Robot robot;
    private boolean isStop = false;

    public MouseController() {
        try {
            ControllerFrame frame = new ControllerFrame("Prevent Locking");
            frame.setVisible(true);
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int x;
        int y;
        Random random = new Random();
        while (!isStop) {
            //随机生成坐标。
            x = random.nextInt(1000);
            y = random.nextInt(1000);
            //开始移动
            robot.mouseMove(x, y);
            try {
                Thread.sleep(5*60*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    /**
     * GUI Frame 生成一个button，控制程序
     *
     * @author max
     */
    private class ControllerFrame extends JFrame {
        private static final long serialVersionUID = 1L;

        private JButton close = new JButton("close");

        public ControllerFrame(String title) {
            this();
            setTitle(title);
        }

        public ControllerFrame() {
            setLayout(new FlowLayout(FlowLayout.LEADING));
            setSize(100, 100);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            Dimension preferredSize = new Dimension(100, 60);
            Font font = new Font("", 1, 14);

            //设置button 大小，文字等属性
            close.setPreferredSize(preferredSize);
            close.setFont(font);
            close.setBorderPainted(true);
            close.setFocusable(false);

            add(close);

            //点击button后，程序终止。
            close.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isStop = true;
                    dispose();
                }
            });

        }

    }

    private static int getMatchedRate(int matchedCount, int totalCount) {
        int matchedRate = new BigDecimal(100*matchedCount).divide(new BigDecimal(totalCount), 2, BigDecimal.ROUND_HALF_UP).intValue();
        return matchedRate;
    }

    public static final ObjectMapper OM = new ObjectMapper();
    public static String obj2json(Object obj)  {
        try {
            if(null == obj){
                return "";
            }
            return OM.writeValueAsString(obj);
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        String a = "1111.231";
         NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumFractionDigits(2);
        String format = numberFormat.format(new BigDecimal(a));
        System.out.println(format);


        new MouseController().run();
       // String scope = "REGIN_coUNTRY_BUSS_STRORE";
       // System.out.println(JSON.toJSONString(getSubList(scope)));

    }



    public static ArrayList<String> getSubList(String scope) {
        ArrayList<String> a = new ArrayList();
        String[] arr = scope.split("_");  // 按照下划线分割字符串
        for (int i = arr.length; i > 0; i--) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < i; j++) {
                sb.append(arr[j]);
                if (j < i - 1) {
                    sb.append("_");  // 拼接下划线
                }
            }
            a.add(sb.toString());
        }
        return a;
    }

}