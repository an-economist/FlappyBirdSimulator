package xid.zheng;

import java.applet.AudioClip;
import java.net.URL;

public class Gui {

	URL url;
	static AudioClip bounce, bleep; 
	
	public Gui(){
		
	}
	
	public Gui(BouncyBirdTester testerClass) {
		
		try{
			url = testerClass.getCodeBase();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		bounce = testerClass.getAudioClip(url, "Music/Jump.au");
		bleep = testerClass.getAudioClip(url, "Music/Bleep.au");
	}
}
