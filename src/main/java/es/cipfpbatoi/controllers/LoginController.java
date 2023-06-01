package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.UserAlreadyExistsException;
import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.respositories.*;
import es.cipfpbatoi.utils.AlertCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController implements Initializable {
    @FXML
    private ImageView logoImageView;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;

    private UserRepository userRepository;
    private ProduccionRepository produccionRepository;
    private RankingRepository rankingRepository;
    private ValoracionRepository valoracionRepository;
    private GeneroRepository generoRepository;

    public LoginController(UserRepository userRepository, ProduccionRepository produccionRepository, GeneroRepository generoRepository, ValoracionRepository valoracionRepository,RankingRepository rankingRepository) {
        this.userRepository = userRepository;
        this.produccionRepository= produccionRepository;
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
        this.generoRepository= generoRepository;
    }
    public LoginController(UserRepository userRepository, ProduccionRepository produccionRepository, GeneroRepository generoRepository) {
        this.userRepository = userRepository;
        this.produccionRepository= produccionRepository;
        this.generoRepository= generoRepository;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            logoImageView.setImage(new Image(getPathImage("/images/LogoBatoiCineTop.png")));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void loginUser(ActionEvent event) throws IOException {
        try {
            if (validUser()){
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                MainController mainController= new MainController(produccionRepository, valoracionRepository, rankingRepository, generoRepository, userRepository.getUser(nameTextField.getText(), passwordTextField.getText()));
                ChangeScene.change(stage, mainController, "/views/main.fxml");
            } else {
                if (errorTextFields().length()>0){
                    AlertCreator.errorAlert(String.valueOf(errorTextFields()));
                } else {
                    throw new UserNotExistException();
                }
            }
        } catch (UserNotExistException e) {
            AlertCreator.errorAlert(e.getMessage());
        }
    }
    @FXML
    private void signUpUser(){
        try {
            if (validUser()){
                throw new UserAlreadyExistsException();
            } else {
                if (!nameTextField.getText().equals("") && !passwordTextField.getText().equals("")){
                    String hashedPassword = BCrypt.hashpw(passwordTextField.getText(), BCrypt.gensalt());

                    userRepository.save(new User(userRepository.getLastCod(), nameTextField.getText(),hashedPassword));
                    AlertCreator.infoAlert("Registrado correctamente.");
                    this.passwordTextField.clear();
                    this.nameTextField.clear();
                } else {
                    AlertCreator.errorAlert(errorTextFields());
                }
            }
        } catch (UserAlreadyExistsException e) {
            AlertCreator.errorAlert(e.getMessage());
        }
    }
    private boolean validUser() {
        return userRepository.validUser(nameTextField.getText(), passwordTextField.getText());
    }

    private String errorTextFields(){
        StringBuilder error= new StringBuilder();
        if (nameTextField.getText().equals("")){
            error.append("Debes introducir el nombre. ");
        }
        if (passwordTextField.getText().equals("")){
            error.append("Debes introducir la contraseña. ");
        }
        return String.valueOf(error);
    }


    private String getPathImage(String fileName) throws URISyntaxException {
        return getClass().getResource(fileName).toURI().toString();
    }
}
