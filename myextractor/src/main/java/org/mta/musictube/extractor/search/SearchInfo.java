package org.mta.musictube.extractor.search;

import org.mta.musictube.extractor.InfoItem;
import org.mta.musictube.extractor.ListExtractor;
import org.mta.musictube.extractor.ListInfo;
import org.mta.musictube.extractor.StreamingService;
import org.mta.musictube.extractor.utils.ExtractorHelper;
import org.mta.musictube.extractor.exceptions.ExtractionException;
import org.mta.musictube.extractor.linkhandler.SearchQueryHandler;

import java.io.IOException;


public class SearchInfo extends ListInfo<InfoItem> {

    private String searchString;
    private String searchSuggestion;

    public SearchInfo(int serviceId, SearchQueryHandler qIHandler, String searchString) {
        super(serviceId, qIHandler, "Search");
        this.searchString = searchString;
    }

    public static SearchInfo getInfo(StreamingService service, SearchQueryHandler searchQuery) throws ExtractionException, IOException {
        SearchExtractor extractor = service.getSearchExtractor(searchQuery);
        extractor.fetchPage();
        return getInfo(extractor);
    }

    public static SearchInfo getInfo(SearchExtractor extractor) throws ExtractionException, IOException {
        final SearchInfo info = new SearchInfo(extractor.getServiceId(), extractor.getLinkHandler(), extractor.getSearchString());

        try {
            info.setOriginalUrl(extractor.getOriginalUrl());
        } catch (Exception e) {
            info.addError(e);
        }
        try {
            info.searchSuggestion = extractor.getSearchSuggestion();
        } catch (Exception e) {
            info.addError(e);
        }

        ListExtractor.InfoItemsPage<InfoItem> page = ExtractorHelper.getItemsPageOrLogError(info, extractor);
        info.setRelatedItems(page.getItems());
        info.setNextPageUrl(page.getNextPageUrl());

        return info;
    }


    public static ListExtractor.InfoItemsPage<InfoItem> getMoreItems(StreamingService service, SearchQueryHandler query, String pageUrl) throws IOException, ExtractionException {
        return service.getSearchExtractor(query).getPage(pageUrl);
    }

    // Getter
    public String getSearchString() {
        return searchString;
    }

    public String getSearchSuggestion() {
        return searchSuggestion;
    }
}
