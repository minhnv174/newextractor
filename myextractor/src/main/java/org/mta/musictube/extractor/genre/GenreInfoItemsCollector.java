package org.mta.musictube.extractor.genre;

import org.mta.musictube.extractor.InfoItem;
import org.mta.musictube.extractor.InfoItemsCollector;
import org.mta.musictube.extractor.exceptions.ParsingException;

import java.util.List;
import java.util.Vector;

public class GenreInfoItemsCollector extends InfoItemsCollector<GenreInfoItem, GenreInfoItemExtractor> {
    public GenreInfoItemsCollector(int serviceId) {
        super(serviceId);
    }

    @Override
    public GenreInfoItem extract(GenreInfoItemExtractor extractor) throws ParsingException {

        // important information
        int serviceId = getServiceId();
        String url = extractor.getUrl();
        String name = extractor.getName();
        GenreInfoItem resultItem = new GenreInfoItem(serviceId, url, name);

        // optional information
        try {
            resultItem.setGenreId(extractor.getGenreId());
        } catch (Exception e) {
            addError(e);
        }
        try {
            resultItem.setGenreName(extractor.getGenreName());
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
    public void commit(GenreInfoItemExtractor extractor) {
        try {
            addItem(extract(extractor));
        } catch (Exception e) {
            addError(e);
        }
    }

    public List<GenreInfoItem> getGenreInfoItemList() {
        List<GenreInfoItem> siiList = new Vector<>();
        for(InfoItem ii : super.getItems()) {
            if(ii instanceof GenreInfoItem) {
                siiList.add((GenreInfoItem) ii);
            }
        }
        return siiList;
    }
}
