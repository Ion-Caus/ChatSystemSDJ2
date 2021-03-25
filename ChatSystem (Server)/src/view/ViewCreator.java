package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.util.HashMap;

public abstract class ViewCreator {

    private HashMap<View, ViewController> map;

    public ViewCreator() {
        this.map = new HashMap<>();
    }

    public ViewController getViewController(View view) {
        ViewController controller = map.get(view);
        if (controller == null) {
            controller = loadFromFXML(view.getFxmlFile());
            map.put(view, controller);
        }
        else {
            controller.reset();
        }
        System.out.println("Controller- getVC: " + controller);
        return controller;
    }

    protected abstract void initViewController(ViewController controller, Region root);

    private ViewController loadFromFXML(String fxmlFile) {
        ViewController viewController = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            System.out.println("Root-ViewCreator"+ root);
            viewController = loader.getController();
            System.out.println("Controller-ViewCreator"+ root);
            initViewController(viewController, root);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return viewController;
    }

}
