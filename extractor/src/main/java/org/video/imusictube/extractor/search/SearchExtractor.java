package org.video.imusictube.extractor.search;

import org.video.imusictube.extractor.InfoItem;
import org.video.imusictube.extractor.ListExtractor;
import org.video.imusictube.extractor.StreamingService;
import org.video.imusictube.extractor.exception.ExtractionException;
import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.linkhandler.SearchQueryHandler;
import org.video.imusictube.extractor.utils.Localization;

public abstract class SearchExtractor extends ListExtractor<InfoItem> {

    public static class NothingFoundException extends ExtractionException {
        public NothingFoundException(String message) {
            super(message);
        }
    }

    private final InfoItemsSearchCollector collector;

    public SearchExtractor(StreamingService service, SearchQueryHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
        collector = new InfoItemsSearchCollector(service.getServiceId());
    }

    public String getSearchString() {
        return getLinkHandler().getSearchString();
    }

    public abstract String getSearchSuggestion() throws ParsingException;

    protected InfoItemsSearchCollector getInfoItemSearchCollector() {
        return collector;
    }

    @Override
    public SearchQueryHandler getLinkHandler() {
        return (SearchQueryHandler) super.getLinkHandler();
    }

    @Override
    public String getName() {
        return getLinkHandler().getSearchString();
    }
}
