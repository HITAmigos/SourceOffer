package hitamigos.entity;


public class music {
  private int id;
  private String link;
  private int type;
  private String title;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String t) {
    this.title = t;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }
}
