package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.db.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class MainPageController {

    @FXML
    private AnchorPane ancMainPage;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction() {
        String inputUserName = txtUserName.getText();
        String inputPassword = txtPassword.getText();

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM User_Authentication  WHERE Username = ? AND password_hash  = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, inputUserName);
            stmt.setString(2, inputPassword);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (Objects.equals(inputUserName, "owner")){
                    ancMainPage.getChildren().clear();
                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DashBoardO.fxml"));
                    ancMainPage.getChildren().add(load);
                }else if (Objects.equals(inputUserName,"admin")) {
                    ancMainPage.getChildren().clear();
                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DashBoardA.fxml"));
                    ancMainPage.getChildren().add(load);
                } else if (Objects.equals(inputUserName,"trainer")) {
                    ancMainPage.getChildren().clear();
                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DashBoardT.fxml"));
                    ancMainPage.getChildren().add(load);
                }
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
