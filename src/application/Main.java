
package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    final String[] icons = { "firefox.png", "eclipse.png", "mail.png", "textEdit.png" };

    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

    @Override
    public void start(final Stage primaryStage) throws Exception {

        final Group root = new Group();

        // Dock
        final ImageView imageViewDock = new ImageView(new Image("dock.png"));
        imageViewDock.setTranslateX(12);
        imageViewDock.setTranslateY(100);

        root.getChildren().add(imageViewDock);

        // Eventhanlding Dock
        imageViewDock.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
                    Platform.exit();
                }
            }

        });

        // Bilder hinzufügen
        for (int i = 0; i < icons.length; i++) {
            final ImageView icon = new ImageView(new Image(icons[i]));
            icon.setTranslateX(90 + 80 * i);
            icon.setTranslateY(100);
            icon.setScaleX(0.8);
            icon.setScaleY(0.8);
            icon.setEffect(new Reflection());
            zoomIn(icon);
            zoomOut(icon);
            clickIcon(i, icon);
            root.getChildren().add(icon);
        }

        // Scene und Stage
        final Scene scene = new Scene(root, 500, 200);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setX(screenSize.getWidth() / 2 - 280);
        primaryStage.setY(screenSize.getHeight() - 200);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    // Zoomen
    public void zoomIn(final ImageView icon) {
        icon.setOnMouseEntered(arg0 -> {
            icon.setScaleX(1.0);
            icon.setScaleY(1.0);
        });
    }

    public void zoomOut(final ImageView icon) {
        icon.setOnMouseExited(arg0 -> {
            icon.setScaleX(0.8);
            icon.setScaleY(0.8);
        });
    }

    // Windows Pfad: "C:\\Programme (x86)\\Hobby\\Programm.exe";

    // Icon klicken -> Programm öffnen
    public void clickIcon(final int index, final ImageView icon) {
        icon.setOnMouseClicked(arg0 -> {
            if (index == 0) {
                // System.out.println("Firefox");
                final String path1 = "open /Applications/Firefox.app";
                startProgram(path1);
            } else if (index == 1) {
                // System.out.println("Eclipse");
                final String path2 = "open /Applications/Eclipse/Eclipse.app";
                startProgram(path2);
            } else if (index == 2) {
                // System.out.println("Mail");
                final String path3 = "open /Applications/Mail.app";
                startProgram(path3);
            } else if (index == 3) {
                // System.out.println("TextEdit");
                final String path4 = "open /Applications/TextEdit.app";
                startProgram(path4);
            }

        });
    }

    public void startProgram(final String path) {
        final String cmd = path;
        try {
            System.out.println("Programm öffnet");
            final Process process = Runtime.getRuntime().exec(cmd);
            try {
                Thread.sleep(5000);
            } catch (final Exception e) {
                e.printStackTrace();
            }
            System.out.println("Programm Ende");
            process.destroy();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        launch(args);
    }

}
