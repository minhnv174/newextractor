package org.video.imusictube.extractor.services.media_ccc;

import static java.util.Arrays.asList;
import static org.video.imusictube.extractor.StreamingService.ServiceInfo.MediaCapability.AUDIO;
import static org.video.imusictube.extractor.StreamingService.ServiceInfo.MediaCapability.VIDEO;

import java.io.IOException;

import org.video.imusictube.extractor.StreamingService;
import org.video.imusictube.extractor.SuggestionExtractor;
import org.video.imusictube.extractor.alphabet.AlphabetExtractor;
import org.video.imusictube.extractor.artist.ArtistExtractor;
import org.video.imusictube.extractor.channel.ChannelExtractor;
import org.video.imusictube.extractor.comment.CommentsExtractor;
import org.video.imusictube.extractor.exception.ExtractionException;
import org.video.imusictube.extractor.genre.GenreExtractor;
import org.video.imusictube.extractor.kiosk.KioskExtractor;
import org.video.imusictube.extractor.kiosk.KioskList;
import org.video.imusictube.extractor.linkhandler.LinkHandler;
import org.video.imusictube.extractor.linkhandler.LinkHandlerFactory;
import org.video.imusictube.extractor.linkhandler.ListLinkHandler;
import org.video.imusictube.extractor.linkhandler.ListLinkHandlerFactory;
import org.video.imusictube.extractor.linkhandler.SearchQueryHandler;
import org.video.imusictube.extractor.linkhandler.SearchQueryHandlerFactory;
import org.video.imusictube.extractor.playlist.PlaylistExtractor;
import org.video.imusictube.extractor.listdata.ListDataExtractor;
import org.video.imusictube.extractor.search.SearchExtractor;
import org.video.imusictube.extractor.services.media_ccc.extractors.MediaCCCConferenceExtractor;
import org.video.imusictube.extractor.services.media_ccc.extractors.MediaCCCConferenceKiosk;
import org.video.imusictube.extractor.services.media_ccc.extractors.MediaCCCSearchExtractor;
import org.video.imusictube.extractor.services.media_ccc.extractors.MediaCCCStreamExtractor;
import org.video.imusictube.extractor.services.media_ccc.linkHandler.MediaCCCConferenceLinkHandlerFactory;
import org.video.imusictube.extractor.services.media_ccc.linkHandler.MediaCCCConferencesListLinkHandlerFactory;
import org.video.imusictube.extractor.services.media_ccc.linkHandler.MediaCCCSearchQueryHandlerFactory;
import org.video.imusictube.extractor.services.media_ccc.linkHandler.MediaCCCStreamLinkHandlerFactory;
import org.video.imusictube.extractor.stream.StreamExtractor;
import org.video.imusictube.extractor.subscription.SubscriptionExtractor;
import org.video.imusictube.extractor.suggest.SuggestExtractor;
import org.video.imusictube.extractor.utils.Localization;

public class MediaCCCService extends StreamingService {
    public MediaCCCService(int id) {
        super(id, "MediaCCC", asList(AUDIO, VIDEO));
    }

    @Override
    public SearchExtractor getSearchExtractor(SearchQueryHandler query, Localization localization) {
        return new MediaCCCSearchExtractor(this, query, localization);
    }

    @Override
    public LinkHandlerFactory getStreamLHFactory() {
        return new MediaCCCStreamLinkHandlerFactory();
    }

    @Override
    public ListLinkHandlerFactory getChannelLHFactory() {
        return new MediaCCCConferenceLinkHandlerFactory();
    }

    @Override
    public ListLinkHandlerFactory getGenreLHFactory() {
        return null;
    }

    @Override
    public ListLinkHandlerFactory getPlaylistLHFactory() {
        return null;
    }

    @Override
    public ListLinkHandlerFactory getArtistLHFactory() {
        return null;
    }

    @Override
    public ListLinkHandlerFactory getAlphabetLHFactory() {
        return null;
    }

    @Override
    public ListLinkHandlerFactory getSuggestLHFactory() {
        return null;
    }

    @Override
    public ListLinkHandlerFactory getListDataLHFactory() {
        return null;
    }

    @Override
    public SearchQueryHandlerFactory getSearchQHFactory() {
        return new MediaCCCSearchQueryHandlerFactory();
    }

    @Override
    public StreamExtractor getStreamExtractor(LinkHandler linkHandler, Localization localization) {
        return new MediaCCCStreamExtractor(this, linkHandler, localization);
    }

    @Override
    public ChannelExtractor getChannelExtractor(ListLinkHandler linkHandler, Localization localization) {
        return new MediaCCCConferenceExtractor(this, linkHandler, localization);
    }

    @Override
    public GenreExtractor getGenreExtractor(ListLinkHandler linkHandler, Localization localization) throws ExtractionException {
        return null;
    }

    @Override
    public ArtistExtractor getArtistExtractor(ListLinkHandler linkHandler, Localization localization) throws ExtractionException {
        return null;
    }

    @Override
    public AlphabetExtractor getAlphabetExtractor(ListLinkHandler linkHandler, Localization localization) throws ExtractionException {
        return null;
    }

    @Override
    public SuggestExtractor getSuggestExtractor(ListLinkHandler linkHandler, Localization localization) throws ExtractionException {
        return null;
    }

    @Override
    public ListDataExtractor getListDataExtractor(ListLinkHandler linkHandler, Localization localization) throws ExtractionException {
        return null;
    }

    @Override
    public PlaylistExtractor getPlaylistExtractor(ListLinkHandler linkHandler, Localization localization) {
        return null;
    }

    @Override
    public SuggestionExtractor getSuggestionExtractor(Localization localization) {
        return null;
    }

    @Override
    public KioskList getKioskList() throws ExtractionException {
        KioskList list = new KioskList(getServiceId());

        // add kiosks here e.g.:
        try {
            list.addKioskEntry(new KioskList.KioskExtractorFactory() {
                @Override
                public KioskExtractor createNewKiosk(StreamingService streamingService, String url, String kioskId, Localization localization) throws ExtractionException, IOException {
                    return new MediaCCCConferenceKiosk(MediaCCCService.this, new MediaCCCConferencesListLinkHandlerFactory().fromUrl(url), kioskId, localization);
                }
            }, new MediaCCCConferencesListLinkHandlerFactory(), "conferences");
            list.setDefaultKiosk("conferences");
        } catch (Exception e) {
            throw new ExtractionException(e);
        }

        return list;
    }

    @Override
    public SubscriptionExtractor getSubscriptionExtractor() {
        return null;
    }

    @Override
    public ListLinkHandlerFactory getCommentsLHFactory() {
        return null;
    }

    @Override
    public CommentsExtractor getCommentsExtractor(ListLinkHandler linkHandler, Localization localization) throws ExtractionException {
        return null;
    }

}
