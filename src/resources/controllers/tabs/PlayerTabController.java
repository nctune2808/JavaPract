package resources.controllers.tabs;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import resources.controllers.functions.Players;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static resources.mySQLconnection.*;

public class PlayerTabController implements Initializable {

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField clubField;

    @FXML
    private JFXComboBox<String> posBox;

    @FXML
    private TextField accelerationBox;
    @FXML
    private JFXSlider accelerationSlider;

    @FXML
    private TextField sprintSpeedBox;
    @FXML
    private JFXSlider sprintSpeedSlider;

    @FXML
    private TextField jumpingBox;
    @FXML
    private JFXSlider jumpingSlider;

    @FXML
    private TextField strengthBox;
    @FXML
    private JFXSlider strengthSlider;

    @FXML
    private TextField aggressionBox;
    @FXML
    private JFXSlider aggressionSlider;

    @FXML
    private TextField positioningBox;
    @FXML
    private JFXSlider positioningSlider;

    @FXML
    private TextField finishingBox;
    @FXML
    private JFXSlider finishingSlider;

    @FXML
    private TextField shotPowerBox;
    @FXML
    private JFXSlider shotPowerSlider;

    @FXML
    private TextField longShotBox;
    @FXML
    private JFXSlider longShotSlider;

    @FXML
    private TextField volleysBox;
    @FXML
    private JFXSlider volleysSlider;

    @FXML
    private TextField penaltiesBox;
    @FXML
    private JFXSlider penaltiesSlider;

    @FXML
    private TextField visionBox;
    @FXML
    private JFXSlider visionSlider;

    @FXML
    private TextField crossingBox;
    @FXML
    private JFXSlider crossingSlider;

    @FXML
    private TextField freeKickBox;
    @FXML
    private JFXSlider freeKickSlider;

    @FXML
    private TextField shortPassingBox;
    @FXML
    private JFXSlider shortPassingSlider;

    @FXML
    private TextField longPassingBox;
    @FXML
    private JFXSlider longPassingSlider;

    @FXML
    private TextField curveBox;
    @FXML
    private JFXSlider curveSlider;

    @FXML
    private TextField agilityBox;
    @FXML
    private JFXSlider agilitySlider;

    @FXML
    private TextField balanceBox;
    @FXML
    private JFXSlider balanceSlider;

    @FXML
    private TextField reactionsBox;
    @FXML
    private JFXSlider reactionsSlider;

    @FXML
    private TextField ballControlBox;
    @FXML
    private JFXSlider ballControlSlider;

    @FXML
    private TextField dribblingBox;
    @FXML
    private JFXSlider dribblingSlider;

    @FXML
    private TextField interceptionsBox;
    @FXML
    private JFXSlider interceptionsSlider;

    @FXML
    private TextField headingBox;
    @FXML
    private JFXSlider headingSlider;

    @FXML
    private TextField markingBox;
    @FXML
    private JFXSlider markingSlider;

    @FXML
    private TextField standTackleBox;
    @FXML
    private JFXSlider standTackleSlider;

    @FXML
    private TextField slidingTackleBox;
    @FXML
    private JFXSlider slidingTackleSlider;

    @FXML
    private TreeTableView<Players> treePlayer;

    @FXML
    private TreeTableColumn<Players, String> nameCol;

    @FXML
    private TreeTableColumn<Players, String> clubCol;

    @FXML
    private TreeTableColumn<Players, String> positionCol;

    @FXML
    private TreeTableColumn<Players, Integer> paceCol;

    @FXML
    private TreeTableColumn<Players, Integer> shootingCol;

    @FXML
    private TreeTableColumn<Players, Integer> passingCol;

    @FXML
    private TreeTableColumn<Players, Integer> agilityCol;

    @FXML
    private TreeTableColumn<Players, Integer> defendingCol;

    @FXML
    private TreeTableColumn<Players, Integer> physicalCol;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton delBtn;

    @FXML
    private JFXButton editBtn;




    @FXML
    void deleteDB(MouseEvent event) {
        String query = "Delete from players where name = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, nameField.getText());
            pst.execute();
            getUpdateTable();
        } catch (Exception e) {
        }
    }
    
    @FXML
    void insertDB(MouseEvent event) {
        String query = "Insert into players (" +
                "name,club,position," +
                "acceleration, sprintspeed, positioning," +
                "finishing, shotpower, longshot, volleys," +
                "penalties, vision, crossing, freekick," +
                "shortpassing, longpassing, curve, agility," +
                "balance, reactions, ballcontrol, dribbling," +
                "interceptions, heading, marking, standtackle," +
                "slidingtackle, jumping, strength,aggression" +
                ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1,nameField.getText());
            pst.setString(2,clubField.getText());
            pst.setString(3,posBox.getValue());
            pst.setDouble(4,accelerationSlider.getValue());
            pst.setDouble(5,sprintSpeedSlider.getValue());
            pst.setDouble(6,positioningSlider.getValue());
            pst.setDouble(7,finishingSlider.getValue());
            pst.setDouble(8,shotPowerSlider.getValue());
            pst.setDouble(9,longShotSlider.getValue());
            pst.setDouble(10,volleysSlider.getValue());
            pst.setDouble(11,penaltiesSlider.getValue());
            pst.setDouble(12,visionSlider.getValue());
            pst.setDouble(13,crossingSlider.getValue());
            pst.setDouble(14,freeKickSlider.getValue());
            pst.setDouble(15,shortPassingSlider.getValue());
            pst.setDouble(16,longPassingSlider.getValue());
            pst.setDouble(17,curveSlider.getValue());
            pst.setDouble(18,agilitySlider.getValue());
            pst.setDouble(19,balanceSlider.getValue());
            pst.setDouble(20,reactionsSlider.getValue());
            pst.setDouble(21,ballControlSlider.getValue());
            pst.setDouble(22,dribblingSlider.getValue());
            pst.setDouble(23,interceptionsSlider.getValue());
            pst.setDouble(24,headingSlider.getValue());
            pst.setDouble(25,markingSlider.getValue());
            pst.setDouble(26,standTackleSlider.getValue());
            pst.setDouble(27,slidingTackleSlider.getValue());
            pst.setDouble(28,jumpingSlider.getValue());
            pst.setDouble(29,strengthSlider.getValue());
            pst.setDouble(30,aggressionSlider.getValue());
            pst.execute();
            getUpdateTable();
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        }
    }

    @FXML
    void updateDB(MouseEvent event) {
        try {
            String newName = nameField.getText();
            String sql = "Update players Set " +
                    "name = '"+newName+"', " +
                    "club = '"+clubField.getText()+"', " +
                    "position = '"+posBox.getValue()+"', " +
                    "acceleration= '"+accelerationSlider.getValue()+"', " +
                    "sprintspeed= '"+sprintSpeedSlider.getValue()+"', " +
                    "positioning= '"+positioningSlider.getValue()+"'," +
                    "finishing= '"+finishingSlider.getValue()+"'," +
                    "shotpower= '"+shotPowerSlider.getValue()+"', " +
                    "longshot= '"+longShotSlider.getValue()+"', " +
                    "volleys= '"+volleysSlider.getValue()+"'," +
                    "penalties= '"+penaltiesSlider.getValue()+"'," +
                    "vision= '"+visionSlider.getValue()+"'," +
                    "crossing= '"+crossingSlider.getValue()+"'," +
                    "freekick= '"+freeKickSlider.getValue()+"'," +
                    "shortpassing= '"+shortPassingSlider.getValue()+"'," +
                    "longpassing= '"+longPassingSlider.getValue()+"'," +
                    "curve= '"+curveSlider.getValue()+"'," +
                    "agility= '"+agilitySlider.getValue()+"'," +
                    "balance= '"+balanceSlider.getValue()+"'," +
                    "reactions= '"+reactionsSlider.getValue()+"'," +
                    "ballcontrol= '"+ballControlSlider.getValue()+"'," +
                    "dribbling= '"+dribblingSlider.getValue()+"'," +
                    "interceptions= '"+interceptionsSlider.getValue()+"'," +
                    "heading= '"+headingSlider.getValue()+"'," +
                    "marking= '"+markingSlider.getValue()+"'," +
                    "standtackle= '"+standTackleSlider.getValue()+"'," +
                    "slidingtackle= '"+slidingTackleSlider.getValue()+"'," +
                    "jumping= '"+jumpingSlider.getValue()+"'," +
                    "strength= '"+strengthSlider.getValue()+"'," +
                    "aggression= '"+aggressionSlider.getValue()+"' " +
                    "where name='"+newName+"' ";

            pst= connection.prepareStatement(sql);
            pst.execute();
            getUpdateTable();

        } catch (Exception e) {
        }
    }

    @FXML
    void clickOnTree(MouseEvent event) {
        
        if(event.getClickCount() == 2){
            Players selectedPlayer = treePlayer.getSelectionModel().getSelectedItem().getValue();
            getDataOnRow(selectedPlayer);
//            delBtn.setDisable(false);
//            editBtn.setDisable(false);
//            addBtn.setDisable(true);
        }else{
            treePlayer.getSelectionModel().clearSelection(treePlayer.getSelectionModel().getSelectedIndex());
        }


    }

    void setDataShow(TextField statField, JFXSlider statSlider, int statNum){
        statSlider.setValue(statNum);
        statField.setText(String.valueOf(statNum));

        statSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
              statField.textProperty().setValue(
                        String.valueOf(newValue.intValue()));
            }
        );


        statField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")){
                statField.setText(newValue.replaceAll("[^\\d]", ""));
            } else if(newValue.isEmpty()){
                statField.setStyle(  "-fx-text-box-border: #ff1a1a ; -fx-focus-color: #ff1a1a ;");
                statSlider.valueProperty().setValue(0);
            }
            else {
                statField.setStyle(null);
                statSlider.valueProperty().setValue( Double.parseDouble(newValue));
            }
        });
    }
    
    void getDataOnRow(Players pl){
        nameField.setText(pl.getPname());
        clubField.setText(pl.getPclub());
        posBox.setValue(pl.getPosition());

        setDataShow(accelerationBox,accelerationSlider,pl.getAcceleration());
        setDataShow(sprintSpeedBox,sprintSpeedSlider,pl.getSprintspeed());
        setDataShow(jumpingBox, jumpingSlider,pl.getJumping());
        setDataShow(strengthBox, strengthSlider,pl.getStrength());
        setDataShow(aggressionBox, aggressionSlider,pl.getAggression());
        setDataShow(positioningBox, positioningSlider,pl.getPositioning());
        setDataShow(finishingBox, finishingSlider,pl.getFinishing());
        setDataShow(shotPowerBox, shotPowerSlider,pl.getShotpower());
        setDataShow(longShotBox, longShotSlider,pl.getLongshot());
        setDataShow(volleysBox, volleysSlider,pl.getVolleys());
        setDataShow(penaltiesBox, penaltiesSlider,pl.getPenalties());
        setDataShow(visionBox, visionSlider,pl.getVision());
        setDataShow(crossingBox, crossingSlider,pl.getCrossing());
        setDataShow(freeKickBox, freeKickSlider,pl.getFreekick());
        setDataShow(shortPassingBox, shortPassingSlider,pl.getShortpassing());
        setDataShow(longPassingBox, longPassingSlider,pl.getLongpassing());
        setDataShow(curveBox, curveSlider,pl.getCurve());
        setDataShow(agilityBox, agilitySlider,pl.getAgility());
        setDataShow(balanceBox, balanceSlider,pl.getBalance());
        setDataShow(reactionsBox, reactionsSlider,pl.getReactions());
        setDataShow(ballControlBox, ballControlSlider,pl.getBallcontrol());
        setDataShow(dribblingBox, dribblingSlider,pl.getDribbling());
        setDataShow(interceptionsBox, interceptionsSlider,pl.getInterceptions());
        setDataShow(headingBox, headingSlider,pl.getHeading());
        setDataShow(markingBox, markingSlider,pl.getMarking());
        setDataShow(standTackleBox, standTackleSlider,pl.getStandtackle());
        setDataShow(slidingTackleBox, slidingTackleSlider,pl.getSlidingtackle());


    }

    ObservableList<Players>  playerLists = FXCollections.observableArrayList();
    ObservableList<String> positionLists = FXCollections.observableArrayList(
            "LW","ST","RW","LF","CF","RF",
            "LM","CM","CAM","CDM","RM",
            "LWB","LB","CB","RB","RWB","GK"
            );
    void getUpdateTable(){
        nameCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Players,String>("pname")
        );
        clubCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Players,String>("pclub")
        );
        positionCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Players,String>("position")
        );
        paceCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Players,Integer>("statPace")
        );
        shootingCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Players,Integer>("statShooting")
        );
        passingCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Players,Integer>("statPassing")
        );
        agilityCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Players,Integer>("statAgility")
        );
        defendingCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Players,Integer>("statDefending")
        );
        physicalCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Players,Integer>("statPhysical")
        );

        posBox.setItems(positionLists);
        playerLists = getPlayerDataOnTable();
        TreeItem<Players> root = new RecursiveTreeItem<>(playerLists, RecursiveTreeObject::getChildren);
        treePlayer.setRoot(root);
        treePlayer.setShowRoot(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getUpdateTable();


    }
}