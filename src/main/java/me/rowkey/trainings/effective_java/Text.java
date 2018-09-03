package me.rowkey.trainings.effective_java;

import java.util.EnumSet;
import java.util.Set;

public class Text {
    public static void main(String args[]) {
        Text text = new Text();
        text.applyStyles(EnumSet.of(Style.Bold, Style.ITALIC));
    }

    public void applyStyles(Set<Style> styles) {

    }

    public enum Style {
        Bold, ITALIC, UNDERLINE, STRIKETHROUGH
    }
}
