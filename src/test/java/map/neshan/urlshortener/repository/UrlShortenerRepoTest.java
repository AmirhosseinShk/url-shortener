package map.neshan.urlshortener.repository;

import map.neshan.urlshortener.models.UrlStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@PostgresDataJpaTest
public class UrlShortenerRepoTest {

    @Autowired
    private UrlShortenerRepo urlShortenerRepo;

    @Test
    public void testAddUrl() {
        //define url status and save it
        UrlStatus urlStatus = new UrlStatus();
        urlStatus.setUrl("https://www.google.com");
        urlStatus.setShortUrl("qwe12asdv");
        urlStatus.setUsername("test");
        urlStatus.setVisits(2L);
        urlStatus.setLastVisitTime(10000L);
        urlShortenerRepo.save(urlStatus);

        UrlStatus us = urlShortenerRepo.findAll().get(0);
        Assert.assertEquals(us.getUrl(), urlStatus.getUrl());
        Assert.assertEquals(us.getShortUrl(), urlStatus.getShortUrl());
        Assert.assertEquals(us.getVisits(), urlStatus.getVisits());
        Assert.assertEquals(us.getUsername(), urlStatus.getUsername());
        Assert.assertEquals(us.getLastVisitTime(), urlStatus.getLastVisitTime());
    }

    @Test
    public void getAddedUrlByShortUrl() {
        //define url status and save it
        UrlStatus urlStatus = new UrlStatus();
        urlStatus.setUrl("https://www.google.com");
        urlStatus.setShortUrl("qwe12asdv");
        urlStatus.setUsername("test");
        urlStatus.setVisits(2L);
        urlStatus.setLastVisitTime(10000L);
        urlShortenerRepo.save(urlStatus);

        UrlStatus us = urlShortenerRepo.findUrlStatusByShortUrl("qwe12asdv");

        Assert.assertEquals(us.getUrl(), urlStatus.getUrl());
        Assert.assertEquals(us.getShortUrl(), urlStatus.getShortUrl());
        Assert.assertEquals(us.getVisits(), urlStatus.getVisits());
        Assert.assertEquals(us.getUsername(), urlStatus.getUsername());
        Assert.assertEquals(us.getLastVisitTime(), urlStatus.getLastVisitTime());
    }

    @Test
    public void getCountOfUrlsAddedBySpecifiedUser() {
        //define url status and save it
        UrlStatus urlStatus = new UrlStatus();
        urlStatus.setUrl("https://www.google.com");
        urlStatus.setShortUrl("qwe12asdv");
        urlStatus.setUsername("test");
        urlStatus.setVisits(2L);
        urlStatus.setLastVisitTime(10000L);
        UrlStatus urlStatus2 = new UrlStatus();
        urlStatus2.setUrl("https://www.farsnews.com");
        urlStatus2.setShortUrl("asdcasw");
        urlStatus2.setUsername("test");
        urlStatus2.setVisits(5L);
        urlStatus2.setLastVisitTime(10000L);
        UrlStatus urlStatus3 = new UrlStatus();
        urlStatus3.setUrl("https://www.github.com");
        urlStatus3.setShortUrl("eqwr1233");
        urlStatus3.setUsername("test");
        urlStatus3.setVisits(26L);
        urlStatus3.setLastVisitTime(10000L);
        UrlStatus urlStatus4 = new UrlStatus();
        urlStatus4.setUrl("https://www.linkdin.com");
        urlStatus4.setShortUrl("kpok23k23");
        urlStatus4.setUsername("test");
        urlStatus4.setVisits(100L);
        urlStatus4.setLastVisitTime(10000L);

        //add some urls on user test
        urlShortenerRepo.save(urlStatus);
        urlShortenerRepo.save(urlStatus2);
        urlShortenerRepo.save(urlStatus3);
        urlShortenerRepo.save(urlStatus4);

        //add some urls on user test2
        UrlStatus urlStatus5 = new UrlStatus();
        urlStatus5.setUrl("https://www.neshan.com");
        urlStatus5.setShortUrl("eopqwjep3");
        urlStatus5.setUsername("test2");
        urlStatus5.setVisits(21L);
        urlStatus5.setLastVisitTime(10000L);
        UrlStatus urlStatus6 = new UrlStatus();
        urlStatus6.setUrl("https://www.facebook.com");
        urlStatus6.setShortUrl("lokweqo02");
        urlStatus6.setUsername("test2");
        urlStatus6.setVisits(3L);
        urlStatus6.setLastVisitTime(10000L);
        urlShortenerRepo.save(urlStatus5);
        urlShortenerRepo.save(urlStatus6);

        Assert.assertEquals(urlShortenerRepo.count(), 6);

        int count = urlShortenerRepo.countAllByUsername("test");
        Assert.assertEquals(count, 4);

        count = urlShortenerRepo.countAllByUsername("test2");
        Assert.assertEquals(count, 2);
    }

    @Test
    public void addUrlThenGetAndRemove() {
        //define url status and save it
        UrlStatus urlStatus = new UrlStatus();
        urlStatus.setUrl("https://www.google.com");
        urlStatus.setShortUrl("qwe12asdv");
        urlStatus.setUsername("test");
        urlStatus.setVisits(2L);
        urlStatus.setLastVisitTime(10000L);
        urlShortenerRepo.save(urlStatus);

        Assert.assertEquals(urlShortenerRepo.findAll().size(), 1);

        urlShortenerRepo.deleteUrlStatusByUrlAndUsername("https://www.google.com", "test");
        Assert.assertEquals(urlShortenerRepo.findAll().size(), 0);
    }

    @Test
    public void findAllUrlsThatLastVisitedTimeForMoreThanOneYears() {
        //define url status and save it
        UrlStatus urlStatus = new UrlStatus();
        urlStatus.setUrl("https://www.google.com");
        urlStatus.setShortUrl("qwe12asdv");
        urlStatus.setUsername("test1");
        urlStatus.setVisits(2L);
        urlStatus.setLastVisitTime(System.currentTimeMillis());
        UrlStatus urlStatus2 = new UrlStatus();
        urlStatus2.setUrl("https://www.farsnews.com");
        urlStatus2.setShortUrl("asdcasw");
        urlStatus2.setUsername("test2");
        urlStatus2.setVisits(5L);
        urlStatus2.setLastVisitTime(System.currentTimeMillis());
        UrlStatus urlStatus3 = new UrlStatus();
        urlStatus3.setUrl("https://www.github.com");
        urlStatus3.setShortUrl("eqwr1233");
        urlStatus3.setUsername("test3");
        urlStatus3.setVisits(26L);
        urlStatus3.setLastVisitTime(System.currentTimeMillis() - 31536000456L);
        UrlStatus urlStatus4 = new UrlStatus();
        urlStatus4.setUrl("https://www.linkdin.com");
        urlStatus4.setShortUrl("kpok23k23");
        urlStatus4.setUsername("test4");
        urlStatus4.setVisits(100L);
        urlStatus4.setLastVisitTime(System.currentTimeMillis() - 31536000123L);

        //add some urls on user test
        urlShortenerRepo.save(urlStatus);
        urlShortenerRepo.save(urlStatus2);
        urlShortenerRepo.save(urlStatus3);
        urlShortenerRepo.save(urlStatus4);

        long oneYearAgoTimeStamp = System.currentTimeMillis() - 31536000000L;
        List<UrlStatus> urls = urlShortenerRepo.findAllByLastVisitTimeIsLessThan(oneYearAgoTimeStamp);
        Assert.assertEquals(urls.size(), 2);
        Assert.assertTrue(urls.contains(urlStatus3));
        Assert.assertTrue(urls.contains(urlStatus4));
    }
}
