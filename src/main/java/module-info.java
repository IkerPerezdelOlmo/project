module eus.ehu.mastodon {
    requires javafx.controls;
    requires javafx.fxml;
            
                        requires org.kordamp.bootstrapfx.core;
            
    opens eus.ehu.mastodon to javafx.fxml;
    exports eus.ehu.mastodon;
}