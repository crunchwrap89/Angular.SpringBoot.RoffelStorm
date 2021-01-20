package com.bezkoder.spring.files.upload.db.message;



public class ResponseFile {
  private String name;
  private String path;
  private int userId;

  public ResponseFile(String name, String url, int userId) {
    this.name = name;
    this.path = url;
    this.userId = userId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPath() {
    return path;
  }
  public void setPath(String url) {
    this.path = path;
  }
  public int getUserId() { return userId; }
  public void setUserId(int userId) { this.userId = userId; }
}