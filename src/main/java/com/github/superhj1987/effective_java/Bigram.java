package com.github.superhj1987.effective_java;

public class Bigram {
	private final char first;
	private final char second;

	public Bigram(char first,char second){
		this.first = first;
		this.second = second;
	}
	
	@Override public boolean equals(Object o){
		if(!(o instanceof Bigram)) return false;
		
		Bigram b = (Bigram)o;
		return b.first == first && b.second == second;
	}
	
	@Override public int hashCode(){
		return 31 * first + second;
	}
}
