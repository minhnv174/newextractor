package org.video.imusictube.extractor.services.soundcloud;

import static java.util.Collections.singletonList;
import static org.video.imusictube.extractor.StreamingService.ServiceInfo.MediaCapability.AUDIO;

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
import org.video.imusictube.extractor.stream.StreamExtractor;
import org.video.imusictube.extractor.subscription.SubscriptionExtractor;
import org.video.imusictube.extractor.suggest.SuggestExtractor;
import org.video.imusictube.extractor.utils.ExtractorConstant;
import org.video.imusictube.extractor.utils.Localization;

public class SoundcloudService extends StreamingService {

    public SoundcloudService(int id) {
        super(id, "SoundCloud", singletonList(AUDIO));
    }

    @Override
    public SearchExtractor getSearchExtractor(SearchQueryHandler queryHandler, Localization localization) {
        return new SoundcloudSearchExtractor(this, queryHandler, localization);
    }

    @Override
    public SearchQueryHandlerFactory getSearchQHFactory() {
        return new SoundcloudSearchQueryHandlerFactory();
    }

    @Override
    public LinkHandlerFactory getStreamLHFactory() {
        return SoundcloudStreamLinkHandlerFactory.getInstance();
    }

    @Override
    public ListLinkHandlerFactory getChannelLHFactory() {
        return SoundcloudChannelLinkHandlerFactory.getInstance();
    }

    @Override
    public ListLinkHandlerFactory getGenreLHFactory() {
        return SoundcloudGenreLinkHandlerFactory.getInstance();
    }

    @Override
    public ListLinkHandlerFactory getPlaylistLHFactory() {
        return SoundcloudPlaylistLinkHandlerFactory.getInstance();
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
        return SoundcloudListDataLinkHandlerFactory.getInstance();
    }


    @Override
    public StreamExtractor getStreamExtractor(LinkHandler LinkHandler, Localization localization) {
        return new SoundcloudStreamExtractor(this, LinkHandler, localization);
    }

    @Override
    public ChannelExtractor getChannelExtractor(ListLinkHandler linkHandler, Localization localization) {
        return new SoundcloudChannelExtractor(this, linkHandler, localization);
    }

    @Override
    public GenreExtractor getGenreExtractor(ListLinkHandler linkHandler, Localization localization) throws ExtractionException {
        return new SoundcloudGenreExtractor(this, linkHandler, localization);
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
        return new SoundcloudListDataExtractor(this, linkHandler, localization);
    }

    @Override
    public PlaylistExtractor getPlaylistExtractor(ListLinkHandler linkHandler, Localization localization) {
        return new SoundcloudPlaylistExtractor(this, linkHandler, localization);
    }

    @Override
    public SuggestionExtractor getSuggestionExtractor(Localization localization) {
        return new SoundcloudSuggestionExtractor(getServiceId(), localization);
    }

    @Override
    public KioskList getKioskList() throws ExtractionException {
        KioskList.KioskExtractorFactory chartsFactory = new KioskList.KioskExtractorFactory() {
            @Override
            public KioskExtractor createNewKiosk(StreamingService streamingService, String url, String id, Localization local) throws ExtractionException {
                return new SoundcloudChartsExtractor(SoundcloudService.this, new SoundcloudChartsLinkHandlerFactory().fromUrl(url), id, local);
            }
        };

        KioskList list = new KioskList(getServiceId());

        // add kiosks here e.g.:
        final SoundcloudChartsLinkHandlerFactory h = new SoundcloudChartsLinkHandlerFactory();
        try {
            list.addKioskEntry(chartsFactory, h, ExtractorConstant.KIOS_TOP50);
            list.addKioskEntry(chartsFactory, h, ExtractorConstant.KIOS_NEW_HOT);
            list.setDefaultKiosk(ExtractorConstant.KIOS_NEW_HOT);
        } catch (Exception e) {
            throw new ExtractionException(e);
        }

        return list;
    }


    @Override
    public SubscriptionExtractor getSubscriptionExtractor() {
        return new SoundcloudSubscriptionExtractor(this);
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
