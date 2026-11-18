module com.mycompany.carracesimulation {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.mycompany.carracesimulation to javafx.fxml;
    exports com.mycompany.carracesimulation;
}