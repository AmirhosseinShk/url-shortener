package map.neshan.urlshortener.utils;

public interface UrlHasher {
    abstract String hashUrl(String url, String username);
}
