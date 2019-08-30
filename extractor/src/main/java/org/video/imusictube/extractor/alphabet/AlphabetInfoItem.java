package org.video.imusictube.extractor.alphabet;

import org.video.imusictube.extractor.InfoItem;

public class AlphabetInfoItem extends InfoItem {

    public AlphabetInfoItem(int serviceId, String url, String name) {
        super(InfoType.ALPHABEL, serviceId, url, name);
    }

    private String AlphabetId;
    private String AlphabetName;
    private String Total;

    public String getAlphabetId() {
        return AlphabetId;
    }

    public void setAlphabetId(String alphabetId) {
        AlphabetId = alphabetId;
    }

    public String getAlphabetName() {
        return AlphabetName;
    }

    public void setAlphabetName(String alphabetName) {
        AlphabetName = alphabetName;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
