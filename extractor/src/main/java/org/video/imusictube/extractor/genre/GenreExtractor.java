package org.video.imusictube.extractor.genre;

import org.video.imusictube.extractor.ListExtractor;
import org.video.imusictube.extractor.StreamingService;
import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.linkhandler.ListLinkHandler;
import org.video.imusictube.extractor.utils.Localization;
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
