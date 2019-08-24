package com.mta.playtube.extractor.services.youtube.linkHandler;

import com.mta.playtube.extractor.utils.ExtractorConstant;
import com.mta.playtube.extractor.utils.LogHelper;
import com.mta.playtube.extractor.utils.Utils;
import com.mta.playtube.extractor.exceptions.ParsingException;
import com.mta.playtube.extractor.linkhandler.ListLinkHandlerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ArtistLinkHandlerFactory extends ListLinkHandlerFactory {

    private static final ArtistLinkHandlerFactory instance = new ArtistLinkHandlerFactory();

    public static ArtistLinkHandlerFactory getInstance() {
        return instance;
    }

    public String getUrl(String id, List<String> contentFilters, String sortFilter) {
        return Utils.getHostMyData() + id;
    }

    @Override
    public String getId(String url) throws ParsingException{
        String id = "";
        try {
            URL urlObj = Utils.stringToURL(url);
            String path = urlObj.getPath();
            if (!path.startsWith("/_1492013_json/") && !path.startsWith("/data_apps/")) {
                throw new ParsingException("the URL not match");
            }
            id = path.substring(39);
        } catch (MalformedURLException e) {
            LogHelper.i("ArtistLink", "MalformedURLException", e.getMessage());
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
