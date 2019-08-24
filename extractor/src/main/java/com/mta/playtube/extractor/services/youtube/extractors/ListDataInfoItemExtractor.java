package com.mta.playtube.extractor.services.youtube.extractors;

import com.grack.nanojson.JsonObject;
import com.mta.playtube.extractor.stream.StreamInfoItemExtractor;
import com.mta.playtube.extractor.stream.StreamType;
import com.mta.playtube.extractor.utils.CryptHelper;
import com.mta.playtube.extractor.utils.ExtractorConstant;
import com.mta.playtube.extractor.utils.LogHelper;
import com.mta.playtube.extractor.utils.Utils;

import com.mta.playtube.extractor.exceptions.ParsingException;

public class ListDataInfoItemExtractor implements StreamInfoItemExtractor {
    protected static final String TAG = LogHelper.makeLogTag(ListDataInfoItemExtractor.class.getSimpleName());

    private final JsonObject object;
    private final CryptHelper objScrypt;

    public ListDataInfoItemExtractor(JsonObject object) {
        this.object = object;
        this.objScrypt = new CryptHelper();
    }

    @Override
    public String getName() throws ParsingException {
        return object.getString("Title");
    }

    @Override
    public String getUrl() throws ParsingException {
        String id = new String(objScrypt.decrypt(object.getString("Id")));
        return ExtractorConstant.EXTRACTOR_PRE_LINK + id;
    }

    @Override
    public String getThumbnailUrl() throws ParsingException {
        String id = new String(objScrypt.decrypt(object.getString("Id")));
        return String.format(ExtractorConstant.EXTRACTOR_PRE_THUMBNAIL, id);
    }

    @Override
    public StreamType getStreamType() throws ParsingException {
        return StreamType.VIDEO_STREAM;
    }

    @Override
    public boolean isAd() throws ParsingException {
        return false;
    }

    @Override
    public long getDuration() throws ParsingException {
        return Utils.parseInt(object.getString("Duration"));
    }

    @Override
    public long getViewCount() throws ParsingException {
        return Utils.parseLong(object.getString("View"));
    }

    @Override
    public String getUploaderName() throws ParsingException {
        return object.getString("Artist");
    }

    @Override
    public String getUploaderUrl() throws ParsingException {
        String authorId =  object.getString("Author");
        return  ExtractorConstant.EXTRACTOR_PRE_CHANNEL + authorId;
    }

    @Override
    public String getUploadDate() throws ParsingException {
        return null;
    }
}
