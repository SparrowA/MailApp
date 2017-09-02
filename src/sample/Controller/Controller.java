package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import sample.Model.MessageViewCell;

import javax.mail.*;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    WebView webView;

    @FXML
    ListView<Message> listView;

    ObservableList<Message> observableList = FXCollections.observableArrayList();

    public void setText(String text){
        webView.getEngine().loadContent(text);
    }


    public void addMessageChilde(Message item){
        observableList.add(item);
    }

    public void UpdateObservListMessage() throws MessagingException, IOException {
        Properties props = new Properties();

        props.put("mail.store.protocol", "pop3s");
        props.put("mail.pop3.host", "pop.mail.ru");
        props.put("mail.pop3.user", "45_93@mail.ru");
        props.put("mail.pop3.socketFactory", 995);
        props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.pop3.port", 995);


        Session session = Session.getInstance(props);

        Store store = session.getStore("pop3");

        store.connect("45_93@mail.ru", "Sparrow20091993");

        Folder folder = store.getFolder("INBOX");

        folder.open(Folder.READ_ONLY);

        Message[] fol = folder.getMessages(folder.getMessageCount() - 100, folder.getMessageCount() - 1);

        for (Message message : fol){
            if (message.getContentType().contains("text/html") && message.getContent() != null) {
                if(message.getSubject() == null){
                    System.out.print("1");
                }
                addMessageChilde(message);
            }
        }

        listView.setItems(observableList);
        listView.setCellFactory(messageItem -> new MessageViewCell(this));
    }


    @FXML
    void initialize() {
        try {
            UpdateObservListMessage();
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}
