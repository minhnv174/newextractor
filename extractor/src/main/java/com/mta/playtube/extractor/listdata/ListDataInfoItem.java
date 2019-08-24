package com.mta.playtube.extractor.listdata;

import com.mta.playtube.extractor.InfoItem;

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
