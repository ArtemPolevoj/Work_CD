package org.example.view;

import org.example.controller.ContRepl;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame implements Runnable {

    private final ContRepl contRepl;
    private boolean isExcel = true;
    private String replace;
    private String replacement;
    private boolean replaceNull;

    public MainFrame(ContRepl contRepl) {
        this.contRepl = contRepl;
    }

    @Override
    public void run() {
        JButton buttonReplace = new JButton("Выполнить замену");
        JLabel labelReplace = new JLabel("Заменить");
        JLabel labelReplacement = new JLabel("Заменить на");
        JTextArea areaOutText = new JTextArea();
        JScrollPane scrollFieldOutText = new JScrollPane(areaOutText);
        JButton buttonExit = new JButton("ВЫХОД");
        JTextField textField = new JTextField(10);
        JTextField fieldReplacement = new JTextField(10);


        areaOutText.setText("Вывод результатов работы:\n");
        areaOutText.setLineWrap(true);
        areaOutText.setWrapStyleWord(true);
        Font fontField = new Font("Verdana", Font.BOLD, 14);
        Font fontBatten = new Font("Verdana", Font.BOLD, 20);

        labelReplacement.setFont(fontField);
        labelReplace.setFont(fontField);
        textField.setFont(fontField);
        fieldReplacement.setFont(fontField);

        buttonReplace.setFont(fontBatten);
        buttonReplace.setBorderPainted(true);
        buttonReplace.addActionListener(e -> {
            setStrings(textField.getText(), fieldReplacement.getText());
            if (!replaceNull) {
                if ((isExcel)) {
                     areaOutText.setText(contRepl.replacementExcel(replace, replacement));
                } else {
                    areaOutText.setText(contRepl.replacementWord(replace, replacement));
                }
            }
        });

        scrollFieldOutText.setVerticalScrollBarPolicy(20);
        scrollFieldOutText.setVisible(true);

        buttonExit.setFont(fontBatten);
        buttonExit.setBorderPainted(true);
        buttonExit.addActionListener(e -> System.exit(0));

        JPanel replace = new JPanel(new FlowLayout());
        replace.add(labelReplace);
        replace.add(textField);

        JPanel replacement = new JPanel(new FlowLayout());
        replacement.add(labelReplacement);
        replacement.add(fieldReplacement);

        JPanel labels = new JPanel(new FlowLayout());

        labels.add(replace);
        labels.add(replacement);

        JRadioButton excelButton = new JRadioButton("Excel");
        JRadioButton wordButton = new JRadioButton("Word");
        excelButton.setSelected(true);
        excelButton.addActionListener(e -> isExcel = true);
        wordButton.addActionListener(e -> isExcel = false);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(excelButton);
        buttonGroup.add(wordButton);
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(new JLabel("Выберите формат файла:"));
        buttons.add(excelButton);
        buttons.add(wordButton);

        JPanel info = new JPanel(new BorderLayout());
        info.add(buttons, BorderLayout.NORTH);
        info.add(labels, BorderLayout.SOUTH);

        JPanel north = new JPanel(new BorderLayout());
        north.add(info, BorderLayout.NORTH);
        north.add(buttonReplace, BorderLayout.SOUTH);


        BorderLayout borderLayout = new BorderLayout(20, 10);
        super.setLayout(borderLayout);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setResizable(true);
        super.setTitle("Замена слов в файле");
        super.setBounds(400, 120, 665, 520);

        super.add(north, BorderLayout.NORTH);
        super.add(scrollFieldOutText, BorderLayout.CENTER);
        super.add(buttonExit, BorderLayout.SOUTH);
        super.setVisible(true);
    }

    private void setStrings(String replace, String replacement) {
        this.replacement = replacement;
        if (replace == null || replace.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Введите слово для замены");
            replaceNull = true;
        } else {
            this.replace = replace;
            replaceNull = false;
        }
    }

}
