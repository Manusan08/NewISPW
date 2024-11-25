package it.uniroma2.ispw.utils.exception;

import javafx.scene.control.Alert;

public abstract class AlertUtil {



    protected void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected Alert getAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        return alert;
    }

    protected Alert getAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("ops, qualcosa è andato storto");
        return alert;
    }




    protected Alert showSuccess(String title,String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        return alert;

    }
}
