package org.example.view;

import org.example.controller.ContMand;
import org.example.controller.ContRepl;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame implements Runnable, Repl, TextArea {

    private final ContRepl contRepl;
    private final ContMand contMand;


    public MainFrame(ContRepl contRepl, ContMand contMand) {
        this.contMand = contMand;
        this.contRepl = contRepl;
    }

    @Override
    public void run() {
        JButton buttonReplace = new JButton("Выполнить замену");

        JButton buttonMandatory = new JButton("Получить ОЗ");
        JLabel labelReplace = new JLabel("Заменить");
        JLabel labelReplacement = new JLabel("Заменить на");
        JScrollPane scrollFieldOutText = new JScrollPane(areaOutText);
        JButton buttonExit = new JButton("ВЫХОД");
        buttonExit.setText("ВЫХОД");

        areaOutText.setText("Вывод результатов работы:\n");
        areaOutText.setLineWrap(true);
        areaOutText.setWrapStyleWord(true);
        Font fontField = new Font("Verdana", Font.BOLD, 14);
        Font fontBatten = new Font("Verdana", Font.BOLD, 20);

        labelReplacement.setFont(fontField);
        labelReplacement.setBounds(330, 5, 110, 30);

        labelReplace.setFont(fontField);
        labelReplace.setBounds(10, 5, 110, 30);

        textField.setFont(fontField);
        textField.setBounds(115, 5, 200, 30);

        fieldReplacement.setFont(fontField);
        fieldReplacement.setBounds(440, 5, 200, 30);


        buttonReplace.setFont(fontBatten);
        buttonReplace.setBounds(10, 50, 630, 50);
        buttonReplace.setBorderPainted(true);

        buttonReplace.addActionListener(e -> contRepl.repls());

        buttonMandatory.setFont(fontBatten);
        buttonMandatory.setBounds(10, 320, 630, 50);
        buttonMandatory.setBorderPainted(true);

        buttonMandatory.addActionListener(e -> contMand.mandat());

        scrollFieldOutText.setVerticalScrollBarPolicy(20);
        scrollFieldOutText.setVisible(true);
        scrollFieldOutText.setBounds(10, 120, 630, 180);

        buttonExit.setFont(fontBatten);
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
        super.add(buttonReplace);
        super.add(scrollFieldOutText);
        super.add(buttonMandatory);
        super.add(buttonExit);
        super.setVisible(true);
    }
}
