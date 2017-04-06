package musicplayer.audio;

public interface Audio {
    public void play();

    public void pause();

    public void stop();

    public void close();

    public void setCurrentSecs(long currentSecs);

    public long getCurrentSecs();

    public static long getTotalSecs() {
		// TODO Auto-generated method stub
		return 0;
	}
}
