module com.mycompany.carracesimulation {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.carracesimulation to javafx.fxml;
    exports com.mycompany.carracesimulation;
}
