package org.mta.musictube.extractor.alphabet;

import org.mta.musictube.extractor.InfoItem;
import org.mta.musictube.extractor.InfoItemsCollector;
import org.mta.musictube.extractor.exceptions.ParsingException;

import java.util.List;
import java.util.Vector;

public class AlphabetInfoItemsCollector extends InfoItemsCollector<AlphabetInfoItem, AlphabetInfoItemExtractor> {
    public AlphabetInfoItemsCollector(int serviceId) {
        super(serviceId);
    }

    @Override
    public AlphabetInfoItem extract(AlphabetInfoItemExtractor extractor) throws ParsingException {

        // important information
        int serviceId = getServiceId();
        String url = extractor.getUrl();
        String name = extractor.getName();
        AlphabetInfoItem resultItem = new AlphabetInfoItem(serviceId, url, name);

        // optional information
        try {
            resultItem.setAlphabetId(extractor.getAlphabetId());
        } catch (Exception e) {
            addError(e);
        }

        try {
            resultItem.setAlphabetName(extractor.getAlphabetName());
        } catch (Exception e) {
            addError(e);
        }

        try {
            resultItem.setTotal(extractor.getTotal());
        } catch (Exception e) {
            addError(e);
        }
        try {
            resultItem.setThumbnailUrl(extractor.getThumbnailUrl());
        } catch (Exception e) {
            addError(e);
        }
        return resultItem;
    }

    @Override
    public void commit(AlphabetInfoItemExtractor extractor) {
        try {
            addItem(extract(extractor));
        } catch (Exception e) {
            addError(e);
        }
    }

    public List<AlphabetInfoItem> getAlphabetInfoItemList() {
        List<AlphabetInfoItem> siiList = new Vector<>();
        for(InfoItem ii : super.getItems()) {
            if(ii instanceof AlphabetInfoItem) {
                siiList.add((AlphabetInfoItem) ii);
            }
        }
        return siiList;
    }
}
