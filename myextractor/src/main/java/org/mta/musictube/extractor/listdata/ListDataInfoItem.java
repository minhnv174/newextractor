package org.mta.musictube.extractor.listdata;

import org.mta.musictube.extractor.InfoItem;

public class ListDataInfoItem extends InfoItem {
    public ListDataInfoItem(int serviceId, String url, String name) {
        super(InfoType.LISTDATA, serviceId, url, name);
    }

    private String ListId;
    private String ListName;
    private String Thumbnail;

    public String getListId() {
        return ListId;
    }

    public void setListId(String listId) {
        ListId = listId;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }
}
