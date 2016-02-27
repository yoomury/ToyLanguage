package samplee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*IStmt st1 = new AssignStmt("v", new ConstExp(10));
        IStmt st2 = new NewStmt("a", new ConstExp(22));
        IStmt st3 = new AssignStmt("v", new ConstExp(32));
        IStmt st4 = new PrintStmt(new VarExp("v"));
        IStmt st5 = new PrintStmt(new ReadHeapExp("a"));
        IStmt st8 = new forkStmt(new CompStmt(new WriteHeapStmt("a", new ConstExp(30)), new CompStmt(st3, new CompStmt(st4, st5))));
        IStmt st6 = new PrintStmt(new VarExp("v"));
        IStmt st7 = new PrintStmt(new ReadHeapExp("a"));
        IStmt prgStatement = new CompStmt(st1, new CompStmt(st2, new CompStmt(st8, new CompStmt(st6,new CompStmt (st7, new CompStmt(new SkipStmt(), new CompStmt(new SkipStmt(), new SkipStmt() ))))))); */

      //  programs.add(new PrgState(new MyLibraryStack<>(), new MyLibraryDictionary<>(), new MyLibraryHeap<>(),new MyLibraryList<>(), prgStatement));
       // ctrl.setPrgList(programs);

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        stage.setTitle("MainMenu");
        stage.setScene(new Scene(root));
        stage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
