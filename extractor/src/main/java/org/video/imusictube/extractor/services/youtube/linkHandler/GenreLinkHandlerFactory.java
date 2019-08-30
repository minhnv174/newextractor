package org.video.imusictube.extractor.services.youtube.linkHandler;

import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.linkhandler.ListLinkHandlerFactory;
import org.video.imusictube.extractor.utils.ExtractorConstant;
import org.video.imusictube.extractor.utils.LogHelper;
import org.video.imusictube.extractor.utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GenreLinkHandlerFactory extends ListLinkHandlerFactory {

    private static final GenreLinkHandlerFactory instance = new GenreLinkHandlerFactory();

    public static GenreLinkHandlerFactory getInstance() {
        return instance;
    }

    public String getUrl(String id, List<String> contentFilters, String sortFilter) {
        return Utils.getHostMyData() + id;
    }

    @Override
    public String getId(String url) throws ParsingException {
        String id = "";
        try {
            URL urlObj = Utils.stringToURL(url);
            String path = urlObj.getPath();
            if (!path.startsWith("/data_apps/") && !path.startsWith("/imusictube/")) {
                throw new ParsingException("the URL not match");
            }
            id = path.substring(2);
        } catch (MalformedURLException e) {
            LogHelper.i("GenreLink", "MalformedURLException", e.getMessage());
        }
        return id;

    }

    @Override
    public boolean onAcceptUrl(final String url) {
        URL urlObj;
        try {
            urlObj = Utils.stringToURL(url);
        } catch (MalformedURLException e) {
            return false;
        }
        String host = urlObj.getHost();
        return host.equalsIgnoreCase(ExtractorConstant.MY_HOST_NO_WWW) || host.equalsIgnoreCase(ExtractorConstant.MY_HOST_WITH_WWW);
    }
}
