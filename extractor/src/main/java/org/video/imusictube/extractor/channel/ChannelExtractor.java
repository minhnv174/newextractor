package org.video.imusictube.extractor.channel;

import org.video.imusictube.extractor.ListExtractor;
import org.video.imusictube.extractor.StreamingService;
import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.stream.StreamInfoItem;
import org.video.imusictube.extractor.linkhandler.ListLinkHandler;
import org.video.imusictube.extractor.utils.Localization;


public abstract class ChannelExtractor extends ListExtractor<StreamInfoItem> {

    public ChannelExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
    }

    public abstract String getAvatarUrl() throws ParsingException;
    public abstract String getBannerUrl() throws ParsingException;
    public abstract String getFeedUrl() throws ParsingException;
    public abstract long getSubscriberCount() throws ParsingException;
    public abstract String getDescription() throws ParsingException;
}
