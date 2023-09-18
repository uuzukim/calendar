package kr.or.ddit.designpattern.adapter;

public class Adapter implements Target {
	private Adaptee adaptee; // has A
	
	public Adapter(Adaptee adaptee) {// 이제 어뎁티 없이 어뎁터 사용 못해~ 
		super();
		this.adaptee = adaptee;
	}

	@Override
	public void request() {
		adaptee.specificRequest();
	}

}
