package org.video.imusictube.extractor.services.youtube;

import static java.util.Arrays.asList;
import static org.video.imusictube.extractor.StreamingService.ServiceInfo.MediaCapability.AUDIO;
import static org.video.imusictube.extractor.StreamingService.ServiceInfo.MediaCapability.COMMENTS;
import static org.video.imusictube.extractor.StreamingService.ServiceInfo.MediaCapability.LIVE;
import static org.video.imusictube.extractor.StreamingService.ServiceInfo.MediaCapability.VIDEO;

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
import org.video.imusictube.extractor.services.youtube.extractors.MyAlphabetExtractor;
import org.video.imusictube.extractor.services.youtube.extractors.MyArtistExtractor;
import org.video.imusictube.extractor.services.youtube.extractors.MyDataListExtractor;
import org.video.imusictube.extractor.services.youtube.extractors.MyGenreExtractor;
import org.video.imusictube.extractor.services.youtube.extractors.YoutubeChannelExtractor;
import org.video.imusictube.extractor.services.youtube.extractors.YoutubeCommentsExtractor;
import org.video.imusictube.extractor.services.youtube.extractors.YoutubePlaylistExtractor;
import org.video.imusictube.extractor.services.youtube.extractors.MySuggestExtractor;
import org.video.imusictube.extractor.services.youtube.extractors.YoutubeSearchExtractor;
import org.video.imusictube.extractor.services.youtube.extractors.YoutubeStreamExtractor;
import org.video.imusictube.extractor.services.youtube.extractors.YoutubeSubscriptionExtractor;
import org.video.imusictube.extractor.services.youtube.extractors.YoutubeSuggestionExtractor;
import org.video.imusictube.extractor.services.youtube.extractors.YoutubeTrendingExtractor;
import org.video.imusictube.extractor.services.youtube.linkHandler.AlphabetLinkHandlerFactory;
import org.video.imusictube.extractor.services.youtube.linkHandler.ArtistLinkHandlerFactory;
import org.video.imusictube.extractor.services.youtube.linkHandler.GenreLinkHandlerFactory;
import org.video.imusictube.extractor.services.youtube.linkHandler.ListDataLinkHandlerFactory;
import org.video.imusictube.extractor.services.youtube.linkHandler.YoutubeChannelLinkHandlerFactory;
import org.video.imusictube.extractor.services.youtube.linkHandler.YoutubeCommentsLinkHandlerFactory;
import org.video.imusictube.extractor.services.youtube.linkHandler.YoutubePlaylistLinkHandlerFactory;
import org.video.imusictube.extractor.services.youtube.linkHandler.YoutubeSuggestLinkHandlerFactory;
import org.video.imusictube.extractor.services.youtube.linkHandler.YoutubeSearchQueryHandlerFactory;
import org.video.imusictube.extractor.services.youtube.linkHandler.YoutubeStreamLinkHandlerFactory;
import org.video.imusictube.extractor.services.youtube.linkHandler.YoutubeTrendingLinkHandlerFactory;
import org.video.imusictube.extractor.stream.StreamExtractor;
import org.video.imusictube.extractor.subscription.SubscriptionExtractor;
import org.video.imusictube.extractor.suggest.SuggestExtractor;
import org.video.imusictube.extractor.utils.ExtractorConstant;
import org.video.imusictube.extractor.utils.Localization;

public class YoutubeService extends StreamingService {

    public YoutubeService(int id) {
        super(id, "YouTube", asList(AUDIO, VIDEO, LIVE, COMMENTS));
    }

    @Override
    public SearchExtractor getSearchExtractor(SearchQueryHandler query, Localization localization) {
        return new YoutubeSearchExtractor(this, query, localization);
    }

    @Override
    public LinkHandlerFactory getStreamLHFactory() {
        return YoutubeStreamLinkHandlerFactory.getInstance();
    }

    @Override
    public ListLinkHandlerFactory getChannelLHFactory() {
        return YoutubeChannelLinkHandlerFactory.getInstance();
    }

    @Override
    public ListLinkHandlerFactory getPlaylistLHFactory() {
        return YoutubePlaylistLinkHandlerFactory.getInstance();
    }

    @Override
    public ListLinkHandlerFactory getAlphabetLHFactory() {
        return AlphabetLinkHandlerFactory.getInstance();
    }

    @Override
    public ListLinkHandlerFactory getArtistLHFactory() {
        return ArtistLinkHandlerFactory.getInstance();
    }

    @Override
    public ListLinkHandlerFactory getGenreLHFactory() {
        return GenreLinkHandlerFactory.getInstance();
    }

    @Override
    public ListLinkHandlerFactory getSuggestLHFactory() {
        return YoutubeSuggestLinkHandlerFactory.getInstance();
    }

    @Override
    public ListLinkHandlerFactory getListDataLHFactory() {
        return ListDataLinkHandlerFactory.getInstance();
    }

    @Override
    public SearchQueryHandlerFactory getSearchQHFactory() {
        return YoutubeSearchQueryHandlerFactory.getInstance();
    }

    @Override
    public StreamExtractor getStreamExtractor(LinkHandler linkHandler, Localization localization) {
        return new YoutubeStreamExtractor(this, linkHandler, localization);
    }

    @Override
    public ChannelExtractor getChannelExtractor(ListLinkHandler linkHandler, Localization localization) {
        return new YoutubeChannelExtractor(this, linkHandler, localization);
    }

    @Override
    public AlphabetExtractor getAlphabetExtractor(ListLinkHandler linkHandler, Localization localization) throws ExtractionException {
        return new MyAlphabetExtractor(this, linkHandler, localization);
    }

    @Override
    public ArtistExtractor getArtistExtractor(ListLinkHandler linkHandler, Localization localization) {
        return new MyArtistExtractor(this, linkHandler, localization);
    }

    @Override
    public GenreExtractor getGenreExtractor(ListLinkHandler linkHandler, Localization localization) throws ExtractionException {
        return new MyGenreExtractor(this, linkHandler, localization);
    }

    @Override
    public SuggestExtractor getSuggestExtractor(ListLinkHandler linkHandler, Localization localization) throws ExtractionException {
        return new MySuggestExtractor(this, linkHandler, localization);
    }

    @Override
    public ListDataExtractor getListDataExtractor(ListLinkHandler linkHandler, Localization localization) throws ExtractionException {
        return new MyDataListExtractor(this, linkHandler, localization);
    }

    @Override
    public PlaylistExtractor getPlaylistExtractor(ListLinkHandler linkHandler, Localization localization) {
        return new YoutubePlaylistExtractor(this, linkHandler, localization);
    }

    @Override
    public SuggestionExtractor getSuggestionExtractor(Localization localization) {
        return new YoutubeSuggestionExtractor(getServiceId(), localization);
    }

    @Override
    public KioskList getKioskList() throws ExtractionException {
        KioskList list = new KioskList(getServiceId());
        // add kiosks here e.g.:
        try {
            list.addKioskEntry(new KioskList.KioskExtractorFactory() {
                @Override
                public KioskExtractor createNewKiosk(StreamingService streamingService, String url, String id, Localization local) throws ExtractionException {
                    return new YoutubeTrendingExtractor(YoutubeService.this, new YoutubeTrendingLinkHandlerFactory().fromUrl(url), id, local);
                }
            }, new YoutubeTrendingLinkHandlerFactory(), ExtractorConstant.KIOS_TRENDING);
            list.setDefaultKiosk(ExtractorConstant.KIOS_TRENDING);
        } catch (Exception e) {
            throw new ExtractionException(e);
        }

        return list;
    }

    @Override
    public SubscriptionExtractor getSubscriptionExtractor() {
        return new YoutubeSubscriptionExtractor(this);
    }

    @Override
    public ListLinkHandlerFactory getCommentsLHFactory() {
        return YoutubeCommentsLinkHandlerFactory.getInstance();
    }

    @Override
    public CommentsExtractor getCommentsExtractor(ListLinkHandler urlIdHandler, Localization localization) throws ExtractionException {
        return new YoutubeCommentsExtractor(this, urlIdHandler, localization);
    }

}
