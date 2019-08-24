package org.mta.musictube.extractor.suggest;

import org.mta.musictube.extractor.ListExtractor;
import org.mta.musictube.extractor.StreamingService;
import org.mta.musictube.extractor.stream.StreamInfoItem;
import org.mta.musictube.extractor.utils.Localization;
import org.mta.musictube.extractor.exceptions.ParsingException;
import org.mta.musictube.extractor.linkhandler.ListLinkHandler;

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
