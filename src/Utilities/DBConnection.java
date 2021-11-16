/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.mysql.jdbc.Connection;
import gaming_hub.Login_PanelController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Administrator
 */
public class DBConnection {

    private static Login_PanelController lc;
    private static int port;
    private static String ip;
    private static String dbname;
    private static String username;
    private static String password;
    public static Connection conn;
    private static String val;

    public static Connection Database_Con() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            return conn;
        } else {
            try {
                Inet4Address addr = (Inet4Address) Inet4Address.getLocalHost();
                //System.out.println("Localhost address: " + addr.getHostAddress());
                String hostname = addr.getHostName();
                // System.out.println("Localhost name: " + hostname);
                getConfig();
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://";
                //System.out.println("URL:" + url);
                conn = (Connection) DriverManager.getConnection(url + ip + ":" + port + "/" + dbname, username, password);
                return conn;
            } catch (ClassNotFoundException | UnknownHostException | SQLException e) {
                System.err.println("Server Database is offline");
                alert();
                //lc.notif_label.setText("Server Database is offline");
            }
            System.out.println("Cant connect to server!");
            return null;
        }
    }

    public static void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public static ResultSet query(String query, boolean isUpdate, boolean verbose) throws SQLException {
        ResultSet rs = null;
        Statement stmnt = Database_Con().createStatement();
        if (verbose) {
            System.out.println("Executing query: " + query);
        }
        if (isUpdate) {
            stmnt.executeUpdate(query);
            if (verbose) {
                System.out.println("Successfully Executed Update Query!");
            }
        } else {
            rs = stmnt.executeQuery(query);
            if (verbose) {
                System.out.println("Successfully Executed Query!");
            }
        }
        return rs;
    }

    private static void getConfig() {
        try {
            Scanner scan = new Scanner(new File("DatabaseConfig.spt"));
            ip = scan.next();
            port = scan.nextInt();
            dbname = scan.next();
            username = scan.next();
            password = scan.next();
            /* if (scan.hasNext()) {
                password = scan.next();
            } else {
                password = "";
            }*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void alert() {

        JFXAlert alert = new JFXAlert();
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        alert.setAnimation(com.jfoenix.animation.alert.JFXAlertAnimation.CENTER_ANIMATION);
        alert.titleProperty().setValue("Error Property");
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setCache(true);
        layout.setHeading(new Label("ERROR ALERT!"));
        layout.setBody(new Label("[System Message] \n\t->Can't Connect to database"));
        //layout.getStylesheets().add(getResource("/Styles/myDialog.css").toString());
        //layout.getStylesheets().add("/Styles/myDialog.css");
        layout.getStyleClass().add("jfx-dialog-layout");
        layout.autosize();
        layout.setId("spraket");
        JFXButton Okay = new JFXButton("Ok");
        Hyperlink sm = new Hyperlink("Show more");
        Okay.setOnAction(event -> alert.hideWithAnimation());
        layout.setActions(Okay, sm);
        alert.setContent(layout);
        alert.show();
    }
}
