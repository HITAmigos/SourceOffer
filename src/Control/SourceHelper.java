package Control;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import MySQL.hibernate.Music;
import MySQL.hibernate.Video;
import MySQL.specificTable.MusicTable;
import MySQL.specificTable.VideoTable;
import net.sf.json.JSONObject;

/*
 * try { /* 如果不采用接口注入的方式的获取HttpServletRequest，HttpServletResponse的方式 HttpServletRequest
 * request=ServletActionContext.getRequest(); HttpServletResponse
 * response=ServletActionContext.getResponse();
 *
 * this.response.setContentType("text/json;charset=utf-8");
 * this.response.setCharacterEncoding("UTF-8"); JSONObject json = new JSONObject();
 * 
 * 
 * 
 * byte[] jsonBytes = json.toString().getBytes("utf-8");
 * response.setContentLength(jsonBytes.length); response.getOutputStream().write(jsonBytes);
 * response.getOutputStream().flush(); response.getOutputStream().close(); } catch (Exception e) {
 * e.printStackTrace(); }
 */


/**
 * 获取资源时，先分词，然后模糊查找
 * 
 * @author liuyx
 *
 */
public class SourceHelper extends ActionSupport
    implements ServletRequestAware, ServletResponseAware {

  private static final long serialVersionUID = 1L;

  private HttpServletRequest request;
  private HttpServletResponse response;

  private String musicTitle;
  private String videoTitle;

  public String getMusicTitle() {
    return musicTitle;
  }

  public void setMusicTitle(String musicTitle) {
    this.musicTitle = musicTitle;
  }

  public String getVideoTitle() {
    return videoTitle;
  }

  public void setVideoTitle(String videoTitle) {
    this.videoTitle = videoTitle;
  }

  public void setServletRequest(HttpServletRequest request) {
    this.request = request;
  }

  public void setServletResponse(HttpServletResponse response) {
    this.response = response;
  }

  private ArrayList<String> split(String key) {
    ArrayList<String> resultSet = new ArrayList<String>();
    int keyLen = key.length();
    for (int i = keyLen; i > 0; i--) {
      for (int j = 0; j <= keyLen - i; j++) {
        resultSet.add(key.substring(j, j + i));
      }
    }
    return resultSet;
  }

  public Music getMusic(String key) {
    Music music = null;
    return music;
  }

  public Video getVideo(String key) {
    Video video = null;
    return video;
  }

  public void getMusicSet() {
    try {
      /*
       * 如果不采用接口注入的方式的获取HttpServletRequest，HttpServletResponse的方式 HttpServletRequest
       * request=ServletActionContext.getRequest(); HttpServletResponse
       * response=ServletActionContext.getResponse();
       */
      this.response.setContentType("text/json;charset=utf-8");
      this.response.setCharacterEncoding("UTF-8");

      /*
       * List<Map<String, String>> musicSet = new ArrayList<Map<String, String>>(); for (Music music
       * : MusicTable.getMusicSet()) { Map<String, String> tmp = new LinkedHashMap<String,
       * String>(); tmp.put(music.getTitle(), music.getLink()); musicSet.add(tmp); }
       */

      List<String> musicSet = new ArrayList<String>();
      for (Music music : MusicTable.getMusicSet()) {
        musicSet.add(music.getTitle());
        musicSet.add(music.getLink());
      }

      byte[] jsonBytes = musicSet.toString().getBytes("utf-8");
      response.setContentLength(jsonBytes.length);
      response.getOutputStream().write(jsonBytes);
      response.getOutputStream().flush();
      response.getOutputStream().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getVideoSet() {
    try {
      /*
       * 如果不采用接口注入的方式的获取HttpServletRequest，HttpServletResponse的方式 HttpServletRequest
       * request=ServletActionContext.getRequest(); HttpServletResponse
       * response=ServletActionContext.getResponse();
       */
      this.response.setContentType("text/json;charset=utf-8");
      this.response.setCharacterEncoding("UTF-8");

      /*
       * List<Map<String, String>> videoSet = new ArrayList<Map<String, String>>(); for (Video video
       * : VideoTable.getVideoSet()) { Map<String, String> tmp = new LinkedHashMap<String,
       * String>(); tmp.put(video.getTitle(), video.getLink()); musicSet.add(tmp); }
       */

      List<String> videoSet = new ArrayList<String>();
      for (Video video : VideoTable.getVideoSet()) {
        videoSet.add(video.getTitle());
        videoSet.add(video.getLink());
      }

      byte[] jsonBytes = videoSet.toString().getBytes("utf-8");
      response.setContentLength(jsonBytes.length);
      response.getOutputStream().write(jsonBytes);
      response.getOutputStream().flush();
      response.getOutputStream().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteMusic(String musicTitle) {
    try {
      /*
       * 如果不采用接口注入的方式的获取HttpServletRequest，HttpServletResponse的方式 HttpServletRequest
       * request=ServletActionContext.getRequest(); HttpServletResponse
       * response=ServletActionContext.getResponse();
       */
      this.response.setContentType("text/json;charset=utf-8");
      this.response.setCharacterEncoding("UTF-8");
      JSONObject json = new JSONObject();

      json.put("result", MusicTable.deletemusic(musicTitle));

      byte[] jsonBytes = json.toString().getBytes("utf-8");
      response.setContentLength(jsonBytes.length);
      response.getOutputStream().write(jsonBytes);
      response.getOutputStream().flush();
      response.getOutputStream().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteVideo(String videoTitle) {
    try {
      /*
       * 如果不采用接口注入的方式的获取HttpServletRequest，HttpServletResponse的方式 HttpServletRequest
       * request=ServletActionContext.getRequest(); HttpServletResponse
       * response=ServletActionContext.getResponse();
       */
      this.response.setContentType("text/json;charset=utf-8");
      this.response.setCharacterEncoding("UTF-8");
      JSONObject json = new JSONObject();

      json.put("result", MusicTable.deletemusic(videoTitle));

      byte[] jsonBytes = json.toString().getBytes("utf-8");
      response.setContentLength(jsonBytes.length);
      response.getOutputStream().write(jsonBytes);
      response.getOutputStream().flush();
      response.getOutputStream().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String args[]) {
    List<String> musicSet = new ArrayList<String>();
    for (Music music : MusicTable.getMusicSet()) {
      musicSet.add(music.getTitle());
      musicSet.add(music.getLink());
    }

    System.out.println(musicSet.toString());
  }
}
