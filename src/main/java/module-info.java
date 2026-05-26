module com.beginsecure.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires java.sql;
    requires jdk.compiler;
    requires javafx.base;


    opens com.beginsecure.test to javafx.fxml;

    exports com.beginsecure.test;
}