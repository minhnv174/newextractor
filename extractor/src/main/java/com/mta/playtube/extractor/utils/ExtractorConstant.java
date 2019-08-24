package com.mta.playtube.extractor.utils;

import java.util.HashMap;

public class ExtractorConstant {
    public static final String KIOS_TRENDING = "Trending";
    public static final String KIOS_TOP50 = "Top 50";
    public static final String KIOS_NEW_HOT = "New & hot";
    public static final String KIOS_CONFERENCES = "conferences";
    public static final String KIOS_ALPHABET = "alphabet";

    public static final String DEFAULT_VERSION = "2";
    public static final String EXTRACTOR_PRE_LINK = "https://www.youtube.com/watch?v=";
    public static final String EXTRACTOR_PRE_CHANNEL = "https://www.youtube.com/channel/";
    public static final String EXTRACTOR_PRE_THUMBNAIL = "http://i.ytimg.com/vi/%s/default.jpg";
    public static final String MY_URL_DATA = "c8a98bb99c6f2c711e6457b8d564be7ca6c9bd99321ac43040cd37dc4a6cac13efd3e09ec1ee9ccad3c4369c57c3918a639a5c361cf4d166560af17a45b631e7";
    public static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/";
    public static final String MY_HOST_NO_WWW = "npbamultimedia.com";
    public static final String MY_HOST_WITH_WWW = "www.npbamultimedia.com";

    public static String JSON_FILE_FORMAT = "%s.json";
    public static final String POPULAR_MUSIC = "Popular";
    public static final String POPULAR_BY_VERSION = "Popular_%s";
    public static final String ARTIST_MUSIC = "ArtistData";
    public static final String ARTIST_BY_VERSION = "ArtistData_%s";
    public static final String CATEGORIES_MUSIC = "GenreData";
    public static final String CATEGORIES_MUSIC_BY_VERSION = "GenreData_%s";
    public static final String ARTISTS = "artist.json";
    public static final String GENRES = "genre.json";
    public static final String ALPHABET = "alphabet.json";


    public class SongFieldName {
        public final static String ID = "Id";
        public final static String TITLE = "Title";
        /*public final static String THUMBNAIL = "Thumbnail";
        public final static String GENRE = "Genre";
        public final static String ARTIST = "Artist";*/
        public final static String DURATION = "Duration";
        public final static String TOTAL_VIEW = "TotalView";
        /*public final static String COUNTRY = "Country";*/
        public final static String AUTHOR_ID = "AuthorId";
        public final static String AUTHOR_NAME = "AuthorName";
        public final static String ARTIST = "Artist";
        public final static String GENRE = "Genre";
    }

    public class  ConstantJson {
        public static final String VERSION = "Version";
        public static final String DATA = "Data";
        public static final String NAME = "Name";
        public static final String ID = "Id";
        public static final String THUMBNAIL = "Thumbnail";
    }

    public class BlockJson {
        public static final String ID = "id";
        public static final String SNIPPET = "snippet";
        public static final String CONTENT_DETAILS = "contentDetails";
        public static final String STATISTICS = "statistics";
        public static final String ITEMS = "items";
        public static final String PAGE_TOKEN = "nextPageToken";
    }

    public class Video {
        public static final String TITLE = "title";;
        public static final String ID = "id";
        public static final String VIDEO_ID = "videoId";
        public static final String THUMBNAILS = "thumbnails";
        public static final String VIEW_COUNT = "viewCount";
        public static final String NUM_DIS_LIKES = "dislikeCount";
        public static final String NUM_LIKES = "likeCount";
        public static final String CHANNEL_TITLE = "channelTitle";
        public static final String CHANNEL_ID = "channelId";
        //public static final String DESCRIPTION = "description";
        public static final String DURATION = "duration";
        public static final String PUBLISHEDAT = "publishedAt";
    }

    public static HashMap<String, String> getHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("soundcloud:genres:all-music", "Popular");
        hashMap.put("soundcloud:genres:alternativerock", "Alternative Rock");
        hashMap.put("soundcloud:genres:ambient", "Ambient");
        hashMap.put("soundcloud:genres:classical", "Classical");
        hashMap.put("soundcloud:genres:country", "Country");
        hashMap.put("soundcloud:genres:danceedm", "Dance & EDM");
        hashMap.put("soundcloud:genres:dancehall", "Dancehall");
        hashMap.put("soundcloud:genres:deephouse", "Deep House");
        hashMap.put("soundcloud:genres:disco", "Disco");
        hashMap.put("soundcloud:genres:drumbass", "Drum & Bass");
        hashMap.put("soundcloud:genres:dubstep", "Dubstep");
        hashMap.put("soundcloud:genres:electronic", "Electronic");
        hashMap.put("soundcloud:genres:folksingersongwriter", "Folk & Singer-Songwriter");
        hashMap.put("soundcloud:genres:hiphoprap", "Hip Hop & Rap");
        hashMap.put("soundcloud:genres:house", "House");
        hashMap.put("soundcloud:genres:indie", "Indie");
        hashMap.put("soundcloud:genres:jazzblues", "Jazz & Blues");
        hashMap.put("soundcloud:genres:latin", "Latin");
        hashMap.put("soundcloud:genres:metal", "Metal");
        hashMap.put("soundcloud:genres:piano", "Piano");
        hashMap.put("soundcloud:genres:pop", "Pop");
        hashMap.put("soundcloud:genres:rbsoul", "R&B & Soul");
        hashMap.put("soundcloud:genres:reggae", "Reggae");
        hashMap.put("soundcloud:genres:reggaeton", "Reggaeton");
        hashMap.put("soundcloud:genres:rock", "Rock");
        hashMap.put("soundcloud:genres:soundtrack", "Soundtrack");
        hashMap.put("soundcloud:genres:techno", "Techno");
        hashMap.put("soundcloud:genres:trance", "Trance");
        hashMap.put("soundcloud:genres:trap", "Trap");
        hashMap.put("soundcloud:genres:triphop", "Trip Hop");
        hashMap.put("soundcloud:genres:world", "World");
        return  hashMap;
    }
}
