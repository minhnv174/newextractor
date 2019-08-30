package org.video.imusictube.extractor.artist;

import org.video.imusictube.extractor.InfoItemExtractor;
import org.video.imusictube.extractor.exception.ParsingException;

public interface ArtistInfoItemExtractor extends InfoItemExtractor {
    String getArtistId() throws ParsingException;
    String getArtistName() throws ParsingException;
    String getThumbnail() throws ParsingException;
    String getGenre() throws ParsingException;
    int getTotal() throws ParsingException;
    String getPrefix() throws ParsingException;
}
