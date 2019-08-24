package com.mta.musictube.extractor.services.media_ccc.extractors;

import com.mta.musictube.extractor.SuggestionExtractor;
import com.mta.musictube.extractor.utils.Localization;
import com.mta.musictube.extractor.exceptions.ExtractionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MediaCCCSuggestionExtractor extends SuggestionExtractor {

    public MediaCCCSuggestionExtractor(int serviceId, Localization localization) {
        super(serviceId, localization);
    }

    @Override
    public List<String> suggestionList(String query) throws IOException, ExtractionException {
        return new ArrayList<>(0);
    }
}
