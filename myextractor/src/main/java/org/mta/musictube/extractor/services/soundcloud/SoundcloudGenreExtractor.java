package org.mta.musictube.extractor.services.soundcloud;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;

import org.mta.musictube.extractor.Downloader;
import org.mta.musictube.extractor.StreamingService;
import org.mta.musictube.extractor.genre.GenreExtractor;
import org.mta.musictube.extractor.genre.GenreInfoItem;
import org.mta.musictube.extractor.genre.GenreInfoItemsCollector;
import org.mta.musictube.extractor.utils.Localization;
import org.mta.musictube.extractor.utils.LogHelper;
import org.mta.musictube.extractor.exceptions.ExtractionException;
import org.mta.musictube.extractor.exceptions.ParsingException;
import org.mta.musictube.extractor.linkhandler.ListLinkHandler;

import java.io.IOException;

import javax.annotation.Nonnull;

public class SoundcloudGenreExtractor extends GenreExtractor {
    private static final String TAG = LogHelper.makeLogTag(SoundcloudGenreExtractor.class.getSimpleName());
    private JsonObject jsonObject = null;

    public SoundcloudGenreExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
    }

    @Nonnull
    @Override
    public InfoItemsPage<GenreInfoItem> getInitialPage() throws IOException, ExtractionException {
        GenreInfoItemsCollector collector = new GenreInfoItemsCollector(getServiceId());
        collector.reset();
        if(jsonObject != null) {
            JsonArray jsonArray = jsonObject.getArray("Data");
            int version = jsonObject.getInt("Version");
            if(jsonArray != null) {
                for (int i = 0, len = jsonArray.size(); i < len; i++) {
                    final JsonObject object = jsonArray.getObject(i);
                    collector.commit(new GenreItemExtractor(object, version));
                }
            }
        }
        return new InfoItemsPage<>(collector, getNextPageUrl());
    }

    @Override
    public String getNextPageUrl() throws IOException, ExtractionException {
        return null;
    }

    @Override
    public InfoItemsPage<GenreInfoItem> getPage(String pageUrl) throws IOException, ExtractionException {
        return null;
    }

    @Override
    public void onFetchPage(@Nonnull Downloader downloader) throws IOException, ExtractionException {
        try {
            jsonObject = JsonParser.object().from(downloader.downloadCustomize(getUrl()));
        } catch (JsonParserException jpe) {
            throw new ExtractionException("Could not parse json returnd by url: " + getUrl());
        }
    }

    @Nonnull
    @Override
    public String getName() throws ParsingException {
        return jsonObject.getString("Name");
    }

    @Override
    public String getGenreId() throws ParsingException {
        return jsonObject.getString("Id");
    }

    @Override
    public String getGenreName() throws ParsingException {
        return jsonObject.getString("Name");
    }

    @Override
    public String getVersion() throws ParsingException {
        return String.valueOf(jsonObject.getInt("Version"));
    }
}
