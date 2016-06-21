package com.github.superhj1987.trainings.effective_java;

import java.util.EnumSet;
import java.util.Set;

public class Text {
	public enum Style{
		Bold,ITALIC,UNDERLINE,STRIKETHROUGH
	}
	
	public void applyStyles(Set<Style> styles){
		
	}
	
	public static void main(String args[]){
		Text text = new Text();
		text.applyStyles(EnumSet.of(Style.Bold,Style.ITALIC));
	}
}
