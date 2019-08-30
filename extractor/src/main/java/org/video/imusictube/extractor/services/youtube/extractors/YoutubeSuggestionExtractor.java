package org.video.imusictube.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonParser;
import com.grack.nanojson.JsonParserException;

import org.video.imusictube.extractor.MusicTube;
import org.video.imusictube.extractor.SuggestionExtractor;
import org.video.imusictube.extractor.exception.ExtractionException;
import org.video.imusictube.extractor.utils.Localization;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class YoutubeSuggestionExtractor extends SuggestionExtractor {

    public static final String CHARSET_UTF_8 = "UTF-8";

    public YoutubeSuggestionExtractor(int serviceId, Localization localization) {
        super(serviceId, localization);
    }

    @Override
    public List<String> suggestionList(String query) throws IOException, ExtractionException {
        List<String> suggestions = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        buffer.append("https://suggestqueries.google.com/complete/search")
                .append("?client=youtube")//"firefox" for JSON, 'toolbar' for xml
                .append("&jsonp=JP")
                .append("&ds=yt")
                .append("&hl=").append(URLEncoder.encode(getLocalization().getCountry(), CHARSET_UTF_8))
                .append("&q=").append(URLEncoder.encode(query, CHARSET_UTF_8));

        String response = MusicTube.getDownloader().download(buffer.toString());
        // trim JSONP part "JP(...)"
        response = response.substring(3, response.length()-1);
        try {
            JsonArray collection = JsonParser.array().from(response).getArray(1, new JsonArray());
            for (Object suggestion : collection) {
                if (!(suggestion instanceof JsonArray)) continue;
                String suggestionStr = ((JsonArray)suggestion).getString(0);
                if (suggestionStr == null) continue;
                suggestions.add(suggestionStr);
            }

            return suggestions;
        } catch (JsonParserException e) {
            return suggestions;
            //throw new ParsingException("Could not parse json response", e);
        }
    }
}
