package com.mta.musictube.extractor.listdata;

import com.mta.musictube.extractor.InfoItemExtractor;
import com.mta.musictube.extractor.exceptions.ParsingException;

public interface ListDataInfoItemExtractor extends InfoItemExtractor {
    String getListId() throws ParsingException;
    String getListName() throws ParsingException;
    String getThumbnail() throws ParsingException;
}
