package ActiveObject.A12_3b;

import ActiveObject.A12_3b.searcher.Display;
import ActiveObject.A12_3b.searcher.Searcher;
import ActiveObject.A12_3b.searcher.SearcherFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements Display, ActionListener {
    private final JTextField textfield = new JTextField("word", 10);
    private final JButton button = new JButton("Search");
    private final JTextArea textarea = new JTextArea(20, 30);
    private final Searcher searcher = SearcherFactory.createSearcher();
    private final static String NEWLINE = System.getProperty("line.separator");

    public MyFrame() {
        super("ActiveObject Sample");
        getContentPane().setLayout(new BorderLayout());

        // North
        JPanel north = new JPanel();
        north.add(new JLabel("Search:"));
        north.add(textfield);
        north.add(button);
        button.addActionListener(this);

        // Center
        JScrollPane center = new JScrollPane(textarea);

        // Layout
        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // 显示
    @Override
    public void display(String line) {
        // 委托事件分发线程进行显示
        SwingUtilities.invokeLater(() -> {
            MyFrame.this.println(line);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        searchWord(textfield.getText());
    }

    private void println(String line) {
        textarea.append(line + NEWLINE);
    }

    // 搜索
    private void searchWord(String word) {
        // 调用搜索
        searcher.search(word, this);
        println("Searching " + word + "...");
    }
}
