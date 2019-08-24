package org.mta.musictube.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonObject;

import org.mta.musictube.extractor.artist.ArtistInfoItemExtractor;
import org.mta.musictube.extractor.utils.CryptHelper;
import org.mta.musictube.extractor.utils.Utils;
import org.mta.musictube.extractor.exceptions.ParsingException;

public class ArtistItemExtractor implements ArtistInfoItemExtractor {
    protected static final String TAG = ArtistItemExtractor.class.getSimpleName();

    private final JsonObject object;
    private final int version;
    private final CryptHelper objScrypt;

    public ArtistItemExtractor(JsonObject object, int version) {
        this.object = object;
        this.version = version;
        this.objScrypt = new CryptHelper();
    }

    @Override
    public String getArtistId() throws ParsingException {
        return object.getString("Id");
    }

    @Override
    public String getArtistName() throws ParsingException {
        return object.getString("Name");
    }

    @Override
    public String getThumbnail() throws ParsingException {
        return object.getString("Thumbnail");
    }

    @Override
    public String getGenre() throws ParsingException {
        return object.getString("Genre");
    }

    @Override
    public int getTotal() throws ParsingException {
        return object.getInt("Total");
    }

    @Override
    public String getPrefix() throws ParsingException {
        return object.getString("Prefix");
    }

    @Override
    public String getName() throws ParsingException {
        return getArtistName();
    }

    @Override
    public String getUrl() throws ParsingException {
        try {
            final String artist = CryptHelper.bytesToHex(objScrypt.encrypt(getArtistId()));
            return Utils.buildUrlListByArtist(String.valueOf(this.version), getPrefix(), artist);
        } catch (Exception e) {
            throw new ParsingException("Could not get url for the artist", e);
        }
    }

    @Override
    public String getThumbnailUrl() throws ParsingException {
        return getThumbnail();
    }
}
