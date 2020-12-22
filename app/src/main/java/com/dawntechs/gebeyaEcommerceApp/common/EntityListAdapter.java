package com.dawntechs.gebeyaEcommerceApp.common;

import java.util.List;

public interface EntityListAdapter<E extends BaseEntity> {
    void setData(List<E> items);
}
