package com.mayzn.note.android.model;

import com.mayzn.note.android.utils.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

public class Item extends BaseModel {

    public String content;
    public Priority priority;
    public String title;

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date created;

    public Long coupleId;

    public Item() {
    }

    public Item(String title, String content, Priority priority, Long coupleId, Date created) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.coupleId = coupleId;
        this.created = created;
    }

    public enum Priority {
        high,
        medium,
        low;
    }

    @Override
    public String toString() {
        return "Item{" +
                "content='" + content + '\'' +
                ", priority=" + priority +
                ", title='" + title + '\'' +
                ", created=" + created +
                ", coupleId=" + coupleId +
                '}';
    }
}
