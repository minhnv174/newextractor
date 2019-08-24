package com.mta.playtube.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonObject;
import com.mta.playtube.extractor.alphabet.AlphabetInfoItemExtractor;
import com.mta.playtube.extractor.utils.Utils;

import com.mta.playtube.extractor.exceptions.ParsingException;

public class AlphabetItemExtractor implements AlphabetInfoItemExtractor {
    protected static final String TAG = AlphabetItemExtractor.class.getSimpleName();
    private final JsonObject object;
    private final int version;

    public AlphabetItemExtractor(JsonObject object, int version) {
        this.object = object;
        this.version = version;
    }

    @Override
    public String getName() throws ParsingException {
        return getAlphabetName();
    }

    @Override
    public String getUrl() throws ParsingException {
        return Utils.buildUrlArtist(String.valueOf(this.version), getAlphabetName().toLowerCase());
    }

    @Override
    public String getThumbnailUrl() throws ParsingException {
        return null;
    }

    @Override
    public String getAlphabetId() throws ParsingException {
        return object.getString("Id");
    }

    @Override
    public String getAlphabetName() throws ParsingException {
        return object.getString("Name");
    }

    @Override
    public String getTotal() throws ParsingException {
        return object.getString("Total");
    }
}
