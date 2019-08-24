package com.mta.musictube.extractor.utils;

import com.mta.musictube.extractor.Info;
import com.mta.musictube.extractor.InfoItem;
import com.mta.musictube.extractor.InfoItemsCollector;
import com.mta.musictube.extractor.ListExtractor;
import com.mta.musictube.extractor.stream.StreamExtractor;
import com.mta.musictube.extractor.stream.StreamInfo;

import java.util.Collections;
import java.util.List;

public class ExtractorHelper {
    private ExtractorHelper() {}

    public static <T extends InfoItem> ListExtractor.InfoItemsPage<T> getItemsPageOrLogError(Info info, ListExtractor<T> extractor) {
        try {
            ListExtractor.InfoItemsPage<T> page = extractor.getInitialPage();
            info.addAllErrors(page.getErrors());

            return page;
        } catch (Exception e) {
            info.addError(e);
            return ListExtractor.InfoItemsPage.emptyPage();
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
