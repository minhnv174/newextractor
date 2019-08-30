package org.video.imusictube.extractor.suggest;

import org.video.imusictube.extractor.InfoItemExtractor;
import org.video.imusictube.extractor.exception.ParsingException;

public interface SuggestInfoItemExtractor extends InfoItemExtractor {

    String getNextPageToken() throws ParsingException;
    String getKey() throws ParsingException;
}
