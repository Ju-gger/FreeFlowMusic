module ffm.freeflowmusic {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens ffm.freeflowmusic to javafx.fxml;
    exports ffm.freeflowmusic;
}