package org.mta.musictube.extractor.playlist;

import org.mta.musictube.extractor.InfoItemExtractor;
import org.mta.musictube.extractor.exceptions.ParsingException;

public interface PlaylistInfoItemExtractor extends InfoItemExtractor {

    /**
     * Get the uploader name
     * @return the uploader name
     * @throws ParsingException
     */
    String getUploaderName() throws ParsingException;

    /**
     * Get the number of streams
     * @return the number of streams
     * @throws ParsingException
     */
    long getStreamCount() throws ParsingException;
}
