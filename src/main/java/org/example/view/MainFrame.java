package org.example.view;


import javax.swing.*;
import java.awt.*;


abstract class MainFrame implements Runnable {
    private final JFrame FRAME = new JFrame("Работа с КД");
    private final JLabel LABEL_REPLACE = new JLabel("Заменить");
    private final JLabel LABEL_REPLACEMENT = new JLabel("Заменить на");
    JTextField FIELD_REPLACE = new JTextField();
    JTextField FIELD_REPLACEMENT = new JTextField();
    JTextArea AREA_OUT_TEXT = new JTextArea("Вывод результатов работы:\n");
    JButton BUTTON_REPLACE = new JButton("Выполнить замену");
    private final JScrollPane SCROLL_FIELD_OUT_TEXT = new JScrollPane(AREA_OUT_TEXT);
    JButton BUTTON_MANDATORY = new JButton("Получить ОЗ");
    private final JButton BUTTON_EXIT = new JButton("ВЫХОД");
//    private final Controller CONTROLLER;


//    public MainFrame(Controller controller) {
//        CONTROLLER = controller;
//    }

    @Override
    public void run() {

        FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FRAME.setResizable(false);
        FRAME.setLayout(new GridLayout(4, 2, 2, 10));
        FRAME.setLayout(null);
        FRAME.setBounds(400, 120, 665, 520);

        LABEL_REPLACE.setFont(new Font("Verdana", Font.BOLD, 14));
        LABEL_REPLACE.setBounds(10, 5, 110, 30);


        FIELD_REPLACE.setFont(new Font("Verdana", Font.BOLD, 14));
        FIELD_REPLACE.setBounds(115, 5, 200, 30);


        LABEL_REPLACEMENT.setFont(new Font("Verdana", Font.BOLD, 14));
        LABEL_REPLACEMENT.setBounds(330, 5, 110, 30);


        FIELD_REPLACEMENT.setFont(new Font("Verdana", Font.BOLD, 14));
        FIELD_REPLACEMENT.setBounds(440, 5, 200, 30);


        AREA_OUT_TEXT.setFont(new Font("Verdana", Font.PLAIN, 14));
        AREA_OUT_TEXT.setBounds(10, 120, 630, 180);
        AREA_OUT_TEXT.setLineWrap(true);
        AREA_OUT_TEXT.setWrapStyleWord(true);


        BUTTON_REPLACE.setFont(new Font("Verdana", Font.BOLD, 16));
        BUTTON_REPLACE.setBounds(10, 50, 630, 50);
//        BUTTON_REPLACE.addActionListener(e -> AREA_OUT_TEXT.append(
//                CONTROLLER.replacement(
//                        FIELD_REPLACE.getText(),
//                        FIELD_REPLACEMENT.getText())));

        BUTTON_REPLACE.setBorderPainted(true);


        SCROLL_FIELD_OUT_TEXT.setVerticalScrollBarPolicy(20);
        SCROLL_FIELD_OUT_TEXT.setVisible(true);
        SCROLL_FIELD_OUT_TEXT.setBounds(10, 120, 630, 180);


        BUTTON_MANDATORY.setFont(new Font("Verdana", Font.BOLD, 20));
        BUTTON_MANDATORY.setBorderPainted(true);
        BUTTON_MANDATORY.setBounds(10, 320, 630, 50);

//        BUTTON_MANDATORY.addActionListener(e ->
//                AREA_OUT_TEXT.append(CONTROLLER.mandatory()));


        BUTTON_EXIT.setFont(new Font("Verdana", Font.BOLD, 20));
        BUTTON_EXIT.setBorderPainted(true);
        BUTTON_EXIT.setBounds(10, 420, 630, 50);
        BUTTON_EXIT.addActionListener(e -> System.exit(0));


        FRAME.add(LABEL_REPLACE);
        FRAME.add(FIELD_REPLACE);
        FRAME.add(LABEL_REPLACEMENT);
        FRAME.add(FIELD_REPLACEMENT);
        FRAME.add(AREA_OUT_TEXT);
        FRAME.add(BUTTON_REPLACE);
        FRAME.add(SCROLL_FIELD_OUT_TEXT);
        FRAME.add(BUTTON_MANDATORY);
        FRAME.add(BUTTON_EXIT);
        FRAME.setVisible(true);
    }
}
