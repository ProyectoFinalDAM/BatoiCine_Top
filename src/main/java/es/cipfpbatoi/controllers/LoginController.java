package es.cipfpbatoi.controllers;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.UserAlreadyExistsException;
import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.respositories.*;
import es.cipfpbatoi.utils.AlertCreator;
import es.cipfpbatoi.utils.PasswordEncryptor;
import es.cipfpbatoi.utils.Validator;
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
    private VisualizarRepository visualizarRepository;
    private EsFavoritaRepository esFavoritaRepository;

    public LoginController(UserRepository userRepository, ProduccionRepository produccionRepository, GeneroRepository generoRepository, ValoracionRepository valoracionRepository,RankingRepository rankingRepository, VisualizarRepository visualizarRepository,  EsFavoritaRepository esFavoritaRepository) {
        this.userRepository = userRepository;
        this.produccionRepository= produccionRepository;
        this.valoracionRepository = valoracionRepository;
        this.rankingRepository = rankingRepository;
        this.generoRepository= generoRepository;
        this.visualizarRepository= visualizarRepository;
        this.esFavoritaRepository=esFavoritaRepository;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            logoImageView.setImage(new Image(getPathImage("/images/LogoBatoiCineTop.png")));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Obtiene los datos de los textField y los valida para logear al usuario, este pasa a la vista MainController con el user logeado
     * @author Marcos Sanz
     * @param event clic
     */
    @FXML
    private void loginUser(ActionEvent event) throws IOException {
        try {
            userRepository.getUser(nameTextField.getText(), passwordTextField.getText());
            if (validUser()){
                MainController mainController= new MainController(produccionRepository, valoracionRepository, rankingRepository, generoRepository,userRepository.getUser(nameTextField.getText(), passwordTextField.getText()), visualizarRepository, esFavoritaRepository);
                ChangeScene.change(event, mainController, "/views/main.fxml");
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
    /**
     * Obtiene los datos de los textField y los valida para logear al usuario, este registra el nuevo usuario si es válido, si no lanza una alerta
     * @author Marcos Sanz
     * @param event clic
     */
    @FXML
    private void signUpUser(ActionEvent event){
        try {
            if (validUser()){
                throw new UserAlreadyExistsException();
            } else {
                if (!nameTextField.getText().equals("") && !passwordTextField.getText().equals("")){
                    if (passwordTextField.getText().matches( Validator.PASSWORD_REGEXP )){
                        String encryptedPassword = PasswordEncryptor.encryptPassword(passwordTextField.getText());
                        userRepository.save(new User(userRepository.getLastCod(), nameTextField.getText(), encryptedPassword));
                        AlertCreator.infoAlert("Registrado correctamente.");
                        MainController mainController= new MainController(produccionRepository, valoracionRepository, rankingRepository, generoRepository,userRepository.getUser(nameTextField.getText(), passwordTextField.getText()), visualizarRepository, esFavoritaRepository);
                        ChangeScene.change(event, mainController, "/views/main.fxml");
                    } else {
                        AlertCreator.errorAlert("Contraseña debe contener:\n" +
                                "- Carácter especial\n" +
                                "- Mayúsculas y minúsculas\n" +
                                "- De 5 a 20 carácteres");
                    }

                } else {
                    AlertCreator.errorAlert(errorTextFields());
                }
            }
        } catch (UserAlreadyExistsException e) {
            AlertCreator.errorAlert(e.getMessage());
        } catch (IOException | UserNotExistException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Valida las credenciales insertadas por el usuario
     * @return booleano si el usuario existe en la base de datos
     * @author Marcos Sanz
     */
    private boolean validUser() {
        return userRepository.validUser(nameTextField.getText(), passwordTextField.getText());
    }
    /**
     * Genera una alerta con los erres al logearse. Este puede ser por no introducir el nombre o la contraseña.
     * @author Marcos Sanz
     * @return String del error
     */
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
