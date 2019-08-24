package org.mta.musictube.extractor.search;

import org.mta.musictube.extractor.InfoItem;
import org.mta.musictube.extractor.ListExtractor;
import org.mta.musictube.extractor.StreamingService;
import org.mta.musictube.extractor.utils.Localization;
import org.mta.musictube.extractor.exceptions.ExtractionException;
import org.mta.musictube.extractor.exceptions.ParsingException;
import org.mta.musictube.extractor.linkhandler.SearchQueryHandler;

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
