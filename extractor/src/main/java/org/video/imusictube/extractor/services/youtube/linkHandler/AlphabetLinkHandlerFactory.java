package org.video.imusictube.extractor.services.youtube.linkHandler;
import org.video.imusictube.extractor.exception.ParsingException;
import org.video.imusictube.extractor.linkhandler.ListLinkHandlerFactory;
import org.video.imusictube.extractor.utils.ExtractorConstant;
import org.video.imusictube.extractor.utils.LogHelper;
import org.video.imusictube.extractor.utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AlphabetLinkHandlerFactory  extends ListLinkHandlerFactory {

    private static final AlphabetLinkHandlerFactory instance = new AlphabetLinkHandlerFactory();

    public static AlphabetLinkHandlerFactory getInstance() {
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
            if (!path.startsWith("/data_apps/") && !path.startsWith("/imusictube/")) {
                throw new ParsingException("the URL not match");
            }
            id = path.substring(21);
        } catch (MalformedURLException e) {
            LogHelper.i("AlphabetLink", "MalformedURLException", e.getMessage());
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
