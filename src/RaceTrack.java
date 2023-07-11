public class RaceTrack {
    private String track;
    private int laps;
    private double difficulty;
    private double danger;

    public RaceTrack(String track, int laps, double difficulty, double danger) {
        this.track = track;
        this.laps = laps;
        this.difficulty = difficulty;
        this.danger = danger;
    }

    public String getTrack() {
        return track;
    }

    public int getLaps() {
        return laps;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public double getDanger() {
        return danger;
    }
}
