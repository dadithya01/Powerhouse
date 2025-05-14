package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.db.DBConnection;
import edu.ijse.powerhouse.dto.UserListDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserListController implements Initializable {

    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    private AnchorPane ancUserList;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<UserListDto, String> colEmail;

    @FXML
    private TableColumn<UserListDto, String> colId;

    @FXML
    private TableColumn<UserListDto, String> colName;

    @FXML
    private TableColumn<UserListDto, String> colPassword;

    @FXML
    private TableColumn<UserListDto, String> colPhone;

    @FXML
    private TableColumn<UserListDto,String> colRegistrationDate;

    @FXML
    private TableColumn<UserListDto, String> colStatus;

    @FXML
    private TableColumn<UserListDto, String> colUserName;

    @FXML
    private TableColumn<UserListDto, String> colUserTypeId;

    @FXML
    private TableView<UserListDto> tblUserList;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtRegistrationDate;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserTypeId;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();

    }

    void clear(){
        txtId.setText(null);
        txtName.setText(null);
        txtPhone.setText(null);
        txtEmail.setText(null);
        txtUserName.setText(null);
        txtPassword.setText(null);
        txtUserTypeId.setText(null);
        txtRegistrationDate.setText(null);
        txtStatus.setText(null);
        btnSave.setDisable(false);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String delete = "DELETE FROM Users WHERE user_Id = ?";
        try {
            con = DBConnection.getInstance().getConnection();
            st = con.prepareStatement(delete);
            st.setString(1, txtId.getText());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                showUserList();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("User deleted successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Failed to delete user.");
                alert.showAndWait();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String save = "INSERT INTO Users (user_Id, name, phone, email, Username, Password, user_Type_Id, registration_Date, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = DBConnection.getInstance().getConnection();
            st = con.prepareStatement(save);
            st.setString(1, txtId.getText());
            st.setString(2, txtName.getText());
            st.setString(3, txtPhone.getText());
            st.setString(4, txtEmail.getText());
            st.setString(5, txtUserName.getText());
            st.setString(6, txtPassword.getText());
            st.setString(7, txtUserTypeId.getText());
            st.setString(8, txtRegistrationDate.getText());
            st.setString(9, txtStatus.getText());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                showUserList();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("User added successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Failed to add user.");
                alert.showAndWait();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getData(MouseEvent event) {
        UserListDto userListDto = tblUserList.getSelectionModel().getSelectedItem();
        txtId.setText(userListDto.getUser_Id());
        txtName.setText(userListDto.getName());
        txtPhone.setText(userListDto.getPhone());
        txtEmail.setText(userListDto.getEmail());
        txtUserName.setText(userListDto.getUser_Name());
        txtPassword.setText(userListDto.getPassword());
        txtUserTypeId.setText(userListDto.getUser_Type_Id());
        txtRegistrationDate.setText(String.valueOf(userListDto.getRegistration_Date()));
        txtStatus.setText(userListDto.getStatus());
        btnSave.setDisable(true);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String update = "UPDATE Users SET name = ?, phone = ?, email = ?, Username = ?, Password = ?, user_Type_Id = ?, registration_Date = ?, Status = ? WHERE user_Id = ?";
        con = DBConnection.getInstance().getConnection();
        st=con.prepareStatement(update);
        st.setString(1, txtName.getText());
        st.setString(2, txtPhone.getText());
        st.setString(3, txtEmail.getText());
        st.setString(4, txtUserName.getText());
        st.setString(5, txtPassword.getText());
        st.setString(6, txtUserTypeId.getText());
        st.setString(7, txtRegistrationDate.getText());
        st.setString(8, txtStatus.getText());
        st.setString(9, txtId.getText());
        int rowsAffected = st.executeUpdate();
        if (rowsAffected > 0) {
            showUserList();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User updated successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to update user.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showUserList();
    }

    public ObservableList<UserListDto> getUserList() {
        ObservableList<UserListDto> userList = FXCollections.observableArrayList();

        String Query = "SELECT * FROM Users";
        try {
            con = DBConnection.getInstance().getConnection();
            try {
                st = con.prepareStatement(Query);
                rs = st.executeQuery();

                while (rs.next()) {
                    UserListDto user = new UserListDto();
                    user.setUser_Id(rs.getString("user_Id"));
                    user.setName(rs.getString("name"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    user.setUser_Name(rs.getString("Username"));
                    user.setPassword(rs.getString("password"));
                    user.setUser_Type_Id(rs.getString("user_Type_Id"));
                    user.setRegistration_Date(rs.getDate("registration_Date"));
                    user.setStatus(rs.getString("status"));
                    userList.add(user);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void showUserList() {
        ObservableList<UserListDto> userList = getUserList();
        tblUserList.setItems(userList);
        colId.setCellValueFactory(new PropertyValueFactory<UserListDto,String >("user_Id"));

        colName.setCellValueFactory(new PropertyValueFactory<UserListDto,String>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<UserListDto,String>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<UserListDto,String>("email"));
        colUserName.setCellValueFactory(new PropertyValueFactory<UserListDto,String>("user_Name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<UserListDto,String>("password"));
        colUserTypeId.setCellValueFactory(new PropertyValueFactory<UserListDto,String>("user_Type_Id"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<UserListDto,String>("registration_Date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<UserListDto,String>("status"));

    }
}