package com.mta.musictube.extractor.artist;

import com.mta.musictube.extractor.ListExtractor;
import com.mta.musictube.extractor.StreamingService;
import com.mta.musictube.extractor.utils.Localization;
import com.mta.musictube.extractor.exceptions.ParsingException;
import com.mta.musictube.extractor.linkhandler.ListLinkHandler;

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
