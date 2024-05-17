package org.example.controller;

import org.example.model.Replacement;
import org.example.view.Repl;

public class ContRepl implements Repl {
    public ContRepl(Replacement replacement) {
        this.replacement = replacement;
    }

    private final Replacement replacement;

    public void repls() {
        areaOutText.append("Замена " + textField.getText() + " на " + fieldReplacement.getText() + "\n");
        areaOutText.append(replacement.getResult(
                textField.getText(),
                fieldReplacement.getText()));

    }
}
