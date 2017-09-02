package sample.Model;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.Main;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by a.gusev on 02.09.2017.
 */
public class MessageListItem {

    @FXML
    GridPane msgGrid;

    @FXML
    Label msgSender;

    @FXML
    Label msgHeader;

    @FXML
    Label msgText;

    public MessageListItem() throws MalformedURLException {

    }

    public void setMessage(Message message) throws MessagingException, IOException {
        msgSender.setText(message.getSubject());
        msgHeader.setText(message.getSubject());
        msgText.setText(message.getContent().toString());
    }

    public Pane getControl(){
        return msgGrid;
    }

}
