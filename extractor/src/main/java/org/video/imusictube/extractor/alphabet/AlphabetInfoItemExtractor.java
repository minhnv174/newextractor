package org.video.imusictube.extractor.alphabet;

import org.video.imusictube.extractor.InfoItemExtractor;
import org.video.imusictube.extractor.exception.ParsingException;

public interface AlphabetInfoItemExtractor extends InfoItemExtractor {

    String getAlphabetId() throws ParsingException;
    String getAlphabetName() throws ParsingException;
    String getTotal() throws ParsingException;
}
