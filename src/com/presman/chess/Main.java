package com.presman.chess;

import com.presman.chess.ChessBoardHandler.GameController;
import com.presman.chess.Communication.Client;
import com.presman.chess.Communication.Server;
import com.presman.chess.Misc.ConfigReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class Main extends Application {
    public static HashMap<String, String> configProperties = new HashMap<>();
    public static Server server;
    public static Client client;
    public static boolean singlePlayer = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        ConfigReader.getConfigDetails();
        determineGameMode();

        //init chessboard drawing
        Pane canvas = new Pane();


        GameController gameController = new GameController(canvas);

        //Need to parse through config file
        //If host set up ServerSocket, if not host set up Socket to connect to ServerSocket



        //setting the init scene - can not resize the stage
        Scene scene = new Scene(canvas, 600, 600);
        primaryStage.setTitle("Chess");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        //make it run based on config file
        //setup ServerSocket


        //Event-driven responses
        scene.setOnMouseClicked(event -> {
            gameController.handleEvent(event);
        });
    }


    public void determineGameMode() {
        if (configProperties.get("SINGLE_PLAYER").equals("true")) {
            singlePlayer = true;
            return;
        } else if (configProperties.get("HOST?").equals("true")) {
                Main.server = new Server();
            } else {
                Main.client = new Client();
            }
        }
    }

