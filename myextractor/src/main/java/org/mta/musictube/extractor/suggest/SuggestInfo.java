package org.mta.musictube.extractor.suggest;

import org.mta.musictube.extractor.ListExtractor;
import org.mta.musictube.extractor.ListInfo;
import org.mta.musictube.extractor.NewPipe;
import org.mta.musictube.extractor.StreamingService;
import org.mta.musictube.extractor.stream.StreamInfoItem;
import org.mta.musictube.extractor.utils.ExtractorHelper;
import org.mta.musictube.extractor.exceptions.ExtractionException;
import org.mta.musictube.extractor.exceptions.ParsingException;
import org.mta.musictube.extractor.linkhandler.ListLinkHandler;

import java.io.IOException;

public class SuggestInfo extends ListInfo<StreamInfoItem> {
    private static final String TAG = SuggestInfo.class.getSimpleName();
    public SuggestInfo(int serviceId, ListLinkHandler linkHandler, String name) throws ParsingException {
        super(serviceId, linkHandler, name);
    }

    public static SuggestInfo getInfo(String url) throws IOException, ExtractionException {
        return getInfo(NewPipe.getServiceByUrl(url), url);
    }

    public static SuggestInfo getInfo(StreamingService service, String url) throws IOException, ExtractionException {
        SuggestExtractor extractor = service.getSuggestExtractor(url);
        extractor.fetchPage();
        return getInfo(extractor);
    }

    public static ListExtractor.InfoItemsPage<StreamInfoItem> getMoreItems(StreamingService service, String url, String pageUrl) throws IOException, ExtractionException {
        return service.getSuggestExtractor(url).getPage(pageUrl);
    }

    public static SuggestInfo getInfo(SuggestExtractor extractor) throws ExtractionException {
        final SuggestInfo info = new SuggestInfo(extractor.getServiceId(), extractor.getLinkHandler(), extractor.getName());

        try {
            info.setOriginalUrl(extractor.getOriginalUrl());
        } catch (Exception e) {
            info.addError(e);
        }

        try {
            info.setNextPageToken(extractor.getNextPageToken());
        } catch (Exception e) {
            info.addError(e);
        }

        try {
            info.setKey(extractor.getKey());
        } catch (Exception e) {
            info.addError(e);
        }

        final ListExtractor.InfoItemsPage<StreamInfoItem> itemsPage = ExtractorHelper.getItemsPageOrLogError(info, extractor);
        info.setRelatedItems(itemsPage.getItems());
        info.setNextPageUrl(itemsPage.getNextPageUrl());
        return info;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String nextPageToken;
    private String key;

}
