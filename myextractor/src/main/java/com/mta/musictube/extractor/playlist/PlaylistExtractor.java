package com.mta.musictube.extractor.playlist;

import com.mta.musictube.extractor.ListExtractor;
import com.mta.musictube.extractor.StreamingService;
import com.mta.musictube.extractor.stream.StreamInfoItem;
import com.mta.musictube.extractor.utils.Localization;
import com.mta.musictube.extractor.exceptions.ParsingException;
import com.mta.musictube.extractor.linkhandler.ListLinkHandler;

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
