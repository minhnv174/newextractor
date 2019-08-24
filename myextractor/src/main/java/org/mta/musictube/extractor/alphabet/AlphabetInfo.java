package org.mta.musictube.extractor.alphabet;

import org.mta.musictube.extractor.ListExtractor;
import org.mta.musictube.extractor.ListInfo;
import org.mta.musictube.extractor.NewPipe;
import org.mta.musictube.extractor.StreamingService;
import org.mta.musictube.extractor.utils.ExtractorHelper;
import org.mta.musictube.extractor.exceptions.ExtractionException;
import org.mta.musictube.extractor.linkhandler.ListLinkHandler;

import java.io.IOException;

public class AlphabetInfo extends ListInfo<AlphabetInfoItem> {
    private static final String TAG = AlphabetInfo.class.getSimpleName();

    public AlphabetInfo(int serviceId, ListLinkHandler listUrlIdHandler, String name) {
        super(serviceId, listUrlIdHandler, name);
    }

    public static AlphabetInfo getInfo(String url) throws IOException, ExtractionException {
        return getInfo(NewPipe.getServiceByUrl(url), url);
    }

    public static AlphabetInfo getInfo(StreamingService service, String url) throws IOException, ExtractionException {
        AlphabetExtractor extractor = service.getAlphabetExtractor(url);
        extractor.fetchPage();
        return getInfo(extractor);
    }

    public static AlphabetInfo getInfo(AlphabetExtractor extractor) throws ExtractionException {
        final AlphabetInfo info = new AlphabetInfo(extractor.getServiceId(), extractor.getLinkHandler(), extractor.getName());

        try {
            info.setOriginalUrl(extractor.getOriginalUrl());
        } catch (Exception e) {
            info.addError(e);
        }
       try {
            info.setVersion(extractor.getVersion());
        } catch (Exception e) {
            info.addError(e);
        }

        final ListExtractor.InfoItemsPage<AlphabetInfoItem> itemsPage = ExtractorHelper.getItemsPageOrLogError(info, extractor);
        info.setRelatedItems(itemsPage.getItems());
        info.setNextPageUrl(itemsPage.getNextPageUrl());
        return info;
    }

    private String Version;
    public String getVersion() {
        return Version;
    }
    public void setVersion(String version) {
        this.Version = version;
    }

}
