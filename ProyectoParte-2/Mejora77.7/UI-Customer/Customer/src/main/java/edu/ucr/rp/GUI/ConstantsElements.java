package edu.ucr.rp.GUI;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ConstantsElements {
    //con esto controlamos el tam de todas las interfaces

    public static final int GRID_WIDTH = 600;
    public static final int GRID_HIGH = 700;
    public static final int BORDER_WIDTH = 600;
    public static final int BORDER_HIGH = 550;
    public static final int SCENE_WIDTH = 900;
    public static final int SCENE_HIGH = 700;

    /**
     * Este metodo reproduce los sonidos-musica que se requiera
     *
     * @param nameMp3 este parametro es el nombre del archivo.mp3
     */
    public void soundPlayer(String nameMp3) {
        File fileURL = new File("");
        String urlMusic = fileURL.getAbsolutePath().replaceAll(fileURL.getName(), "") + "\\src\\music\\";
        String path = urlMusic + nameMp3 + ".mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }//end soundPlayer

    /**
     * Metodo que contiene las ventanas que saltan en ciertas acciones
     *
     * @param message message que se quiere expresar
     */
    public void alertInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acción concretada");
        alert.setHeaderText("Aviso");
        alert.setContentText(message);
        alert.showAndWait();
    }//end alertInformation

    /**
     * Metodo que contiene las ventanas que saltan en ciertas acciones
     *
     * @param message message que se quiere expresar
     */
    public void alertWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText("Alerta, sucedió un error");
        alert.setContentText(message);
        alert.showAndWait();
    }//end alertWarning

    /**
     * Metodo que contiene las ventanas que saltan en ciertas acciones
     *
     * @param message message que se quiere expresar
     * @return
     */
    public Alert alertConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Información");
        alert.setHeaderText("Ventana de confirmación");
        alert.setContentText(message);
        return alert;
    }//end alertConfirmation

}
