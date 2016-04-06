package xid.zheng;

import java.awt.Image;
import java.net.URL;

//Done
public class Pictures {

	static Image bird;
	static Image coin;
	static Image tubes;
	URL url;
	static BouncyBirdTester testClass;
	
	public Pictures(BouncyBirdTester testClass) {
		try{
			url = testClass.getDocumentBase();
		}catch(Exception e){
			e.printStackTrace();
		}
		bird = testClass.getImage(url, "images/frame-9.png");
		coin = testClass.getImage(url, "images/bitcoin.png");
		tubes = testClass.getImage(url, "images/tubes2.png");
		this.testClass = testClass;
	}
}
