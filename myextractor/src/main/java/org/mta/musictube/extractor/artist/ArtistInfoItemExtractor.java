package org.mta.musictube.extractor.artist;

import org.mta.musictube.extractor.InfoItemExtractor;
import org.mta.musictube.extractor.exceptions.ParsingException;

public interface ArtistInfoItemExtractor extends InfoItemExtractor {
    String getArtistId() throws ParsingException;
    String getArtistName() throws ParsingException;
    String getThumbnail() throws ParsingException;
    String getGenre() throws ParsingException;
    int getTotal() throws ParsingException;
    String getPrefix() throws ParsingException;
}
