package org.example.view;

import org.example.controller.ControllerButton;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame implements Runnable  {
    private final ControllerButton controllerButton;

    public MainFrame(ControllerButton controllerButton) {
        this.controllerButton = controllerButton;
    }

    private final JTextField textField = new JTextField();
    private final JTextField fieldReplacement = new JTextField();
    private final JTextArea areaOutText = new JTextArea("Вывод результатов работы:\n");
    private final JButton buttonReplace = new JButton("Выполнить замену");
    private final JButton buttonMandatory = new JButton("Получить ОЗ");
    private final JLabel labelReplace = new JLabel("Заменить");
    private final JLabel labelReplacement = new JLabel("Заменить на");
    private final JScrollPane scrollFieldOutText = new JScrollPane(areaOutText);
    private final JButton buttonExit = new JButton("ВЫХОД");
    private final Font font = new Font("Verdana", Font.BOLD, 14);

    @Override
    public void run() {


        labelReplacement.setFont(font);
        labelReplacement.setBounds(330, 5, 110, 30);

        labelReplace.setFont(font);
        labelReplace.setBounds(10, 5, 110, 30);

        textField.setFont(font);
        textField.setBounds(115, 5, 200, 30);

        fieldReplacement.setFont(font);
        fieldReplacement.setBounds(440, 5, 200, 30);

        areaOutText.setBounds(10, 120, 630, 180);
        areaOutText.setLineWrap(true);
        areaOutText.setWrapStyleWord(true);

        buttonReplace.setFont(font);
        buttonReplace.setBounds(10, 50, 630, 50);
        buttonReplace.setBorderPainted(true);
        buttonReplace.addActionListener(e -> areaOutText.append(
                controllerButton.replacement(
                        textField.getText(),
                        fieldReplacement.getText())));
        buttonMandatory.setFont(font);
        buttonMandatory.setBounds(10, 320, 630, 50);
        buttonMandatory.setBorderPainted(true);
        buttonMandatory.addActionListener(e ->
                areaOutText.append(controllerButton.mandatory()));

        scrollFieldOutText.setVerticalScrollBarPolicy(20);
        scrollFieldOutText.setVisible(true);
        scrollFieldOutText.setBounds(10, 120, 630, 180);

        buttonExit.setFont(font);
        buttonExit.setBorderPainted(true);
        buttonExit.setBounds(10, 420, 630, 50);
        buttonExit.addActionListener(e -> System.exit(0));

        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLayout(new GridLayout(4, 2, 2, 10));
        super.setLayout(null);
        super.setBounds(400, 120, 665, 520);
        super.add(labelReplace);
        super.add(textField);
        super.add(labelReplacement);
        super.add(fieldReplacement);
        super.add(areaOutText);
        super.add(buttonReplace);
        super.add(scrollFieldOutText);
        super.add(buttonMandatory);
        super.add(buttonExit);
        super.setVisible(true);
    }
}
