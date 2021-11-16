/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import gaming_hub.Login_PanelController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Login_Helper {

    private String uname;
    private String pass;
    private String getpass;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private Login_PanelController lc;
    boolean good;
    boolean bad ;
    boolean notfound;

    public void Login_Helper() {
       // lc.adminEntry(uname, pass, good, bad, notfound);
        
    }

}
