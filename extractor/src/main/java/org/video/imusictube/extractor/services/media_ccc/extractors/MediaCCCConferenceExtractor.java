package org.video.imusictube.extractor.services.media_ccc.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import org.video.imusictube.extractor.Downloader;
import org.video.imusictube.extractor.StreamingService;
import org.video.imusictube.extractor.channel.ChannelExtractor;
import org.video.imusictube.extractor.exception.ExtractionException;
import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.linkhandler.ListLinkHandler;
import org.video.imusictube.extractor.services.media_ccc.extractors.infoItems.MediaCCCStreamInfoItemExtractor;
import org.video.imusictube.extractor.stream.StreamInfoItem;
import org.video.imusictube.extractor.stream.StreamInfoItemsCollector;
import org.video.imusictube.extractor.utils.Localization;

import javax.annotation.Nonnull;
import java.io.IOException;

public class MediaCCCConferenceExtractor extends ChannelExtractor {

    private JsonObject conferenceData;

    public MediaCCCConferenceExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
    }

    @Override
    public String getAvatarUrl() throws ParsingException {
        return conferenceData.getString("logo_url");
    }

    @Override
    public String getBannerUrl() throws ParsingException {
        return conferenceData.getString("logo_url");
    }

    @Override
    public String getFeedUrl() throws ParsingException {
        return null;
    }

    @Override
    public long getSubscriberCount() throws ParsingException {
        return -1;
    }

    @Override
    public String getDescription() throws ParsingException {
        return null;
    }

    @Nonnull
    @Override
    public InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        StreamInfoItemsCollector collector = new StreamInfoItemsCollector(getServiceId());
        JsonArray events = conferenceData.getArray("events");
        for(int i = 0; i < events.size(); i++) {
            collector.commit(new MediaCCCStreamInfoItemExtractor(events.getObject(i)));
        }
        return new InfoItemsPage<>(collector, null);
    }

    @Override
    public String getNextPageUrl() throws IOException, ExtractionException {
        return null;
    }

    @Override
    public InfoItemsPage<StreamInfoItem> getPage(String pageUrl) throws IOException, ExtractionException {
        return null;
    }

    @Override
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        try {
            conferenceData = JsonParser.object().from(downloader.download(getUrl()));
        } catch (JsonParserException jpe) {
            throw new ExtractionException("Could not parse json returnd by url: " + getUrl());
        }
    }

    @Nonnull
    @Override
    public String getName() throws ParsingException {
        return conferenceData.getString("title");
    }

    @Override
    public String getOriginalUrl() throws ParsingException {
        return "https://media.ccc.de/c/" + conferenceData.getString("acronym");
    }
}
