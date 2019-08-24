package com.mta.musictube.extractor.services.youtube.linkHandler;

import com.mta.musictube.extractor.utils.ExtractorConstant;
import com.mta.musictube.extractor.utils.LogHelper;
import com.mta.musictube.extractor.utils.Utils;
import com.mta.musictube.extractor.exceptions.ParsingException;
import com.mta.musictube.extractor.linkhandler.ListLinkHandlerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ListDataLinkHandlerFactory extends ListLinkHandlerFactory {

    private static final ListDataLinkHandlerFactory instance = new ListDataLinkHandlerFactory();
    public static ListDataLinkHandlerFactory getInstance() {
        return instance;
    }

    @Override
    public String getUrl(String id, List<String> contentFilter, String sortFilter) throws ParsingException {
        return Utils.getHostMyData() + id;
    }

    @Override
    public String getId(String url) throws ParsingException {
        String id = "";
        try {
            URL urlObj = Utils.stringToURL(url);
            String path = urlObj.getPath();
            if (!path.startsWith("/_1492013_json/") && !path.startsWith("/data_apps/")) {
                throw new ParsingException("the URL not match");
            }
            id = path.substring(39);
        } catch (MalformedURLException e) {
            LogHelper.i("ListDataLink", "MalformedURLException", e.getMessage());
        }
        return id;
    }

    @Override
    public boolean onAcceptUrl(String url) throws ParsingException {
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
