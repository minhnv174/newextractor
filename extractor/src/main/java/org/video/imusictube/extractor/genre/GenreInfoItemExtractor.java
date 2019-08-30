package org.video.imusictube.extractor.genre;

import org.video.imusictube.extractor.InfoItemExtractor;
import org.video.imusictube.extractor.exception.ParsingException;

public interface GenreInfoItemExtractor extends InfoItemExtractor {

    String getGenreId() throws ParsingException;
    String getGenreName() throws ParsingException;
    String getThumbnail() throws ParsingException;
}
