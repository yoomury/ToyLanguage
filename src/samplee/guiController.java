package samplee;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Region;
import samplee.Controller.Controller;
import samplee.Domain.Expressions.*;
import samplee.Domain.Heap.MyLibraryHeap;
import samplee.Domain.List.MyLibraryList;
import samplee.Domain.LockTable.LockTable;
import samplee.Domain.Map.MyLibraryDictionary;
import samplee.Domain.ProgramState.PrgState;
import samplee.Domain.Stack.MyLibraryStack;
import samplee.Domain.Statements.*;
import samplee.Repository.MyRepository;
import samplee.Repository.Repository;
import samplee.Repository.RepositoryException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class guiController implements Initializable{

    private final String[] statements = {"Compound Statement", "Assignment Statement", "Print Statement",
            "If Statement", "While Statement", "IfThen Statement", "Switch Statement", "Skip Statement",
            "New Statement", "Write Heap", "Fork","New Lock","Lock","Unlock"};
    private final String[] expressions = {"Arithmetic Exp", "Constant Exp",
            "Variable Exp", "Logical Exp", "Unary Logical Exp", "Relational Exp",
            "Read value", "Read Heap"};

    private Controller ctrl;

    @FXML
    private CheckBox printFlagCheck;

    @FXML
    private CheckBox logFlagCheck;

    @FXML
    private TreeItem<String> stack;
    @FXML
    private TreeItem<String> symtable;
    @FXML
    private TreeItem<String> heap;
    @FXML
    private TreeItem<String> output;

    @FXML
            private TreeItem<String> lockTable;

    ChoiceDialog<String> statementDialog;
    ChoiceDialog<String> ExpDialog;
    TextInputDialog textDialog;

    public void onInput(ActionEvent e) throws Exception {
        IStmt statement = newStatement(statementDialog, "Input statement please: ");
        /*IStmt st1 = new AssignStmt("v", new ConstExp(10));
        IStmt st2 = new NewStmt("a", new ConstExp(22));
        IStmt st3 = new AssignStmt("v", new ConstExp(32));
        IStmt st4 = new PrintStmt(new VarExp("v"));
        IStmt st5 = new PrintStmt(new ReadHeapExp("a"));
        IStmt st8 = new forkStmt(new CompStmt(new WriteHeapStmt("a", new ConstExp(30)), new CompStmt(st3, new CompStmt(st4, st5))));
        IStmt st6 = new PrintStmt(new VarExp("v"));
        IStmt st7 = new PrintStmt(new ReadHeapExp("a"));
        IStmt prgStatement = new CompStmt(st1, new CompStmt(st2, new CompStmt(st8, new CompStmt(st6,new CompStmt (st7, new CompStmt(new SkipStmt(), new CompStmt(new SkipStmt(), new SkipStmt() ))))))); */
        Repository repo = new MyRepository();
        ctrl = new Controller(repo);
        List<PrgState> programs = new ArrayList<>();
        programs.add(new PrgState(new MyLibraryStack<>(), new MyLibraryDictionary<>(), new MyLibraryHeap<>(), new MyLibraryList<>(),new LockTable(), statement));
        ctrl.setPrgList(programs);
        updateTree();
    }

    public void onOneStep(ActionEvent e) throws Exception{
        ctrl.oneStep();
        if (printFlagCheck.selectedProperty().get()){
            updateTree();
        }
    }

    public void onAllStep(ActionEvent e) throws Exception{
        ctrl.allStep();
        updateTree();
    }

    public void onSerialize(ActionEvent e) throws Exception{
        ctrl.serializeProgramState();
    }
    public void onDeserialize(ActionEvent e) throws Exception{
        ctrl.deserializeProgramState();
    }




    private void updateTree() throws RepositoryException {
        stack.getChildren().clear();
        symtable.getChildren().clear();
        output.getChildren().clear();
        heap.getChildren().clear();
        lockTable.getChildren().clear();
        ctrl.getPrgStates().stream().forEach((state) -> {
            TreeItem<String> stackk = new TreeItem<>(state.printID());
            TreeItem<String> symtablee = new TreeItem<>(state.printID());
            TreeItem<String> heapp = new TreeItem<>(state.printID());
            TreeItem<String> outputt = new TreeItem<>(state.printID());
            TreeItem<String> lockTablee = new TreeItem<>(state.printID());

            stackk.getChildren().add(new TreeItem<>("Stack: " + state.printStack()));
            symtablee.getChildren().add(new TreeItem<>("Symbols: " + state.printSymTable()));
            heapp.getChildren().add(new TreeItem<>("Heap: " + state.printHeap()));
            outputt.getChildren().add(new TreeItem<>("Output: " + state.printOutput()));
            lockTablee.getChildren().add(new TreeItem<>("LockTable: " + state.printLockTable()));
            stackk.setExpanded(true);
            symtablee.setExpanded(true);
            heapp.setExpanded(true);
            outputt.setExpanded(true);
            lockTablee.setExpanded(true);
            stack.getChildren().add(stackk);
            symtable.getChildren().add(symtablee);
            heap.getChildren().add(heapp);
            output.getChildren().add(outputt);
            lockTable.getChildren().add(lockTablee);

        });
    }

    private IStmt newStatement(ChoiceDialog<String> statementDialog, String s) throws Exception {
        statementDialog.setContentText(s);
        Optional<String> result = statementDialog.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            if (choice.equals(statements[0])) {
                IStmt first = newStatement(statementDialog, "First Statement:");
                IStmt second = newStatement(statementDialog, "Second Statement:");
                return new CompStmt(first, second);
            }
            if (choice.equals(statements[1])) {
                String name = newStringDialog(textDialog, "Variable's: ");
                Exp value = newExp(ExpDialog, "Value: ");
                return new AssignStmt(name, value);
            }
            if (choice.equals(statements[2])) {
                Exp Exp = newExp(ExpDialog, "Exp: ");
                return new PrintStmt(Exp);
            }
            if (choice.equals(statements[3])) {
                Exp condition = newExp(ExpDialog, "Condition: ");
                IStmt thenStatement = newStatement(statementDialog, "Then branch: ");
                IStmt elseStatement = newStatement(statementDialog, "Else branch: ");
                return new IfStmt(condition, thenStatement, elseStatement);
            }
            if (choice.equals(statements[4])) {
                Exp condition = newExp(ExpDialog, "Condition: ");
                IStmt body = newStatement(statementDialog, "Body: ");
                return new WhileStmt(condition, body);
            }
            if (choice.equals(statements[5])) {
                Exp condition = newExp(ExpDialog, "Condition: ");
                IStmt thenStatement = newStatement(statementDialog, "Then branch: ");
                return new IfThenStmt(condition, thenStatement);
            }
            if (choice.equals(statements[6])) {
                Exp condition = newExp(ExpDialog, "Condition: ");
                Exp case1Exp = newExp(ExpDialog, "Case 1 Exp:");
                IStmt case1Statement = newStatement(statementDialog, "Case 1 Statement:");
                Exp case2Exp = newExp(ExpDialog, "Case 2 Exp:");
                IStmt case2Statement = newStatement(statementDialog, "Case 2 Statement:");
                IStmt defaultStatement = newStatement(statementDialog, "Default Statement:");
                return new SwitchStmt(condition, case1Exp, case1Statement, case2Exp, case2Statement, defaultStatement);
            }
            if (choice.equals(statements[7])) {
                return new SkipStmt();
            }
            if (choice.equals(statements[8])) {
                String name = newStringDialog(textDialog, "Name: ");
                Exp Exp = newExp(ExpDialog, "Exp: ");
                return new NewStmt(name, Exp);
            }
            if (choice.equals(statements[9])) {
                String name = newStringDialog(textDialog, "Name: ");
                Exp Exp = newExp(ExpDialog, "Exp: ");
                return new WriteHeapStmt(name, Exp);
            }
            if (choice.equals(statements[10])) {
                return new forkStmt(newStatement(statementDialog, "Statement: "));
            }
            if (choice.equals(statements[11])) {
                String name = newStringDialog(textDialog, "Variable's: ");

                return new newLock(name);
            }
            if (choice.equals(statements[12])) {
                String name = newStringDialog(textDialog, "Variable's: ");
                return new lockStatement(name);
            }
            if (choice.equals(statements[13])) {
                String name = newStringDialog(textDialog, "Variable's: ");
                Exp value = newExp(ExpDialog, "Value: ");
                return new unlock(name);
            }




        }
        throw new Exception("Invalid option");
    }
    private String newStringDialog(TextInputDialog textInputDialog, String content) throws IOException {
        textInputDialog.setContentText(content);
        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent())
            return result.get();
        throw new IOException();
    }


    private Exp newExp(ChoiceDialog<String> ExpDialog, String s) throws Exception {
        statementDialog.setContentText(s);
        Optional<String> result = ExpDialog.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            if (choice.equals(expressions[0])) {
                String operator = newStringDialog(textDialog, "Enter operator (+, -, *): ");
                Exp left = newExp(ExpDialog, "Left Expression: ");
                Exp right = newExp(ExpDialog, "Right Expression: ");
                return new ArithExp(left, operator, right);
            }
            if (choice.equals(expressions[1])) {
                Integer value = Integer.valueOf(newStringDialog(textDialog, "Enter constant value: "));
                return new ConstExp(value);
            }
            if (choice.equals(expressions[2])) {
                String name = newStringDialog(textDialog, "Name: ");
                return new VarExp(name);
            }
            if (choice.equals(expressions[3])) {
                String operator = newStringDialog(textDialog, "Enter operator (&&, ||): ");
                Exp left = newExp(ExpDialog, "Left Expression: ");
                Exp right = newExp(ExpDialog, "Right Expression: ");
                return new LogicalExp(left,operator,right);
            }
            if (choice.equals(expressions[4])) {

                Exp right = newExp(ExpDialog, "Expression: ");
                return new NotExp(right);
            }
            if (choice.equals(expressions[5])) {
                String operator = newStringDialog(textDialog, "Enter operator (<, <=, !=, ==, >=, >): ");
                Exp left = newExp(ExpDialog, "Left Expression: ");
                Exp right = newExp(ExpDialog, "Right Expression: ");
                return new RelationalExp(left,operator,right);
            }
            if (choice.equals(expressions[6])) {
                return new ReadExp();
            }
            if (choice.equals(expressions[7])) {
                String name = newStringDialog(textDialog, "Name: ");
                return new ReadHeapExp(name);
            }
        }
        throw new Exception("Invalid option");
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Repository repo= new MyRepository();
        try {
            ctrl= new Controller(repo);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        List<PrgState> programs= new ArrayList<>();

        IStmt st1=new NewStmt("v1",new ConstExp(20));
        IStmt st2=new NewStmt("v2",new ConstExp(30));
        IStmt st3=new newLock("x");
        IStmt st4=new forkStmt(new CompStmt(new lockStatement("x"),new CompStmt(new WriteHeapStmt("v1",new ArithExp(new ReadHeapExp("v1"),"-",new ConstExp(1))),new unlock("x"))));
        IStmt st5= new CompStmt(new lockStatement("x"),new CompStmt(new WriteHeapStmt("v1",new ArithExp(new ReadHeapExp("v1"),"+",new ConstExp(1))),new unlock("x")));
        IStmt st6=new forkStmt(new WriteHeapStmt("v2",new ArithExp(new ReadHeapExp("v2"),"+",new ConstExp(1))));
        IStmt st7 =new CompStmt(new WriteHeapStmt("v2",new ArithExp(new ReadHeapExp("v2"),"+",new ConstExp(1))),new unlock("x"));

        IStmt st8 = new CompStmt(new SkipStmt(),new CompStmt(new SkipStmt(),new CompStmt(new SkipStmt(),new CompStmt(new SkipStmt(),new CompStmt(new SkipStmt(),new SkipStmt())))));


        IStmt prgStatement= new CompStmt(st1,new CompStmt(st2,new CompStmt(st3,new CompStmt(new forkStmt(new CompStmt(st4,st5)),new CompStmt(new forkStmt(new CompStmt(st6,st7)),new CompStmt(st8,new CompStmt(new PrintStmt(new ReadHeapExp("v1")),new PrintStmt(new ReadHeapExp("v2")))))))));


        programs.add(new PrgState(new MyLibraryStack<>(), new MyLibraryDictionary<>(), new MyLibraryHeap<>(),new MyLibraryList<>(),new LockTable(), prgStatement));
        ctrl.setPrgList(programs);



        printFlagCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            ctrl.setPrintFlag(newValue);
        });
        logFlagCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            ctrl.setLogFlag(newValue);
        });
        statementDialog = new ChoiceDialog<>(statements[0], statements);
        statementDialog.setTitle("New Statement");
        statementDialog.getDialogPane().setHeader(new Region());
        ExpDialog = new ChoiceDialog<>(expressions[0], expressions);
        ExpDialog.setTitle("New Expression");
        ExpDialog.getDialogPane().setHeader(new Region());
        ExpDialog.setContentText("Choose an expression: ");
        textDialog = new TextInputDialog();
        textDialog.getDialogPane().setHeader(new Region());
        textDialog.setContentText("Name: ");
    }


}


