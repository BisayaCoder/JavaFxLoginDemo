/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaming_hub;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Models.Model_Table;
import Utilities.DBConnection;
import com.jfoenix.controls.JFXButton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class Admin_PanelController implements Initializable {

    private double x = 0, y = 0;
    private Stage stage;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    @FXML
    private TableView<Model_Table> table_view;
    @FXML
    private TableColumn<Model_Table, String> col_name;
    @FXML
    private TableColumn<Model_Table, String> col_lname;
    @FXML
    private TableColumn<Model_Table, String> col_email;
    @FXML
    private TableColumn<Model_Table, String> col_status;

    /**
     * Initializes the controller class.
     */
    ObservableList<Model_Table> objlist = FXCollections.observableArrayList();
    @FXML
    private AnchorPane parent;
    @FXML
    private JFXButton min;
    @FXML
    private JFXButton exit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String query = "select * from player";
        try {
            conn = (Connection) DBConnection.Database_Con();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                objlist.add(new Model_Table(rs.getString("name"), rs.getString("lastname"), rs.getString("email_add"), rs.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_PanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        table_view.setItems(objlist);
    }

    @FXML

    private void on_press(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    private void on_drag(MouseEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void minimize(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void close(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
