package com.mta.musictube.extractor.comments;

import com.mta.musictube.extractor.ListExtractor;
import com.mta.musictube.extractor.StreamingService;
import com.mta.musictube.extractor.utils.Localization;
import com.mta.musictube.extractor.linkhandler.ListLinkHandler;

public abstract class CommentsExtractor extends ListExtractor<CommentsInfoItem> {

	public CommentsExtractor(StreamingService service, ListLinkHandler uiHandler, Localization localization) {
		super(service, uiHandler, localization);
		// TODO Auto-generated constructor stub
	}

}
