package design.pattern.Adapter;

interface AdvanceMediaPlayer {
	public void playMP3(String fileName);
	public void playMP4(String fileName);
}

class MP3 implements AdvanceMediaPlayer {
	MP3() {
		System.out.println("MP3 file opened!");
	}
	
	public void playMP3(String fileName) {
		System.out.println(fileName+" is playning...");
	}
	
	public void playMP4(String fileName) {
		//System.out.println(fileName+" is playning...");
	}

}

class MP4 implements AdvanceMediaPlayer {
	MP4() {
		System.out.println("MP4 file opened!");
	}
	
	public void playMP3(String fileName) {
		//System.out.println(fileName+" is playning...");
	}
	
	public void playMP4(String fileName) {
		System.out.println(fileName+" is playning...");
	}
}

interface MediaAdapter {
	public void play(String mediaType, String fileName);
}

class Adapter implements MediaAdapter {
	AdvanceMediaPlayer advanceMediaPlayer;

	@Override
	public void play(String mediaType, String fileName) {
		if("MP4".equalsIgnoreCase(mediaType)) {
			advanceMediaPlayer = new MP4();
			advanceMediaPlayer.playMP4(fileName);
		} else if("MP3".equalsIgnoreCase(mediaType)) {
			advanceMediaPlayer = new MP3();
			advanceMediaPlayer.playMP3(fileName);
		} else   {
			System.out.println("MediaAdapter does not support media type "+mediaType);
		}
	}
}


public class AdapterExecutor {

	public static void main(String[] args) {
		MediaAdapter mediaAdapter = new Adapter();
		mediaAdapter.play("MP3", "The Ocean");
		mediaAdapter.play("MP4", "Sunny Day");
		mediaAdapter.play("VLC", "Happy");

	}

}
