package org.mta.musictube.extractor.genre;

import org.mta.musictube.extractor.ListExtractor;
import org.mta.musictube.extractor.StreamingService;
import org.mta.musictube.extractor.exceptions.ParsingException;
import org.mta.musictube.extractor.linkhandler.ListLinkHandler;
import org.mta.musictube.extractor.utils.Localization;
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
