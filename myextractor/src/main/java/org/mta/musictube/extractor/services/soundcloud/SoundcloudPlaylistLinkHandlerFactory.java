package org.mta.musictube.extractor.services.soundcloud;

import org.mta.musictube.extractor.utils.Parser;
import org.mta.musictube.extractor.utils.Utils;
import org.mta.musictube.extractor.linkhandler.ListLinkHandlerFactory;
import org.mta.musictube.extractor.exceptions.ParsingException;

import java.util.List;

public class SoundcloudPlaylistLinkHandlerFactory extends ListLinkHandlerFactory {
    private static final SoundcloudPlaylistLinkHandlerFactory instance = new SoundcloudPlaylistLinkHandlerFactory();
    private final String URL_PATTERN = "^https?://(www\\.|m\\.)?soundcloud.com/[0-9a-z_-]+" +
            "/sets/[0-9a-z_-]+/?([#?].*)?$";

    public static SoundcloudPlaylistLinkHandlerFactory getInstance() {
        return instance;
    }

    @Override
    public String getId(String url) throws ParsingException {
        Utils.checkUrl(URL_PATTERN, url);

        try {
            return SoundcloudParsingHelper.resolveIdWithEmbedPlayer(url);
        } catch (Exception e) {
            throw new ParsingException("Could not get id of url: " + url + " " + e.getMessage(), e);
        }
    }

    @Override
    public String getUrl(String id, List<String> contentFilter, String sortFilter) throws ParsingException {
        try {
            return SoundcloudParsingHelper.resolveUrlWithEmbedPlayer("https://api.soundcloud.com/playlists/" + id);
        } catch (Exception e) {
            throw new ParsingException(e.getMessage(), e);
        }
    }

    @Override
    public boolean onAcceptUrl(final String url) throws ParsingException {
        return Parser.isMatch(URL_PATTERN, url.toLowerCase());
    }
}
