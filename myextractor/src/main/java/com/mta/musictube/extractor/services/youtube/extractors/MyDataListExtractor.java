package com.mta.musictube.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonObject;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;
import com.mta.musictube.extractor.Downloader;
import com.mta.musictube.extractor.StreamingService;
import com.mta.musictube.extractor.listdata.ListDataExtractor;
import com.mta.musictube.extractor.stream.StreamInfoItem;
import com.mta.musictube.extractor.stream.StreamInfoItemsCollector;
import com.mta.musictube.extractor.utils.CryptHelper;
import com.mta.musictube.extractor.utils.ExtractorConstant;
import com.mta.musictube.extractor.utils.Localization;

import org.jsoup.nodes.Element;

import com.mta.musictube.extractor.exceptions.ExtractionException;
import com.mta.musictube.extractor.exceptions.ParsingException;
import com.mta.musictube.extractor.linkhandler.ListLinkHandler;

import java.io.IOException;

import javax.annotation.Nonnull;

public class MyDataListExtractor extends ListDataExtractor {
    private static final String TAG = MyDataListExtractor.class.getSimpleName();
    private JsonObject jsonObject = null;

    public MyDataListExtractor(StreamingService service, ListLinkHandler linkHandler, Localization localization) {
        super(service, linkHandler, localization);
    }

    @Nonnull
    @Override
    public InfoItemsPage<StreamInfoItem> getInitialPage() throws IOException, ExtractionException {
        StreamInfoItemsCollector collector = new StreamInfoItemsCollector(getServiceId());
        collector.reset();
        if(jsonObject != null) {
            CryptHelper objScrypt = new CryptHelper();
            JsonArray jsonArray = jsonObject.getArray(ExtractorConstant.ConstantJson.DATA);
            if(jsonArray != null) {
                for (int i = 0, len = jsonArray.size(); i < len; i++) {
                    final JsonObject object = jsonArray.getObject(i);
                    collector.commit(new ListDataInfoItemExtractor(object) {
                        @Override
                        public String getUrl() throws ParsingException {
                            String id = new String(objScrypt.decrypt(object.getString("Id")));
                            return ExtractorConstant.EXTRACTOR_PRE_LINK + id;
                        }

                        @Override
                        public String getName() throws ParsingException {
                            return object.getString("Title");
                        }

                        @Override
                        public String getUploaderUrl() throws ParsingException {
                            String authorId = object.getString("Author");
                            return ExtractorConstant.EXTRACTOR_PRE_CHANNEL + authorId;
                        }

                        private Element getUploaderLink() {
                            return null;
                        }

                        @Override
                        public String getUploaderName() throws ParsingException {
                            return object.getString("Artist");
                        }

                        @Override
                        public String getThumbnailUrl() throws ParsingException {
                            String id = new String(objScrypt.decrypt(object.getString("Id")));
                            return String.format(ExtractorConstant.EXTRACTOR_PRE_THUMBNAIL, id);
                        }
                    });
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
    public InfoItemsPage<StreamInfoItem> getPage(String pageUrl) throws IOException, ExtractionException {
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
        return getListName();
    }

    @Override
    public String getVersion() throws ParsingException {
        return jsonObject.getString("Version");
    }

    @Override
    public String getListId() throws ParsingException {
        return jsonObject.getString("Id");
    }

    @Override
    public String getListName() throws ParsingException {
        return jsonObject.getString("Name");
    }

    @Override
    public String getThumbnail() throws ParsingException {
        return jsonObject.has("Thumbnail") ? jsonObject.getString("Thumbnail") : "";
    }
}
