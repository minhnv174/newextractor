package org.mta.musictube.extractor.listdata;

import org.mta.musictube.extractor.InfoItemExtractor;
import org.mta.musictube.extractor.exceptions.ParsingException;

public interface ListDataInfoItemExtractor extends InfoItemExtractor {
    String getListId() throws ParsingException;
    String getListName() throws ParsingException;
    String getThumbnail() throws ParsingException;
}
