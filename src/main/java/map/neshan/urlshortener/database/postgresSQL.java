package map.neshan.urlshortener.database;

public class postgresSQL implements Connector {
    @Override
    public boolean addUserUrl(String url, String shortUrl, String username) {
        return false;
    }

    @Override
    public boolean removeUserUrl(String url, String username) {
        return false;
    }

    @Override
    public String getUrl(String shortUrl) {
        return null;
    }

    @Override
    public void addUrlVisit(String shortUrl) {

    }

    @Override
    public long geUrlVisit(String shortUrl) {
        return 0;
    }
}
