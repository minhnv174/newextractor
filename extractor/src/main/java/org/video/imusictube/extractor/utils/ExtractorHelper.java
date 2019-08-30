package org.video.imusictube.extractor.utils;

import org.video.imusictube.extractor.Info;
import org.video.imusictube.extractor.InfoItem;
import org.video.imusictube.extractor.InfoItemsCollector;
import org.video.imusictube.extractor.ListExtractor;
import org.video.imusictube.extractor.ListExtractor.InfoItemsPage;
import org.video.imusictube.extractor.stream.StreamExtractor;
import org.video.imusictube.extractor.stream.StreamInfo;

import java.util.Collections;
import java.util.List;

public class ExtractorHelper {
    private ExtractorHelper() {}

    public static <T extends InfoItem> InfoItemsPage<T> getItemsPageOrLogError(Info info, ListExtractor<T> extractor) {
        try {
            InfoItemsPage<T> page = extractor.getInitialPage();
            info.addAllErrors(page.getErrors());

            return page;
        } catch (Exception e) {
            info.addError(e);
            return InfoItemsPage.emptyPage();
        }
    }


    public static List<InfoItem> getRelatedVideosOrLogError(StreamInfo info, StreamExtractor extractor) {
        try {
            InfoItemsCollector<? extends InfoItem, ?> collector = extractor.getRelatedStreams();
            if(collector == null) return Collections.emptyList();
            info.addAllErrors(collector.getErrors());

            //noinspection unchecked
            return (List<InfoItem>) collector.getItems();
        } catch (Exception e) {
            info.addError(e);
            return Collections.emptyList();
        }
    }

}
