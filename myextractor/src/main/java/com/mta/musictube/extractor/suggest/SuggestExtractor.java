package com.mta.musictube.extractor.suggest;

import com.mta.musictube.extractor.ListExtractor;
import com.mta.musictube.extractor.StreamingService;
import com.mta.musictube.extractor.stream.StreamInfoItem;
import com.mta.musictube.extractor.utils.Localization;
import com.mta.musictube.extractor.exceptions.ParsingException;
import com.mta.musictube.extractor.linkhandler.ListLinkHandler;

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
