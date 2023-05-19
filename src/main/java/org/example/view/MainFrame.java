package org.example.view;

import org.example.controller.ControllerButton;

import javax.swing.*;
import java.awt.*;


public class MainFrame implements Runnable {
    private final ControllerButton controllerButton;

    public MainFrame(ControllerButton controllerButton) {
        this.controllerButton = controllerButton;
    }
    private final JFrame FRAME = new JFrame("Работа с КД");
    private final JTextField FIELD_REPLACE = new JTextField();
    private final JTextField FIELD_REPLACEMENT = new JTextField();
    private final JTextArea AREA_OUT_TEXT = new JTextArea("Вывод результатов работы:\n");
    private final JButton BUTTON_REPLACE = new JButton("Выполнить замену");
    private final JButton BUTTON_MANDATORY = new JButton("Получить ОЗ");
    private final JLabel LABEL_REPLACE = new JLabel("Заменить");
    private final JLabel LABEL_REPLACEMENT = new JLabel("Заменить на");
    private final JScrollPane SCROLL_FIELD_OUT_TEXT = new JScrollPane(AREA_OUT_TEXT);
    private final JButton BUTTON_EXIT = new JButton("ВЫХОД");
    private final Font FONT = new Font("Verdana", Font.BOLD, 14);
    private final Font FONT_BUTTON = new Font("Verdana", Font.BOLD, 18);

    @Override
    public void run() {


        LABEL_REPLACEMENT.setFont(FONT);
        LABEL_REPLACEMENT.setBounds(330, 5, 110, 30);

        LABEL_REPLACE.setFont(FONT);
        LABEL_REPLACE.setBounds(10, 5, 110, 30);

        FIELD_REPLACE.setFont(FONT);
        FIELD_REPLACE.setBounds(115, 5, 200, 30);

        FIELD_REPLACEMENT.setFont(FONT);
        FIELD_REPLACEMENT.setBounds(440, 5, 200, 30);

        AREA_OUT_TEXT.setBounds(10, 120, 630, 180);
        AREA_OUT_TEXT.setLineWrap(true);
        AREA_OUT_TEXT.setWrapStyleWord(true);

        BUTTON_REPLACE.setFont(FONT_BUTTON);
        BUTTON_REPLACE.setBounds(10, 50, 630, 50);
        BUTTON_REPLACE.setBorderPainted(true);
        BUTTON_REPLACE.addActionListener(e -> AREA_OUT_TEXT.append(
                controllerButton.replacement(
                        FIELD_REPLACE.getText(),
                        FIELD_REPLACEMENT.getText())));
        BUTTON_MANDATORY.setFont(FONT_BUTTON);
        BUTTON_MANDATORY.setBounds(10, 320, 630, 50);
        BUTTON_MANDATORY.setBorderPainted(true);
        BUTTON_MANDATORY.addActionListener(e ->
                AREA_OUT_TEXT.append(controllerButton.mandatory()));

        SCROLL_FIELD_OUT_TEXT.setVerticalScrollBarPolicy(20);
        SCROLL_FIELD_OUT_TEXT.setVisible(true);
        SCROLL_FIELD_OUT_TEXT.setBounds(10, 120, 630, 180);

        BUTTON_EXIT.setFont(FONT_BUTTON);
        BUTTON_EXIT.setBorderPainted(true);
        BUTTON_EXIT.setBounds(10, 420, 630, 50);
        BUTTON_EXIT.addActionListener(e -> System.exit(0));

        FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FRAME.setResizable(false);
        FRAME.setLayout(new GridLayout(4, 2, 2, 10));
        FRAME.setLayout(null);
        FRAME.setBounds(400, 120, 665, 520);
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
