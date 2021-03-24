package view;

public enum View
{
  CHATLOG("ChatLogView.fxml");

  private String fxmlFile;

  private View(String fxmlFile) {this.fxmlFile = fxmlFile;}

  public String getFxmlFile() {
    return fxmlFile;
  }

}
