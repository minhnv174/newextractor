package com.mta.musictube.extractor.search;

import com.mta.musictube.extractor.InfoItem;
import com.mta.musictube.extractor.ListExtractor;
import com.mta.musictube.extractor.StreamingService;
import com.mta.musictube.extractor.utils.Localization;
import com.mta.musictube.extractor.exceptions.ExtractionException;
import com.mta.musictube.extractor.exceptions.ParsingException;
import com.mta.musictube.extractor.linkhandler.SearchQueryHandler;

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
