package com.website.automation;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.List;
import java.util.ArrayList;

public class SiteMapParser {

    public static List<String> getPagesFromSitemap(String sitemapUrl) throws Exception {
        List<String> urls = new ArrayList<>();
        Document doc = Jsoup.connect(sitemapUrl).get();
        Elements locs = doc.select("loc");
        for (int i = 0; i < locs.size(); i++) {
            urls.add(locs.get(i).text());
        }
        return urls; // Return the list of URLs
    }
}
