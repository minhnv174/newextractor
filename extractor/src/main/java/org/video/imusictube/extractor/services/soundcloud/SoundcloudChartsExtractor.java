package org.video.imusictube.extractor.services.soundcloud;

import org.video.imusictube.extractor.Downloader;
import org.video.imusictube.extractor.linkhandler.ListLinkHandler;
import org.video.imusictube.extractor.StreamingService;
import org.video.imusictube.extractor.exception.ExtractionException;
import org.video.imusictube.extractor.kiosk.KioskExtractor;
import org.video.imusictube.extractor.stream.StreamInfoItem;
import org.video.imusictube.extractor.stream.StreamInfoItemsCollector;
import org.video.imusictube.extractor.utils.ExtractorConstant;
import org.video.imusictube.extractor.utils.Localization;

import javax.annotation.Nonnull;
import java.io.IOException;

public class SoundcloudChartsExtractor extends KioskExtractor<StreamInfoItem> {
	private StreamInfoItemsCollector collector = null;
	private String nextPageUrl = null;

    public SoundcloudChartsExtractor(StreamingService service, ListLinkHandler linkHandler, String kioskId, Localization localization) {
        super(service, linkHandler, kioskId, localization);
    }

    @Override
    public void onFetchPage(@Nonnull Downloader downloader) {
    }

    @Nonnull
    @Override
    public String getName() {
        return getId();
    }

    @Override
    public InfoItemsPage<StreamInfoItem> getPage(String pageUrl) throws IOException, ExtractionException {
        if (pageUrl == null || pageUrl.isEmpty()) {
            throw new ExtractionException(new IllegalArgumentException("Page url is empty or null"));
        }

        StreamInfoItemsCollector collector = new StreamInfoItemsCollector(getServiceId());
        String nextPageUrl = SoundcloudParsingHelper.getStreamsFromApi(collector, pageUrl, true);

        return new InfoItemsPage<>(collector, nextPageUrl);
    }


    private void computNextPageAndStreams() throws IOException, ExtractionException {
        collector = new StreamInfoItemsCollector(getServiceId());

        String apiUrl = "https://api-v2.soundcloud.com/charts" +
                "?genre=soundcloud:genres:all-music" +
                "&client_id=" + SoundcloudParsingHelper.clientId();

        if (getId().equals(ExtractorConstant.KIOS_TOP50)) {
            apiUrl += "&kind=top";
        } else {
            apiUrl += "&kind=trending";
        }

        /*List<String> supportedCountries = Arrays.asList("AU", "CA", "FR", "DE", "IE", "NL", "NZ", "GB", "US");
        String contentCountry = getContentCountry();
        if (supportedCountries.contains(contentCountry)) {
            apiUrl += "&region=soundcloud:regions:" + contentCountry;
        }*/

        nextPageUrl = SoundcloudParsingHelper.getStreamsFromApi(collector, apiUrl, true);
    }

    @Override
    public String getNextPageUrl() throws IOException, ExtractionException {
        if(nextPageUrl == null) {
            computNextPageAndStreams();
        }
        return nextPageUrl;
    }

    @Nonnull
    @Override
    public InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        if(collector == null) {
            computNextPageAndStreams();
        }
        return new InfoItemsPage<>(collector, getNextPageUrl());
    }
}
