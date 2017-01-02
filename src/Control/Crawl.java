package Control;

import crawler.VideoCrawler;

public class Crawl {
  public void crawlMusic() {

  }

  public void crawlVideo() {
    VideoCrawler.saveData("http://www.80s.la/movie/list/-2015----p");
  }
}
