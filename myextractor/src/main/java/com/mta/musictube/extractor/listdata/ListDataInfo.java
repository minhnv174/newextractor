package com.mta.musictube.extractor.listdata;

import com.mta.musictube.extractor.ListExtractor;
import com.mta.musictube.extractor.ListInfo;
import com.mta.musictube.extractor.NewPipe;
import com.mta.musictube.extractor.StreamingService;
import com.mta.musictube.extractor.stream.StreamInfoItem;
import com.mta.musictube.extractor.utils.ExtractorHelper;
import com.mta.musictube.extractor.exceptions.ExtractionException;
import com.mta.musictube.extractor.exceptions.ParsingException;
import com.mta.musictube.extractor.linkhandler.ListLinkHandler;

import java.io.IOException;

public class ListDataInfo  extends ListInfo<StreamInfoItem> {
    private static final String TAG = ListDataInfo.class.getSimpleName();

    public ListDataInfo(int serviceId, ListLinkHandler linkHandler, String name) throws ParsingException {
        super(serviceId, linkHandler, name);
    }

    public static ListDataInfo getInfo(String url) throws IOException, ExtractionException {
        return getInfo(NewPipe.getServiceByUrl(url), url);
    }

    public static ListDataInfo getInfo(StreamingService service, String url) throws IOException, ExtractionException {
        ListDataExtractor extractor = service.getListDataExtractor(url);
        extractor.fetchPage();
        return getInfo(extractor);
    }

    public static ListExtractor.InfoItemsPage<StreamInfoItem> getMoreItems(StreamingService service, String url, String pageUrl) throws IOException, ExtractionException {
        return service.getListDataExtractor(url).getPage(pageUrl);
    }

    public static ListDataInfo getInfo(ListDataExtractor extractor) throws ExtractionException {
        final ListDataInfo info = new ListDataInfo(extractor.getServiceId(), extractor.getLinkHandler(), extractor.getName());

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
            info.setListId(extractor.getListId());
        } catch (Exception e) {
            info.addError(e);
        }
        try {
            info.setListName(extractor.getListName());
        } catch (Exception e) {
            info.addError(e);
        }
        try {
            info.setThumbnail(extractor.getThumbnail());
        } catch (Exception e) {
            info.addError(e);
        }
        final ListExtractor.InfoItemsPage<StreamInfoItem> itemsPage = ExtractorHelper.getItemsPageOrLogError(info, extractor);
        info.setRelatedItems(itemsPage.getItems());
        info.setNextPageUrl(itemsPage.getNextPageUrl());
        return info;
    }

    private String Version;
    private String ListId;
    private String ListName;
    private String Thumbnail;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getListId() {
        return ListId;
    }

    public void setListId(String listId) {
        ListId = listId;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }
}
