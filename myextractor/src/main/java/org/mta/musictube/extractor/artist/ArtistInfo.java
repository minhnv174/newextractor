package org.mta.musictube.extractor.artist;

import org.mta.musictube.extractor.ListExtractor;
import org.mta.musictube.extractor.ListInfo;
import org.mta.musictube.extractor.NewPipe;
import org.mta.musictube.extractor.StreamingService;
import org.mta.musictube.extractor.utils.ExtractorHelper;
import org.mta.musictube.extractor.exceptions.ExtractionException;
import org.mta.musictube.extractor.exceptions.ParsingException;
import org.mta.musictube.extractor.linkhandler.ListLinkHandler;

import java.io.IOException;

public class ArtistInfo extends ListInfo<ArtistInfoItem> {
    private static final String TAG = ArtistInfo.class.getSimpleName();
    public ArtistInfo(int serviceId, ListLinkHandler linkHandler, String name) throws ParsingException {
        super(serviceId, linkHandler, name);
    }

    public static ArtistInfo getInfo(String url) throws IOException, ExtractionException {
        return getInfo(NewPipe.getServiceByUrl(url), url);
    }

    public static ArtistInfo getInfo(StreamingService service, String url) throws IOException, ExtractionException {
        ArtistExtractor extractor = service.getArtistExtractor(url);
        extractor.fetchPage();
        return getInfo(extractor);
    }

    public static ArtistInfo getInfo(ArtistExtractor extractor) throws ExtractionException {
        final ArtistInfo info = new ArtistInfo(extractor.getServiceId(), extractor.getLinkHandler(), extractor.getName());

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
        try {
            info.setArtistId(extractor.getArtistId());
        } catch (Exception e) {
            info.addError(e);
        }
        try {
            info.setArtistName(extractor.getArtistName());
        } catch (Exception e) {
            info.addError(e);
        }

        final ListExtractor.InfoItemsPage<ArtistInfoItem> itemsPage = ExtractorHelper.getItemsPageOrLogError(info, extractor);
        info.setRelatedItems(itemsPage.getItems());
        info.setNextPageUrl(itemsPage.getNextPageUrl());
        return info;
    }

    private String Version;
    private String ArtistId;
    private String ArtistName;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getArtistId() {
        return ArtistId;
    }

    public void setArtistId(String artistId) {
        ArtistId = artistId;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        ArtistName = artistName;
    }

}
