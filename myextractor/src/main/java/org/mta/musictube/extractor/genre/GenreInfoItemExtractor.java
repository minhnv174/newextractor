package org.mta.musictube.extractor.genre;

import org.mta.musictube.extractor.InfoItemExtractor;
import org.mta.musictube.extractor.exceptions.ParsingException;

public interface GenreInfoItemExtractor extends InfoItemExtractor {

    String getGenreId() throws ParsingException;
    String getGenreName() throws ParsingException;
    String getThumbnail() throws ParsingException;
}
