package com.itranlin.reimu.common.util.page;

@FunctionalInterface
public interface TranslateEntity<T,M> {
    M trans(T source);
}
