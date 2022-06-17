package com.example.tp_java;

import com.example.tp_java.Cases.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.*;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Plateau implements Initializable, Serializable {
    @FXML
    private ImageView imageL;
    @FXML
    private ImageView imageR;
    @FXML
    private GridPane myGrid;
    @FXML
    private Label myText;
    @FXML
    private Label score;
    @FXML
    private Button rollButton;
    @FXML
    private Button exitbutton;
    @FXML
    private Label currentposition;
    @FXML
    private Label playernametext;
    public static Case tabCase[] = new Case[100];
    private De des[] = new De[2];
    private Joueur joueur;
    public static Button[] tabButtons = new Button[100];
    private int cpt = 0;
    private int cptBonus = 0;
    private int cptMalus = 0;
    private int cptSaut = 0;
    private int cptDef = 0;
    private int cptImage = 0;
    public static int pos = 0;
    public static ImageView impostor = new ImageView();
    public static boolean caseIsClicked = true;
    private int[] numTab;

    public static int oldPos = 0;

    {
        numTab = new int[98];
    }

    private static int a, b;
    public static boolean reponse;
    public static boolean sautage = false;

    public void initialize(URL location, ResourceBundle resources) {
        if (!MenuPrincipaleController.loaded) {
            if (NewGameMenuController.name.length() > 0) {
                joueur = MenuPrincipaleController.jeu.getLastJoueur();
            } else {
                joueur = MenuPrincipaleController.jeu.findJoueur(NewGameMenuController.existingname);
            }
            remplire();
        } else {
            readNumTab();
            remplireload();
            pos = joueur.getPosition();
        }
        playernametext.setText("Nom de joueur :\n" + joueur.getNom().toUpperCase());
        colorer();
        numerote();
        des[0] = new De();
        des[1] = new De();
        if (MenuPrincipaleController.loaded) {
            GridPane.setConstraints(impostor, GridPane.getColumnIndex(tabButtons[pos]), GridPane.getRowIndex(tabButtons[pos]));
            score.setText(Integer.toString(joueur.getScore()));
            currentposition.setText("Position actuelle : " + (pos + 1));
        }
    }

    public void remplire() {
        impostor.setImage(new Image(getClass().getResourceAsStream("impostor.png")));
        impostor.setFitHeight(40);
        impostor.setFitWidth(40);
        impostor.setSmooth(false);
        Button button;
        Random rand = new Random();
        int n, i;
        int maxC = 13;
        int maxL = 12;
        int minC = 0;
        int minL = 2;
        int column = 2;
        int ligne = 0;
        Case a;
        button = new Button();
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(0.0);
        button.setMinWidth(0.0);
        CaseDepart caseDepart = new CaseDepart(cpt, button);
        tabCase[cpt] = caseDepart;
        tabButtons[cpt] = button;
        myGrid.add(tabButtons[cpt], column, ligne);
        cpt++;
        column++;
        int cptParcours = 0;
        i = 0;
        n = rand.nextInt(6);
        while (cpt < 99) {
            while (column <= maxC) {
                if ((cptParcours = cptBonus + cptMalus + cptImage + cptDef + cptSaut) != 25) {
                    if ((25 - cptParcours) == (99 - cpt)) {
                        a = randomCase();
                        while (a == null) {
                            a = randomCase();
                        }
                    } else {
                        if (i < n) {
                            parcourir();
                            i++;
                        } else {
                            i = 0;
                            n = rand.nextInt(6);
                            a = randomCase();
                            while (a == null) {
                                a = randomCase();
                            }
                        }
                    }
                } else {
                    parcourir();
                }
                myGrid.add(tabButtons[cpt], column, ligne);
                column++;
                cpt++;
            }
            column--;
            ligne++;
            maxC -= 2;

            while (ligne <= maxL) {
                if ((cptParcours = cptBonus + cptMalus + cptImage + cptDef + cptSaut) != 25) {
                    if ((25 - cptParcours) == (99 - cpt)) {
                        a = randomCase();
                        while (a == null) {
                            a = randomCase();
                        }
                    } else {
                        if (i < n) {
                            parcourir();
                            i++;
                        } else {
                            i = 0;
                            n = rand.nextInt(6);
                            a = randomCase();
                            while (a == null) {
                                a = randomCase();
                            }
                        }
                    }
                } else {
                    parcourir();
                }
                myGrid.add(tabButtons[cpt], column, ligne);
                ligne++;
                cpt++;
            }

            ligne--;
            column--;
            maxL -= 2;

            while (column >= minC) {
                if ((cptParcours = cptBonus + cptMalus + cptImage + cptDef + cptSaut) != 25) {
                    if ((25 - cptParcours) == (99 - cpt)) {
                        a = randomCase();
                        while (a == null) {
                            a = randomCase();
                        }
                    } else {
                        if (i < n) {
                            parcourir();
                            i++;
                        } else {
                            i = 0;
                            n = rand.nextInt(6);
                            a = randomCase();
                            while (a == null) {
                                a = randomCase();
                            }
                        }
                    }
                } else {
                    parcourir();
                }
                myGrid.add(tabButtons[cpt], column, ligne);
                column--;
                cpt++;
            }

            column++;
            ligne--;
            minC += 2;

            while (ligne >= minL) {
                if ((cptParcours = cptBonus + cptMalus + cptImage + cptDef + cptSaut) != 25) {
                    if ((25 - cptParcours) == (99 - cpt)) {
                        a = randomCase();
                        while (a == null) {
                            a = randomCase();
                        }
                    } else {
                        if (i < n) {
                            parcourir();
                            i++;
                        } else {
                            i = 0;
                            n = rand.nextInt(6);
                            a = randomCase();
                            while (a == null) {
                                a = randomCase();
                            }
                        }
                    }
                } else {
                    parcourir();
                }
                myGrid.add(tabButtons[cpt], column, ligne);
                ligne--;
                cpt++;
            }
            ligne++;
            column++;
            minL += 2;

        }
        button = new Button();
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(0.0);
        button.setMinWidth(0.0);
        myGrid.add(button, column, ligne);
        CaseFin caseFin = new CaseFin(cpt, button);
        tabCase[cpt] = caseFin;
        tabButtons[cpt] = button;
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                pos = 99;
                tabCase[pos].changerScore(score);
                tabCase[pos].deplacer(joueur);
                joueur.setScore(Integer.parseInt(score.getText()));
                joueur.beatScore();
                currentposition.setText("Position actuelle : " + (pos + 1));
                myText.setTextFill(Color.GREEN);
                myText.setText("Vous avez cliqué sur la bonne case !\n JEU FINI !");
                caseIsClicked = true;
            }
        });
        tabButtons[11].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[23].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[36].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[46].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[57].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[65].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[74].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[80].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[87].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[91].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[96].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[98].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
    }

    public Case randomCase() {
        int n;
        Button button = new Button();
        Random random = new Random();
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(0.0);
        button.setMinWidth(0.0);
        tabButtons[cpt] = button;
        if (!MenuPrincipaleController.loaded) {
            n = random.nextInt(5) + 1;
            numTab[cpt - 1] = n;
        } else n = numTab[cpt - 1];
        if (n == 4) button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (!caseIsClicked) {
                    for (int i = 0; i < 100; i++) {
                        if (event.getTarget() == tabButtons[i]) {
                            pos = i;
                            break;
                        }
                    }
                    GridPane.setConstraints(impostor, GridPane.getColumnIndex(Plateau.tabButtons[pos]), GridPane.getRowIndex(Plateau.tabButtons[pos]));
                    myText.setTextFill(Color.GREEN);
                    myText.setText("Vous avez cliqué sur la bonne case !");
                    caseIsClicked = true;
                    if (!sautage) {
                        currentposition.setText("Position actuelle : " + (pos + 1));
                        ((CaseDef) tabCase[pos]).deplacerNormal(joueur);
                        open();
                        tabCase[pos].changerScore(score);
                        tabCase[pos].deplacer(joueur);
                        joueur.setScore(Integer.parseInt(score.getText()));
                        if (pos == 99) {
                            joueur.beatScore();
                        }
                    } else sautage = false;
                    currentposition.setText("Position actuelle : " + (pos + 1));
                }
            }
        });
        else if (n == 3) button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (!caseIsClicked) {
                    for (int i = 0; i < 100; i++) {
                        if (event.getTarget() == tabButtons[i]) {
                            pos = i;
                            break;
                        }
                    }
                    if (!sautage) {
                        currentposition.setText("Position actuelle : " + (pos + 1));
                        tabCase[pos].changerScore(score);
                        tabCase[pos].deplacer(joueur);
                    } else {
                        GridPane.setConstraints(Plateau.impostor, GridPane.getColumnIndex(Plateau.tabButtons[Plateau.pos]), GridPane.getRowIndex(Plateau.tabButtons[Plateau.pos]));
                        caseIsClicked = true;
                        sautage = false;
                        currentposition.setText("Position actuelle : " + (pos + 1));
                    }
                    myText.setTextFill(Color.GREEN);
                    myText.setText("Vous avez cliqué sur la bonne case !");
                }
            }
        });
        else if (n == 5) button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (!caseIsClicked) {
                    for (int i = 0; i < 100; i++) {
                        if (event.getTarget() == tabButtons[i]) {
                            pos = i;
                            break;
                        }
                    }
                    GridPane.setConstraints(impostor, GridPane.getColumnIndex(Plateau.tabButtons[pos]), GridPane.getRowIndex(Plateau.tabButtons[pos]));
                    myText.setTextFill(Color.GREEN);
                    myText.setText("Vous avez cliqué sur la bonne case !");
                    caseIsClicked = true;
                    if (!sautage) {
                        currentposition.setText("Position actuelle : " + (pos + 1));
                        ((CaseImage) tabCase[pos]).deplacerNormal(joueur);
                        openimg();
                        tabCase[pos].changerScore(score);
                        tabCase[pos].deplacer(joueur);
                        joueur.setScore(Integer.parseInt(score.getText()));
                        if (pos == 99) {
                            joueur.beatScore();
                        }
                    } else sautage = false;
                    currentposition.setText("Position actuelle : " + (pos + 1));


                }
            }
        });
        else button.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    if (!caseIsClicked) {
                        for (int i = 0; i < 100; i++) {
                            if (event.getTarget() == tabButtons[i]) {
                                pos = i;
                                break;
                            }
                        }
                        if (!sautage) {
                            tabCase[pos].changerScore(score);
                            tabCase[pos].deplacer(joueur);
                            joueur.setScore(Integer.parseInt(score.getText()));
                            if (pos == 99) {
                                joueur.beatScore();
                            }
                        } else {
                            GridPane.setConstraints(impostor, GridPane.getColumnIndex(Plateau.tabButtons[pos]), GridPane.getRowIndex(Plateau.tabButtons[pos]));
                            sautage = false;
                        }
                        currentposition.setText("Position actuelle : " + (pos + 1));

                        myText.setTextFill(Color.GREEN);
                        myText.setText("Vous avez cliqué sur la bonne case !");
                        caseIsClicked = true;

                    }
                }
            });
        if (n == 1) {
            if (cptBonus < 5) {
                CaseBonus caseBonus = new CaseBonus(cpt, button);
                tabCase[cpt] = caseBonus;
                cptBonus++;
                return caseBonus;
            }
        } else if (n == 2) {
            if (cptMalus < 5) {
                CaseMalus caseMalus = new CaseMalus(cpt, button);
                tabCase[cpt] = caseMalus;
                cptMalus++;
                return caseMalus;
            }

        } else if (n == 3) {
            if (cptSaut < 5) {
                CaseSaut caseSaut = new CaseSaut(cpt, button);
                tabCase[cpt] = caseSaut;
                cptSaut++;
                return caseSaut;
            }
        } else if (n == 4) {
            if (cptDef < 5) {
                CaseDef caseDef = new CaseDef(cpt, button);
                tabCase[cpt] = caseDef;
                cptDef++;
                return caseDef;
            }
        } else if (n == 5) {
            if (cptImage < 5) {
                CaseImage caseImage = new CaseImage(cpt, button);
                tabCase[cpt] = caseImage;
                cptImage++;
                return caseImage;
            }
        }
        return null;
    }

    public void parcourir() {
        Button button = new Button();
        Random random = new Random();
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(0.0);
        button.setMinWidth(0.0);
        if (!MenuPrincipaleController.loaded) {
            numTab[cpt - 1] = 0;
        }
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (!caseIsClicked) {
                    if (event.getTarget() == tabButtons[pos]) {
                        if (sautage) {
                            sautage = false;
                            GridPane.setConstraints(Plateau.impostor, GridPane.getColumnIndex(Plateau.tabButtons[Plateau.pos]), GridPane.getRowIndex(Plateau.tabButtons[Plateau.pos]));
                        } else {
                            tabCase[pos].deplacer(joueur);
                        }
                        currentposition.setText("Position actuelle : " + (pos + 1));
                        caseIsClicked = true;
                        myText.setTextFill(Color.GREEN);
                        myText.setText("Vous avez cliqué sur la bonne case !");
                    } else {
                        myText.setTextFill(Color.RED);
                        myText.setText("Vous avez cliqué sur la mauvaise case. il faut cliquez sur la case : " + (pos + 1));
                    }
                }

            }
        });
        CaseParcours caseParcours = new CaseParcours(cpt, button);
        tabCase[cpt] = caseParcours;
        tabButtons[cpt] = button;
    }

    public void colorer() {
        for (int i = 0; i < 100; i++) {
            if (tabCase[i].getColor().equals(Color.GREEN)) {
                tabCase[i].getButton().setStyle("-fx-background-color: #00ff37;-fx-border-color:black;-fx-border-width: 3px ; -fx-border-radius: 20px; -fx-background-radius: 20px ;-fx-background-insets: 0");
            } else if (tabCase[i].getColor().equals(Color.BLUE)) {
                tabCase[i].getButton().setStyle("-fx-background-color: #079bf0;-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
            } else if (tabCase[i].getColor().equals(Color.YELLOW)) {
                tabCase[i].getButton().setStyle("-fx-background-color: #FFFF00;-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
            } else if (tabCase[i].getColor().equals(Color.PINK)) {
                tabCase[i].getButton().setStyle("-fx-background-color: #FF69B4;-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
            } else if (tabCase[i].getColor().equals(Color.RED)) {
                tabCase[i].getButton().setStyle("-fx-background-color: #ff2f2b;-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
            } else if (tabCase[i].getColor().equals(Color.GRAY)) {
                tabCase[i].getButton().setStyle("-fx-background-color: #808080;-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
            } else if (tabCase[i].getColor().equals(Color.ORANGE)) {
                tabCase[i].getButton().setStyle("-fx-background-color: #FFA500;-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
            } else if (tabCase[i].getColor().equals(Color.BLACK)) {
                tabCase[i].getButton().setStyle("-fx-background-color: #000000;-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
            }
        }
    }

    public void numerote() {
        for (int i = 0; i < 99; i++) {
            tabCase[i].getButton().setText(Integer.toString(i + 1));
            tabCase[i].setNumero(i + 1);
            tabCase[i].getButton().setAlignment(Pos.TOP_LEFT);
            tabCase[i].getButton().setFont(Font.font("Poppins", FontWeight.BOLD, 13));
            tabCase[i].getButton().setTextFill(Color.BLACK);
        }
        tabCase[99].getButton().setText(Integer.toString(100));
        tabCase[99].setNumero(100);
        tabCase[99].getButton().setAlignment(Pos.CENTER);
        tabCase[99].getButton().setFont(Font.font("Poppins", FontWeight.BOLD, 13));
        tabCase[99].getButton().setTextFill(Color.WHITE);
        myGrid.add(impostor, 2, 0);
        GridPane.setHalignment(impostor, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(impostor, VPos.CENTER); // To align vertically in the cell
    }

    public void deplacerJoueur(ActionEvent actionEvent) throws InterruptedException {
        if (pos != 99 && caseIsClicked) {
            a = des[0].throwDice();
            b = des[1].throwDice();
            if (pos + a + b > 99) {
                oldPos = pos;
                pos -= (pos + a + b - 99);
            } else {
                oldPos = pos;
                pos += a + b;
            }
            rollButton.setDisable(true);
            Thread what = Thread.currentThread();
            Thread thread = new Thread() {
                public void run() {
                    try {
                        int n = 0;
                        int m = 0;
                        for (int i = 0; i < 2; i++) {
                            n = des[0].throwDice();
                            m = des[1].throwDice();
                            imageR.setImage(new Image(getClass().getResourceAsStream("dice " + n + ".png")));
                            imageL.setImage(new Image(getClass().getResourceAsStream("dice " + m + ".png")));
                            Thread.sleep(50);
                        }
                        n = a;
                        m = b;
                        imageR.setImage(new Image(getClass().getResourceAsStream("dice " + n + ".png")));
                        imageL.setImage(new Image(getClass().getResourceAsStream("dice " + m + ".png")));
                        Thread.sleep(50);
                        rollButton.setDisable(false);
                        caseIsClicked = false;

                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }

                }
            };
            thread.start();
            myText.setText("Cliquez sur la case : " + (pos + 1));
        }
    }

    public void open() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Plateau.class.getResource("definition.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            DefController defController = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openimg() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Plateau.class.getResource("image.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ImgController imgController = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exitProgram(ActionEvent actionEvent) throws InterruptedException {
        try {
            FileOutputStream fileOut = new FileOutputStream(joueur.getNom() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            joueur.setScore(Integer.parseInt(score.getText()));
            joueur.setPosition(pos);
            joueur.setTab(numTab);
            out.writeObject(joueur);
            out.close();
            fileOut.close();
            fileOut = new FileOutputStream("jeu.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(MenuPrincipaleController.jeu);
            out.close();
            fileOut.close();
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void readNumTab() {
        try {
            FileInputStream fileIn = new FileInputStream(LoadGameMenuController.existingplayername + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            joueur = (Joueur) in.readObject();
            MenuPrincipaleController.jeu.setJoueur(joueur);
            numTab = joueur.getTab();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void remplireload() {
        impostor.setImage(new Image(getClass().getResourceAsStream("impostor.png")));
        impostor.setFitHeight(40);
        impostor.setFitWidth(40);
        impostor.setSmooth(false);
        Button button;
        Random rand = new Random();
        int n, i;
        int maxC = 13;
        int maxL = 12;
        int minC = 0;
        int minL = 2;
        int column = 2;
        int ligne = 0;
        Case a;
        button = new Button();
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(0.0);
        button.setMinWidth(0.0);
        CaseDepart caseDepart = new CaseDepart(cpt, button);
        tabCase[cpt] = caseDepart;
        tabButtons[cpt] = button;
        myGrid.add(tabButtons[cpt], column, ligne);
        cpt++;
        column++;
        while (cpt < 99) {
            while (column <= maxC) {
                if (numTab[cpt - 1] == 0) {
                    parcourir();
                } else a = randomCase();
                myGrid.add(tabButtons[cpt], column, ligne);
                column++;
                cpt++;
            }
            column--;
            ligne++;
            maxC -= 2;

            while (ligne <= maxL) {
                if (numTab[cpt - 1] == 0) {
                    parcourir();
                } else a = randomCase();
                myGrid.add(tabButtons[cpt], column, ligne);
                ligne++;
                cpt++;
            }

            ligne--;
            column--;
            maxL -= 2;

            while (column >= minC) {
                if (numTab[cpt - 1] == 0) {
                    parcourir();
                } else a = randomCase();
                myGrid.add(tabButtons[cpt], column, ligne);
                column--;
                cpt++;
            }

            column++;
            ligne--;
            minC += 2;

            while (ligne >= minL) {
                if (numTab[cpt - 1] == 0) {
                    parcourir();
                } else a = randomCase();
                myGrid.add(tabButtons[cpt], column, ligne);
                ligne--;
                cpt++;
            }
            ligne++;
            column++;
            minL += 2;

        }
        button = new Button();
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMinHeight(0.0);
        button.setMinWidth(0.0);
        myGrid.add(button, column, ligne);
        CaseFin caseFin = new CaseFin(cpt, button);
        tabCase[cpt] = caseFin;
        tabButtons[cpt] = button;
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                pos = 99;
                tabCase[pos].changerScore(score);
                tabCase[pos].deplacer(joueur);
                joueur.setScore(Integer.parseInt(score.getText()));
                joueur.beatScore();
                currentposition.setText("Position actuelle : " + (pos + 1));
                myText.setTextFill(Color.GREEN);
                myText.setText("Vous avez cliqué sur la bonne case !\n JEU FINI !");
                caseIsClicked = true;
            }
        });
        tabButtons[11].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[23].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[36].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[46].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[57].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[65].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[74].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[80].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[87].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[91].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[96].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
        tabButtons[98].setStyle("-fx-border-color:black;-fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-radius: 20px;-fx-background-insets: 0");
    }
}
