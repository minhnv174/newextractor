package org.mta.musictube.extractor.comments;

import org.mta.musictube.extractor.ListExtractor;
import org.mta.musictube.extractor.StreamingService;
import org.mta.musictube.extractor.utils.Localization;
import org.mta.musictube.extractor.linkhandler.ListLinkHandler;

public abstract class CommentsExtractor extends ListExtractor<CommentsInfoItem> {

	public CommentsExtractor(StreamingService service, ListLinkHandler uiHandler, Localization localization) {
		super(service, uiHandler, localization);
		// TODO Auto-generated constructor stub
	}

}
