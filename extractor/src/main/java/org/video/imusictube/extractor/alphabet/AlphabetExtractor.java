package org.video.imusictube.extractor.alphabet;

import org.video.imusictube.extractor.ListExtractor;
import org.video.imusictube.extractor.StreamingService;
import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.linkhandler.ListLinkHandler;
import org.video.imusictube.extractor.utils.Localization;

import javax.annotation.Nonnull;

public abstract class AlphabetExtractor extends ListExtractor<AlphabetInfoItem> {
    @Nonnull
    @Override
    public abstract String getName() throws ParsingException;

    public abstract String getVersion() throws ParsingException;

    public AlphabetExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
    }
}
