package com.mta.playtube.extractor.alphabet;

import com.mta.playtube.extractor.InfoItemExtractor;
import com.mta.playtube.extractor.exceptions.ParsingException;

public interface AlphabetInfoItemExtractor extends InfoItemExtractor {

    String getAlphabetId() throws ParsingException;
    String getAlphabetName() throws ParsingException;
    String getTotal() throws ParsingException;
}
