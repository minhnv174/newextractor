package org.mta.musictube.extractor.suggest;

import org.mta.musictube.extractor.InfoItemExtractor;
import org.mta.musictube.extractor.exceptions.ParsingException;

public interface SuggestInfoItemExtractor extends InfoItemExtractor {

    String getNextPageToken() throws ParsingException;
    String getKey() throws ParsingException;
}
