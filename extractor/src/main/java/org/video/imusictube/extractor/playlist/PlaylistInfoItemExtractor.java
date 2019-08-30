package org.video.imusictube.extractor.playlist;

import org.video.imusictube.extractor.InfoItemExtractor;
import org.video.imusictube.extractor.exception.ParsingException;

public interface PlaylistInfoItemExtractor extends InfoItemExtractor {

    /**
     * Get the uploader name
     * @return the uploader name
     * @throws ParsingException
     */
    String getUploaderName() throws ParsingException;

    /**
     * Get the number of stream
     * @return the number of stream
     * @throws ParsingException
     */
    long getStreamCount() throws ParsingException;
}
