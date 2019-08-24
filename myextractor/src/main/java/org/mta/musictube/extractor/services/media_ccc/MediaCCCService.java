package org.mta.musictube.extractor.services.media_ccc;

import static java.util.Arrays.asList;

import java.io.IOException;

import org.mta.musictube.extractor.StreamingService;
import org.mta.musictube.extractor.SuggestionExtractor;
import org.mta.musictube.extractor.alphabet.AlphabetExtractor;
import org.mta.musictube.extractor.artist.ArtistExtractor;
import org.mta.musictube.extractor.channel.ChannelExtractor;
import org.mta.musictube.extractor.comments.CommentsExtractor;
import org.mta.musictube.extractor.genre.GenreExtractor;
import org.mta.musictube.extractor.kiosk.KioskExtractor;
import org.mta.musictube.extractor.kiosk.KioskList;
import org.mta.musictube.extractor.listdata.ListDataExtractor;
import org.mta.musictube.extractor.playlist.PlaylistExtractor;
import org.mta.musictube.extractor.search.SearchExtractor;
import org.mta.musictube.extractor.stream.StreamExtractor;
import org.mta.musictube.extractor.suggest.SuggestExtractor;
import org.mta.musictube.extractor.utils.Localization;
import org.mta.musictube.extractor.exceptions.ExtractionException;
import org.mta.musictube.extractor.linkhandler.LinkHandler;
import org.mta.musictube.extractor.linkhandler.LinkHandlerFactory;
import org.mta.musictube.extractor.linkhandler.ListLinkHandler;
import org.mta.musictube.extractor.linkhandler.ListLinkHandlerFactory;
import org.mta.musictube.extractor.linkhandler.SearchQueryHandler;
import org.mta.musictube.extractor.linkhandler.SearchQueryHandlerFactory;
import org.mta.musictube.extractor.services.media_ccc.extractors.MediaCCCConferenceExtractor;
import org.mta.musictube.extractor.services.media_ccc.extractors.MediaCCCConferenceKiosk;
import org.mta.musictube.extractor.services.media_ccc.extractors.MediaCCCSearchExtractor;
import org.mta.musictube.extractor.services.media_ccc.extractors.MediaCCCStreamExtractor;
import org.mta.musictube.extractor.services.media_ccc.linkHandler.MediaCCCConferenceLinkHandlerFactory;
import org.mta.musictube.extractor.services.media_ccc.linkHandler.MediaCCCConferencesListLinkHandlerFactory;
import org.mta.musictube.extractor.services.media_ccc.linkHandler.MediaCCCSearchQueryHandlerFactory;
import org.mta.musictube.extractor.services.media_ccc.linkHandler.MediaCCCStreamLinkHandlerFactory;
import org.mta.musictube.extractor.subscription.SubscriptionExtractor;

public class MediaCCCService extends StreamingService {
    public MediaCCCService(int id) {
        super(id, "MediaCCC", asList(ServiceInfo.MediaCapability.AUDIO, ServiceInfo.MediaCapability.VIDEO));
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
