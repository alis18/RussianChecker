module checker.russianchecker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    exports Model;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens checker.russianchecker to javafx.fxml;
    exports checker.russianchecker;
}