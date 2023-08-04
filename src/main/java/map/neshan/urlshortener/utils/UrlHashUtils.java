package map.neshan.urlshortener.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UrlHashUtils implements UrlHasher {

    private HashMap<String, String> urlsMapper;

    UrlHashUtils() {
        urlsMapper = new HashMap<>();
    }

    @Override
    public String hashUrl(String url) {
        //we get 6 first chars
        String hash = DigestUtils.sha1Hex(url).substring(0, 6);
        urlsMapper.put(hash, url);
        return hash;
    }

    @Override
    public String getUrl(String hash) {
        return urlsMapper.get(hash);
    }
}
