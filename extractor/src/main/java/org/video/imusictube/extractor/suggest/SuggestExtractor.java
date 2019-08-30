package org.video.imusictube.extractor.suggest;

import org.video.imusictube.extractor.ListExtractor;
import org.video.imusictube.extractor.StreamingService;
import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.linkhandler.ListLinkHandler;
import org.video.imusictube.extractor.stream.StreamInfoItem;
import org.video.imusictube.extractor.utils.Localization;

import javax.annotation.Nonnull;

public abstract class SuggestExtractor extends ListExtractor<StreamInfoItem> {

    @Nonnull
    @Override
    public abstract String getName() throws ParsingException;

    public abstract String getNextPageToken() throws ParsingException;
    public abstract String getKey() throws ParsingException;

    public SuggestExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
    }
}
