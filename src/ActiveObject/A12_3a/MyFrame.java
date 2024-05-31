package ActiveObject.A12_3a;

import ActiveObject.A12_3a.activeobject.ActiveObject;
import ActiveObject.A12_3a.activeobject.ActiveObjectFactory;
import ActiveObject.A12_3a.activeobject.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    private final JTextField textField = new JTextField("word", 10);
    private final JButton button = new JButton("Search");
    private final JTextArea textArea = new JTextArea(20, 20);
    private ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
    private final static String NEWLINE = System.getProperty("line.separator");

    public MyFrame() {
        super("ActiveObject Sample");
        getContentPane().setLayout(new BorderLayout());

        // North
        JPanel north = new JPanel();
        north.add(new JLabel("Search:"));
        north.add(textField);
        north.add(button);
        button.addActionListener(this);

        // Center
        JScrollPane center = new JScrollPane(textArea);

        // Layout
        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // 当[Search]按钮被按下时
    @Override
    public void actionPerformed(ActionEvent e) {
        searchWord(textField.getText());
    }

    // 显示
    private void println(String line) {
        textArea.append(line + NEWLINE);
    }

    // 搜索
    private void searchWord(final String word) {
        // 调用搜索
        final Result<String> result = activeObject.search(word);
        println("Searching " + word + "...");
        // 等待搜索结果的线程
        new Thread(() -> {
            // 等待结果
            final String url = result.getResultValue();
            // 已经获取到了结果，所以这里要委托事件分发线程显示结果
            SwingUtilities.invokeLater(() -> {
                MyFrame.this.println("word = " + word + ", URL = " + url);
            });
        }).start();
    }
}
