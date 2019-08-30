package org.video.imusictube.extractor.channel;

import org.video.imusictube.extractor.InfoItemExtractor;
import org.video.imusictube.extractor.exception.ParsingException;

public interface ChannelInfoItemExtractor extends InfoItemExtractor {
    String getDescription() throws ParsingException;

    long getSubscriberCount() throws ParsingException;
    long getStreamCount() throws ParsingException;
}
