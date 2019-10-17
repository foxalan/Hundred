package com.example.adanvace.main;

import java.util.LinkedHashMap;

/**
 * Created by 傅令杰
 */

public final class ItemBuilder {

    private final LinkedHashMap<PageItemBean, BaseItemFragment> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(PageItemBean bean, BaseItemFragment delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<PageItemBean, BaseItemFragment> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<PageItemBean, BaseItemFragment> build() {
        return ITEMS;
    }
}
