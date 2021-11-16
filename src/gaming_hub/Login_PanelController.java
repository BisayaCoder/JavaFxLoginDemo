/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaming_hub;

import DAO.Login_Helper;
import Models.JFXAlertAnimation;
import Utilities.DBConnection;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
//import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class Login_PanelController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private double x = 0, y = 0;
    private int totalAttemps = 3;
    private boolean login;
    String em;
    private Stage stage;
    private Login_Helper lh;

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton btn_login;
    @FXML
    private Hyperlink create_acc;
    @FXML
    private ImageView min;
    @FXML
    private ImageView close;
    @FXML
    private Label prompt_text;
    @FXML
    private ImageView header;
    @FXML
    public Label notif_label;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void login(ActionEvent event) throws SQLException, Exception {
        Login();
    }

    @FXML
    private void register(ActionEvent event) {
        register_account();
    }

    @FXML
    private void minimize(MouseEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void close(MouseEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void press(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    private void drag(MouseEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
        //stage.setOpacity(0.75);
    }

    @FXML
    private void drag_done(MouseEvent event) {
        //stage.setOpacity(1);
    }

    @FXML
    private void un_press_enter(ActionEvent event) {
        btn_login.fire();
    }

    @FXML
    private void pass_press_enter(ActionEvent event) {
        btn_login.fire();
    }

    void register_account() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gaming_hub/Registration.fxml"));
            Parent r1 = (Parent) loader.load();
            stage = new Stage();
            stage.setScene(new Scene(r1));
            stage.setTitle("Alienware/ROG Registration");
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Icons/frame_title_logo.png")));
            stage.show();
            stage.setOnCloseRequest((event) -> {
                System.out.println("Closing event");
                try {
                    load();
                } catch (IOException ex) {
                    Logger.getLogger(Login_PanelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            stage = (Stage) create_acc.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
        }
    }

    void load() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login_Panel.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.FIREBRICK);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Icons/frame_title_logo.png")));
        stage.show();
    }

    private boolean fieldsValidate(String _uname, String _pass) {
        _uname = username.getText();
        _pass = password.getText();
        return _uname.isEmpty() || _pass.isEmpty();
    }

    @SuppressWarnings("empty-statement")
    private void Login() throws SQLException, Exception {
        if (fieldsValidate(String.valueOf(username), String.valueOf(password))) {
            prompt_text.setText("Fields must not be empty.");
            prompt_text.setTextFill(Paint.valueOf(String.valueOf(Color.web("orange"))));
        } else if (ifInactive(username.getText())) {
            AlertErrorInactive();
        } else if (username.getText().equals("admin")) {
            admin_entry();
        } else {
            String query = "Select * from player where email_add = ?";
            try {
                conn = (Connection) DBConnection.Database_Con();
                pst = (PreparedStatement) conn.prepareStatement(query);
                pst.setString(1, username.getText());
                rs = pst.executeQuery();
                if (totalAttemps != 0) {
                    if (rs.next()) {
                        String passrec = rs.getString("password");
                        System.out.println(passrec);
                        String in = decrypt(passrec);
                        System.out.println(in);
                        if (in.equals(password.getText())) {
                            prompt_text.setText("Successfully Login");
                            prompt_text.setTextFill(Paint.valueOf(String.valueOf(Color.web("white"))));
                            System.out.println(rs.getString("name") + " " + rs.getString("lastname"));
                            //conn.close();
                        } else {
                            prompt_text.setText("Invalid Password");
                            prompt_text.setTextFill(Paint.valueOf(String.valueOf(Color.web("red"))));
                            totalAttemps--;
                            System.out.println("Error Attempts: " + totalAttemps);
                        }
                    } else {
                        prompt_text.setText("Cannot find this user");
                        prompt_text.setTextFill(Paint.valueOf(String.valueOf(Color.web("orange"))));
                    }
                } else {
                    System.out.println("Maximum number of attempts exceeded");
                    AlertErrorMaxAttempt();
                    String update = "Update player set status = 'Inactive' where email_add = '" + username.getText() + "'";
                    pst = (PreparedStatement) conn.prepareStatement(update);
                    pst.execute();
                    String sql = "CREATE EVENT error_event\n"
                            + "ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 20 second DO\n"
                            + "UPDATE player\n"
                            + "   SET status = 'Active' where email_add = '" + username.getText() + "'";
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.execute();
                    System.exit(0);
                }
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
    }
    //Deciphering encrypted password (using low level cryptography)

    public static String decrypt(String encstr) throws Exception {
        if (encstr.length() > 12) {
            String cipher = encstr.substring(12);
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                return new String(decoder.decodeBuffer(cipher));
            } catch (IOException e) {

            }
        }
        return null;
    }

    private boolean ifInactive(String status) throws SQLException {
        String sql = "Select * from player where status ='Inactive'";
        conn = (Connection) DBConnection.Database_Con();
        pst = (PreparedStatement) conn.prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.next()) {
            status = rs.getString("status");
            return true;
        } else {
            return false;
        }

    }

    void AlertErrorInactive() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("System Message");
        alert.setHeaderText("ACCOUNT LOCKED!");
        alert.setContentText("Your account is temporarily locked due to 4 invalid login attempts,"
                + "please wait for a few seconds.");
        alert.showAndWait();
    }

    void AlertErrorMaxAttempt() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("System Message");
        alert.setHeaderText("ACCOUNT LOCKED!");
        alert.setContentText("You reached the maximum invalid login attempts, Account is temporarily locked for 30 seconds.");
        alert.showAndWait();
    }
    boolean isUsed = true;

    private boolean checkUserName(String _username) {
        //conn = (Connection) DBConnection.Database_Con();
        String check = "Select * from player where email_add = ?";
        try {
            pst = conn.prepareStatement(check);
            pst.setString(1, _username);
            rs = pst.executeQuery();
            if (rs.next()) {
                _username = rs.getString("email_add");
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUsed;
    }

    void admin_entry() {
        String uname = username.getText();
        String pass = password.getText().trim();
        String qadm = "Select * from admin where a_user = ?";
        try {
            pst = (PreparedStatement) conn.prepareStatement(qadm);
            pst.setString(1, uname);
            rs = pst.executeQuery();
            if (rs.next()) {
                String getpass = rs.getString("a_pass");
                if (getpass.equals(pass)) {
                    prompt_text.setText("Admin Sucessfully Login");
                    prompt_text.setTextFill(Paint.valueOf(String.valueOf(Color.web("blue"))));
                } else {
                    prompt_text.setText("Invalid Administrator Credentials");
                    prompt_text.setTextFill(Paint.valueOf(String.valueOf(Color.web("red"))));
                }
            } else {
                prompt_text.setText("Can't find this Admin");
                prompt_text.setTextFill(Paint.valueOf(String.valueOf(Color.web("red"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void alert() {

        //<editor-fold defaultstate="collapsed" desc="spraket mo">
        JFXAlert alert = new JFXAlert((Stage) btn_login.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        alert.setAnimation(com.jfoenix.animation.alert.JFXAlertAnimation.CENTER_ANIMATION);
        alert.titleProperty().setValue("Error Property");
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setCache(true);
        layout.setHeading(new Label("ERROR ALERT!"));
        layout.setBody(new Label("[System Message] \n\t->Please fill all the fields."));
        layout.getStylesheets().add(getClass().getResource("/Styles/myDialog.css").toExternalForm());
        layout.getStyleClass().add("jfx-dialog-layout ");
        layout.autosize();
        layout.setId("spraket");
        JFXButton Okay = new JFXButton("Ok");
        Hyperlink sm = new Hyperlink("Show more");
        Okay.setOnAction(event -> alert.hideWithAnimation());
        layout.setActions(Okay, sm);
        alert.setContent(layout);
        alert.show();
//</editor-fold>
    }
}
