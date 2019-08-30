package org.video.imusictube.extractor.genre;

import org.video.imusictube.extractor.ListExtractor;
import org.video.imusictube.extractor.ListInfo;
import org.video.imusictube.extractor.MusicTube;
import org.video.imusictube.extractor.StreamingService;
import org.video.imusictube.extractor.exception.ExtractionException;
import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.linkhandler.ListLinkHandler;
import org.video.imusictube.extractor.utils.ExtractorHelper;
import java.io.IOException;

public class GenreInfo extends ListInfo<GenreInfoItem> {
    public GenreInfo(int serviceId, ListLinkHandler linkHandler, String name) throws ParsingException {
        super(serviceId, linkHandler, name);
    }

    public static GenreInfo getInfo(String url) throws IOException, ExtractionException {
        return getInfo(MusicTube.getServiceByUrl(url), url);
    }

    public static GenreInfo getInfo(StreamingService service, String url) throws IOException, ExtractionException {
        GenreExtractor extractor = service.getGenreExtractor(url);
        extractor.fetchPage();
        return getInfo(extractor);
    }


    public static GenreInfo getInfo(GenreExtractor extractor) throws IOException, ExtractionException {

        GenreInfo info = new GenreInfo(extractor.getServiceId(), extractor.getLinkHandler(), extractor.getName());

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
            info.setGenreId(extractor.getGenreId());
        } catch (Exception e) {
            info.addError(e);
        }
        try {
            info.setGenreName(extractor.getGenreName());
        } catch (Exception e) {
            info.addError(e);
        }

        final ListExtractor.InfoItemsPage<GenreInfoItem> itemsPage = ExtractorHelper.getItemsPageOrLogError(info, extractor);
        info.setRelatedItems(itemsPage.getItems());
        info.setNextPageUrl(itemsPage.getNextPageUrl());
        return info;
    }

    private String Version;
    private String GenreId;
    private String GenreName;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getGenreId() {
        return GenreId;
    }

    public void setGenreId(String genreId) {
        GenreId = genreId;
    }

    public String getGenreName() {
        return GenreName;
    }

    public void setGenreName(String genreName) {
        GenreName = genreName;
    }
}
