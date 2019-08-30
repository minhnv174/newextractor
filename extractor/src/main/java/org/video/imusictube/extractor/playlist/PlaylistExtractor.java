package org.video.imusictube.extractor.playlist;

import org.video.imusictube.extractor.ListExtractor;
import org.video.imusictube.extractor.StreamingService;
import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.linkhandler.ListLinkHandler;
import org.video.imusictube.extractor.stream.StreamInfoItem;
import org.video.imusictube.extractor.utils.Localization;

public abstract class PlaylistExtractor extends ListExtractor<StreamInfoItem> {

    public PlaylistExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
    }

    public abstract String getThumbnailUrl() throws ParsingException;
    public abstract String getBannerUrl() throws ParsingException;

    public abstract String getUploaderUrl() throws ParsingException;
    public abstract String getUploaderName() throws ParsingException;
    public abstract String getUploaderAvatarUrl() throws ParsingException;

    public abstract long getStreamCount() throws ParsingException;
}
