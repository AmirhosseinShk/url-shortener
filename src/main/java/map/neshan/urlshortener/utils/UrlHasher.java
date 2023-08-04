package map.neshan.urlshortener.utils;

public interface UrlHasher {
    abstract String hashUrl(String url);

    abstract String getUrl(String hash);
}
