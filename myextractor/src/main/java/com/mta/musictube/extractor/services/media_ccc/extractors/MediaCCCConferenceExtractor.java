package com.mta.musictube.extractor.services.media_ccc.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import com.mta.musictube.extractor.Downloader;
import com.mta.musictube.extractor.ListExtractor;
import com.mta.musictube.extractor.StreamingService;
import com.mta.musictube.extractor.channel.ChannelExtractor;
import com.mta.musictube.extractor.stream.StreamInfoItem;
import com.mta.musictube.extractor.stream.StreamInfoItemsCollector;
import com.mta.musictube.extractor.utils.Localization;

import com.mta.musictube.extractor.exceptions.ExtractionException;
import com.mta.musictube.extractor.exceptions.ParsingException;
import com.mta.musictube.extractor.linkhandler.ListLinkHandler;
import com.mta.musictube.extractor.services.media_ccc.extractors.infoItems.MediaCCCStreamInfoItemExtractor;

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
    public ListExtractor.InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        StreamInfoItemsCollector collector = new StreamInfoItemsCollector(getServiceId());
        JsonArray events = conferenceData.getArray("events");
        for(int i = 0; i < events.size(); i++) {
            collector.commit(new MediaCCCStreamInfoItemExtractor(events.getObject(i)));
        }
        return new ListExtractor.InfoItemsPage<>(collector, null);
    }

    @Override
    public String getNextPageUrl() throws IOException, ExtractionException {
        return null;
    }

    @Override
    public ListExtractor.InfoItemsPage<StreamInfoItem> getPage(String pageUrl) throws IOException, ExtractionException {
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
