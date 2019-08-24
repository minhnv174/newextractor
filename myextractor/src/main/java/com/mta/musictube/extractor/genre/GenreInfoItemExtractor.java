package com.mta.musictube.extractor.genre;

import com.mta.musictube.extractor.InfoItemExtractor;
import com.mta.musictube.extractor.exceptions.ParsingException;

public interface GenreInfoItemExtractor extends InfoItemExtractor {

    String getGenreId() throws ParsingException;
    String getGenreName() throws ParsingException;
    String getThumbnail() throws ParsingException;
}
