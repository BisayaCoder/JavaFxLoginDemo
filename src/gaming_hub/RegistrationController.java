/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaming_hub;

import Utilities.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class RegistrationController implements Initializable {

    private Stage stage;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    List<String> listfile;
    String path;
    Image img;
    File file;
    String location;
    @FXML
    private JFXCheckBox check_show; //1st pass
    private PasswordField pass_con_pf; //2nd pass
    private JFXCheckBox con_check_show; //2nd pass
    @FXML
    private ImageView img_pane;
    @FXML
    private BorderPane pane;
    @FXML
    private Label prompt_label;
    @FXML
    private Tooltip check_tp;
    @FXML
    private Label ia_label;
    @FXML
    private JFXCheckBox check_show1;
    @FXML
    private Tooltip check_tp1;
    @FXML
    private JFXTextField fxname;
    @FXML
    private JFXTextField fxlastname;
    @FXML
    private JFXTextField fxemail;
    @FXML
    private JFXTextField fxusername;
    @FXML
    private JFXPasswordField fxpass;
    @FXML
    private JFXPasswordField fxpasscon;
    @FXML
    private MediaView mview;
    @FXML
    private BorderPane b_pane;
    @FXML
    private JFXButton reg;
    @FXML
    private JFXTextField sec_ans;
    @FXML
    private JFXComboBox<Label> sec_quest;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sec_ans.setEditable(false);
        sec_quest.getItems().add(new Label("For how many years you're playing computer games?"));
        sec_quest.getItems().add(new Label("what is your first online games played?"));
        sec_quest.getItems().add(new Label("Who is your very best friend?"));
        sec_quest.setEditable(true);
        sec_quest.setPromptText("Select Secret Question");
        sec_quest.setConverter(new StringConverter<Label>() {
            @Override
            public String toString(Label object) {
                //System.out.println(String.valueOf(object));
                return object == null ? "" : object.getText();
            }

            @Override
            public Label fromString(String string) {
                //System.out.println(string);
                return string == null || string.isEmpty() ? null : new Label(string);
            }
        });
        //System.out.println(sec_quest.getSelectionModel().getSelectedItem());
        /* if (sec_quest.isEditable()) {
            sec_quest.getEditor().setStyle("-fx-background-color:TRANSPARENT;-fx-padding: 0.333333em 0em;");
            sec_quest.getEditor().promptTextProperty().unbind();
            sec_quest.getEditor().setPromptText(null);
        }*/
        //sec_quest.setItems(FXCollections.observableArrayList(vals));
        //conn = (Connection) DBConnection.Database_Con();
        // TODO
        //check_show.setTooltip(new Tooltip("Show Password"));
        //con_check_show.setTooltip(new Tooltip("Show Password"));
        //check_tp.show(check_tp);
        /*Media media = new Media("file:///C:/Users/Administrator/Desktop/asus.mp4");
        MediaPlayer player = new MediaPlayer(media);
        mview.fitWidthProperty().bind(s_pane.widthProperty());
        mview.fitHeightProperty().bind(s_pane.heightProperty());
        s_pane.setContent(mview);
        mview.setMediaPlayer(player);
        mview.isSmooth();
        //mview.setFitHeight(400);
        // mview.setFitWidth(400);
        player.setVolume(50);
        player.play();*/
        listfile = new ArrayList<>();
        listfile.add("*png");
        listfile.add("*PNG");
        listfile.add("*jpg");
        listfile.add("*JPG");
        listfile.add("*jpeg");
        listfile.add("*JPEG");
    }

    private void show_password(ActionEvent event
    ) {
        //Stage stage = new Stage();
        if (check_show.isSelected()) {
            check_tp.setText(fxpass.getText());
            check_tp.setHideOnEscape(false);
            check_tp.setAutoHide(false);
            /*javafx.geometry.Point2D p = fxpass.localToParent(fxpass.getBoundsInParent().getMaxX(), fxpass.getBoundsInParent().getMaxY());
            check_tp.setText(fxpass.getText());
            check_tp.show(fxpass,
                    p.getX() + check_tp.getX() + fxpass.getLayoutX(),
                    p.getY() + check_tp.getY() + fxpass.getLayoutY());*/
            //check_tp.setText(fxpass.getText());
            //check_tp.autoHideProperty().setValue(Boolean.FALSE);
            //check_show.setTooltip(new Tooltip(pass.getText()));
            //javafx.geometry.Point2D p = pass.localToScene(pass.getBoundsInLocal().getMaxX(), pass.getBoundsInLocal().getMaxY());
            //pass_orig.managedProperty().bind(check_show.selectedProperty());
            //pass_orig.visibleProperty().bind(check_show.selectedProperty());
            //pass.managedProperty().bind(check_show.selectedProperty().not());
            //pass.visibleProperty().bind(check_show.selectedProperty().not());
            //pass_orig.setText(pass.getText());
            //System.out.println("Clicked");
        } else {
            //System.out.println("Not Clicked");

        }
    }

    private void con_show_password(ActionEvent event) {
        if (con_check_show.isSelected()) {
            //check_tp.autoHideProperty().setValue(Boolean.FALSE);
            //con_check_show.setTooltip(new Tooltip(pass_con_pf.getText()));
            //javafx.geometry.Point2D p = pass.localToScene(pass.getBoundsInLocal().getMaxX(), pass.getBoundsInLocal().getMaxY());
            //pass_orig.managedProperty().bind(con_check_show.selectedProperty());
            //pass_con_tf.visibleProperty().bind(con_check_show.selectedProperty());
            //pass_con_pf.managedProperty().bind(con_check_show.selectedProperty().not());
            //pass_con_pf.visibleProperty().bind(con_check_show.selectedProperty().not());
            //pass_con_tf.setText(pass_con_pf.getText());
            //System.out.println("Clicked");
        } else {
            //System.out.println("Not Clicked");
        }
    }

    @FXML
    private void browse_image(ActionEvent event) throws MalformedURLException {
        browse();
    }

    void browse() throws MalformedURLException { //method for browsing image
        //Stage stage = new Stage();
        //BorderPane bp = new BorderPane();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", listfile));
        file = fc.showOpenDialog(null);
        System.out.println("from directory: " + path);
        if (file != null) {
            //System.out.println("Image Fetched successfully");
            img = new Image(file.toURI().toURL().toExternalForm());
            img_pane.fitWidthProperty().bind(pane.widthProperty());
            img_pane.fitHeightProperty().bind(pane.heightProperty());
            img_pane.setPreserveRatio(true);
            img_pane.setCache(true);
            img_pane.setImage(img);
            img_pane.setSmooth(true);
            pane.setCenter(img_pane);
            ia_label.setText(null);

        } else {
            System.err.print("Please select an image.");
        }
    }

    private void _submit(ActionEvent event) throws SQLException, Exception {
        register_player();
    }

    void register_player() throws SQLException, Exception { //method for registration in database
        conn = (Connection) DBConnection.Database_Con();
        String rp_name = fxname.getText();
        String rp_lastname = fxlastname.getText();
        String rp_email = fxemail.getText();
        String rp_username = fxusername.getText();
        String rp_pass = fxpass.getText();
        String rp_conpassword = fxpasscon.getText();
        String encrypted = encrypt(rp_conpassword);
        String answer = sec_ans.getText();
        if (rp_name.isEmpty() || rp_lastname.isEmpty() || rp_username.isEmpty() || rp_pass.isEmpty() || rp_email.isEmpty()
                || rp_conpassword.isEmpty() || answer.isEmpty()) {
            prompt_label.setText("Please fill all the fields...");
            prompt_label.setVisible(true);
        } else if (!fxpass.getText().equals(rp_conpassword)) {
            prompt_label.setText("Password Mismatch!");
            prompt_label.setVisible(true);
        } else if (checkUserName(rp_username)) {
            prompt_label.setText("Username Already in used");
            prompt_label.setVisible(true);
        } else if (rp_conpassword.length() <= 8) {
            prompt_label.setText("Password is too short!");
            prompt_label.setVisible(true);
        } else if (!checkPassCredentials(rp_conpassword)) {
            prompt_label.setText("Password is aleast 8 digits, consist only with letters and digits");
            prompt_label.setVisible(true);
        } else if (emailvalidate(rp_email)) {
            prompt_label.setText("Invalid Email Address");
            prompt_label.setVisible(true);
        } else if (checkEmail(rp_email)) {
            prompt_label.setText("Email Address Already in used");
            prompt_label.setVisible(true);
        } else {
            String sql = "Insert into player (name, lastname, email_add, username, password, question, answer, image_path, date_reg) values (?,?,?,?,?,?,?,?,now())";
            try {
                //System.out.println(file.exists() ? "Naa" : "Wala");
                if (file == null) {
                    //System.err.println("Please select an image");
                    prompt_label.setText("Please select an image.");
                } else {
                    BufferedImage bImage = ImageIO.read(file);
                    location = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Gaming_Hub\\src\\Player Image/" + rp_name + " " + rp_lastname + ".png";
                    ImageIO.write(bImage, "PNG", new File(location));
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, rp_name);
                    pst.setString(2, rp_lastname);
                    pst.setString(3, rp_email);
                    pst.setString(4, rp_username);
                    pst.setString(5, encrypted);
                    pst.setString(6, sec_quest.getSelectionModel().getSelectedItem().getText());
                    pst.setString(7, sec_ans.getText());
                    pst.setString(8, location);
                    pst.execute();
                    AlertSuccess();
                    System.out.println(encrypted);
                }
            } catch (SQLException | IOException e) {
                Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    private static final Random rand = new Random((new Date()).getTime());

    //Simple cryptography for password
    public static String encrypt(String str) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] salt = new byte[8];
        rand.nextBytes(salt);
        return encoder.encode(salt) + encoder.encode(str.getBytes());
    }

    public static String decrypt(String encstr) {
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

    //username existing validation
    boolean isUsed = true;

    private boolean checkUserName(String _username) {
        //conn = (Connection) DBConnection.Database_Con();
        String check = "Select * from player where username = ?";
        try {
            pst = conn.prepareStatement(check);
            pst.setString(1, _username);
            rs = pst.executeQuery();
            if (rs.next()) {
                _username = rs.getString("username");
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUsed;
    }

    //email existing validation
    private boolean checkEmail(String _email) {
        String check = "Select * from player where email_add = ?";
        try {
            pst = conn.prepareStatement(check);
            pst.setString(1, _email);
            rs = pst.executeQuery();
            if (rs.next()) {
                _email = rs.getString("email_add");
            } else {
                return false;
            }
        } catch (SQLException e) {
        }
        return isUsed;
    }

    void AlertSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("System Message");
        alert.setHeaderText("Succesfully Registered");
        alert.setContentText("You have successfully registered, enjoy playing [gamers]");
        alert.showAndWait();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gaming_hub/Login_Panel.fxml"));
            Parent r1 = (Parent) loader.load();
            stage = new Stage();
            stage.setScene(new Scene(r1));
            stage.setTitle("Alienware/ROG Registration");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Icons/frame_title_logo.png")));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            stage = (Stage) reg.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
        }
    }

    boolean isValid = false;

    //[a-zA-z0-9][a-zA-z0-9._]*@[a-zA-z0-9]+([.][a-zA-Z]+)+
    //"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
    private boolean emailvalidate(String _email) {
        Pattern pattern = Pattern.compile("[a-zA-z0-9][a-zA-z0-9._]*@[a-zA-z0-9]+([.][a-zA-Z]+)+");
        Matcher matcher = pattern.matcher(_email);
        if (matcher.find() && matcher.group().equals(_email)) {
            return isValid;
        } else {
            System.out.println("Invalid Email");
            return true;
        }

    }

    private void lname_handler(KeyEvent event) {
        String urname = fxlastname.getText();
        if (validateLetters(urname)) {
            prompt_label.setVisible(false);
        } else {
            prompt_label.setText("Numeric value is not allowed!");
            prompt_label.setVisible(true);
            fxlastname.setText("");
        }
    }

    private void name_handler(KeyEvent event) {
        String urname = fxname.getText();
        if (validateLetters(urname)) {
            prompt_label.setVisible(false);
        } else {
            prompt_label.setText("Numeric value is not allowed!");
            prompt_label.setVisible(true);
            fxname.setText("");
        }
    }

    public static boolean validateLetters(String txt) {
        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();

    }

    public static boolean checkPassCredentials(String pass) {
        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < pass.length(); i++) {
            char ch = pass.charAt(i);

            if (is_Numeric(ch)) {
                numCount++;
            } else if (is_Letter(ch)) {
                charCount++;
            } else {
                return false;

            }
        }
        return (charCount >= 2 && numCount >= 2);
    }

    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }

    public static boolean is_Numeric(char ch) {

        return (ch >= '0' && ch <= '9');
    }

    private void selecting(ActionEvent event) {
        String output = sec_quest.getSelectionModel().getSelectedItem().getText();
        System.out.println(output.replaceFirst("\n", " "));
        sec_ans.setEditable(true);
        prompt_label.setVisible(false);
    }

    private void ans_handler(KeyEvent event) {
        if (!sec_ans.isEditable()) {
            sec_ans.setEditable(false);
            prompt_label.setText("Select one of the questions above first");
        } else {
            sec_ans.setEditable(true);
        }
    }
}
