package org.video.imusictube.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;

import org.video.imusictube.extractor.Downloader;
import org.video.imusictube.extractor.StreamingService;
import org.video.imusictube.extractor.exception.ExtractionException;
import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.genre.GenreExtractor;
import org.video.imusictube.extractor.genre.GenreInfoItem;
import org.video.imusictube.extractor.genre.GenreInfoItemsCollector;
import org.video.imusictube.extractor.linkhandler.ListLinkHandler;
import org.video.imusictube.extractor.utils.Localization;

import java.io.IOException;

import javax.annotation.Nonnull;

public class MyGenreExtractor extends GenreExtractor {
    private static final String TAG = MyGenreExtractor.class.getSimpleName();
    private JsonObject jsonObject = null;

    public MyGenreExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
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
        return getGenreName();
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
