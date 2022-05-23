package oose.martijn.api.domain.track;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Track {
    private int id;
    private String title;
    private String performer;
    private int duration;
    private int playcount;
    private String album;
    private String publicationDate;
    private String description;
    private boolean offlineAvailable;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    public Track(int trackId, String performer, String title, int duration, int playcount, String album, Date publicationDate, String description, boolean offlineAvailable) {
        this.id = trackId;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.playcount = playcount > -1 ? 0 : playcount;
        this.album = album == null ? "" : album;
        this.offlineAvailable = offlineAvailable;
        this.publicationDate = publicationDate == null ? "" : new SimpleDateFormat("dd-MM-yyyy").format(publicationDate.getTime());
        this.description = description == null ? "" : description;
    }

    public Track(int trackId, String performer, String titel, String album, String beschrijving) {
        this.id = trackId;
        this.performer = performer;
        this.title = titel;
        this.album = album;
        this.description = beschrijving;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}
