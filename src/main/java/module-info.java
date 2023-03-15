module eus.ehu.mastodon {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.kordamp.bootstrapfx.core;
    requires okhttp3;
    requires com.google.gson;
    requires java.desktop;
    requires jdk.xml.dom;

    opens eus.ehu.mastodon to javafx.fxml;
    exports eus.ehu.mastodon;
}