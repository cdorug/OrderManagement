module com.pt2022_30423_chete_doru_assignment_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires java.desktop;


    opens com.pt2022_30423_chete_doru_assignment_3 to javafx.fxml;
    exports com.pt2022_30423_chete_doru_assignment_3;
    exports com.pt2022_30423_chete_doru_assignment_3.Presentation;
    exports com.pt2022_30423_chete_doru_assignment_3.Model;
    opens com.pt2022_30423_chete_doru_assignment_3.Presentation to javafx.fxml;
}