package com.example.demofon.models.api;
public final class ApiConnection {
    public static final String BASE_URL = "https://api.is74.ru";

    public static final String URL_ACTION_GETING_LIST_DOMOFONS(int pageSize, int pagination) {
        return String.format("/domofon/relays?pagesize=%d&pagination=%d", pageSize, pagination);
    }

    public static final String URL_ACTION_OPEN_DOMOFON(String id) {
        return String.format("/domofon/relays/%s/open", id);
    }
}
