package view;

public enum View
{
    LOGIN("ChatLoginView.fxml"),
    CHAT("MainChatView.fxml");

    private String fxmlFile;

    View(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }

}
