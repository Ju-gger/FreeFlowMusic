module ffm.freeflowmusic {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;
    requires java.management;

    opens ffm.freeflowmusic to javafx.fxml;
    exports ffm.freeflowmusic;
}