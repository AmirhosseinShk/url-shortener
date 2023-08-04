package map.neshan.urlshortener.database;

public interface Connector {

    /**
     * @param url      the url to be shortened
     * @param shortUrl the shortened url
     * @param username the username of the user who wants to add the url
     * @return true if the url was added successfully
     */
    abstract boolean addUserUrl(String url, String shortUrl, String username);

    /**
     * @param url      the original url
     * @param username the username of the user who wants to remove the url
     * @return true if the url was removed successfully
     */
    abstract boolean removeUserUrl(String url, String username);

    /**
     * @param shortUrl the shortened url
     * @return the original url
     */
    abstract String getUrl(String shortUrl);

    /**
     * @param shortUrl the shortened url
     * @return
     */
    abstract void addUrlVisit(String shortUrl);

    /**
     * @param shortUrl the shortened url
     * @return the number of visits of the url
     */
    abstract long getAllUrlsVisits(String shortUrl);
}
