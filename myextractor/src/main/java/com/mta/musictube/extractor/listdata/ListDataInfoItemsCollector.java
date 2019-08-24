package com.mta.musictube.extractor.listdata;

import com.mta.musictube.extractor.InfoItem;
import com.mta.musictube.extractor.InfoItemsCollector;
import com.mta.musictube.extractor.exceptions.ParsingException;
import java.util.List;
import java.util.Vector;

public class ListDataInfoItemsCollector  extends InfoItemsCollector<ListDataInfoItem, ListDataInfoItemExtractor> {

    public ListDataInfoItemsCollector(int serviceId) {
        super(serviceId);
    }

    @Override
    public ListDataInfoItem extract(ListDataInfoItemExtractor extractor) throws ParsingException {

        // important information
        int serviceId = getServiceId();
        String url = extractor.getUrl();
        String name = extractor.getName();
        ListDataInfoItem resultItem = new ListDataInfoItem(serviceId, url, name);

        // optional information
        try {
            resultItem.setListId(extractor.getListId());
        } catch (Exception e) {
            addError(e);
        }
        try {
            resultItem.setListName(extractor.getListName());
        } catch (Exception e) {
            addError(e);
        }
        try {
            resultItem.setThumbnailUrl(extractor.getThumbnailUrl());
        } catch (Exception e) {
            addError(e);
        }
        try {
            resultItem.setThumbnail(extractor.getThumbnail());
        } catch (Exception e) {
            addError(e);
        }
        return resultItem;
    }

    @Override
    public void commit(ListDataInfoItemExtractor extractor) {
        try {
            addItem(extract(extractor));
        } catch (Exception e) {
            addError(e);
        }
    }

    public List<ListDataInfoItem> getListDataInfoItemList() {
        List<ListDataInfoItem> siiList = new Vector<>();
        for(InfoItem ii : super.getItems()) {
            if(ii instanceof ListDataInfoItem) {
                siiList.add((ListDataInfoItem) ii);
            }
        }
        return siiList;
    }
}
