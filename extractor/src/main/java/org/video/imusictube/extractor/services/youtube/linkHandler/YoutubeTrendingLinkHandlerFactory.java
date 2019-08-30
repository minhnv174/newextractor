package org.video.imusictube.extractor.services.youtube.linkHandler;


import org.video.imusictube.extractor.linkhandler.ListLinkHandlerFactory;
import org.video.imusictube.extractor.utils.ExtractorConstant;
import org.video.imusictube.extractor.utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class YoutubeTrendingLinkHandlerFactory extends ListLinkHandlerFactory {

    public String getUrl(String id, List<String> contentFilters, String sortFilter) {
        return "https://www.youtube.com/feed/trending";
    }

    @Override
    public String getId(String url) {
        return ExtractorConstant.KIOS_TRENDING;
    }

    @Override
    public boolean onAcceptUrl(final String url) {
        URL urlObj;
        try {
            urlObj = Utils.stringToURL(url);
        } catch (MalformedURLException e) {
            return false;
        }

        String urlPath = urlObj.getPath();
        return Utils.isHTTP(urlObj) && (YoutubeParsingHelper.isYoutubeURL(urlObj) || YoutubeParsingHelper.isInvidioURL(urlObj)) && urlPath.equals("/feed/trending");
    }
}
