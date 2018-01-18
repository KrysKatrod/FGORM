package fgopkg;

import bdd.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Appli extends Application {

	private ObservableList<Servant> data = FXCollections.observableArrayList();
	private ObservableList<Ressource> data_rsc = FXCollections.observableArrayList();
	private ObservableList<Servant> data_na = FXCollections.observableArrayList();
	private ObservableList<Ressource> data_rsc_na = FXCollections.observableArrayList();
	
	
	public void start(Stage primaryStage) {
		try {
			
			Tab JP_tab = new Tab("JP");
			Tab NA_tab = new Tab("NA");
			Tab Twitter_tab = new Tab("Twitter");
			Tab xp_calc_tab = new Tab("XP Calculator");
			Tab News_tab = new Tab("News");
			Tab Links_tab = new Tab("Usefull Links");
			Tab Reddit_tab = new Tab("/r/grandorder");
			Tab DQMM_tab = new Tab("Daily Quests and Master Missions");
			Tab Players_tab = new Tab("Player Database");
			TabPane tabpane = new TabPane();
			tabpane.getTabs().setAll(JP_tab,NA_tab,xp_calc_tab,News_tab,Twitter_tab,Reddit_tab,DQMM_tab,Links_tab,Players_tab);
			String path = (new File("")).getPath();
			System.out.println(path);
			int na_last_ID=85; //Mysterious Heroine X
			
			Scanner scanner_voiceline = new Scanner(new FileReader(path+"voiceline.csv"));
			scanner_voiceline.useDelimiter(";");
			List<String> voiceline = new ArrayList<>();
			while(scanner_voiceline.hasNext()) {
				voiceline.add(scanner_voiceline.next());
			}
			scanner_voiceline.close();
			TextArea errorbox = new TextArea();
			errorbox.setEditable(false);
			errorbox.setText(voiceline.get(0));
			errorbox.setWrapText(true);
			TextArea na_errorbox = new TextArea();
			na_errorbox.setEditable(false);
			na_errorbox.setText(voiceline.get(0));
			na_errorbox.setWrapText(true);
			TextArea xp_errorbox = new TextArea();
			xp_errorbox.setEditable(false);
			xp_errorbox.setText(voiceline.get(0));
			xp_errorbox.setWrapText(true);
			TextArea players_errorbox = new TextArea();
			players_errorbox.setEditable(false);
			players_errorbox.setText(voiceline.get(0));
			players_errorbox.setWrapText(true);

	        
			ObservableList<String> ServantNames = FXCollections.observableArrayList();
			ObservableList<String> ServantNames_NA = FXCollections.observableArrayList();
			Scanner scanner = new Scanner(new FileReader(path+"matBDD.csv"));
	        //Scanner scanner = new Scanner(new FileReader("Project_0/WSEL Pairing System/WSELBDD.csv"));
			scanner.useDelimiter(",");
	        Map<String,Servant> ServantMap = new HashMap<String,Servant>();
	        while(scanner.hasNext()){
	        	Servant nouv = new Servant(scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.next());
	            ServantMap.put(nouv.getName(), nouv);
	            if(ServantMap.size()<=na_last_ID) ServantNames_NA.add(nouv.getName());
	            //data.add(nouv);
	            ServantNames.add(nouv.getName());
	        }
	        scanner.close();
	        java.util.Collections.sort(ServantNames);
	        java.util.Collections.sort(ServantNames_NA);
	        List<String> RessourcesList = new ArrayList<>();
	        Scanner scanner2 = new Scanner(new FileReader(path+"matlistBDD.csv"));
	        //Scanner scanner = new Scanner(new FileReader("Project_0/WSEL Pairing System/WSELBDD.csv"));
			scanner2.useDelimiter(";");
	        while(scanner2.hasNext()){
	            RessourcesList.add(scanner2.next());
	            Ressource nouv = new Ressource(RessourcesList.get(RessourcesList.size()-1),0,0,0,"NONE","NONE");
	            Ressource nouv2 = new Ressource(RessourcesList.get(RessourcesList.size()-1),0,0,0,"NONE","NONE");
	            data_rsc.add(nouv);
	            data_rsc_na.add(nouv2);
	        }
	        scanner2.close();
	        Scanner scanner3 = new Scanner(new FileReader(path+"bestloclistBDD.csv"));
	        //Scanner scanner = new Scanner(new FileReader("Project_0/WSEL Pairing System/WSELBDD.csv"));
			scanner3.useDelimiter(";");
			Scanner scanner4 = new Scanner(new FileReader(path+"bestloclistnaBDD.csv"));
	        //Scanner scanner = new Scanner(new FileReader("Project_0/WSEL Pairing System/WSELBDD.csv"));
			scanner4.useDelimiter(";");
			int i=0;
			while(scanner4.hasNext()) {
				data_rsc.get(i).setBestlocjp(scanner3.next());
				data_rsc_na.get(i).setBestlocna(scanner4.next());
				i++;
			}
			scanner3.close();
			scanner4.close();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double scrnH = screenSize.height*0.9;
			double scrnW = screenSize.width*0.9;
			Scene scene = new Scene(new Group());
			Group JP_element = new Group();
			Group NA_element = new Group();
			Group XP_element = new Group();
			Group News_element = new Group();
			Group Twitter_element = new Group();
			Group Links_element = new Group();
			Group Reddit_element = new Group();
			Group DQMM_element = new Group();
			Group Players_element = new Group();
			TextArea QP_total_cost = new TextArea();
			QP_total_cost.setEditable(false);
			QP_total_cost.setText("0");
			QP_total_cost.setMinSize(scrnW*0.15, scrnH*0.035);
			QP_total_cost.setMaxSize(scrnW*0.15, scrnH*0.035);
			TextArea na_QP_total_cost = new TextArea();
			na_QP_total_cost.setEditable(false);
			na_QP_total_cost.setText("0");
			na_QP_total_cost.setMinSize(scrnW*0.15, scrnH*0.035);
			na_QP_total_cost.setMaxSize(scrnW*0.15, scrnH*0.035);
			HBox QPtotbox = new HBox();
			Label QPtotlbl = new Label("QP needed : ");
			QPtotbox.getChildren().addAll(QPtotlbl,QP_total_cost);
			HBox na_QPtotbox = new HBox();
			Label na_QPtotlbl = new Label("QP needed : ");
			na_QPtotbox.getChildren().addAll(na_QPtotlbl,na_QP_total_cost);
			primaryStage.setTitle("Fate/Grand Order Ressources Management");
			primaryStage.setHeight(scrnH);
			primaryStage.setWidth(scrnW);
			primaryStage.setScene(scene);
			
			//ErrorZone generation.
			HBox errorzone = new HBox();
			Image Mashu = new Image("file:neutral.png");
			Image Mashu_error = new Image("file:error.png");
			Image Mashu_sad = new Image("file:sad.png");
			ImageView MashuZone = new ImageView();
			MashuZone.setImage(Mashu);
			errorzone.getChildren().addAll(MashuZone,errorbox);
			errorzone.setSpacing(5);
			errorzone.setPadding(new Insets(10, 0, 0, 10));
			errorzone.setTranslateY(scrnH*0.64);
			errorbox.setMinWidth(scrnW*0.6);
			errorbox.setMinHeight(scrnH*0.2);
			HBox na_errorzone = new HBox();
			ImageView MashuZone_na = new ImageView();
			MashuZone_na.setImage(Mashu);
			na_errorzone.getChildren().addAll(MashuZone_na,na_errorbox);
			na_errorzone.setSpacing(5);
			na_errorzone.setPadding(new Insets(10, 0, 0, 10));
			na_errorzone.setTranslateY(scrnH*0.64);
			na_errorbox.setMinWidth(scrnW*0.6);
			na_errorbox.setMinHeight(scrnH*0.2);
			HBox xp_errorzone = new HBox();
			ImageView MashuZone_xp = new ImageView();
			MashuZone_xp.setImage(Mashu);
			xp_errorzone.getChildren().addAll(MashuZone_xp,xp_errorbox);
			xp_errorzone.setSpacing(5);
			xp_errorzone.setPadding(new Insets(10, 0, 0, 10));
			xp_errorzone.setTranslateY(scrnH*0.66);
			xp_errorbox.setMinWidth(scrnW*0.6);
			xp_errorbox.setMinHeight(scrnH*0.2);
			HBox players_errorzone = new HBox();
			ImageView MashuZone_players = new ImageView();
			MashuZone_players.setImage(Mashu);
			players_errorzone.getChildren().addAll(MashuZone_players,players_errorbox);
			players_errorzone.setSpacing(5);
			players_errorzone.setPadding(new Insets(10, 0, 0, 10));
			players_errorzone.setTranslateY(scrnH*0.66);
			players_errorbox.setMinWidth(scrnW*0.6);
			players_errorbox.setMinHeight(scrnH*0.2);
			
			TableView<Servant> ServantList = new TableView<>();
			
			Label label = new Label("Servant List");
	        label.setFont(new Font("Arial", 20));
	 
	        ServantList.setEditable(false);
	 
	        TableColumn<Servant,String> nameCol = new TableColumn<>("Name");
	        nameCol.setMinWidth(scrnW*0.45*0.45);
	        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
	        TableColumn<Servant,String> classCol = new TableColumn<>("Class");
	        classCol.setMinWidth(scrnW*0.45*0.15);
	        classCol.setMaxWidth(scrnW*0.45*0.15);
	        classCol.setCellValueFactory(new PropertyValueFactory<>("cls"));
	        TableColumn<Servant,Integer> ascCol = new TableColumn<>("Ascension");
	        ascCol.setMaxWidth(scrnW*0.45*0.1);
	        ascCol.setCellValueFactory(new PropertyValueFactory<>("ascrank"));
	        TableColumn<Servant,Integer> skill1Col = new TableColumn<>("Skill 1");
	        skill1Col.setMaxWidth(scrnW*0.45*0.1);
	        skill1Col.setCellValueFactory(new PropertyValueFactory<>("s1lvl"));
	        TableColumn<Servant,Integer> skill2Col = new TableColumn<>("Skill 2");
	        skill2Col.setMaxWidth(scrnW*0.45*0.1);
	        skill2Col.setCellValueFactory(new PropertyValueFactory<>("s2lvl"));
	        TableColumn<Servant,Integer> skill3Col = new TableColumn<>("Skill 3");
	        skill3Col.setMaxWidth(scrnW*0.45*0.1);
	        skill3Col.setCellValueFactory(new PropertyValueFactory<>("s3lvl"));
	        
	        ServantList.setItems(data);
	        ServantList.getColumns().addAll(nameCol, classCol, ascCol, skill1Col, skill2Col, skill3Col);
	        VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.setTranslateY(scrnH*0.05);
	        vbox.setMaxWidth(scrnW*0.48);
	        vbox.setMaxHeight(scrnH*0.45);
	 
	        
	        
	        //NA Servant list
	        TableView<Servant> ServantList_na = new TableView<>();
	        
			Label na_label = new Label("Servant List");
			na_label.setFont(new Font("Arial", 20));
	 
	        ServantList_na.setEditable(false);
	 
	        TableColumn<Servant,String> na_nameCol = new TableColumn<>("Name");
	        na_nameCol.setMinWidth(scrnW*0.45*0.45);
	        na_nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
	        TableColumn<Servant,String> na_classCol = new TableColumn<>("Class");
	        na_classCol.setMinWidth(scrnW*0.45*0.15);
	        na_classCol.setMaxWidth(scrnW*0.45*0.15);
	        na_classCol.setCellValueFactory(new PropertyValueFactory<>("cls"));
	        TableColumn<Servant,Integer> na_ascCol = new TableColumn<>("Ascension");
	        na_ascCol.setMaxWidth(scrnW*0.45*0.1);
	        na_ascCol.setCellValueFactory(new PropertyValueFactory<>("ascrank"));
	        TableColumn<Servant,Integer> na_skill1Col = new TableColumn<>("Skill 1");
	        na_skill1Col.setMaxWidth(scrnW*0.45*0.1);
	        na_skill1Col.setCellValueFactory(new PropertyValueFactory<>("s1lvl"));
	        TableColumn<Servant,Integer> na_skill2Col = new TableColumn<>("Skill 2");
	        na_skill2Col.setMaxWidth(scrnW*0.45*0.1);
	        na_skill2Col.setCellValueFactory(new PropertyValueFactory<>("s2lvl"));
	        TableColumn<Servant,Integer> na_skill3Col = new TableColumn<>("Skill 3");
	        na_skill3Col.setMaxWidth(scrnW*0.45*0.1);
	        na_skill3Col.setCellValueFactory(new PropertyValueFactory<>("s3lvl"));
	        
	        ServantList_na.setItems(data_na);
	        ServantList_na.getColumns().addAll(na_nameCol, na_classCol, na_ascCol, na_skill1Col, na_skill2Col, na_skill3Col);
	        VBox na_vbox = new VBox();
	        na_vbox.setSpacing(5);
	        na_vbox.setPadding(new Insets(10, 0, 0, 10));
	        na_vbox.setTranslateY(scrnH*0.05);
	        na_vbox.setMaxWidth(scrnW*0.48);
	        na_vbox.setMaxHeight(scrnH*0.45);
	 
	        
			
	        //Ressources Display
	    	TableView<Ressource> Ressources_Display = new TableView<>();
			
			Label label2 = new Label("Ressources");
	        label2.setFont(new Font("Arial", 20));
	        Ressources_Display.setMaxSize(scrnW*0.48, scrnH*0.42);
	 
	        Ressources_Display.setEditable(false);
	 
	        TableColumn<Ressource,String> r_nameCol = new TableColumn<>("Name");
	        r_nameCol.setMinWidth(scrnW*0.45*0.25);
	        r_nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
	        TableColumn<Ressource,Integer> neededCol = new TableColumn<>("Needed");
	        neededCol.setMinWidth(scrnW*0.45*0.1);
	        neededCol.setMaxWidth(scrnW*0.45*0.1);
	        neededCol.setCellValueFactory(new PropertyValueFactory<>("needed"));
	        TableColumn<Ressource,Integer> stockCol = new TableColumn<>("In Stock");
	        stockCol.setMaxWidth(scrnW*0.45*0.1);
	        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
	        stockCol.setEditable(true);
	        TableColumn<Ressource,Integer> lackCol = new TableColumn<>("Lacking");
	        lackCol.setMaxWidth(scrnW*0.45*0.1);
	        lackCol.setCellValueFactory(new PropertyValueFactory<>("lack"));
	        TableColumn<Ressource,String> bestjpCol = new TableColumn<>("Best Location (JP)");
	        bestjpCol.setMinWidth(scrnW*0.45*0.1);
	        bestjpCol.setCellValueFactory(new PropertyValueFactory<>("bestlocjp"));
	        TableColumn<Ressource,String> bestnaCol = new TableColumn<>("Best Location (NA)");
	        bestnaCol.setMinWidth(scrnW*0.45*0.1);
	        bestnaCol.setCellValueFactory(new PropertyValueFactory<>("bestlocna"));
	        
	        Ressources_Display.setItems(data_rsc);
	        Ressources_Display.getColumns().addAll(r_nameCol, neededCol, stockCol, lackCol, bestjpCol);
	        
	        
	        Ressources_Display.setRowFactory(row -> new TableRow<Ressource>(){
	            @Override
	            public void updateItem(Ressource item, boolean empty){
	                super.updateItem(item, empty);

	                if (item == null || empty) {
	                    setStyle(null);
	                } else {
	                    //Now 'item' has all the info of the Person in this row
	                    if (item.getLack()>0) {
	                        //We apply now the changes in all the cells of the row
	                        for(int i=0; i<getChildren().size();i++){
	                            ((Labeled) getChildren().get(i)).setTextFill(Color.RED);
	                            ((Labeled) getChildren().get(i)).setStyle("-fx-background-color: yellow");
	                        }                        
	                    } else {
	                    	for(int i=0; i<getChildren().size();i++){
	                            ((Labeled) getChildren().get(i)).setTextFill(Color.GREEN);
	                            ((Labeled) getChildren().get(i)).setStyle("-fx-background-color: lightblue");
	                        }
	                    }
	                    
	                }
	                Ressources_Display.getColumns().get(0).setVisible(false);
	                Ressources_Display.getColumns().get(0).setVisible(true);
	            }
	        });
	        VBox vbox_rdisp = new VBox();
	        vbox_rdisp.setSpacing(5);
	        vbox_rdisp.setPadding(new Insets(10, 0, 0, 10));
	        vbox_rdisp.setTranslateY(scrnH*0.05);
	        vbox_rdisp.setTranslateX(scrnW*0.5);
	        vbox_rdisp.setMinWidth(scrnW*0.48);
	        vbox_rdisp.setMaxHeight(scrnH*0.9);
	 
	        
	        // NA Ressources display
TableView<Ressource> na_Ressources_Display = new TableView<>();
			
			Label na_label2 = new Label("Ressources");
			na_label2.setFont(new Font("Arial", 20));
			na_Ressources_Display.setMaxSize(scrnW*0.48, scrnH*0.42);
	        na_Ressources_Display.setEditable(false);
	 
	        TableColumn<Ressource,String> na_r_nameCol = new TableColumn<>("Name");
	        na_r_nameCol.setMinWidth(scrnW*0.45*0.25);
	        na_r_nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
	        TableColumn<Ressource,Integer> na_neededCol = new TableColumn<>("Needed");
	        na_neededCol.setMinWidth(scrnW*0.45*0.1);
	        na_neededCol.setMaxWidth(scrnW*0.45*0.1);
	        na_neededCol.setCellValueFactory(new PropertyValueFactory<>("needed"));
	        TableColumn<Ressource,Integer> na_stockCol = new TableColumn<>("In Stock");
	        na_stockCol.setMaxWidth(scrnW*0.45*0.1);
	        na_stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
	        na_stockCol.setEditable(true);
	        TableColumn<Ressource,Integer> na_lackCol = new TableColumn<>("Lacking");
	        na_lackCol.setMaxWidth(scrnW*0.45*0.1);
	        na_lackCol.setCellValueFactory(new PropertyValueFactory<>("lack"));
	        TableColumn<Ressource,String> na_bestjpCol = new TableColumn<>("Best Location (JP)");
	        na_bestjpCol.setMinWidth(scrnW*0.45*0.1);
	        na_bestjpCol.setCellValueFactory(new PropertyValueFactory<>("bestlocjp"));
	        TableColumn<Ressource,String> na_bestnaCol = new TableColumn<>("Best Location (NA)");
	        na_bestnaCol.setMinWidth(scrnW*0.45*0.1);
	        na_bestnaCol.setCellValueFactory(new PropertyValueFactory<>("bestlocna"));
	        
	        na_Ressources_Display.setItems(data_rsc_na);
	        na_Ressources_Display.getColumns().addAll(na_r_nameCol, na_neededCol, na_stockCol, na_lackCol, bestnaCol);
	        na_Ressources_Display.setRowFactory(row -> new TableRow<Ressource>(){
	            @Override
	            public void updateItem(Ressource item, boolean empty){
	                super.updateItem(item, empty);

	                if (item == null || empty) {
	                    setStyle(null);
	                } else {
	                    //Now 'item' has all the info of the Person in this row
	                    if (item.getLack()>0) {
	                        //We apply now the changes in all the cells of the row
	                        for(int i=0; i<getChildren().size();i++){
	                            ((Labeled) getChildren().get(i)).setTextFill(Color.RED);
	                            ((Labeled) getChildren().get(i)).setStyle("-fx-background-color: yellow");
	                        }                        
	                    } else {
	                    	for(int i=0; i<getChildren().size();i++){
	                            ((Labeled) getChildren().get(i)).setTextFill(Color.GREEN);
	                            ((Labeled) getChildren().get(i)).setStyle("-fx-background-color: lightblue");
	                        }
	                    }
	                    
	                }
	            }
	        });
	        VBox na_vbox_rdisp = new VBox();
	        na_vbox_rdisp.setSpacing(5);
	        na_vbox_rdisp.setPadding(new Insets(10, 0, 0, 10));
	        na_vbox_rdisp.setTranslateY(scrnH*0.05);
	        na_vbox_rdisp.setTranslateX(scrnW*0.5);
	        na_vbox_rdisp.setMinWidth(scrnW*0.48);
	        na_vbox_rdisp.setMaxHeight(scrnH*0.9);
	 
	        
	        
	        //Adding Servant
	        ComboBox<String> add_serv_list = new ComboBox<>(ServantNames);
	        add_serv_list.getSelectionModel().selectFirst();
	        new SelectKeyComboBoxListener(add_serv_list);
	        HBox box_add_serv = new HBox();
	        box_add_serv.setSpacing(5);
	        box_add_serv.setPadding(new Insets(10,0,0,10));
	        box_add_serv.setTranslateY(scrnH*0.55);
	        VBox ascensionrank_vbox = new VBox();
	        TextField ascensionrank = new TextField();
	        ascensionrank.setPromptText("0");
	        ascensionrank.setMaxWidth(scrnH*0.05);
	        VBox skill1level_vbox = new VBox();
	        TextField skill1level = new TextField();
	        skill1level.setPromptText("0");
	        skill1level.setMaxWidth(scrnH*0.05);
	        VBox skill2level_vbox = new VBox();
	        TextField skill2level = new TextField();
	        skill2level.setPromptText("0");
	        skill2level.setMaxWidth(scrnH*0.05);
	        VBox skill3level_vbox = new VBox();
	        TextField skill3level = new TextField();
	        skill3level.setPromptText("0");
	        skill3level.setMaxWidth(scrnH*0.05);
	        Label ascensionrank_lbl = new Label("Ascension");
	        Label skill1level_lbl = new Label("Skill1");
	        Label skill2level_lbl = new Label("Skill2");
	        Label skill3level_lbl = new Label("Skill3");
	        ascensionrank_vbox.getChildren().addAll(ascensionrank,ascensionrank_lbl);
	        skill1level_vbox.getChildren().addAll(skill1level,skill1level_lbl);
	        skill2level_vbox.getChildren().addAll(skill2level,skill2level_lbl);
	        skill3level_vbox.getChildren().addAll(skill3level,skill3level_lbl);
	        
	        Button addButton = new Button("Add Servant");
	        addButton.setOnAction((ActionEvent e0) -> {
	        	if(Integer.parseInt(ascensionrank.getText())>=0&&Integer.parseInt(ascensionrank.getText())<=4&&Integer.parseInt(skill1level.getText())>=1&&Integer.parseInt(skill1level.getText())<=10&&Integer.parseInt(skill2level.getText())>=1&&Integer.parseInt(skill2level.getText())<=10&&Integer.parseInt(skill3level.getText())>=1&&Integer.parseInt(skill3level.getText())<=10) {
	        		boolean alreadyin=false;
	        		for(int ii=0;ii<data.size();ii++) 
	        			{
	        				if(data.get(ii).getName().equals(add_serv_list.getValue()))
	        				{
	        					alreadyin=true;
	        				}
	        			}
	        		if(!alreadyin)
	        		{
	        		data.add(ServantMap.get(add_serv_list.getValue()));
	        		data.get(data.size()-1).setAscrank(Integer.parseInt(ascensionrank.getText()));
	        		data.get(data.size()-1).setS1lvl(Integer.parseInt(skill1level.getText()));
	        		data.get(data.size()-1).setS2lvl(Integer.parseInt(skill2level.getText()));
	        		data.get(data.size()-1).setS3lvl(Integer.parseInt(skill3level.getText()));
	        		errorbox.setText(voiceline.get(0)+"\n\nAscension : "+ascensionrank.getText()+" dans la case, "+data.get(data.size()-1).getAscrank()+" dans le tableau.");
	        		MashuZone.setImage(Mashu);
	        		}
	        		else
	        		{
	        			errorbox.setText(voiceline.get(2));
		        		MashuZone.setImage(Mashu_error);
	        		}
	        	}
	        	else {
	        		errorbox.setText(voiceline.get(1));
	        		MashuZone.setImage(Mashu_error);
	        	}
	        	refresh_rsc(QP_total_cost,na_QP_total_cost);
	        	Ressources_Display.getColumns().get(0).setVisible(false);
    	    	Ressources_Display.getColumns().get(0).setVisible(true);
	        });
	        
	        Button delButton = new Button("Delete Servant");
	        delButton.setOnAction((ActionEvent e3) -> {
	        		data.remove(ServantList.getSelectionModel().getSelectedIndex());
	        		refresh_rsc(QP_total_cost,na_QP_total_cost);
	        		Ressources_Display.getColumns().get(0).setVisible(false);
        	    	Ressources_Display.getColumns().get(0).setVisible(true);
	        });
	        //Edit Button
	        Dialog<Boolean> editServant = new Dialog<>();
	        Button editButton = new Button("Edit Servant");
	        ButtonType edit_valid = new ButtonType("Edit", ButtonData.OK_DONE);
        	editServant.getDialogPane().getButtonTypes().addAll(edit_valid, ButtonType.CANCEL);	        
	        editButton.setOnAction((ActionEvent e) -> {
	        	GridPane grid = new GridPane();
	        	grid.setHgap(10);
	        	grid.setVgap(10);
	        	grid.setPadding(new Insets(20, 150, 10, 10));

	        	TextField edit_ascension = new TextField(Integer.toString(ServantList.getSelectionModel().getSelectedItem().getAscrank()));
	        	edit_ascension.setPromptText("Ascension");
	        	TextField edit_skill1 = new TextField(Integer.toString(ServantList.getSelectionModel().getSelectedItem().getS1lvl()));
	        	edit_skill1.setPromptText("Skill 1 level");
	        	TextField edit_skill2 = new TextField(Integer.toString(ServantList.getSelectionModel().getSelectedItem().getS2lvl()));
	        	edit_skill2.setPromptText("Skill 2 level");
	        	TextField edit_skill3 = new TextField(Integer.toString(ServantList.getSelectionModel().getSelectedItem().getS3lvl()));
	        	edit_skill3.setPromptText("Skill 3 level");

	        	grid.add(new Label("Ascension :"), 0, 0);
	        	grid.add(edit_ascension, 1, 0);
	        	grid.add(new Label("Skill 1 level :"), 0, 1);
	        	grid.add(edit_skill1, 1, 1);
	        	grid.add(new Label("Skill 2 level :"), 0, 2);
	        	grid.add(edit_skill2, 1, 2);
	        	grid.add(new Label("Skill 3 level :"), 0, 3);
	        	grid.add(edit_skill3, 1, 3);
	        	
	        	editServant.getDialogPane().setContent(grid);
	        	
	        	editServant.setResultConverter(dialogButton -> {
	        	    if (dialogButton == edit_valid) {
	        	    	if(Integer.parseInt(edit_ascension.getText())>=0&&Integer.parseInt(edit_ascension.getText())<=4&&Integer.parseInt(edit_skill1.getText())>=1&&Integer.parseInt(edit_skill1.getText())<=10&&Integer.parseInt(edit_skill2.getText())>=1&&Integer.parseInt(edit_skill2.getText())<=10&&Integer.parseInt(edit_skill3.getText())>=1&&Integer.parseInt(edit_skill3.getText())<=10) {
	        	    		data.get(ServantList.getSelectionModel().getSelectedIndex()).setAscrank(Integer.parseInt(edit_ascension.getText()));
		        	    	data.get(ServantList.getSelectionModel().getSelectedIndex()).setS1lvl(Integer.parseInt(edit_skill1.getText()));
		        	    	data.get(ServantList.getSelectionModel().getSelectedIndex()).setS2lvl(Integer.parseInt(edit_skill2.getText()));
		        	    	data.get(ServantList.getSelectionModel().getSelectedIndex()).setS3lvl(Integer.parseInt(edit_skill3.getText()));
		        	    	System.out.println(data.get(ServantList.getSelectionModel().getSelectedIndex()).getAscrank());
		        	    	errorbox.setText(voiceline.get(0));
		        	    	refresh_rsc(QP_total_cost, na_QP_total_cost);
		        	    	MashuZone.setImage(Mashu);
		        	    	ServantList.refresh();
		        	    	Ressources_Display.getColumns().get(0).setVisible(false);
		        	    	Ressources_Display.getColumns().get(0).setVisible(true);
	        	    	}
	        	    	else {
	        	    		errorbox.setText(voiceline.get(1));
	        	    		MashuZone.setImage(Mashu_error);
	        	    		ServantList.refresh();
	        	    		Ressources_Display.getColumns().get(0).setVisible(false);
		        	    	Ressources_Display.getColumns().get(0).setVisible(true);
	        	    	}
	        	    }
	        	    return null;
	        	});
	        	editServant.showAndWait();
	        });
	      //Edit Button
	        
	        
	        
	        
	        
	        
	        // Update Stock
	        TextField newstockvalue = new TextField();
	        newstockvalue.setPromptText("0");
	        newstockvalue.setMaxWidth(scrnH*0.05);
	        Button updatestockButton = new Button("Update Selected Stock");
	        updatestockButton.setOnAction((ActionEvent e4) -> {
	        	Ressources_Display.getSelectionModel().getSelectedItem().setStock(Integer.parseInt(newstockvalue.getText()));
	        	refresh_rsc(QP_total_cost,na_QP_total_cost);
	        	Ressources_Display.getColumns().get(0).setVisible(false);
    	    	Ressources_Display.getColumns().get(0).setVisible(true);
	        });
	        
	        
	        HBox updatestockbox = new HBox();
	        updatestockbox.setSpacing(5);
	        updatestockbox.setPadding(new Insets(10,0,0,10));
	        updatestockbox.setTranslateX(scrnW*0.75);
	        updatestockbox.setTranslateY(scrnH*0.505);
	        updatestockbox.getChildren().addAll(newstockvalue,updatestockButton);
	        
	        //Selected Servant
	        Button selected_status = new Button("Display for selected servant");
	        Dialog<Boolean> selected_choice = new Dialog<>();
	        ButtonType selected_choice_valid = new ButtonType("Edit", ButtonData.OK_DONE);
        	selected_choice.getDialogPane().getButtonTypes().addAll(selected_choice_valid, ButtonType.CANCEL);
	        selected_status.setOnAction((ActionEvent e) -> {
	        	GridPane grid = new GridPane();
	        	grid.setHgap(10);
	        	grid.setVgap(10);
	        	grid.setPadding(new Insets(20, 150, 10, 10));

	        	Slider s1_target= new Slider(data.get(ServantList.getSelectionModel().getSelectedIndex()).getS1lvl(),10,10);
	        	s1_target.setShowTickMarks(true);
	        	s1_target.setShowTickLabels(true);
	        	s1_target.setSnapToTicks(true);
	        	s1_target.setMajorTickUnit(1);
	        	s1_target.setBlockIncrement(1);
	        	Slider s2_target= new Slider(data.get(ServantList.getSelectionModel().getSelectedIndex()).getS2lvl(),10,10);
	        	s2_target.setShowTickMarks(true);
	        	s2_target.setShowTickLabels(true);
	        	s2_target.setSnapToTicks(true);
	        	s2_target.setMajorTickUnit(1);
	        	s2_target.setBlockIncrement(1);
	        	Slider s3_target= new Slider(data.get(ServantList.getSelectionModel().getSelectedIndex()).getS3lvl(),10,10);
	        	s3_target.setShowTickMarks(true);
	        	s3_target.setShowTickLabels(true);
	        	s3_target.setSnapToTicks(true);
	        	s3_target.setMajorTickUnit(1);
	        	s3_target.setBlockIncrement(1);
	        	Slider asc_target= new Slider(data.get(ServantList.getSelectionModel().getSelectedIndex()).getAscrank(),4,4);
	        	asc_target.setShowTickMarks(true);
	        	asc_target.setShowTickLabels(true);
	        	asc_target.setSnapToTicks(true);
	        	asc_target.setMajorTickUnit(1);
	        	asc_target.setBlockIncrement(1);
	        	
	        	CheckBox s1_target_check = new CheckBox();
	        	s1_target_check.setSelected(true);
	        	CheckBox s2_target_check = new CheckBox();
	        	s2_target_check.setSelected(true);
	        	CheckBox s3_target_check = new CheckBox();
	        	s3_target_check.setSelected(true);
	        	CheckBox asc_target_check = new CheckBox();
	        	asc_target_check.setSelected(true);

	        	grid.add(new Label("Ascension :"), 0, 0);
	        	grid.add(asc_target, 1, 0);
	        	grid.add(asc_target_check, 2, 0);
	        	grid.add(new Label("Skill 1 level :"), 0, 1);
	        	grid.add(s1_target, 1, 1);
	        	grid.add(s1_target_check, 2, 1);
	        	grid.add(new Label("Skill 2 level :"), 0, 2);
	        	grid.add(s2_target, 1, 2);
	        	grid.add(s2_target_check, 2, 2);
	        	grid.add(new Label("Skill 3 level :"), 0, 3);
	        	grid.add(s3_target, 1, 3);
	        	grid.add(s3_target_check, 2, 3);
	        	
	        	selected_choice.getDialogPane().setContent(grid);
	        	
	        	selected_choice.setResultConverter(dialogButton -> {
	        	    if (dialogButton == selected_choice_valid) {
	        	    	String status="";
	        	    	boolean emptycheck=true;
	    	        	Map<String,Integer> selected_needed_s1 = new HashMap<>();
	    	        	Map<String,Integer> selected_needed_s2 = new HashMap<>();
	    	        	Map<String,Integer> selected_needed_s3 = new HashMap<>();
	    	        	Map<String,Integer> selected_needed_asc = new HashMap<>();
	    	        	Map<String,Integer> selected_needed_tot = new HashMap<>();
	    	        	for(int ii=0;ii<data_rsc.size();ii++) {
	    	        		selected_needed_s1.put(data_rsc.get(ii).getName(), 0);
	    	        		selected_needed_s2.put(data_rsc.get(ii).getName(), 0);
	    	        		selected_needed_s3.put(data_rsc.get(ii).getName(), 0);
	    	        		selected_needed_asc.put(data_rsc.get(ii).getName(), 0);
	    	        		selected_needed_tot.put(data_rsc.get(ii).getName(), 0);
	    	        	}
	    	    			for (int j=0;j<data_rsc.size();j++) {
	    	    				for(int lvl=Math.toIntExact(Math.round(s1_target.getValue()))-2;lvl>=data.get(ServantList.getSelectionModel().getSelectedIndex()).getS1lvl()-1;lvl--) {
	    	    					for(int k=0;k<=4;k++) {
	    	    						for(int w=0;w<=4;w++) {
	    	    							if(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w)!=null) {
	    	    							if(data_rsc.get(j).getName().equals(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w))) {
	    	    								selected_needed_s1.put(data_rsc.get(j).getName(),selected_needed_s1.get(data_rsc.get(j).getName())+Integer.parseInt(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w+1)));
	    	    								selected_needed_tot.put(data_rsc.get(j).getName(),selected_needed_tot.get(data_rsc.get(j).getName())+Integer.parseInt(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w+1)));
	    	    							}
	    	    							}
	    	    						}
	    	    					}
	    	    				}
	    	    				for(int lvl=Math.toIntExact(Math.round(s2_target.getValue()))-2;lvl>=data.get(ServantList.getSelectionModel().getSelectedIndex()).getS2lvl()-1;lvl--) {
	    	    					for(int k=0;k<=4;k++) {
	    	    						for(int w=0;w<=4;w++) {
	    	    							if(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w)!=null) {
	    	    								if(data_rsc.get(j).getName().equals(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w))) {
	    	    									selected_needed_s2.put(data_rsc.get(j).getName(),selected_needed_s2.get(data_rsc.get(j).getName())+Integer.parseInt(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w+1)));
	    	    									selected_needed_tot.put(data_rsc.get(j).getName(),selected_needed_tot.get(data_rsc.get(j).getName())+Integer.parseInt(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w+1)));
	    	    								}
	    	    							}
	    	    						}
	    	    					}
	    	    				}
	    	    				for(int lvl=Math.toIntExact(Math.round(s3_target.getValue()))-2;lvl>=data.get(ServantList.getSelectionModel().getSelectedIndex()).getS3lvl()-1;lvl--) {
	    	    					for(int k=0;k<=4;k++) {
	    	    						for(int w=0;w<=4;w++) {
	    	    							if(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w)!=null) {
	    	    							if(data_rsc.get(j).getName().equals(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w))) {
	    	    								selected_needed_s3.put(data_rsc.get(j).getName(),selected_needed_s3.get(data_rsc.get(j).getName())+Integer.parseInt(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w+1)));
	    	    								selected_needed_tot.put(data_rsc.get(j).getName(),selected_needed_tot.get(data_rsc.get(j).getName())+Integer.parseInt(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w+1)));
	    	    							}
	    	    							}
	    	    						}
	    	    					}
	    	    				}
	    	    				for(int lvl=Math.toIntExact(Math.round(asc_target.getValue()))-1;lvl>=data.get(ServantList.getSelectionModel().getSelectedIndex()).getAscrank();lvl--) {
	    	    					for(int k=0;k<=4;k++) {
	    	    						for(int w=0;w<=4;w++) {
	    	    							if(data.get(ServantList.getSelectionModel().getSelectedIndex()).getAscension(lvl,k,w)!=null) {
	    	    							if(data_rsc.get(j).getName().equals(data.get(ServantList.getSelectionModel().getSelectedIndex()).getAscension(lvl, k, w))) {
	    	    								selected_needed_asc.put(data_rsc.get(j).getName(),selected_needed_asc.get(data_rsc.get(j).getName())+Integer.parseInt(data.get(ServantList.getSelectionModel().getSelectedIndex()).getAscension(lvl, k, w+1)));;
	    	    								selected_needed_tot.put(data_rsc.get(j).getName(),selected_needed_tot.get(data_rsc.get(j).getName())+Integer.parseInt(data.get(ServantList.getSelectionModel().getSelectedIndex()).getAscension(lvl, k, w+1)));
	    	    							}
	    	    							}
	    	    						}
	    	    					}
	    	    				}
	    	    			}
	    	    			int QP_S1=0,QP_S2=0,QP_S3=0,QP_asc=0,str_i=2;
	    	    			for(int ii=Math.toIntExact(Math.round(s1_target.getValue()))-2;ii>=data.get(ServantList.getSelectionModel().getSelectedIndex()).getS1lvl()-1;ii--) {
	    	    				QP_S1+=Integer.parseInt(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(ii,0,0));
	    	    			}
	    	    			for(int ii=Math.toIntExact(Math.round(s2_target.getValue()))-2;ii>=data.get(ServantList.getSelectionModel().getSelectedIndex()).getS2lvl()-1;ii--) {
	    	    				QP_S2+=Integer.parseInt(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(ii,0,0));;
	    	    			}
	    	    			for(int ii=Math.toIntExact(Math.round(s3_target.getValue()))-2;ii>=data.get(ServantList.getSelectionModel().getSelectedIndex()).getS3lvl()-1;ii--) {
	    	    				QP_S3+=Integer.parseInt(data.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(ii,0,0));
	    	    			}
	    	    			for(int ii=Math.toIntExact(Math.round(asc_target.getValue()))-1;ii>=data.get(ServantList.getSelectionModel().getSelectedIndex()).getAscrank();ii--) {
	    	    				QP_asc+=Integer.parseInt(data.get(ServantList.getSelectionModel().getSelectedIndex()).getAscension(ii,0,0));
	    	    			}
	    	    			if(s1_target_check.isSelected()||s2_target_check.isSelected()||s3_target_check.isSelected()||asc_target_check.isSelected()) {
	    	    			status+=ServantList.getSelectionModel().getSelectedItem().getName()+" needs :";
	    	    			if(s1_target_check.isSelected()) 
	    	    			{
	    	    				status+="\n- ";
	    	    			for(int j=0;j<data_rsc.size();j++) {
	    	    				if(selected_needed_s1.get(data_rsc.get(j).getName())>0) {
	    	    					emptycheck=false;
	    	    					status+=selected_needed_s1.get(data_rsc.get(j).getName())+" "+data_rsc.get(j).getName();
	    	    					if(j!=data_rsc.size()-1) status+=", ";
	    	    				}
	    	    			}
	    	    			if(!emptycheck) {
	    	    				status=status.substring(0, status.length()-2);
	    	    				String QP_S1_str = "";
	    	    				double nmb_group=Math.floor(Integer.toString(QP_S1).length()/3);
	    	    				int digitleft=(Integer.toString(QP_S1).length())%3;
	    	    				for(str_i=0;str_i<nmb_group;str_i++)
	    	    				{
	    	    					QP_S1_str = Integer.toString(QP_S1).substring(Integer.toString(QP_S1).length()-3-3*str_i,Integer.toString(QP_S1).length()-3*str_i)+" "+QP_S1_str;
	    	    				}
	    	    				QP_S1_str = Integer.toString(QP_S1).substring(0,digitleft)+" "+QP_S1_str;
	    	    				
	    	    				status+=" and "+QP_S1_str+" QP";
	    	    			}
	    	    			if(emptycheck) {
	    	    				status+="Nothing";
	    	    			}
	    	    			status+=" for the 1st skill from level "+data.get(ServantList.getSelectionModel().getSelectedIndex()).getS1lvl()+" to "+Math.toIntExact(Math.round(s1_target.getValue()))+";";
	    	    			emptycheck=true;
	    	    			}
	    	    			if(s2_target_check.isSelected())
	    	    			{
	    	    				status+="\n- ";
	    	    			for(int j=0;j<data_rsc.size();j++) {
	    	    				if(selected_needed_s2.get(data_rsc.get(j).getName())>0) {
	    	    					emptycheck=false;
	    	    					status+=selected_needed_s2.get(data_rsc.get(j).getName())+" "+data_rsc.get(j).getName();
	    	    					if(j!=data_rsc.size()-1) status+=", ";
	    	    				}
	    	    			}
	    	    			if(!emptycheck) {
	    	    				status=status.substring(0, status.length()-2);
	    	    				String QP_S2_str = "";
	    	    				double nmb_group2=Math.floor(Integer.toString(QP_S2).length()/3);
	    	    				int digitleft2=(Integer.toString(QP_S2).length())%3;
	    	    				for(str_i=0;str_i<nmb_group2;str_i++)
	    	    				{
	    	    					QP_S2_str = Integer.toString(QP_S2).substring(Integer.toString(QP_S2).length()-3-3*str_i,Integer.toString(QP_S2).length()-3*str_i)+" "+QP_S2_str;
	    	    				}
	    	    				QP_S2_str = Integer.toString(QP_S2).substring(0,digitleft2)+" "+QP_S2_str;
	    	    				
	    	    				status+=" and "+QP_S2_str+" QP";
	    	    			}
	    	    			if(emptycheck) {
	    	    				status+="Nothing";
	    	    			}
	    	    			status+=" for the 2nd skill from level "+data.get(ServantList.getSelectionModel().getSelectedIndex()).getS2lvl()+" to "+Math.toIntExact(Math.round(s2_target.getValue()))+";";
	    	    			emptycheck=true;
	    	    			}
	    	    			if(s3_target_check.isSelected())
	    	    			{
	    	    				status+="\n- ";
	    	    			for(int j=0;j<data_rsc.size();j++) {
	    	    				if(selected_needed_s3.get(data_rsc.get(j).getName())>0) {
	    	    					emptycheck=false;
	    	    					status+=selected_needed_s3.get(data_rsc.get(j).getName())+" "+data_rsc.get(j).getName();
	    	    					if(j!=data_rsc.size()-1) status+=", ";
	    	    				}
	    	    			}
	    	    			if(!emptycheck) {
	    	    				status=status.substring(0, status.length()-2);
	    	    				String QP_S3_str = "";
	    	    				double nmb_group3=Math.floor(Integer.toString(QP_S3).length()/3);
	    	    				int digitleft3=(Integer.toString(QP_S3).length())%3;
	    	    				for(str_i=0;str_i<nmb_group3;str_i++)
	    	    				{
	    	    					QP_S3_str = Integer.toString(QP_S3).substring(Integer.toString(QP_S3).length()-3-3*str_i,Integer.toString(QP_S3).length()-3*str_i)+" "+QP_S3_str;
	    	    				}
	    	    				if(digitleft3>0) QP_S3_str = Integer.toString(QP_S3).substring(0,digitleft3)+" "+QP_S3_str;
	    	    				else QP_S3_str = Integer.toString(QP_S3).substring(0,3)+" "+QP_S3_str;
	    	    				status+=" and "+QP_S3_str+" QP";
	    	    			}
	    	    			if(emptycheck) {
	    	    				status+="Nothing";
	    	    			}
	    	    			status+=" for the 3rd skill from level "+data.get(ServantList.getSelectionModel().getSelectedIndex()).getS3lvl()+" to "+Math.toIntExact(Math.round(s3_target.getValue()))+";";
	    	    			emptycheck=true;
	    	    			}
	    	    			if(asc_target_check.isSelected())
	    	    			{
	    	    				status+="\n- ";
	    	    			for(int j=0;j<data_rsc.size();j++) {
	    	    				if(selected_needed_asc.get(data_rsc.get(j).getName())>0) {
	    	    					emptycheck=false;
	    	    					status+=selected_needed_asc.get(data_rsc.get(j).getName())+" "+data_rsc.get(j).getName()+", ";
	    	    				}
	    	    			}
	    	    			if(!emptycheck) {
	    	    				status=status.substring(0, status.length()-2);
	    	    				String QP_asc_str = "";
	    	    				double nmb_groupasc=Math.floor(Integer.toString(QP_asc).length()/3);
	    	    				int digitleftasc=(Integer.toString(QP_asc).length())%3;
	    	    				for(str_i=0;str_i<nmb_groupasc;str_i++)
	    	    				{
	    	    					QP_asc_str = Integer.toString(QP_asc).substring(Integer.toString(QP_asc).length()-3-3*str_i,Integer.toString(QP_asc).length()-3*str_i)+" "+QP_asc_str;
	    	    				}
	    	    				if(digitleftasc>0) QP_asc_str = Integer.toString(QP_asc).substring(0,digitleftasc)+" "+QP_asc_str;
	    	    				else QP_asc_str = Integer.toString(QP_asc).substring(0,3)+" "+QP_asc_str;
	    	    				status+=" and "+QP_asc_str+" QP";
	    	    			}
	    	    			if(emptycheck) {
	    	    				status+="Nothing";
	    	    			}
	    	    			status+=" for Ascension from "+data.get(ServantList.getSelectionModel().getSelectedIndex()).getAscrank()+" to "+Math.toIntExact(Math.round(asc_target.getValue()))+".\n";
	    	    			emptycheck=true;
	    	    			}
	    	    			if(!((!s1_target_check.isSelected()&&!s2_target_check.isSelected()&&!s3_target_check.isSelected())&&!asc_target_check.isSelected()||(!asc_target_check.isSelected()^!s1_target_check.isSelected()^!s2_target_check.isSelected()^!s3_target_check.isSelected())))
	    	    			{
	    	    				status+="\nFor what you asked, you need a total of ";
	    	    			for(int j=0;j<data_rsc.size();j++) {
	    	    				if(selected_needed_tot.get(data_rsc.get(j).getName())>0) {
	    	    					emptycheck=false;
	    	    					status+=selected_needed_tot.get(data_rsc.get(j).getName())+" "+data_rsc.get(j).getName();
	    	    					if(j!=data_rsc.size()-1) status+=", ";
	    	    				}
	    	    			}
	    	    			if(!emptycheck) {
	    	    				status=status.substring(0, status.length()-2);
	    	    				int QP_tot = QP_S1+QP_S2+QP_S3+QP_asc;
	    	    				String QP_tot_str = "";
	    	    				double nmb_grouptot=Math.floor(Integer.toString(QP_tot).length()/3);
	    	    				int digitlefttot=(Integer.toString(QP_tot).length())%3;
	    	    				for(str_i=0;str_i<nmb_grouptot;str_i++)
	    	    				{
	    	    					QP_tot_str = Integer.toString(QP_tot).substring(Integer.toString(QP_tot).length()-3-3*str_i,Integer.toString(QP_tot).length()-3*str_i)+" "+QP_tot_str;
	    	    				}
	    	    				QP_tot_str = Integer.toString(QP_tot).substring(0,digitlefttot)+" "+QP_tot_str;
	    	    				
	    	    				status+=" and "+QP_tot_str+" QP";
	    	    			}
	    	    			if(emptycheck) {
	    	    				status+="nothing";
	    	    			}
	    	    			}
	    	    			status+=".";
	    	        	errorbox.setText(status);
	    	    			}
	        	    }
	        	    return null;
	        	});
	        	selected_choice.showAndWait();
	        });
	        
	        //Save and Load Buttons
	        VBox savenload = new VBox();
	        savenload.setSpacing(5);
	        savenload.setPadding(new Insets(10,0,0,10));
	        savenload.setTranslateY(scrnH*0.8);
	        savenload.setTranslateX(scrnW*0.8);
	        Button Save = new Button("Save");
	        Save.setOnAction((ActionEvent e) -> {
	        	try {
					Save(primaryStage);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        });
	        Button Load = new Button("Load");
	        Load.setOnAction((ActionEvent e) -> {
	        	Load(primaryStage);
	        });
	        
	        savenload.getChildren().addAll(Save,Load);
	        
	        box_add_serv.getChildren().addAll(add_serv_list,ascensionrank_vbox,skill1level_vbox,skill2level_vbox,skill3level_vbox,addButton,delButton,editButton,selected_status);
	        //NA Buttons
	        ComboBox<String> na_add_serv_list = new ComboBox<>(ServantNames_NA);
	        na_add_serv_list.getSelectionModel().selectFirst();
	        new SelectKeyComboBoxListener(na_add_serv_list);
	        HBox na_box_add_serv = new HBox();
	        na_box_add_serv.setSpacing(5);
	        na_box_add_serv.setPadding(new Insets(10,0,0,10));
	        na_box_add_serv.setTranslateY(scrnH*0.55);
	        TextField na_ascensionrank = new TextField();
	        na_ascensionrank.setPromptText("0");
	        na_ascensionrank.setMaxWidth(scrnH*0.05);
	        TextField na_skill1level = new TextField();
	        na_skill1level.setPromptText("0");
	        na_skill1level.setMaxWidth(scrnH*0.05);
	        TextField na_skill2level = new TextField();
	        na_skill2level.setPromptText("0");
	        na_skill2level.setMaxWidth(scrnH*0.05);
	        TextField na_skill3level = new TextField();
	        na_skill3level.setPromptText("0");
	        na_skill3level.setMaxWidth(scrnH*0.05);
	        Label na_ascensionrank_lbl = new Label("Ascension");
	        Label na_skill1level_lbl = new Label("Skill1");
	        Label na_skill2level_lbl = new Label("Skill2");
	        Label na_skill3level_lbl = new Label("Skill3");
	        VBox na_ascensionrank_vbox = new VBox();
	        VBox na_skill1level_vbox = new VBox();
	        VBox na_skill2level_vbox = new VBox();
	        VBox na_skill3level_vbox = new VBox();
	        na_ascensionrank_vbox.getChildren().addAll(na_ascensionrank,na_ascensionrank_lbl);
	        na_skill1level_vbox.getChildren().addAll(na_skill1level,na_skill1level_lbl);
	        na_skill2level_vbox.getChildren().addAll(na_skill2level,na_skill2level_lbl);
	        na_skill3level_vbox.getChildren().addAll(na_skill3level,na_skill3level_lbl);
	        
	        Button na_addButton = new Button("Add Servant");
	        na_addButton.setOnAction((ActionEvent e0) -> {
	        	if(Integer.parseInt(na_ascensionrank.getText())>=0&&Integer.parseInt(na_ascensionrank.getText())<=4&&Integer.parseInt(na_skill1level.getText())>=1&&Integer.parseInt(na_skill1level.getText())<=10&&Integer.parseInt(na_skill2level.getText())>=1&&Integer.parseInt(na_skill2level.getText())<=10&&Integer.parseInt(na_skill3level.getText())>=1&&Integer.parseInt(na_skill3level.getText())<=10) {
	        		data_na.add(ServantMap.get(na_add_serv_list.getValue()));
	        		data_na.get(data_na.size()-1).setAscrank(Integer.parseInt(na_ascensionrank.getText()));
	        		data_na.get(data_na.size()-1).setS1lvl(Integer.parseInt(na_skill1level.getText()));
	        		data_na.get(data_na.size()-1).setS2lvl(Integer.parseInt(na_skill2level.getText()));
	        		data_na.get(data_na.size()-1).setS3lvl(Integer.parseInt(na_skill3level.getText()));
	        		na_errorbox.setText(voiceline.get(0));
	        		MashuZone_na.setImage(Mashu);
	        	}
	        	else {
	        		na_errorbox.setText(voiceline.get(1));
	        		MashuZone_na.setImage(Mashu_error);
	        	}
	        	refresh_rsc(QP_total_cost,na_QP_total_cost);
	        	na_Ressources_Display.getColumns().get(0).setVisible(false);
    	    	na_Ressources_Display.getColumns().get(0).setVisible(true);
	        });
	        
	        Button na_delButton = new Button("Delete Servant");
	        na_delButton.setOnAction((ActionEvent e3) -> {
	        		data_na.remove(ServantList_na.getSelectionModel().getSelectedIndex());
	        		refresh_rsc(QP_total_cost,na_QP_total_cost);
	        		ServantList_na.refresh();
	        		na_Ressources_Display.getColumns().get(0).setVisible(false);
        	    	na_Ressources_Display.getColumns().get(0).setVisible(true);
	        });
	        
	        Button na_refreshButton = new Button("Refresh");
	        na_refreshButton.setOnAction((ActionEvent e1) -> {
	        	refresh_rsc(QP_total_cost,na_QP_total_cost);
	        });
	        
	        TextField na_newstockvalue = new TextField();
	        na_newstockvalue.setPromptText("0");
	        na_newstockvalue.setMaxWidth(scrnH*0.05);
	        Button na_updatestockButton = new Button("Update Selected Stock");
	        na_updatestockButton.setOnAction((ActionEvent e4) -> {
	        	na_Ressources_Display.getSelectionModel().getSelectedItem().setStock(Integer.parseInt(na_newstockvalue.getText()));
	        	refresh_rsc(QP_total_cost,na_QP_total_cost);
	        	na_Ressources_Display.getColumns().get(0).setVisible(false);
    	    	na_Ressources_Display.getColumns().get(0).setVisible(true);
	        });
	        
	        
	        
	        HBox na_updatestockbox = new HBox();
	        na_updatestockbox.setSpacing(5);
	        na_updatestockbox.setPadding(new Insets(10,0,0,10));
	        na_updatestockbox.setTranslateX(scrnW*0.75);
	        na_updatestockbox.setTranslateY(scrnH*0.505);
	        na_updatestockbox.getChildren().addAll(na_newstockvalue,na_updatestockButton);
	        
	        Button na_selected_status = new Button("Display for selected servant");
	        Dialog<Boolean> na_selected_choice = new Dialog<>();
	        ButtonType na_selected_choice_valid = new ButtonType("Ok", ButtonData.OK_DONE);
	        na_selected_choice.getDialogPane().getButtonTypes().addAll(na_selected_choice_valid, ButtonType.CANCEL);
	        na_selected_status.setOnAction((ActionEvent e4) -> {
	        	GridPane grid = new GridPane();
	        	grid.setHgap(10);
	        	grid.setVgap(10);
	        	grid.setPadding(new Insets(20, 150, 10, 10));
	        
	        	
	        	Slider s1_target= new Slider(data_na.get(ServantList_na.getSelectionModel().getSelectedIndex()).getS1lvl(),10,10);
	        	s1_target.setShowTickMarks(true);
	        	s1_target.setShowTickLabels(true);
	        	s1_target.setSnapToTicks(true);
	        	s1_target.setMajorTickUnit(1);
	        	s1_target.setBlockIncrement(1);
	        	Slider s2_target= new Slider(data_na.get(ServantList_na.getSelectionModel().getSelectedIndex()).getS2lvl(),10,10);
	        	s2_target.setShowTickMarks(true);
	        	s2_target.setShowTickLabels(true);
	        	s2_target.setSnapToTicks(true);
	        	s2_target.setMajorTickUnit(1);
	        	s2_target.setBlockIncrement(1);
	        	Slider s3_target= new Slider(data_na.get(ServantList_na.getSelectionModel().getSelectedIndex()).getS3lvl(),10,10);
	        	s3_target.setShowTickMarks(true);
	        	s3_target.setShowTickLabels(true);
	        	s3_target.setSnapToTicks(true);
	        	s3_target.setMajorTickUnit(1);
	        	s3_target.setBlockIncrement(1);
	        	Slider asc_target= new Slider(data_na.get(ServantList_na.getSelectionModel().getSelectedIndex()).getAscrank(),4,4);
	        	asc_target.setShowTickMarks(true);
	        	asc_target.setShowTickLabels(true);
	        	asc_target.setSnapToTicks(true);
	        	asc_target.setMajorTickUnit(1);
	        	asc_target.setBlockIncrement(1);
	        	
	        	CheckBox s1_target_check = new CheckBox();
	        	s1_target_check.setSelected(true);
	        	CheckBox s2_target_check = new CheckBox();
	        	s2_target_check.setSelected(true);
	        	CheckBox s3_target_check = new CheckBox();
	        	s3_target_check.setSelected(true);
	        	CheckBox asc_target_check = new CheckBox();
	        	asc_target_check.setSelected(true);

	        	grid.add(new Label("Ascension :"), 0, 0);
	        	grid.add(asc_target, 1, 0);
	        	grid.add(asc_target_check, 2, 0);
	        	grid.add(new Label("Skill 1 level :"), 0, 1);
	        	grid.add(s1_target, 1, 1);
	        	grid.add(s1_target_check, 2, 1);
	        	grid.add(new Label("Skill 2 level :"), 0, 2);
	        	grid.add(s2_target, 1, 2);
	        	grid.add(s2_target_check, 2, 2);
	        	grid.add(new Label("Skill 3 level :"), 0, 3);
	        	grid.add(s3_target, 1, 3);
	        	grid.add(s3_target_check, 2, 3);
	        	

	        	na_selected_choice.getDialogPane().setContent(grid);
	        	
	        	na_selected_choice.setResultConverter(dialogButton -> {
	        	    if (dialogButton == selected_choice_valid) {
	        	    	String na_status="";
	        	    	boolean emptycheck=true;
	    	        	Map<String,Integer> na_selected_needed_s1 = new HashMap<>();
	    	        	Map<String,Integer> na_selected_needed_s2 = new HashMap<>();
	    	        	Map<String,Integer> na_selected_needed_s3 = new HashMap<>();
	    	        	Map<String,Integer> na_selected_needed_asc = new HashMap<>();
	    	        	Map<String,Integer> na_selected_needed_tot = new HashMap<>();
	    	        	for(int ii=0;ii<data_rsc_na.size();ii++) {
	    	        		na_selected_needed_s1.put(data_rsc_na.get(ii).getName(), 0);
	    	        		na_selected_needed_s2.put(data_rsc_na.get(ii).getName(), 0);
	    	        		na_selected_needed_s3.put(data_rsc_na.get(ii).getName(), 0);
	    	        		na_selected_needed_asc.put(data_rsc_na.get(ii).getName(), 0);
	    	        		na_selected_needed_tot.put(data_rsc_na.get(ii).getName(), 0);
	    	        	}
	    	    			for (int j=0;j<data_rsc_na.size();j++) {
	    	    				for(int lvl=Math.toIntExact(Math.round(s1_target.getValue()))-2;lvl>=data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getS1lvl()-1;lvl--) {
	    	    					for(int k=0;k<=4;k++) {
	    	    						for(int w=0;w<=4;w++) {
	    	    							if(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w)!=null) {
	    	    							if(data_rsc_na.get(j).getName().equals(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w))) {
	    	    								na_selected_needed_s1.put(data_rsc_na.get(j).getName(),na_selected_needed_s1.get(data_rsc_na.get(j).getName())+Integer.parseInt(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w+1)));
	    	    								na_selected_needed_tot.put(data_rsc_na.get(j).getName(),na_selected_needed_tot.get(data_rsc_na.get(j).getName())+Integer.parseInt(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w+1)));
	    	    							}
	    	    							}
	    	    						}
	    	    					}
	    	    				}
	    	    				for(int lvl=Math.toIntExact(Math.round(s2_target.getValue()))-2;lvl>=data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getS2lvl()-1;lvl--) {
	    	    					for(int k=0;k<=4;k++) {
	    	    						for(int w=0;w<=4;w++) {
	    	    							if(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w)!=null) {
	    	    								if(data_rsc_na.get(j).getName().equals(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w))) {
	    	    									na_selected_needed_s2.put(data_rsc_na.get(j).getName(),na_selected_needed_s2.get(data_rsc_na.get(j).getName())+Integer.parseInt(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w+1)));
	    	    									na_selected_needed_tot.put(data_rsc_na.get(j).getName(),na_selected_needed_tot.get(data_rsc_na.get(j).getName())+Integer.parseInt(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w+1)));
	    	    								}
	    	    							}
	    	    						}
	    	    					}
	    	    				}
	    	    				for(int lvl=Math.toIntExact(Math.round(s3_target.getValue()))-2;lvl>=data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getS3lvl()-1;lvl--) {
	    	    					for(int k=0;k<=4;k++) {
	    	    						for(int w=0;w<=4;w++) {
	    	    							if(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w)!=null) {
	    	    							if(data_rsc_na.get(j).getName().equals(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w))) {
	    	    								na_selected_needed_s3.put(data_rsc_na.get(j).getName(),na_selected_needed_s3.get(data_rsc_na.get(j).getName())+Integer.parseInt(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w+1)));
	    	    								na_selected_needed_tot.put(data_rsc_na.get(j).getName(),na_selected_needed_tot.get(data_rsc_na.get(j).getName())+Integer.parseInt(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(lvl, k, w+1)));
	    	    							}
	    	    							}
	    	    						}
	    	    					}
	    	    				}
	    	    				for(int lvl=Math.toIntExact(Math.round(asc_target.getValue()))-1;lvl>=data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getAscrank();lvl--) {
	    	    					for(int k=0;k<=4;k++) {
	    	    						for(int w=0;w<=4;w++) {
	    	    							if(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getAscension(lvl,k,w)!=null) {
	    	    							if(data_rsc_na.get(j).getName().equals(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getAscension(lvl, k, w))) {
	    	    								na_selected_needed_asc.put(data_rsc_na.get(j).getName(),na_selected_needed_asc.get(data_rsc_na.get(j).getName())+Integer.parseInt(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getAscension(lvl, k, w+1)));;
	    	    								na_selected_needed_tot.put(data_rsc_na.get(j).getName(),na_selected_needed_tot.get(data_rsc_na.get(j).getName())+Integer.parseInt(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getAscension(lvl, k, w+1)));
	    	    							}
	    	    							}
	    	    						}
	    	    					}
	    	    				}
	    	    			}
	    	    			int QP_S1=0,QP_S2=0,QP_S3=0,QP_asc=0,str_i=2;
	    	    			for(int ii=Math.toIntExact(Math.round(s1_target.getValue()))-2;ii>=data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getS1lvl()-1;ii--) {
	    	    				QP_S1+=Integer.parseInt(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(ii,0,0));
	    	    			}
	    	    			for(int ii=Math.toIntExact(Math.round(s2_target.getValue()))-2;ii>=data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getS2lvl()-1;ii--) {
	    	    				QP_S2+=Integer.parseInt(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(ii,0,0));;
	    	    			}
	    	    			for(int ii=Math.toIntExact(Math.round(s3_target.getValue()))-2;ii>=data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getS3lvl()-1;ii--) {
	    	    				QP_S3+=Integer.parseInt(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getSkill1(ii,0,0));
	    	    			}
	    	    			for(int ii=Math.toIntExact(Math.round(asc_target.getValue()))-1;ii>=data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getAscrank();ii--) {
	    	    				QP_asc+=Integer.parseInt(data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getAscension(ii,0,0));
	    	    			}
	    	    			if(s1_target_check.isSelected()||s2_target_check.isSelected()||s3_target_check.isSelected()||asc_target_check.isSelected()) {
	    	    			na_status+=ServantList.getSelectionModel().getSelectedItem().getName()+" needs :";
	    	    			if(s1_target_check.isSelected()) 
	    	    			{
	    	    				na_status+="\n- ";
	    	    			for(int j=0;j<data_rsc_na.size();j++) {
	    	    				if(na_selected_needed_s1.get(data_rsc_na.get(j).getName())>0) {
	    	    					emptycheck=false;
	    	    					na_status+=na_selected_needed_s1.get(data_rsc_na.get(j).getName())+" "+data_rsc_na.get(j).getName();
	    	    					if(j!=data_rsc_na.size()-1) na_status+=", ";
	    	    				}
	    	    			}
	    	    			if(!emptycheck) {
	    	    				na_status=na_status.substring(0, na_status.length()-2);
	    	    				String QP_S1_str = "";
	    	    				double nmb_group=Math.floor(Integer.toString(QP_S1).length()/3);
	    	    				int digitleft=(Integer.toString(QP_S1).length())%3;
	    	    				for(str_i=0;str_i<nmb_group;str_i++)
	    	    				{
	    	    					QP_S1_str = Integer.toString(QP_S1).substring(Integer.toString(QP_S1).length()-3-3*str_i,Integer.toString(QP_S1).length()-3*str_i)+" "+QP_S1_str;
	    	    				}
	    	    				QP_S1_str = Integer.toString(QP_S1).substring(0,digitleft)+" "+QP_S1_str;
	    	    				
	    	    				na_status+=" and "+QP_S1_str+" QP";
	    	    			}
	    	    			if(emptycheck) {
	    	    				na_status+="Nothing";
	    	    			}
	    	    			na_status+=" for the 1st skill from level "+data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getS1lvl()+" to "+Math.toIntExact(Math.round(s1_target.getValue()))+";";
	    	    			emptycheck=true;
	    	    			}
	    	    			if(s2_target_check.isSelected())
	    	    			{
	    	    				na_status+="\n- ";
	    	    			for(int j=0;j<data_rsc_na.size();j++) {
	    	    				if(na_selected_needed_s2.get(data_rsc_na.get(j).getName())>0) {
	    	    					emptycheck=false;
	    	    					na_status+=na_selected_needed_s2.get(data_rsc_na.get(j).getName())+" "+data_rsc_na.get(j).getName();
	    	    					if(j!=data_rsc_na.size()-1) na_status+=", ";
	    	    				}
	    	    			}
	    	    			if(!emptycheck) {
	    	    				na_status=na_status.substring(0, na_status.length()-2);
	    	    				String QP_S2_str = "";
	    	    				double nmb_group2=Math.floor(Integer.toString(QP_S2).length()/3);
	    	    				int digitleft2=(Integer.toString(QP_S2).length())%3;
	    	    				for(str_i=0;str_i<nmb_group2;str_i++)
	    	    				{
	    	    					QP_S2_str = Integer.toString(QP_S2).substring(Integer.toString(QP_S2).length()-3-3*str_i,Integer.toString(QP_S2).length()-3*str_i)+" "+QP_S2_str;
	    	    				}
	    	    				QP_S2_str = Integer.toString(QP_S2).substring(0,digitleft2)+" "+QP_S2_str;
	    	    				
	    	    				na_status+=" and "+QP_S2_str+" QP";
	    	    			}
	    	    			if(emptycheck) {
	    	    				na_status+="Nothing";
	    	    			}
	    	    			na_status+=" for the 2nd skill from level "+data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getS2lvl()+" to "+Math.toIntExact(Math.round(s2_target.getValue()))+";";
	    	    			emptycheck=true;
	    	    			}
	    	    			if(s3_target_check.isSelected())
	    	    			{
	    	    				na_status+="\n- ";
	    	    			for(int j=0;j<data_rsc_na.size();j++) {
	    	    				if(na_selected_needed_s3.get(data_rsc_na.get(j).getName())>0) {
	    	    					emptycheck=false;
	    	    					na_status+=na_selected_needed_s3.get(data_rsc_na.get(j).getName())+" "+data_rsc_na.get(j).getName();
	    	    					if(j!=data_rsc_na.size()-1) na_status+=", ";
	    	    				}
	    	    			}
	    	    			if(!emptycheck) {
	    	    				na_status=na_status.substring(0, na_status.length()-2);
	    	    				String QP_S3_str = "";
	    	    				double nmb_group3=Math.floor(Integer.toString(QP_S3).length()/3);
	    	    				int digitleft3=(Integer.toString(QP_S3).length())%3;
	    	    				for(str_i=0;str_i<nmb_group3;str_i++)
	    	    				{
	    	    					QP_S3_str = Integer.toString(QP_S3).substring(Integer.toString(QP_S3).length()-3-3*str_i,Integer.toString(QP_S3).length()-3*str_i)+" "+QP_S3_str;
	    	    				}
	    	    				if(digitleft3>0) QP_S3_str = Integer.toString(QP_S3).substring(0,digitleft3)+" "+QP_S3_str;
	    	    				else QP_S3_str = Integer.toString(QP_S3).substring(0,3)+" "+QP_S3_str;
	    	    				na_status+=" and "+QP_S3_str+" QP";
	    	    			}
	    	    			if(emptycheck) {
	    	    				na_status+="Nothing";
	    	    			}
	    	    			na_status+=" for the 3rd skill from level "+data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getS3lvl()+" to "+Math.toIntExact(Math.round(s3_target.getValue()))+";";
	    	    			emptycheck=true;
	    	    			}
	    	    			if(asc_target_check.isSelected())
	    	    			{
	    	    				na_status+="\n- ";
	    	    			for(int j=0;j<data_rsc_na.size();j++) {
	    	    				if(na_selected_needed_asc.get(data_rsc_na.get(j).getName())>0) {
	    	    					emptycheck=false;
	    	    					na_status+=na_selected_needed_asc.get(data_rsc_na.get(j).getName())+" "+data_rsc_na.get(j).getName()+", ";
	    	    				}
	    	    			}
	    	    			if(!emptycheck) {
	    	    				na_status=na_status.substring(0, na_status.length()-2);
	    	    				String QP_asc_str = "";
	    	    				double nmb_groupasc=Math.floor(Integer.toString(QP_asc).length()/3);
	    	    				int digitleftasc=(Integer.toString(QP_asc).length())%3;
	    	    				for(str_i=0;str_i<nmb_groupasc;str_i++)
	    	    				{
	    	    					QP_asc_str = Integer.toString(QP_asc).substring(Integer.toString(QP_asc).length()-3-3*str_i,Integer.toString(QP_asc).length()-3*str_i)+" "+QP_asc_str;
	    	    				}
	    	    				if(digitleftasc>0) QP_asc_str = Integer.toString(QP_asc).substring(0,digitleftasc)+" "+QP_asc_str;
	    	    				else QP_asc_str = Integer.toString(QP_asc).substring(0,3)+" "+QP_asc_str;
	    	    				na_status+=" and "+QP_asc_str+" QP";
	    	    			}
	    	    			if(emptycheck) {
	    	    				na_status+="Nothing";
	    	    			}
	    	    			na_status+=" for Ascension from "+data_na.get(ServantList.getSelectionModel().getSelectedIndex()).getAscrank()+" to "+Math.toIntExact(Math.round(asc_target.getValue()))+".\n";
	    	    			emptycheck=true;
	    	    			}
	    	    			if(!((!s1_target_check.isSelected()&&!s2_target_check.isSelected()&&!s3_target_check.isSelected())&&!asc_target_check.isSelected()||(!asc_target_check.isSelected()^!s1_target_check.isSelected()^!s2_target_check.isSelected()^!s3_target_check.isSelected())))
	    	    			{
	    	    				na_status+="\nFor what you asked, you need a total of ";
	    	    			for(int j=0;j<data_rsc_na.size();j++) {
	    	    				if(na_selected_needed_tot.get(data_rsc_na.get(j).getName())>0) {
	    	    					emptycheck=false;
	    	    					na_status+=na_selected_needed_tot.get(data_rsc_na.get(j).getName())+" "+data_rsc_na.get(j).getName();
	    	    					if(j!=data_rsc_na.size()-1) na_status+=", ";
	    	    				}
	    	    			}
	    	    			if(!emptycheck) {
	    	    				na_status=na_status.substring(0, na_status.length()-2);
	    	    				int QP_tot = QP_S1+QP_S2+QP_S3+QP_asc;
	    	    				String QP_tot_str = "";
	    	    				double nmb_grouptot=Math.floor(Integer.toString(QP_tot).length()/3);
	    	    				int digitlefttot=(Integer.toString(QP_tot).length())%3;
	    	    				for(str_i=0;str_i<nmb_grouptot;str_i++)
	    	    				{
	    	    					QP_tot_str = Integer.toString(QP_tot).substring(Integer.toString(QP_tot).length()-3-3*str_i,Integer.toString(QP_tot).length()-3*str_i)+" "+QP_tot_str;
	    	    				}
	    	    				QP_tot_str = Integer.toString(QP_tot).substring(0,digitlefttot)+" "+QP_tot_str;
	    	    				
	    	    				na_status+=" and "+QP_tot_str+" QP";
	    	    			}
	    	    			if(emptycheck) {
	    	    				na_status+="nothing";
	    	    			}
	    	    			}
	    	    			na_status+=".";
	    	        	na_errorbox.setText(na_status);
	    	    			}
	        	    }
	        	    return null;
	        	});
	        	na_selected_choice.showAndWait();
	        });
	        
	        //NA - Dialog Box for Servant Edition
	        Dialog<Boolean> editServant_na = new Dialog<>();
	        Button na_editButton = new Button("Edit Servant");
	        ButtonType na_edit_valid = new ButtonType("Edit", ButtonData.OK_DONE);
        	editServant_na.getDialogPane().getButtonTypes().addAll(na_edit_valid, ButtonType.CANCEL);
	        na_editButton.setOnAction((ActionEvent e) -> {
	        	GridPane grid = new GridPane();
	        	grid.setHgap(10);
	        	grid.setVgap(10);
	        	grid.setPadding(new Insets(20, 150, 10, 10));

	        	TextField na_edit_ascension = new TextField();
	        	na_edit_ascension.setPromptText("Ascension");
	        	TextField na_edit_skill1 = new TextField();
	        	na_edit_skill1.setPromptText("Skill 1 level");
	        	TextField na_edit_skill2 = new TextField();
	        	na_edit_skill2.setPromptText("Skill 2 level");
	        	TextField na_edit_skill3 = new TextField();
	        	na_edit_skill3.setPromptText("Skill 3 level");

	        	grid.add(new Label("Ascension :"), 0, 0);
	        	grid.add(na_edit_ascension, 1, 0);
	        	grid.add(new Label("Skill 1 level :"), 0, 1);
	        	grid.add(na_edit_skill1, 1, 1);
	        	grid.add(new Label("Skill 2 level :"), 0, 2);
	        	grid.add(na_edit_skill2, 1, 2);
	        	grid.add(new Label("Skill 3 level :"), 0, 3);
	        	grid.add(na_edit_skill3, 1, 3);
	        	
	        	editServant_na.getDialogPane().setContent(grid);
	        	
	        	editServant_na.setResultConverter(dialogButton -> {
	        	    if (dialogButton == na_edit_valid) {
	        	    	if(Integer.parseInt(na_edit_ascension.getText())>=0&&Integer.parseInt(na_edit_ascension.getText())<=4&&Integer.parseInt(na_edit_skill1.getText())>=1&&Integer.parseInt(na_edit_skill1.getText())<=10&&Integer.parseInt(na_edit_skill2.getText())>=1&&Integer.parseInt(na_edit_skill2.getText())<=10&&Integer.parseInt(na_edit_skill3.getText())>=1&&Integer.parseInt(na_edit_skill3.getText())<=10) {
	        	    		data_na.get(ServantList_na.getSelectionModel().getSelectedIndex()).setAscrank(Integer.parseInt(na_edit_ascension.getText()));
		        	    	data_na.get(ServantList_na.getSelectionModel().getSelectedIndex()).setS1lvl(Integer.parseInt(na_edit_skill1.getText()));
		        	    	data_na.get(ServantList_na.getSelectionModel().getSelectedIndex()).setS2lvl(Integer.parseInt(na_edit_skill2.getText()));
		        	    	data_na.get(ServantList_na.getSelectionModel().getSelectedIndex()).setS3lvl(Integer.parseInt(na_edit_skill3.getText()));
		        	    	na_errorbox.setText(voiceline.get(0));
		        	    	MashuZone_na.setImage(Mashu);
		        	    	refresh_rsc(QP_total_cost, na_QP_total_cost);
		        	    	ServantList_na.refresh();
		        	    	na_Ressources_Display.getColumns().get(0).setVisible(false);
		        	    	na_Ressources_Display.getColumns().get(0).setVisible(true);
	        	    	}
	        	    	else {
	        	    		na_errorbox.setText(voiceline.get(1));
	        	    		MashuZone_na.setImage(Mashu_error);
	        	    		ServantList_na.refresh();
	        	    		na_Ressources_Display.getColumns().get(0).setVisible(false);
		        	    	na_Ressources_Display.getColumns().get(0).setVisible(true);
	        	    	}
	        	    }
	        	    return null;
	        	});
	        	editServant_na.showAndWait();
	        });
	        
	        na_box_add_serv.getChildren().addAll(na_add_serv_list,na_ascensionrank_vbox,na_skill1level_vbox,na_skill2level_vbox,na_skill3level_vbox,na_addButton,na_delButton,na_editButton,na_selected_status);
	      
	        
	        
	        //NA - Save and Load Buttons
	        VBox na_savenload = new VBox();
	        na_savenload.setSpacing(5);
	        na_savenload.setPadding(new Insets(10,0,0,10));
	        na_savenload.setTranslateY(scrnH*0.8);
	        na_savenload.setTranslateX(scrnW*0.8);
	        Button na_Save = new Button("Save");
	        na_Save.setOnAction((ActionEvent e) -> {
	        	try {
					Save(primaryStage);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        });
	        Button na_Load = new Button("Load");
	        na_Load.setOnAction((ActionEvent e) -> {
	        	Load(primaryStage);
	        });
	        na_savenload.getChildren().addAll(na_Save,na_Load);
	        // XP Calculator Panel
	        List<Integer> xp_step = new ArrayList<>();
	        int xp_needed=0;
	        for(int ii=0;ii<100;ii++) {
	        	if(ii<90) xp_needed=xp_needed+100*ii;
	        	else {
	        		switch(ii) {
	        		case(90): {xp_step.add(418500);break;}
	        		case(91): {xp_step.add(454900);break;}
	        		case(92): {xp_step.add(510000);break;}
	        		case(93): {xp_step.add(584500);break;}
	        		case(94): {xp_step.add(678500);break;}
	        		case(95): {xp_step.add(792500);break;}
	        		case(96): {xp_step.add(926900);break;}
	        		case(97): {xp_step.add(1082100);break;}
	        		case(98): {xp_step.add(1258500);break;}
	        		case(99): {xp_step.add(1456500);break;}
	        		}
	        	}
	        	xp_step.add(xp_needed);
	        }
	        TextField actual_xplvl = new TextField();
	        Label actual_xplvl_lbl = new Label("Actual Value :");
	        TextField target_xplvl = new TextField();
	        Label target_xplvl_lbl = new Label("Target Value :");
	        TextField totalxpneeded = new TextField();
	        Label totalxpneeded_lbl = new Label("Total XP Needed :");
	        totalxpneeded.setEditable(false);
	        TextField fourstar_needed_spe = new TextField();
	        fourstar_needed_spe.setEditable(false);
	        Label fourstar_needed_spe_lbl = new Label("4* XP (Class Bonus)");
	        TextField fourstar_needed_any = new TextField();
	        fourstar_needed_any.setEditable(false);
	        Label fourstar_needed_any_lbl = new Label("4* XP (Normal Exp)");
	        ObservableList<String> xporlvllist = FXCollections.observableArrayList();
	        xporlvllist.add("XP");
	        xporlvllist.add("Level");
	        ComboBox<String> xporlvl = new ComboBox<>(xporlvllist);
	        
	        xporlvl.getSelectionModel().selectFirst();
	        Button xporlvl_calc = new Button("Calculate");
	        Slider speorany = new Slider(0,0,100);
	        speorany.setShowTickMarks(false);
	        speorany.setShowTickLabels(false);
	        speorany.setSnapToTicks(true);
	        speorany.setMajorTickUnit(1);
	        speorany.setBlockIncrement(1);
	        speorany.setMinorTickCount(1);
	        speorany.setTranslateX(scrnW*0.05);
	        xporlvl_calc.setOnAction((ActionEvent e0) -> {
	            	int xp_needed_total=0,isdivisiblenormal=0,isdivisiblespe=0;
	            	if(xporlvl.getSelectionModel().getSelectedItem().equals("XP")) {
	            		xp_needed_total=Integer.parseInt(target_xplvl.getText())-Integer.parseInt(actual_xplvl.getText());
	            		if(xp_needed_total%32400==0) {
	            			isdivisiblespe=1;
	            		}
	            		if(xp_needed_total%27000==0) {
	            			isdivisiblenormal=1;
	            		}
	            		fourstar_needed_spe.setText(Integer.toString(Math.toIntExact(Math.round(Math.ceil(xp_needed_total/32400)))+1-isdivisiblespe));
	            		fourstar_needed_any.setText(Integer.toString(Math.toIntExact(Math.round(Math.ceil(xp_needed_total/27000)))+1-isdivisiblenormal));
	            	}
	            	else {
	            		for(int ii=Integer.parseInt(actual_xplvl.getText())-1;ii<=Integer.parseInt(target_xplvl.getText())-1;ii++) {
	            			xp_needed_total+=xp_step.get(ii);
	            		}
	            	if(xp_needed_total%32400==0) {
            			isdivisiblespe=1;
            		}
            		if(xp_needed_total%27000==0) {
            			isdivisiblenormal=1;
            		}
            		fourstar_needed_spe.setText(Integer.toString(Math.toIntExact(Math.round(Math.ceil(xp_needed_total/32400)))+1-isdivisiblespe));
            		fourstar_needed_any.setText(Integer.toString(Math.toIntExact(Math.round(Math.ceil(xp_needed_total/27000)))+1-isdivisiblenormal));
	            	}
	            	totalxpneeded.setText(Integer.toString(xp_needed_total));
	            	speorany.setMax(Double.parseDouble(fourstar_needed_spe.getText()));
	            });
	        VBox xpvbox1 = new VBox();
	        xpvbox1.setMaxSize(scrnW*0.15,scrnH*0.05);
	        xpvbox1.getChildren().addAll(actual_xplvl_lbl,actual_xplvl);
	        VBox xpvbox2 = new VBox();
	        xpvbox2.setMaxSize(scrnW*0.15,scrnH*0.05);
	        xpvbox2.getChildren().addAll(target_xplvl_lbl,target_xplvl);
	        VBox xpvbox3 = new VBox();
	        xpvbox3.setMaxSize(scrnW*0.15,scrnH*0.05);
	        xpvbox3.getChildren().addAll(fourstar_needed_spe_lbl,fourstar_needed_spe);
	        VBox xpvbox4 = new VBox();
	        xpvbox4.setMaxSize(scrnW*0.15,scrnH*0.05);
	        xpvbox4.getChildren().addAll(fourstar_needed_any_lbl,fourstar_needed_any);
	        VBox xpvbox5 = new VBox();
	        xpvbox5.setMaxSize(scrnW*0.15,scrnH*0.05);
	        xpvbox5.getChildren().addAll(totalxpneeded_lbl,totalxpneeded);
	        HBox xp_calc_box = new HBox();
	        xp_calc_box.setTranslateX(scrnW*0.01);
	        xp_calc_box.setTranslateY(scrnH*0.3);
	        xp_calc_box.getChildren().addAll(xpvbox1,xpvbox2,xpvbox5,xpvbox3,xpvbox4);
	        HBox xporlvl_box = new HBox();
	        xporlvl_box.setTranslateX(scrnW*0.01);
	        xporlvl_box.setTranslateY(scrnH*0.12);
	        xporlvl.setTranslateY(scrnH*0.12);
	        xporlvl_calc.setTranslateY(scrnH*0.12);
	        xporlvl_box.getChildren().addAll(xporlvl,xporlvl_calc);
	        TextField speorany_text_spe = new TextField();
	        Label speorany_text_spe_lbl = new Label("4-star need (Class Bonus) :");
	        TextField speorany_text_any = new TextField();
	        Label speorany_text_any_lbl = new Label("4-star need (Normal XP) :");
	        HBox speorany_spe_box = new HBox();
	        HBox speorany_any_box = new HBox();
	        speorany_text_spe.textProperty().addListener((observable, oldValue, newValue) -> {
	            if(Double.parseDouble(newValue)<=speorany.getMax()&&Double.parseDouble(newValue)>=speorany.getMin()) {
	            	speorany.setValue(Double.parseDouble(speorany_text_spe.getText()));
	            	errorbox.setText(voiceline.get(0));
	        		MashuZone.setImage(Mashu);
	            }
	            else {
	            	xp_errorbox.setText(voiceline.get(3));
	        		MashuZone_xp.setImage(Mashu_error);
	            }
	        });
	        actual_xplvl.textProperty().addListener((observable, oldValue, newValue) -> {
	        	if(xporlvl.getValue().equals("XP")) {
	        		if(!(Integer.parseInt(newValue)>=0&&Integer.parseInt(newValue)<=xp_step.get(99))) {
	        		xp_errorbox.setText(voiceline.get(4));
	        		MashuZone_xp.setImage(Mashu_error);
	        	}
	        	else {
	        		errorbox.setText(voiceline.get(0));
	        		MashuZone.setImage(Mashu);
	        	}
	        	}
	        	if(xporlvl.getValue().equals("Level")) {
	        		if(!(Integer.parseInt(newValue)>=0&&Integer.parseInt(newValue)<=100)) {
	        		xp_errorbox.setText(voiceline.get(5));
	        		MashuZone_xp.setImage(Mashu_sad);
	        	}
	        	else {
	        		errorbox.setText(voiceline.get(0));
	        		MashuZone.setImage(Mashu);
	        	}
	        	}
	        	
	        });
	        target_xplvl.textProperty().addListener((observable, oldValue, newValue) -> {
	        	if(xporlvl.getValue().equals("XP")) {
	        		if(!(Integer.parseInt(newValue)>=0&&Integer.parseInt(newValue)<=xp_step.get(99)&&Integer.parseInt(newValue)>Integer.parseInt(actual_xplvl.getText()))) {
	        		xp_errorbox.setText(voiceline.get(4));
	        		MashuZone_xp.setImage(Mashu_error);
	        	}
	        	else {
	        		xp_errorbox.setText(voiceline.get(0));
	        		MashuZone_xp.setImage(Mashu);
	        	}
	        	}
	        	if(xporlvl.getValue().equals("Level")) {
	        		if(!(Integer.parseInt(newValue)>=0&&Integer.parseInt(newValue)<=100&&Integer.parseInt(newValue)>Integer.parseInt(actual_xplvl.getText()))) {
	        		xp_errorbox.setText(voiceline.get(5));
	        		MashuZone_xp.setImage(Mashu_error);
	        	}
	        	else {
	        		xp_errorbox.setText(voiceline.get(0));
	        		MashuZone_xp.setImage(Mashu);
	        	}
	        	}
	        	if(!(Integer.parseInt(newValue)>=Integer.parseInt(actual_xplvl.getText()))) {
	        		xp_errorbox.setText(voiceline.get(6));
	        		MashuZone_xp.setImage(Mashu_error);
	        	}
	        	
	        });
	        speorany.valueProperty().addListener(new ChangeListener<Number>() {
	            public void changed(ObservableValue<? extends Number> ov,
	                Number old_val, Number new_val) {
	            	speorany.setValue(Math.round(speorany.getValue()));
	                    speorany_text_spe.setText(Double.toString(Integer.parseInt(fourstar_needed_spe.getText())*speorany.getValue()/speorany.getMax()));
	                    speorany_text_any.setText(Double.toString(Math.ceil(Integer.parseInt(fourstar_needed_any.getText())*(speorany.getMax()-speorany.getValue())/speorany.getMax())));
	            }
	        });
	        speorany_spe_box.getChildren().addAll(speorany_text_spe_lbl,speorany_text_spe);
	        speorany_any_box.getChildren().addAll(speorany_text_any_lbl,speorany_text_any);
	        VBox xpsliderbox = new VBox();
	        xpsliderbox.setTranslateY(scrnH*0.5);
	        xpsliderbox.getChildren().addAll(speorany,speorany_spe_box,speorany_any_box);
	        
	        
	        
	        //News & Twitter & Reddit Display & Daily+MasterMission display
	        WebView JP_News = new WebView();
	        WebEngine webEngine = JP_News.getEngine();
	        webEngine.load("http://news.fate-go.jp/");
	        WebView NA_News = new WebView();
	        WebEngine na_webEngine = NA_News.getEngine();
	        na_webEngine.load("http://fate-go.us/news/");
	        WebView JP_twitter = new WebView();
	        JP_twitter.getEngine().load("https://twitter.com/fgoproject");
	        WebView NA_twitter = new WebView();
	        NA_twitter.getEngine().load("https://twitter.com/fatego_usa");
	        WebView Redditview = new WebView();
	        Redditview.getEngine().load("https://www.reddit.com/r/grandorder/");
	        WebView DQMM_view = new WebView();
	        DQMM_view.getEngine().load("http://fategrandorder.wikia.com/wiki/Current_Master_Missions");
	        JP_News.setMaxSize(scrnW*0.495, scrnH*0.9);
	        NA_News.setMaxSize(scrnW*0.495, scrnH*0.9);
	        JP_News.setMinHeight(scrnH*0.9);
	        NA_News.setMinHeight(scrnH*0.9);
	        JP_twitter.setMaxSize(scrnW*0.495, scrnH*0.9);
	        NA_twitter.setMaxSize(scrnW*0.495, scrnH*0.9);
	        JP_twitter.setMinHeight(scrnH*0.9);
	        NA_twitter.setMinHeight(scrnH*0.9);
	        Redditview.setMinSize(scrnW*0.99, scrnH*0.9);
	        Redditview.setMaxSize(scrnW*0.99, scrnH*0.9);
	        DQMM_view.setMinSize(scrnW*0.99, scrnH*0.6);
	        DQMM_view.setMaxSize(scrnW*0.99, scrnH*0.6);
	        TextArea Dailies_XP_desc = new TextArea();
	        Dailies_XP_desc.setText("XP Quests :\n\nLancer & Assassin : Monday and Thursday\n\nSaber & Rider : Tuesday and Friday\n\nArcher & Caster : Wednesday & Saturday\n\nEvery Class : Sunday");
	        TextArea Dailies_mat_desc = new TextArea();
	        Dailies_mat_desc.setText("Materials Quests :\n\nArcher : Monday\nLancer : Tuesday\nBerserker : Wednesday\nThursday : Rider\nFriday : Caster\nSaturday : Assassin\nSaber : Sunday");
	        TextArea Today_matnxp = new TextArea();
	        ZonedDateTime jp_time = ZonedDateTime.now(ZoneId.of( "Asia/Tokyo" ));
	        //ZonedDateTime na_time = ZonedDateTime.now(ZoneId.of( "Asia/Tokyo" ));
	        String todaycontent = "Today's Quests are :\n\n";
	        switch(jp_time.getDayOfWeek().getValue()) {
	        case(1): {todaycontent+="XP : Lancer & Assassin\nMaterials : Archer";break;}
	        case(2): {todaycontent+="XP : Saber & Rider\nMaterials : Lancer";break;}
	        case(3): {todaycontent+="XP : Archer & Caster\nMaterials : Berserker";break;}
	        case(4): {todaycontent+="XP : Lancer & Assassin\nMaterials : Rider";break;}
	        case(5): {todaycontent+="XP : Saber & Rider\nMaterials : Caster";break;}
	        case(6): {todaycontent+="XP : Archer & Caster\nMaterials : Assassin";break;}
	        case(7): {todaycontent+="XP : Any classes\nMaterials : Saber";break;}
	        }
	        Today_matnxp.setText(todaycontent);
	        HBox Daily_infobox = new HBox();
	        Daily_infobox.getChildren().addAll(Dailies_XP_desc,Dailies_mat_desc,Today_matnxp);
	        HBox Bloc_News = new HBox();
	        Bloc_News.setTranslateX(scrnW*0.09);
	        Bloc_News.getChildren().addAll(JP_News,NA_News);
	        HBox Bloc_Twitter = new HBox();
	        Bloc_Twitter.setTranslateX(scrnW*0.09);
	        Bloc_Twitter.getChildren().addAll(JP_twitter,NA_twitter);
	        HBox Bloc_Reddit = new HBox();
	        Bloc_Reddit.setTranslateX(scrnW*0.09);
	        Bloc_Reddit.getChildren().addAll(Redditview);
	        VBox Bloc_DQMM = new VBox();
	        Bloc_DQMM.setTranslateX(scrnW*0.09);
	        Bloc_DQMM.getChildren().addAll(DQMM_view,Daily_infobox);

	        //Usefull Links tab
	        TextArea Usefull_links = new TextArea();
	        Usefull_links.setEditable(false);
	        Usefull_links.setMinSize(scrnW, scrnH);
	        Usefull_links.setMaxSize(scrnW, scrnH);
	        Usefull_links.setWrapText(true);
	        Usefull_links.setText("Cirnopedia : http://fate-go.cirnopedia.org/index.php\n\nWikia Grand Order : http://fategrandorder.wikia.com/wiki/Fate/Grand_Order_Wikia\n\nDiscord Grand Order Wikia : https://discord.gg/BytCQUB\n\nFacebook FGO English Community : https://www.facebook.com/profile.php?id=387608084756280\n\nReddit Grand Order : https://www.reddit.com/r/grandorder/\n\nDiscord Reddit Grand Order : https://discord.gg/grandorder\n\n");
	        
	        tabpane.getSelectionModel().selectedItemProperty().addListener(
	        	    new ChangeListener<Tab>() {
	        	        @Override
	        	        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
	        	            if(tabpane.getSelectionModel().selectedItemProperty().get().getText().equals("News")) {
	        	            	JP_News.getEngine().reload();
	        	            	NA_News.getEngine().reload();
	        	            }
	        	            if(tabpane.getSelectionModel().selectedItemProperty().get().getText().equals("Twitter")) {
	        	            	JP_twitter.getEngine().reload();
	        	            	NA_twitter.getEngine().reload();
	        	            }
	        	            if(tabpane.getSelectionModel().selectedItemProperty().get().getText().equals("/r/grandorder")) {
	        	            	Redditview.getEngine().reload();
	        	            }
	        	        }
	        	    }
	        	);
	        
	        
	        // Players Database tab
	        VBox savenloadBDD = new VBox();
	        savenloadBDD.setSpacing(5);
	        savenloadBDD.setPadding(new Insets(10,0,0,10));
	        Button SaveBDD = new Button("Save your list to Database");
	        SaveBDD.setOnAction((ActionEvent e) -> {
	        	try {
					SaveBDD(primaryStage,players_errorbox,MashuZone_players,voiceline.get(7),Mashu_error);
					
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (NumberFormatException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (InstantiationException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IllegalAccessException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        });
	        TableView<Servant> ListOfPlayer = new TableView<>();
	        TableColumn<Servant,String> BDDnameCol = new TableColumn<>("Name");
	        BDDnameCol.setMinWidth(scrnW*0.45*0.45);
	        BDDnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
	        TableColumn<Servant,Integer> BDDascCol = new TableColumn<>("Ascension");
	        BDDascCol.setMaxWidth(scrnW*0.45*0.1);
	        BDDascCol.setCellValueFactory(new PropertyValueFactory<>("ascrank"));
	        TableColumn<Servant,Integer> BDDlvlCol = new TableColumn<>("Level");
	        BDDlvlCol.setMaxWidth(scrnW*0.45*0.1);
	        BDDlvlCol.setCellValueFactory(new PropertyValueFactory<>("lvl"));
	        TableColumn<Servant,Integer> BDDskill1Col = new TableColumn<>("Skill 1");
	        BDDskill1Col.setMaxWidth(scrnW*0.45*0.1);
	        BDDskill1Col.setCellValueFactory(new PropertyValueFactory<>("s1lvl"));
	        TableColumn<Servant,Integer> BDDskill2Col = new TableColumn<>("Skill 2");
	        BDDskill2Col.setMaxWidth(scrnW*0.45*0.1);
	        BDDskill2Col.setCellValueFactory(new PropertyValueFactory<>("s2lvl"));
	        TableColumn<Servant,Integer> BDDskill3Col = new TableColumn<>("Skill 3");
	        BDDskill3Col.setMaxWidth(scrnW*0.45*0.1);
	        BDDskill3Col.setCellValueFactory(new PropertyValueFactory<>("s3lvl"));
	        TableColumn<Servant,Integer> BDDnplvlCol = new TableColumn<>("NP level");
	        BDDnplvlCol.setMaxWidth(scrnW*0.45*0.1);
	        BDDnplvlCol.setCellValueFactory(new PropertyValueFactory<>("np"));
	        ObservableList<Servant> data_ListOfPlayer = FXCollections.observableArrayList();
	        ListOfPlayer.setItems(data_ListOfPlayer);
	        ListOfPlayer.getColumns().addAll(BDDnameCol,BDDascCol,BDDlvlCol,BDDskill1Col,BDDskill2Col,BDDskill3Col,BDDnplvlCol);
	        ListOfPlayer.setTranslateX(scrnW*0.02);
	        ListOfPlayer.setTranslateY(scrnH*0.1);
	        
	        Button LoadBDD = new Button("Search by ID");
	        LoadBDD.setOnAction((ActionEvent e) -> {
	        	try {
					LoadBDD(primaryStage,QP_total_cost,na_QP_total_cost,data_ListOfPlayer);
					ListOfPlayer.refresh();
				} catch (NumberFormatException | InstantiationException | IllegalAccessException
						| ClassNotFoundException | IOException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        });
	        savenloadBDD.getChildren().addAll(SaveBDD,LoadBDD);
	        
	        vbox_rdisp.getChildren().addAll(label2,Ressources_Display,QPtotbox);
	        vbox.getChildren().addAll(label, ServantList,updatestockbox);
	        na_vbox.getChildren().addAll(na_label, ServantList_na, na_updatestockbox);
	        na_vbox_rdisp.getChildren().addAll(na_label2,na_Ressources_Display,na_QPtotbox);
	        News_element.getChildren().addAll(Bloc_News);
	        JP_element.getChildren().addAll(vbox,vbox_rdisp,box_add_serv,updatestockbox,errorzone,savenload);
	        NA_element.getChildren().addAll(na_vbox,na_vbox_rdisp,na_box_add_serv,na_updatestockbox,na_errorzone,na_savenload);
	        XP_element.getChildren().addAll(xporlvl_box,xp_calc_box,xpsliderbox,xp_errorzone);
	        Twitter_element.getChildren().addAll(Bloc_Twitter);
	        Reddit_element.getChildren().addAll(Bloc_Reddit);
	        Links_element.getChildren().addAll(Usefull_links);
	        DQMM_element.getChildren().addAll(Bloc_DQMM);
	        Players_element.getChildren().addAll(ListOfPlayer,savenloadBDD,players_errorzone);
	        ((Group) scene.getRoot()).getChildren().add(tabpane);
	        JP_tab.setContent(JP_element);
	        NA_tab.setContent(NA_element);
	        xp_calc_tab.setContent(XP_element);
	        News_tab.setContent(News_element);
	        Twitter_tab.setContent(Twitter_element);
	        Links_tab.setContent(Links_element);
	        Reddit_tab.setContent(Reddit_element);
	        DQMM_tab.setContent(DQMM_element);
	        Players_tab.setContent(Players_element);
	        JP_tab.setClosable(false);
	        NA_tab.setClosable(false);
	        xp_calc_tab.setClosable(false);
	        News_tab.setClosable(false);
	        Links_tab.setClosable(false);
	        Reddit_tab.setClosable(false);
	        DQMM_tab.setClosable(false);
	        Players_tab.setClosable(false);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void go() {
		launch();
	}
	
	public void refresh_rsc(TextArea QP_total_cost,TextArea na_QP_total_cost) {
		for(int i=0;i<data_rsc.size();i++) {
			data_rsc.get(i).setNeeded(0);
			data_rsc.get(i).setLack(0);
			data_rsc_na.get(i).setNeeded(0);
			data_rsc_na.get(i).setLack(0);
		}
		for(int i=0;i<data.size();i++) {
			for (int j=0;j<data_rsc.size();j++) {
				for(int lvl=8;lvl>=data.get(i).getS1lvl()-1;lvl--) {
					for(int k=0;k<=4;k++) {
						for(int w=0;w<=4;w++) {
							if(data.get(i).getSkill1(lvl, k, w)!=null) {
								/*System.out.println("i="+i+"|j="+j+"|lvl="+lvl+"|k="+k);
								System.out.println(data_rsc.get(j).getName());
								System.out.println(data.get(i).getSkill1(lvl, k, w));*/
							if(data_rsc.get(j).getName().equals(data.get(i).getSkill1(lvl, k, w))) {
								data_rsc.get(j).setNeeded(data_rsc.get(j).getNeeded()+Integer.parseInt(data.get(i).getSkill1(lvl, k, w+1)));
							}
							}
						}
					}
				}
				for(int lvl=8;lvl>=data.get(i).getS2lvl()-1;lvl--) {
					for(int k=0;k<=4;k++) {
						for(int w=0;w<=4;w++) {
							if(data.get(i).getSkill1(lvl, k, w)!=null) {
								/*System.out.println("i="+i+"|j="+j+"|lvl="+lvl+"|k="+k);
								System.out.println(data_rsc.get(j).getName());
								System.out.println(data.get(i).getSkill1(lvl, k, w));*/
							if(data_rsc.get(j).getName().equals(data.get(i).getSkill1(lvl, k, w))) data_rsc.get(j).setNeeded(data_rsc.get(j).getNeeded()+Integer.parseInt(data.get(i).getSkill1(lvl, k, w+1)));
							}
						}
					}
				}
				for(int lvl=8;lvl>=data.get(i).getS3lvl()-1;lvl--) {
					for(int k=0;k<=4;k++) {
						for(int w=0;w<=4;w++) {
							if(data.get(i).getSkill1(lvl, k, w)!=null) {
								/*System.out.println("i="+i+"|j="+j+"|lvl="+lvl+"|k="+k);
								System.out.println(data_rsc.get(j).getName());
								System.out.println(data.get(i).getSkill1(lvl, k, w));*/
							if(data_rsc.get(j).getName().equals(data.get(i).getSkill1(lvl, k, w))) data_rsc.get(j).setNeeded(data_rsc.get(j).getNeeded()+Integer.parseInt(data.get(i).getSkill1(lvl, k, w+1)));
							}
						}
					}
				}
				for(int lvl=3;lvl>=data.get(i).getAscrank();lvl--) {
					for(int k=0;k<=4;k++) {
						for(int w=0;w<=4;w++) {
							if(data.get(i).getAscension(lvl,k,w)!=null) {
								/*System.out.println("i="+i+"|j="+j+"|lvl="+lvl+"|k="+k);
								System.out.println(data_rsc.get(j).getName());
								System.out.println(data.get(i).getSkill1(lvl, k, w));*/
							if(data_rsc.get(j).getName().equals(data.get(i).getAscension(lvl, k, w))) {
								data_rsc.get(j).setNeeded(data_rsc.get(j).getNeeded()+Integer.parseInt(data.get(i).getAscension(lvl, k, w+1)));
							}
							}
						}
					}
				}
			}
		}
		for(int i=0;i<data_na.size();i++) {
			for (int j=0;j<data_rsc_na.size();j++) {
				for(int lvl=8;lvl>=data_na.get(i).getS1lvl()-1;lvl--) {
					for(int k=0;k<=4;k++) {
						for(int w=0;w<=4;w++) {
							if(data_na.get(i).getSkill1(lvl, k, w)!=null) {
								/*System.out.println("i="+i+"|j="+j+"|lvl="+lvl+"|k="+k);
								System.out.println(data_rsc.get(j).getName());
								System.out.println(data.get(i).getSkill1(lvl, k, w));*/
							if(data_rsc_na.get(j).getName().equals(data_na.get(i).getSkill1(lvl, k, w))) {
								data_rsc_na.get(j).setNeeded(data_rsc_na.get(j).getNeeded()+Integer.parseInt(data_na.get(i).getSkill1(lvl, k, w+1)));
							}
							}
						}
					}
				}
				for(int lvl=8;lvl>=data_na.get(i).getS2lvl()-1;lvl--) {
					for(int k=0;k<=4;k++) {
						for(int w=0;w<=4;w++) {
							if(data_na.get(i).getSkill1(lvl, k, w)!=null) {
								/*System.out.println("i="+i+"|j="+j+"|lvl="+lvl+"|k="+k);
								System.out.println(data_rsc.get(j).getName());
								System.out.println(data.get(i).getSkill1(lvl, k, w));*/
							if(data_rsc_na.get(j).getName().equals(data_na.get(i).getSkill1(lvl, k, w))) data_rsc_na.get(j).setNeeded(data_rsc_na.get(j).getNeeded()+Integer.parseInt(data_na.get(i).getSkill1(lvl, k, w+1)));
							}
						}
					}
				}
				for(int lvl=8;lvl>=data_na.get(i).getS3lvl()-1;lvl--) {
					for(int k=0;k<=4;k++) {
						for(int w=0;w<=4;w++) {
							if(data_na.get(i).getSkill1(lvl, k, w)!=null) {
								/*System.out.println("i="+i+"|j="+j+"|lvl="+lvl+"|k="+k);
								System.out.println(data_rsc.get(j).getName());
								System.out.println(data.get(i).getSkill1(lvl, k, w));*/
							if(data_rsc_na.get(j).getName().equals(data_na.get(i).getSkill1(lvl, k, w))) data_rsc_na.get(j).setNeeded(data_rsc_na.get(j).getNeeded()+Integer.parseInt(data_na.get(i).getSkill1(lvl, k, w+1)));
							}
						}
					}
				}
				for(int lvl=3;lvl>=data_na.get(i).getAscrank();lvl--) {
					for(int k=0;k<=4;k++) {
						for(int w=0;w<=4;w++) {
							if(data_na.get(i).getAscension(lvl,k,w)!=null) {
								/*System.out.println("i="+i+"|j="+j+"|lvl="+lvl+"|k="+k);
								System.out.println(data_rsc.get(j).getName());
								System.out.println(data.get(i).getSkill1(lvl, k, w));*/
							if(data_rsc_na.get(j).getName().equals(data_na.get(i).getAscension(lvl, k, w))) {
								data_rsc_na.get(j).setNeeded(data_rsc_na.get(j).getNeeded()+Integer.parseInt(data_na.get(i).getAscension(lvl, k, w+1)));
							}
							}
						}
					}
				}
			}
		}
		long QPtot=0;
		for(int i=0;i<data.size();i++) {
		for(int ii=8;ii>=data.get(i).getS1lvl()-1;ii--) {
			QPtot+=Long.parseLong(data.get(i).getSkill1(ii,0,0));
		}
		for(int ii=8;ii>=data.get(i).getS2lvl()-1;ii--) {
			QPtot+=Long.parseLong(data.get(i).getSkill1(ii,0,0));
		}
		for(int ii=8;ii>=data.get(i).getS3lvl()-1;ii--) {
			QPtot+=Long.parseLong(data.get(i).getSkill1(ii,0,0));
		}
		for(int ii=3;ii>=data.get(i).getAscrank();ii--) {
			QPtot+=Long.parseLong(data.get(i).getAscension(ii,0,0));
		}
		}
		String QP_str = "";
		double nmb_group=Math.floor(Long.toString(QPtot).length()/3);
		int digitleft=(Long.toString(QPtot).length())%3;
		for(int str_i=0;str_i<nmb_group;str_i++)
		{
			QP_str = Long.toString(QPtot).substring(Long.toString(QPtot).length()-3-3*str_i,Long.toString(QPtot).length()-3*str_i)+" "+QP_str;
		}
		if(digitleft>0) QP_str = Long.toString(QPtot).substring(0,digitleft)+" "+QP_str;
		QP_total_cost.setText(QP_str);
		long na_QPtot=0;
		for(int i=0;i<data_na.size();i++) {
		for(int ii=8;ii>=data_na.get(i).getS1lvl()-1;ii--) {
			na_QPtot+=Long.parseLong(data_na.get(i).getSkill1(ii,0,0));
		}
		for(int ii=8;ii>=data_na.get(i).getS2lvl()-1;ii--) {
			na_QPtot+=Long.parseLong(data_na.get(i).getSkill1(ii,0,0));
		}
		for(int ii=8;ii>=data_na.get(i).getS3lvl()-1;ii--) {
			na_QPtot+=Long.parseLong(data_na.get(i).getSkill1(ii,0,0));
		}
		for(int ii=3;ii>=data_na.get(i).getAscrank();ii--) {
			na_QPtot+=Long.parseLong(data_na.get(i).getAscension(ii,0,0));
		}
		}
		String na_QP_str = "";
		double na_nmb_group=Math.floor(Long.toString(na_QPtot).length()/3);
		int na_digitleft=(Long.toString(na_QPtot).length())%3;
		for(int str_i=0;str_i<na_nmb_group;str_i++)
		{
			na_QP_str = Long.toString(na_QPtot).substring(Long.toString(na_QPtot).length()-3-3*str_i,Long.toString(na_QPtot).length()-3*str_i)+" "+na_QP_str;
		}
		if(na_digitleft>0) na_QP_str = Long.toString(na_QPtot).substring(0,na_digitleft)+" "+na_QP_str;
		na_QP_total_cost.setText(na_QP_str);
		for(int i=0;i<data_rsc.size();i++) {
			if(data_rsc.get(i).getNeeded()-data_rsc.get(i).getStock()>=0) data_rsc.get(i).setLack(data_rsc.get(i).getNeeded()-data_rsc.get(i).getStock());
			else data_rsc.get(i).setLack(0);
			if(data_rsc_na.get(i).getNeeded()-data_rsc_na.get(i).getStock()>=0) data_rsc_na.get(i).setLack(data_rsc_na.get(i).getNeeded()-data_rsc_na.get(i).getStock());
			else data_rsc_na.get(i).setLack(0);
		}
	}
	
	public void Save(Stage stage) throws FileNotFoundException, IOException {
			
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Data");
        fileChooser.setInitialDirectory(new File("").getAbsoluteFile());
        SaveState s = new SaveState(data,data_rsc,data_na,data_rsc_na);
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
            	ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream (file));
    		    oos.writeObject (s);
    		    oos.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
		
	}
	
	public void SaveBDD(Stage stage,TextArea box,ImageView zone,String error_message,Image error_face) throws FileNotFoundException, IOException, NumberFormatException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Dialog<Boolean> dialogbox = new Dialog<>();
        ButtonType dialogbox_valid = new ButtonType("Send", ButtonData.OK_DONE);
        dialogbox.getDialogPane().getButtonTypes().addAll(dialogbox_valid, ButtonType.CANCEL);
        TextField ID_field = new TextField("");
        PasswordField pass_field = new PasswordField();
        GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(20, 150, 10, 10));
    	dialogbox.getDialogPane().setContent(grid);
    	grid.add(new Label("Player ID :"), 0, 0);
    	grid.add(ID_field, 1, 0);
    	grid.add(new Label("Password"), 0, 1);
    	grid.add(pass_field, 1, 1);
    	dialogbox.setResultConverter(dialogButton -> {
    		if(dialogButton == dialogbox_valid) {
    			if(ID_field.getText().length()==9) {
    				FileChooser fileChooser = new FileChooser();
    		        fileChooser.setTitle("Choose file");
    		        fileChooser.setInitialDirectory(new File("").getAbsoluteFile());
    		        File file = fileChooser.showOpenDialog(stage);
    		        if (file != null) {
    		            try {
    		            	BDDActions tobdd_actions = new BDDActions();
    		            	ObjectInputStream oos = new ObjectInputStream (new FileInputStream (file));
    		    		    try {
								tobdd_actions.SendBD(Integer.parseInt(ID_field.getText()),file,pass_field.getText());
							} catch (NumberFormatException | InstantiationException | IllegalAccessException
									| ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    		    		    oos.close();
    		    		    
    		            } catch (IOException ex) {
    		                System.out.println(ex.getMessage());
    		            }
    		        }
    			
    			}
    			else {
    				box.setText(error_message);
					zone.setImage(error_face);
    			}
    		}
    		return null;
    	});
    	dialogbox.showAndWait();
		
	}
	
	public void Load(Stage stage) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Data");
        fileChooser.setInitialDirectory(new File("").getAbsoluteFile());
        File file = fileChooser.showOpenDialog(stage);
		try
		{
		    ObjectInputStream ois = new ObjectInputStream (new FileInputStream (file));
		    SaveState s = (SaveState) ois.readObject();
		    
		 
		    data.clear();
		    data.addAll(s.getData());
		    data_rsc.clear();
		    data_rsc.addAll(s.getData_rsc());
		    data_na.clear();
		    data_na.addAll(s.getData_na());
		    data_rsc_na.clear();
		    data_rsc_na.addAll(s.getData_rsc_na());
		    ois.close();
		}
		catch (ClassNotFoundException exception)
		{
		    System.out.println ("Impossible de lire l'objet : " + exception.getMessage());
		}
		catch (IOException exception)
		{
		    System.out.println ("Erreur lors de l'écriture : " + exception.getMessage());
		}
	}
	
	public void LoadBDD(Stage stage,TextArea QPtc,TextArea na_QPtc,ObservableList<Servant> display) throws FileNotFoundException, IOException, NumberFormatException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Dialog<Boolean> dialogbox = new Dialog<>();
        ButtonType dialogbox_valid = new ButtonType("Load", ButtonData.OK_DONE);
        dialogbox.getDialogPane().getButtonTypes().addAll(dialogbox_valid, ButtonType.CANCEL);
        TextField ID_field = new TextField("");
        GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(20, 150, 10, 10));
    	dialogbox.getDialogPane().setContent(grid);
    	grid.add(new Label("Player ID :"), 0, 0);
    	grid.add(ID_field, 1, 0);
    	dialogbox.showAndWait();
        
        BDDActions tobdd_actions = new BDDActions();
    	ObservableList<Servant> importlist = tobdd_actions.GetDB(Integer.parseInt(ID_field.getText()));
    	
    	
    	display.addAll(importlist);
    	refresh_rsc(QPtc, na_QPtc);
           
        
		
	}
}
