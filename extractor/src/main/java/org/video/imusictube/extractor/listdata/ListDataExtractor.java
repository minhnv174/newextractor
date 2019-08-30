package org.video.imusictube.extractor.listdata;

import org.video.imusictube.extractor.ListExtractor;
import org.video.imusictube.extractor.StreamingService;
import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.linkhandler.ListLinkHandler;
import org.video.imusictube.extractor.stream.StreamInfoItem;
import org.video.imusictube.extractor.utils.Localization;
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
