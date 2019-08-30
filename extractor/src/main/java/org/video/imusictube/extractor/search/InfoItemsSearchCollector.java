package org.video.imusictube.extractor.search;

import org.video.imusictube.extractor.InfoItem;
import org.video.imusictube.extractor.InfoItemExtractor;
import org.video.imusictube.extractor.InfoItemsCollector;
import org.video.imusictube.extractor.channel.ChannelInfoItemExtractor;
import org.video.imusictube.extractor.channel.ChannelInfoItemsCollector;
import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.playlist.PlaylistInfoItemExtractor;
import org.video.imusictube.extractor.playlist.PlaylistInfoItemsCollector;
import org.video.imusictube.extractor.stream.StreamInfoItemExtractor;
import org.video.imusictube.extractor.stream.StreamInfoItemsCollector;

/**
 * Collector for search results
 *
 * This collector can handle the following extractor types:
 * <ul>
 *     <li>{@link StreamInfoItemExtractor}</li>
 *     <li>{@link ChannelInfoItemExtractor}</li>
 *     <li>{@link PlaylistInfoItemExtractor}</li>
 * </ul>
 * Calling {@link #extract(InfoItemExtractor)} or {@link #commit(Object)} with any
 * other extractor type will raise an exception.
 */
public class InfoItemsSearchCollector extends InfoItemsCollector<InfoItem, InfoItemExtractor> {
    private final StreamInfoItemsCollector streamCollector;
    private final ChannelInfoItemsCollector userCollector;
    private final PlaylistInfoItemsCollector playlistCollector;

    InfoItemsSearchCollector(int serviceId) {
        super(serviceId);
        streamCollector = new StreamInfoItemsCollector(serviceId);
        userCollector = new ChannelInfoItemsCollector(serviceId);
        playlistCollector = new PlaylistInfoItemsCollector(serviceId);
    }

    @Override
    public InfoItem extract(InfoItemExtractor extractor) throws ParsingException {
        // Use the corresponding collector for each item extractor type
        if(extractor instanceof StreamInfoItemExtractor) {
            return streamCollector.extract((StreamInfoItemExtractor) extractor);
        } else if(extractor instanceof ChannelInfoItemExtractor) {
            return userCollector.extract((ChannelInfoItemExtractor) extractor);
        } else if(extractor instanceof PlaylistInfoItemExtractor) {
            return playlistCollector.extract((PlaylistInfoItemExtractor) extractor);
        } else {
            throw new IllegalArgumentException("Invalid extractor type: " + extractor);
        }
    }
}
