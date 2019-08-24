package org.mta.musictube.extractor.artist;

import org.mta.musictube.extractor.ListExtractor;
import org.mta.musictube.extractor.StreamingService;
import org.mta.musictube.extractor.utils.Localization;
import org.mta.musictube.extractor.exceptions.ParsingException;
import org.mta.musictube.extractor.linkhandler.ListLinkHandler;

import javax.annotation.Nonnull;

public abstract class ArtistExtractor extends ListExtractor<ArtistInfoItem> {

    public ArtistExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
    }

    @Nonnull
    @Override
    public abstract String getName() throws ParsingException;

    public abstract String getArtistId() throws ParsingException;
    public abstract String getArtistName() throws ParsingException;
    public abstract String getVersion() throws ParsingException;
}
