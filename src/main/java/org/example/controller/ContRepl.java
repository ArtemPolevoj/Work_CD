package org.example.controller;


import org.example.model.ReplacementExcel;
import org.example.model.ReplacementWord;


public class ContRepl {
    private final ReplacementExcel replacementExcel;
    private final ReplacementWord replacementWord;

    public ContRepl(ReplacementExcel replacementExcel, ReplacementWord replacementWord) {
        this.replacementExcel = replacementExcel;
        this.replacementWord = replacementWord;
    }

    public String replacementExcel(String worldReplace, String worldReplacement) {
        return replacementExcel.getResult(worldReplace, worldReplacement);
    }

    public String replacementWord(String worldReplace, String worldReplacement) {
        return replacementWord.getResult(worldReplace, worldReplacement);
    }

}
