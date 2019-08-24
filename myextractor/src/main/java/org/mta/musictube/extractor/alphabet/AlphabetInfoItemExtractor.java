package org.mta.musictube.extractor.alphabet;

import org.mta.musictube.extractor.InfoItemExtractor;
import org.mta.musictube.extractor.exceptions.ParsingException;

public interface AlphabetInfoItemExtractor extends InfoItemExtractor {

    String getAlphabetId() throws ParsingException;
    String getAlphabetName() throws ParsingException;
    String getTotal() throws ParsingException;
}
