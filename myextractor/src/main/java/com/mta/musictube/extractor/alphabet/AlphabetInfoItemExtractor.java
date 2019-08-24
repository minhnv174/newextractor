package com.mta.musictube.extractor.alphabet;

import com.mta.musictube.extractor.InfoItemExtractor;
import com.mta.musictube.extractor.exceptions.ParsingException;

public interface AlphabetInfoItemExtractor extends InfoItemExtractor {

    String getAlphabetId() throws ParsingException;
    String getAlphabetName() throws ParsingException;
    String getTotal() throws ParsingException;
}
