package com.mta.musictube.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import com.mta.musictube.extractor.Downloader;
import com.mta.musictube.extractor.StreamingService;
import com.mta.musictube.extractor.artist.ArtistExtractor;
import com.mta.musictube.extractor.artist.ArtistInfoItem;
import com.mta.musictube.extractor.artist.ArtistInfoItemsCollector;
import com.mta.musictube.extractor.utils.Localization;

import com.mta.musictube.extractor.linkhandler.ListLinkHandler;
import com.mta.musictube.extractor.exceptions.ExtractionException;
import com.mta.musictube.extractor.exceptions.ParsingException;

import javax.annotation.Nonnull;

import java.io.IOException;

public class MyArtistExtractor extends ArtistExtractor {
    private static final String TAG = MyArtistExtractor.class.getSimpleName();
    private JsonObject jsonObject = null;

    public MyArtistExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
    }

    @Nonnull
    @Override
    public String getName() throws ParsingException {
        return  getArtistName();
    }

    @Override
    public String getArtistId() throws ParsingException {
        return jsonObject.getString("Id");
    }

    @Override
    public String getArtistName() throws ParsingException {
        return jsonObject.getString("Name");
    }

    @Override
    public String getVersion() throws ParsingException {
        return String.valueOf(jsonObject.getInt("Version"));
    }

    @Override
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        try {
            jsonObject = JsonParser.object().from(downloader.downloadCustomize(getUrl()));
        } catch (JsonParserException jpe) {
            throw new ExtractionException("Could not parse json returnd by url: " + getUrl());
        }
    }

    @Override
    public String getNextPageUrl() {
        return "";
    }

    @Override
    public InfoItemsPage<ArtistInfoItem> getPage(String pageUrl) {
        return null;
    }


    @Nonnull
    @Override
    public InfoItemsPage<ArtistInfoItem> getInitialPage() throws ParsingException {
        ArtistInfoItemsCollector collector = new ArtistInfoItemsCollector(getServiceId());
        collector.reset();
        if(jsonObject != null) {
            JsonArray jsonArray = jsonObject.getArray("Data");
            int version = jsonObject.getInt("Version");
            if(jsonArray != null) {
                for (int i = 0, len = jsonArray.size(); i < len; i++) {
                    final JsonObject object = jsonArray.getObject(i);
                    collector.commit(new ArtistItemExtractor(object, version));
                }
            }
        }
        return new InfoItemsPage<>(collector, getNextPageUrl());
    }
}
