package org.mta.musictube.extractor.services.youtube;

import static java.util.Arrays.asList;

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
import org.mta.musictube.extractor.services.youtube.extractors.YoutubeChannelExtractor;
import org.mta.musictube.extractor.stream.StreamExtractor;
import org.mta.musictube.extractor.suggest.SuggestExtractor;
import org.mta.musictube.extractor.utils.ExtractorConstant;
import org.mta.musictube.extractor.utils.Localization;
import org.mta.musictube.extractor.exceptions.ExtractionException;
import org.mta.musictube.extractor.linkhandler.LinkHandler;
import org.mta.musictube.extractor.linkhandler.LinkHandlerFactory;
import org.mta.musictube.extractor.linkhandler.ListLinkHandler;
import org.mta.musictube.extractor.linkhandler.ListLinkHandlerFactory;
import org.mta.musictube.extractor.linkhandler.SearchQueryHandler;
import org.mta.musictube.extractor.linkhandler.SearchQueryHandlerFactory;
import org.mta.musictube.extractor.services.youtube.extractors.MyAlphabetExtractor;
import org.mta.musictube.extractor.services.youtube.extractors.MyArtistExtractor;
import org.mta.musictube.extractor.services.youtube.extractors.MyDataListExtractor;
import org.mta.musictube.extractor.services.youtube.extractors.MyGenreExtractor;
import org.mta.musictube.extractor.services.youtube.extractors.YoutubeCommentsExtractor;
import org.mta.musictube.extractor.services.youtube.extractors.YoutubePlaylistExtractor;
import org.mta.musictube.extractor.services.youtube.extractors.MySuggestExtractor;
import org.mta.musictube.extractor.services.youtube.extractors.YoutubeSearchExtractor;
import org.mta.musictube.extractor.services.youtube.extractors.YoutubeStreamExtractor;
import org.mta.musictube.extractor.services.youtube.extractors.YoutubeSubscriptionExtractor;
import org.mta.musictube.extractor.services.youtube.extractors.YoutubeSuggestionExtractor;
import org.mta.musictube.extractor.services.youtube.extractors.YoutubeTrendingExtractor;
import org.mta.musictube.extractor.services.youtube.linkHandler.AlphabetLinkHandlerFactory;
import org.mta.musictube.extractor.services.youtube.linkHandler.ArtistLinkHandlerFactory;
import org.mta.musictube.extractor.services.youtube.linkHandler.GenreLinkHandlerFactory;
import org.mta.musictube.extractor.services.youtube.linkHandler.ListDataLinkHandlerFactory;
import org.mta.musictube.extractor.services.youtube.linkHandler.YoutubeChannelLinkHandlerFactory;
import org.mta.musictube.extractor.services.youtube.linkHandler.YoutubeCommentsLinkHandlerFactory;
import org.mta.musictube.extractor.services.youtube.linkHandler.YoutubePlaylistLinkHandlerFactory;
import org.mta.musictube.extractor.services.youtube.linkHandler.YoutubeSuggestLinkHandlerFactory;
import org.mta.musictube.extractor.services.youtube.linkHandler.YoutubeSearchQueryHandlerFactory;
import org.mta.musictube.extractor.services.youtube.linkHandler.YoutubeStreamLinkHandlerFactory;
import org.mta.musictube.extractor.services.youtube.linkHandler.YoutubeTrendingLinkHandlerFactory;
import org.mta.musictube.extractor.subscription.SubscriptionExtractor;

/*
 * Created by Christian Schabesberger on 23.08.15.
 *
 * Copyright (C) Christian Schabesberger 2018 <chris.schabesberger@mailbox.org>
 * YoutubeService.java is part of NewPipe.
 *
 * NewPipe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NewPipe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NewPipe.  If not, see <http://www.gnu.org/licenses/>.
 */

public class YoutubeService extends StreamingService {

    public YoutubeService(int id) {
        super(id, "YouTube", asList(ServiceInfo.MediaCapability.AUDIO, ServiceInfo.MediaCapability.VIDEO, ServiceInfo.MediaCapability.LIVE, ServiceInfo.MediaCapability.COMMENTS));
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
