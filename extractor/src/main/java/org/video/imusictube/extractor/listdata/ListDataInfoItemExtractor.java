package org.video.imusictube.extractor.listdata;

import org.video.imusictube.extractor.InfoItemExtractor;
import org.video.imusictube.extractor.exception.ParsingException;

public interface ListDataInfoItemExtractor extends InfoItemExtractor {
    String getListId() throws ParsingException;
    String getListName() throws ParsingException;
    String getThumbnail() throws ParsingException;
}
