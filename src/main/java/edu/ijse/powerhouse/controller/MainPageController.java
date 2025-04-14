package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainPageController {

    @FXML
    private AnchorPane ancMainPage;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserId;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String inputUserId = txtUserId.getText();
        String inputPassword = txtPassword.getText();

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM User_Authentication  WHERE user_Id = ? AND password_hash  = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, inputUserId);
            stmt.setString(2, inputPassword);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ancMainPage.getChildren().clear();
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
                ancMainPage.getChildren().add(load);
            } else {
                System.out.println("Wrong username or password!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
