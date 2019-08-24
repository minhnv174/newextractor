package org.mta.musictube.extractor.artist;

import org.mta.musictube.extractor.InfoItem;
import org.mta.musictube.extractor.InfoItemsCollector;
import org.mta.musictube.extractor.exceptions.ParsingException;
import java.util.List;
import java.util.Vector;

public class ArtistInfoItemsCollector extends InfoItemsCollector<ArtistInfoItem, ArtistInfoItemExtractor> {
    public ArtistInfoItemsCollector(int serviceId) {
        super(serviceId);
    }

    @Override
    public ArtistInfoItem extract(ArtistInfoItemExtractor extractor) throws ParsingException {

        // important information
        int serviceId = getServiceId();
        String url = extractor.getUrl();
        String name = extractor.getName();
        ArtistInfoItem resultItem = new ArtistInfoItem(serviceId, url, name);

        // optional information
        try {
            resultItem.setArtistId(extractor.getArtistId());
        } catch (Exception e) {
            addError(e);
        }

        try {
            resultItem.setArtistName(extractor.getArtistName());
        } catch (Exception e) {
            addError(e);
        }

        try {
            resultItem.setTotal(extractor.getTotal());
        } catch (Exception e) {
            addError(e);
        }

        try {
            resultItem.setGenre(extractor.getGenre());
        } catch (Exception e) {
            addError(e);
        }

        try {
            resultItem.setPrefix(extractor.getPrefix());
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
    public void commit(ArtistInfoItemExtractor extractor) {
        try {
            addItem(extract(extractor));
        } catch (Exception e) {
            addError(e);
        }
    }

    public List<ArtistInfoItem> getArtistInfoItemList() {
        List<ArtistInfoItem> siiList = new Vector<>();
        for(InfoItem ii : super.getItems()) {
            if(ii instanceof ArtistInfoItem) {
                siiList.add((ArtistInfoItem) ii);
            }
        }
        return siiList;
    }
}
