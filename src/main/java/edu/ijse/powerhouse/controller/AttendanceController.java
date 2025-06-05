package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.AttendanceDto;
import edu.ijse.powerhouse.dto.tm.AttendanceTM;
import edu.ijse.powerhouse.model.AttendanceModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable {

    public Label lblAttendanceId;
    public TextField txtMemberId;
    public TextField txtCheckIn;
    public TextField txtCheckOut;
    public TextField txtRecordedBy;

    public TableView<AttendanceTM> tblAttendance;
    public TableColumn<AttendanceTM, String> colAId;
    public TableColumn<AttendanceTM, String> colMId;
    public TableColumn<AttendanceTM, String> colCheckIn;
    public TableColumn<AttendanceTM, String> colCheckOut;
    public TableColumn<AttendanceTM, String> colRecordedBy;

    private final AttendanceModel attendanceModel = new AttendanceModel();
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colAId.setCellValueFactory(new PropertyValueFactory<>("attendance_id"));
        colMId.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        colCheckIn.setCellValueFactory(new PropertyValueFactory<>("check_in"));
        colCheckOut.setCellValueFactory(new PropertyValueFactory<>("check_out"));
        colRecordedBy.setCellValueFactory(new PropertyValueFactory<>("recorded_by"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblAttendance.setItems(FXCollections.observableArrayList(
                attendanceModel.getAllAttendance()
                        .stream()
                        .map(attendanceDto -> new AttendanceTM(
                                attendanceDto.getAttendance_id(),
                                attendanceDto.getMember_id(),
                                attendanceDto.getCheck_in(),
                                attendanceDto.getCheck_out(),
                                attendanceDto.getRecorded_by()
                        )).toList()
        ));
    }

    private void resetPage() {
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtMemberId.setText(null);
            txtCheckIn.setText(null);
            txtCheckOut.setText(null);
            txtRecordedBy.setText(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private String validateAttendanceInput(
            String attendanceId, String memberId, String checkIn, String checkOut, String recordedBy) {

        // Check required fields
        if (attendanceId.isEmpty() || memberId.isEmpty() || checkIn.isEmpty() || recordedBy.isEmpty()) {
            return "Please fill all required fields (Attendance ID, Member ID, Check-In, Recorded By)";
        }

        // Check date/time format - assuming "yyyy-MM-dd HH:mm:ss"
        String dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        String TimePattern = "\\d{2}:\\d{2}:\\d{2}";
        if (!checkIn.matches(dateTimePattern)) {
            return "Check-In must be in 'yyyy-MM-dd HH:mm:ss' format";
        }
        if (!checkOut.isEmpty() && !checkOut.matches(TimePattern)) {
            return "Check-Out must be in 'HH:mm:ss' format or left empty";
        }

        // Validate RecordedBy (you can customize as per requirements, e.g., alphanumeric)
        if (recordedBy.length() < 3) {
            return "Recorded By must be at least 3 characters";
        }

        return null; // No errors
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {

        String attendanceId = lblAttendanceId.getText().trim();
        String memberId = txtMemberId.getText().trim();
        String checkIn = txtCheckIn.getText().trim();
        String checkOut = txtCheckOut.getText().trim();
        String recordedBy = txtRecordedBy.getText().trim();

        String validationError = validateAttendanceInput(attendanceId, memberId, checkIn, checkOut, recordedBy);
        if (validationError != null) {
            new Alert(Alert.AlertType.WARNING, validationError).show();
            return;
        }

        AttendanceDto attendanceDto = new AttendanceDto(
                attendanceId,
                memberId,
                checkIn,
                checkOut,
                recordedBy
        );

        try {
            boolean isSaved = attendanceModel.saveAttendance(attendanceDto);
            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
//        String attendanceId = lblAttendanceId.getText();
//        String memberId = txtMemberId.getText();
//        String checkIn = txtCheckIn.getText();
//        String checkOut = txtCheckOut.getText();
//        String recordedBy = txtRecordedBy.getText();
//
//        AttendanceDto attendanceDto = new AttendanceDto(
//                attendanceId,
//                memberId,
//                checkIn,
//                checkOut,
//                recordedBy
//        );
//        try {
//            boolean isSaved = attendanceModel.saveAttendance(attendanceDto);
//
//            if (isSaved) {
//                resetPage();
//                new Alert(Alert.AlertType.INFORMATION, "Saved").show();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Fail").show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
//        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        String attendanceId = lblAttendanceId.getText().trim();
        String memberId = txtMemberId.getText().trim();
        String checkIn = txtCheckIn.getText().trim();
        String checkOut = txtCheckOut.getText().trim();
        String recordedBy = txtRecordedBy.getText().trim();

        String validationError = validateAttendanceInput(attendanceId, memberId, checkIn, checkOut, recordedBy);
        if (validationError != null) {
            new Alert(Alert.AlertType.WARNING, validationError).show();
            return;
        }

        AttendanceDto attendanceDto = new AttendanceDto(
                attendanceId,
                memberId,
                checkIn,
                checkOut,
                recordedBy
        );

        try {
            boolean isUpdated = attendanceModel.updateAttendance(attendanceDto);
            if (isUpdated) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
//        String attendanceId = lblAttendanceId.getText();
//        String memberId = txtMemberId.getText();
//        String checkIn = txtCheckIn.getText();
//        String checkOut = txtCheckOut.getText();
//        String recordedBy = txtRecordedBy.getText();
//
//        AttendanceDto attendanceDto = new AttendanceDto(
//                attendanceId,
//                memberId,
//                checkIn,
//                checkOut,
//                recordedBy
//        );
//        try {
//            boolean isUpdated = attendanceModel.updateAttendance(attendanceDto);
//            if(isUpdated){
//                resetPage();
//                new Alert(Alert.AlertType.INFORMATION,"Updated").show();
//            }else {
//                new Alert(Alert.AlertType.ERROR,"Fail").show();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
//        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are You Sure ? ",
                ButtonType.YES,
                ButtonType.NO
        );
        Optional<ButtonType> response = alert.showAndWait();

        if(response.isPresent() && response.get() == ButtonType.YES){
            String attendanceId = lblAttendanceId.getText();
            try {
                boolean isDeleted = attendanceModel.deleteAttendance(attendanceId);
                if(isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Fail").show();
                }
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
            }
        }
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = attendanceModel.getNextAttendanceId();
        lblAttendanceId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        AttendanceTM selectedItem = tblAttendance.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblAttendanceId.setText(selectedItem.getAttendance_id());
            txtMemberId.setText(selectedItem.getMember_id());
            txtCheckIn.setText(selectedItem.getCheck_in());
            txtCheckOut.setText(selectedItem.getCheck_out());
            txtRecordedBy.setText(selectedItem.getRecorded_by());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
