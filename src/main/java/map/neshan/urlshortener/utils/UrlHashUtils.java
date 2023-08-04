package map.neshan.urlshortener.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class UrlHashUtils implements UrlHasher {
    @Override
    public String hashUrl(String url, String username) {
        //we get 10 first chars of url and username then we hash them together
        String hashUrl = DigestUtils.sha1Hex(url).substring(0, 10);
        String hashUserName = DigestUtils.sha1Hex(url).substring(0, 10);
        return DigestUtils.sha1Hex(hashUrl.concat(hashUserName)).substring(0, 10);
    }
}
