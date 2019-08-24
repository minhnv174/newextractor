package com.mta.musictube.extractor.suggest;

import com.mta.musictube.extractor.InfoItemExtractor;
import com.mta.musictube.extractor.exceptions.ParsingException;

public interface SuggestInfoItemExtractor extends InfoItemExtractor {

    String getNextPageToken() throws ParsingException;
    String getKey() throws ParsingException;
}
