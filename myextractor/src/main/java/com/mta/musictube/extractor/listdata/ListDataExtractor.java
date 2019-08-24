package com.mta.musictube.extractor.listdata;

import com.mta.musictube.extractor.ListExtractor;
import com.mta.musictube.extractor.StreamingService;
import com.mta.musictube.extractor.stream.StreamInfoItem;
import com.mta.musictube.extractor.utils.Localization;
import com.mta.musictube.extractor.exceptions.ParsingException;
import com.mta.musictube.extractor.linkhandler.ListLinkHandler;

import javax.annotation.Nonnull;

public abstract class ListDataExtractor extends ListExtractor<StreamInfoItem> {
    @Nonnull
    @Override
    public abstract String getName() throws ParsingException;

    public abstract String getVersion() throws ParsingException;
    public abstract String getListId() throws ParsingException;
    public abstract String getListName() throws ParsingException;
    public abstract String getThumbnail() throws ParsingException;

    public ListDataExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
    }
}
