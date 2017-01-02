package Control;

import crawler.MusicCrawler;
import crawler.VideoCrawler;

public class Crawl {
  public void crawlMusic() {
    MusicCrawler musicCrawler = new MusicCrawler();
    Thread musicThread = new Thread(musicCrawler);
    musicThread.start();
  }

  public void crawlVideo() {
    VideoCrawler videoCrawler = new VideoCrawler();
    Thread videoThread = new Thread(videoCrawler);
    videoThread.start();
  }
  
  public static void main(String args[]){
    Crawl c = new Crawl();
    c.crawlMusic();
    try {
      Thread.sleep(10*1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("main");
  }
}
