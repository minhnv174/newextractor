package org.video.imusictube.extractor;

import org.video.imusictube.extractor.exception.ParsingException;

public interface InfoItemExtractor {
    String getName() throws ParsingException;
    String getUrl() throws ParsingException;
    String getThumbnailUrl() throws ParsingException;
}
