public class Race {
    private String name;
    private String track;
    private int laps;

    public Race(String name, String track, int laps) {
        this.name = name;
        this.track = track;
        this.laps = laps;
    }

    public String getName() {
        return name;
    }

    public String getTrack() {
        return track;
    }

    public int getLaps() {
        return laps;
    }
}
