package com.mta.musictube.extractor.genre;

import com.mta.musictube.extractor.ListExtractor;
import com.mta.musictube.extractor.StreamingService;
import com.mta.musictube.extractor.exceptions.ParsingException;
import com.mta.musictube.extractor.linkhandler.ListLinkHandler;
import com.mta.musictube.extractor.utils.Localization;
import javax.annotation.Nonnull;

public abstract class GenreExtractor extends ListExtractor<GenreInfoItem> {

    public GenreExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
    }

    @Nonnull
    @Override
    public abstract String getName() throws ParsingException;

    public abstract String getGenreId() throws ParsingException;
    public abstract String getGenreName() throws ParsingException;
    public abstract String getVersion() throws ParsingException;
}
