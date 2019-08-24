package org.mta.musictube.extractor.services.soundcloud;

import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;

import org.mta.musictube.extractor.Downloader;
import org.mta.musictube.extractor.StreamingService;
import org.mta.musictube.extractor.listdata.ListDataExtractor;
import org.mta.musictube.extractor.stream.StreamInfoItem;
import org.mta.musictube.extractor.stream.StreamInfoItemsCollector;
import org.mta.musictube.extractor.utils.Localization;
import org.mta.musictube.extractor.utils.LogHelper;
import org.mta.musictube.extractor.utils.Utils;
import org.mta.musictube.extractor.exceptions.ExtractionException;
import org.mta.musictube.extractor.exceptions.ParsingException;
import org.mta.musictube.extractor.exceptions.ReCaptchaException;
import org.mta.musictube.extractor.linkhandler.ListLinkHandler;

import java.io.IOException;

import javax.annotation.Nonnull;

public class SoundcloudListDataExtractor extends ListDataExtractor {
    private static final String TAG = LogHelper.makeLogTag(SoundcloudListDataExtractor.class.getSimpleName());
    private StreamInfoItemsCollector collector = null;
    private String nextPageUrl = null;
    private String genre = "";

    public SoundcloudListDataExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
    }

    @Override
    public void onFetchPage(@Nonnull Downloader downloader) {
        try {
            String apiUrl = getOriginalUrl();
            String response = downloader.download(apiUrl);
            JsonObject responseObject = JsonParser.object().from(response);
            genre = responseObject.getString("genre");
        } catch (JsonParserException e) {
        } catch (ParsingException e) {
        } catch (IOException e) {
        } catch (ReCaptchaException e) {
        }
    }

    @Nonnull
    @Override
    public String getName() {
        return !Utils.isNullOrEmpty(genre) ? Utils.getTitleByKey(genre)  : "";
    }

    @Override
    public String getVersion() throws ParsingException {
        return null;
    }

    @Override
    public String getListId() throws ParsingException {
        return null;
    }

    @Override
    public String getListName() throws ParsingException {
        return null;
    }

    @Override
    public String getThumbnail() throws ParsingException {
        return null;
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

        String apiUrl = getOriginalUrl();
        LogHelper.i(TAG, "computNextPageAndStreams", apiUrl);

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
