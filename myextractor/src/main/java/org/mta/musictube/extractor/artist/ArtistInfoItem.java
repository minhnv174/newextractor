package org.mta.musictube.extractor.artist;

import org.mta.musictube.extractor.InfoItem;

public class ArtistInfoItem extends InfoItem {

    public ArtistInfoItem(int serviceId, String url, String name) {
        super(InfoType.ARTIST, serviceId, url, name);
    }

    private String ArtistId;
    private String ArtistName;
    private String Thumbnail;
    private String Genre;
    private int Total;
    private String Prefix;

    public String getArtistId() {
        return ArtistId;
    }

    public void setArtistId(String artistId) {
        ArtistId = artistId;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        ArtistName = artistName;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public String getPrefix() {
        return Prefix;
    }

    public void setPrefix(String prefix) {
        Prefix = prefix;
    }
}
