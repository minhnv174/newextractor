package org.video.imusictube.extractor;

import org.video.imusictube.extractor.services.soundcloud.SoundcloudService;
import org.video.imusictube.extractor.services.youtube.YoutubeService;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;



/**
 * A list of supported services.
 */
public final class ServiceList {
    private ServiceList() {
        //no instance
    }

    public static final YoutubeService YouTube = new YoutubeService(0);
    public static final SoundcloudService SoundCloud = new SoundcloudService(1);
    //public static final MediaCCCService MediaCCC = new MediaCCCService(2);

    /**
     * When creating a new service, put this service in the end of this list,
     * and give it the next video id.
     */
    private static final List<StreamingService> SERVICES = unmodifiableList(
            asList(
                    YouTube,
                    SoundCloud
                    //MediaCCC = new MediaCCCService(2)
            ));
    private static final List<StreamingService> YT_SERVICES = unmodifiableList(
            asList(
                    YouTube
                    //MediaCCC = new MediaCCCService(2)
            ));
    /**
     * Get all the supported services.
     *
     * @return a unmodifiable list of all the supported services
     */
    public static List<StreamingService> all() {
        return SERVICES;
    }
    public static List<StreamingService> getYtService() {
        return YT_SERVICES;
    }
}
