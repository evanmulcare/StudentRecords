module com.example.ugh {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mtu.studentrecords to javafx.fxml;
    exports com.mtu.studentrecords;
    exports com.mtu.studentrecords.Model;
    opens com.mtu.studentrecords.Model to javafx.fxml;
    exports com.mtu.studentrecords.Controller;
    opens com.mtu.studentrecords.Controller to javafx.fxml;
    exports com.mtu.studentrecords.View;
    opens com.mtu.studentrecords.View to javafx.fxml;
}