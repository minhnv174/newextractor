package com.mta.musictube.extractor.artist;

import com.mta.musictube.extractor.InfoItemExtractor;
import com.mta.musictube.extractor.exceptions.ParsingException;

public interface ArtistInfoItemExtractor extends InfoItemExtractor {
    String getArtistId() throws ParsingException;
    String getArtistName() throws ParsingException;
    String getThumbnail() throws ParsingException;
    String getGenre() throws ParsingException;
    int getTotal() throws ParsingException;
    String getPrefix() throws ParsingException;
}
