package crawler;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpGetUtils {
  /**
   * get 方法
   * 
   * @param url
   * @return
   */
  public static String get(String url) {
    String result = "";
    try {
      // 获取httpclient实例
      CloseableHttpClient httpclient = HttpClients.createDefault();
      // 获取方法实例。GET
      HttpGet httpGet = new HttpGet(url);
      // 执行方法得到响应
      CloseableHttpResponse response = httpclient.execute(httpGet);
      try {
        // 如果正确执行而且返回值正确，即可解析
        if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
          System.out.println(response.getStatusLine());
          HttpEntity entity = response.getEntity();
          // 从输入流中解析结果
          result = readResponse(entity, "utf-8");
        }
      } finally {
        httpclient.close();
        response.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * stream读取内容，可以传入字符格式
   * 
   * @param resEntity
   * @param charset
   * @return
   */
  private static String readResponse(HttpEntity resEntity, String charset) {
    StringBuffer res = new StringBuffer();
    BufferedReader reader = null;
    try {
      if (resEntity == null) {
        return null;
      }
      reader = new BufferedReader(new InputStreamReader(resEntity.getContent(), charset));
      String line = null;

      while ((line = reader.readLine()) != null) {
        res.append(line);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
      }
    }
    return res.toString();
  }

  public static String regexString(String targetStr, String patternStr) {

    // 获取Pattern对象
    Pattern pattern = Pattern.compile(patternStr);
    // 定义一个matcher用来做匹配
    Matcher matcher = pattern.matcher(targetStr);
    if (matcher.find()) {
      return matcher.group(0);
    }
    return null;
  }

  public static void Save(String title, String link) {
    String USER = "root";
    String PASS = "123456789";
    String DB_URL =
        "jdbc:mysql://localhost:3306/webCrawler?useUnicode=true&characterEncoding=utf8&&useSSL=false";
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      String sql = "insert into music values(\'" + title + "\',\'" + link + "\')";
      stmt.executeUpdate(sql);
    } catch (SQLException s) {
      System.out.println(1);
      return;
    } catch (Exception e) {
      System.out.println(2);
      return;
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      String url = "http://home.9ku.com/share/danqu.php?id=" + i;

      String rege = "<title>.*</title>";
      String result = get(url);
      String title = regexString(result, rege);

      String regex = "<textarea.*http:.*.mp3<";
      result = get(url);
      String link = regexString(result, regex);

      if (title != null && link != null) {
        title = title.substring(7);
        title = title.substring(0, title.length() - 8);
        String[] titles = title.split("\\s+");
        title = titles[0].substring(0, titles[0].length() - 4);
        System.out.println(title);
        String[] links = link.split(">");
        link = links[1].substring(0, links[1].length() - 1);
        System.out.println(link);

      }

    }
    System.out.println("结束！");
  }
}
