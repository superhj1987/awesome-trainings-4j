package srhang.practice;

public enum Elvis {
	INSTANCE;
	
	public void test(){
		System.out.println("test");
	}
    
    public static  void main(String args[]){
        Elvis e = Elvis.INSTANCE;
        e.test();
    }
}
