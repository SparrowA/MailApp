package sample.Model;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import sample.Controller.Controller;
import sample.Main;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by a.gusev on 02.09.2017.
 */
public class MessageViewCell extends ListCell<Message> {

    @FXML
    GridPane msgGrid;

    @FXML
    Label msgSender;

    @FXML
    Label msgHeader;

    @FXML
    Label msgText;

    Controller mController;

    public MessageViewCell(Controller controller){
        mController = controller;
    }

    @Override
    protected void updateItem(Message item, boolean empty) {
        super.updateItem(item, empty);

        FXMLLoader loader = new FXMLLoader(Main.itemResource);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(item != null){
            try {
                setMessage(item);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setGraphic(msgGrid);
        }

        msgGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mController.setText(msgText.getText());
            }
        });
    }

    void setMessage(Message message) throws MessagingException, IOException {
        System.out.print(((InternetAddress)message.getFrom()[0]).getAddress() + "\n" + message.getSubject() + "\n" + message.getFileName() + "\n\n");

        msgSender.setText(((InternetAddress)message.getFrom()[0]).getAddress());
        msgHeader.setText(message.getSubject());
        msgText.setText(message.getContent().toString());
    }
}