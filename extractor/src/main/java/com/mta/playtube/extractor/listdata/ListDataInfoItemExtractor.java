package com.mta.playtube.extractor.listdata;

import com.mta.playtube.extractor.InfoItemExtractor;
import com.mta.playtube.extractor.exceptions.ParsingException;

public interface ListDataInfoItemExtractor extends InfoItemExtractor {
    String getListId() throws ParsingException;
    String getListName() throws ParsingException;
    String getThumbnail() throws ParsingException;
}
