package com.mta.musictube.extractor.genre;

import com.mta.musictube.extractor.InfoItem;

public class GenreInfoItem  extends InfoItem {
    public GenreInfoItem(int serviceId, String url, String name) {
        super(InfoType.GENRE, serviceId, url, name);
    }

    private String GenreId;
    private String GenreName;
    private String Thumbnail;

    public String getGenreId() {
        return GenreId;
    }

    public void setGenreId(String genreId) {
        GenreId = genreId;
    }

    public String getGenreName() {
        return GenreName;
    }

    public void setGenreName(String genreName) {
        GenreName = genreName;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }
}
