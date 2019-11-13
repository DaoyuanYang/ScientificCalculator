package burrellyang.calculatorapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.view.WindowManager;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.util.Log.d;

public class Calculator extends AppCompatActivity {
    private static Boolean isEvaluated = false;

    public static final char SquareRoot = (char) 8730;
    public static final char CubeRoot = (char) 8731;
    public static final String LeftArrow = "◀";
    public static final String RightArrow = "▶";
    public static final String BackSpace = "⌫";


    public Double answer = 0.0;

    TextView DRGstate;
    TextView shiftState;
    EditText myText;
    TextView resultText;

    public static boolean isDegrees;
    public static boolean isFuncEditMode;
    public static Integer funcNumberBeingEdited;
    public static Integer funcNumberBeingUsed;

    public UserFunctions ourSixFunctions; //this is where we get our functions from.........

    private List<Button> buttonList1 = new ArrayList<>();
    private List<Button> buttonList2 = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        findViewById(R.id.scrollView1).bringToFront();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /**Define Buttons and other items from the source file
         *
         */
        shiftState = findViewById(R.id.SHIFT_State);
        DRGstate = findViewById(R.id.DRG_State);
        myText = findViewById(R.id.myText);
        
        resultText = findViewById(R.id.resultText);
        disableKeyboardPopup(myText);

        ourSixFunctions = new UserFunctions(this.getApplicationContext()); //DO I USE GETAPPLICATIONCONTEXT OR GETBASECONTEXT???

        isDegrees = (DRGstate.getText().equals("DEG"));
        isFuncEditMode = false; //not sure if should set this as false on create...
        //Ensure calculator not in editMode
        TextView isFuncEditingMode = findViewById(R.id.funcEditingMode);
        isFuncEditingMode.setText("");
        //can also hide buttons used only for edit mode....
        LinearLayout editModeButtons = findViewById(R.id.row1_2);
        editModeButtons.setVisibility(View.INVISIBLE);
        ///////////////////////////////////////////////////////////////////////////////////


        Button buttonDclPt = findViewById(R.id.buttonDclPt);
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonEqual = findViewById(R.id.buttonEqual);
        Button buttonUserGuide = findViewById(R.id.buttonUserGuide);
        Button buttonAC = findViewById(R.id.buttonAC);


        Button buttonDiv = findViewById(R.id.buttonDiv);
        Button buttonMul = findViewById(R.id.buttonMul);
        Button buttonSub = findViewById(R.id.buttonSub);
        Button buttonAdd = findViewById(R.id.buttonAdd);

        Button buttonComma = findViewById(R.id.buttonComma);
        Button buttonEdit = findViewById(R.id.buttonEdit);
        Button buttonSave = findViewById(R.id.buttonSaveEditingFunc);
        Button buttonUseF = findViewById(R.id.buttonUse_f);
        Button buttonX = findViewById(R.id.buttonParameterX);
        Button buttonY = findViewById(R.id.buttonParameterY);
        Button buttonZ = findViewById(R.id.buttonParameterZ);




        Button buttonLeftArrow = findViewById(R.id.buttonLeftArrow);
        Button buttonRightArrow = findViewById(R.id.buttonRightArrow);
        Button buttonBackspace = findViewById(R.id.buttonBackspace);
        Button buttonDRG = findViewById(R.id.buttonDRG);
        Button buttonSin = findViewById(R.id.buttonSin);
        Button buttonCos = findViewById(R.id.buttonCos);
        Button buttonTan = findViewById(R.id.buttonTan);

        Button buttonLog10 = findViewById(R.id.buttonLogTen);
        Button buttonLBracket = findViewById(R.id.buttonLeftBracket);
        Button buttonPi = findViewById(R.id.buttonPi);

        Button buttonRBracket = findViewById(R.id.buttonRightBracket);
        Button buttonSquare = findViewById(R.id.buttonSquare);
        Button buttonSqrt = findViewById(R.id.buttonsqrt);

        Button buttonANS = findViewById(R.id.buttonAnswer);
        Button buttonShift = findViewById(R.id.buttonShift);

        /**
         * ButtonList contains basic input keys and parameters used for defining functions
         * Used for FUNC.appendOnExpression to decide whether the content needs to be cleared
         * @author DY
         */
        // buttonList1 contains the normal input Keys
        buttonList1 = Arrays.asList(buttonEqual, button0, button1, button2, button3, button4,
                button5, button6, button7, button8, button9, buttonDclPt, buttonAC,
                buttonDiv, buttonMul, buttonSub, buttonAdd, buttonX, buttonY, buttonZ);

        // buttonList2 contains the function Keys
        // buttonSinCounter, buttonCosCounter, buttonTanCounter,
        buttonList2 = Arrays.asList(buttonEdit, buttonSave, buttonUseF, buttonLeftArrow, buttonRightArrow,
                buttonBackspace, buttonDRG, buttonSin, buttonCos, buttonTan, buttonLog10, buttonLBracket, buttonPi,
                buttonRBracket, buttonSquare, buttonSqrt,
                buttonComma, buttonANS, buttonShift);

        //Set button clickListeners
        buttonUserGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToUserGuide();
            }
        });

        /**
         * Apply all input keys FUNC.appendOnExpression
         */
        for (final Button buut : buttonList1){
            buut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appendOnExpression(buut.getText().toString());
                }
            });
        }

        for (final Button buut : buttonList2){
            buut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    d("Dom: ", buut.getText().toString());
                    appendOnExpressionFucKey(buut.getText().toString());
                }
            });
        }


    }

    /**
     * Set up input and functionalities for basic buttons
     * @param str the text shown on button
     * @author DY
     */
    public void appendOnExpression(String str){
        // For buttonList1
        str = str.toLowerCase();
        if (str.equals("=")) {
            if(isFuncEditMode){
                //set function to new function
                //clear text thing....
                String uneditedUserFunc = myText.getText().toString();
                String editedUserFunc = UserFunctions.replaceVariables(uneditedUserFunc);
                ourSixFunctions.userFuncs[funcNumberBeingEdited] = editedUserFunc;
                resultText.setText("Edited f"+funcNumberBeingEdited+"!");
                setEditModeFalse();
                myText.setText(""); //set myText to empty
            }
            else {
                isEvaluated = true;

                resultText.setText(calculateResult());
            }
        }
        else if (str.equals("ac")) myText.setText("");

        // Number keys
        else {
            // if evaluated then clear content
            whetherToClearContent(str);
            positionInputNoBracket(str);
        }
    }

    /**
     *Set up input and functionalities for more complicated buttons
     *Usefunction and Editfunction are defined here
     * @param str is the text shown on button
     * @author DY, NB
     */
    public void appendOnExpressionFucKey(String str){
        // For buttonList2
        str = str.toLowerCase();

        if (str.equals("shift")) {
            if (shiftState.getText().equals("SHIFT")) {
                shiftState.setText("");
            }
            else {
                shiftState.setText("SHIFT");
            }
        }
        else
        if(str.equals("save_f")){
            //NEED TO ALSO ACTUALLY SAVE THE FUNCTION TO CORRECT VARIABLE

            String uneditedUserFunc = myText.getText().toString();
            String editedUserFunc = UserFunctions.replaceVariables(uneditedUserFunc);
            ourSixFunctions.userFuncs[funcNumberBeingEdited] = editedUserFunc;
            resultText.setText("Edited f"+funcNumberBeingEdited+"!");
            setEditModeFalse();
            myText.setText(""); //set myText to empty

        }

        else
        if(str.equals("edit_f")){
            //2 options for importing AlertDialog
            //chose android.support.v7.app
            AlertDialog.Builder builder = new AlertDialog.Builder(Calculator.this);
            builder.setTitle("Select Function to Edit");

            String[] userFuncsForDisplay = UserFunctions.userFuncs;

            int i = 0;
            for(String userFunc : userFuncsForDisplay){
                if(userFunc == null || userFunc.equals("")){
                    userFuncsForDisplay[i] = "f" + i + ": ";
                }
                else {
                    if (!userFunc.contains(":")) {
                        userFuncsForDisplay[i] = "f" + i + ": " + userFunc;
                    } else {
                        userFuncsForDisplay[i] = userFunc;
                    }
                }
                i++;
            }



            builder.setItems(new CharSequence[]
                            {userFuncsForDisplay[0], userFuncsForDisplay[1], userFuncsForDisplay[2], userFuncsForDisplay[3], userFuncsForDisplay[4], userFuncsForDisplay[5]},
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // The 'which' argument contains the index position
                            // of the selected item
                            switch (which) {
                                case 0:
                                    Toast.makeText(Calculator.this, "Editing f0", Toast.LENGTH_LONG).show();
                                    setEditModeTrue("f0");
//                                    isFuncEditMode = true; //add this to every option?
                                    break;
                                case 1:
                                    Toast.makeText(Calculator.this, "Editing f1", Toast.LENGTH_LONG).show();
                                    setEditModeTrue("f1");
//                                    isFuncEditMode = true; //add this to every option?
                                    break;
                                case 2:
                                    Toast.makeText(Calculator.this, "Editing f2", Toast.LENGTH_LONG).show();
                                    setEditModeTrue("f2");
//                                    isFuncEditMode = true; //add this to every option?
                                    break;
                                case 3:
                                    Toast.makeText(Calculator.this, "Editing f3", Toast.LENGTH_LONG).show();
                                    setEditModeTrue("f3");
//                                    isFuncEditMode = true; //add this to every option?
                                    break;
                                case 4:
                                    Toast.makeText(Calculator.this, "Editing f4", Toast.LENGTH_LONG).show();
                                    setEditModeTrue("f4");
//                                    isFuncEditMode = true; //add this to every option?
                                    break;
                                case 5:
                                    Toast.makeText(Calculator.this, "Editing f5", Toast.LENGTH_LONG).show();
                                    setEditModeTrue("f5");
//                                    isFuncEditMode = true; //add this to every option?
                                    break;
                            }
                        }
                    });
            builder.create().show();
//            isFuncEditMode = false; //if don't click any option??

        }

        else
        if(str.equals("use_f")){
            if(isFuncEditMode){
                setEditModeFalse();
            }
            //2 options for importing AlertDialog
            //chose android.support.v7.app
            AlertDialog.Builder builder = new AlertDialog.Builder(Calculator.this);
            builder.setTitle("Select Function to Use");

            String[] userFuncsForDisplay = UserFunctions.userFuncs;

            int i = 0;
            for(String userFunc : userFuncsForDisplay){
                if(userFunc == null || userFunc.equals("")){
                    userFuncsForDisplay[i] = "f" + i + ": ";
                }
                else {
                    if (userFunc.charAt(0) != 'f') {
                        userFuncsForDisplay[i] = "f" + i + ": " + userFunc;
                    } else {
                        userFuncsForDisplay[i] = userFunc;
                    }
                }
                i++;
            }



            builder.setItems(new CharSequence[]
                            {userFuncsForDisplay[0], userFuncsForDisplay[1], userFuncsForDisplay[2], userFuncsForDisplay[3], userFuncsForDisplay[4], userFuncsForDisplay[5]},
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // The 'which' argument contains the index position
                            // of the selected item
                            switch (which) {
                                case 0:
                                    funcNumberBeingUsed = 0;
                                    Toast.makeText(Calculator.this, "Using f0", Toast.LENGTH_LONG).show();
                                    positionInputWBracket("f0()");
                                    break;
                                case 1:
                                    funcNumberBeingUsed = 1;
                                    Toast.makeText(Calculator.this, "Using f1", Toast.LENGTH_LONG).show();
                                    positionInputWBracket("f1()");
                                    break;
                                case 2:
                                    funcNumberBeingUsed = 2;
                                    Toast.makeText(Calculator.this, "Using f2", Toast.LENGTH_LONG).show();
                                    positionInputWBracket("f2()");
                                    break;
                                case 3:
                                    funcNumberBeingUsed = 3;
                                    Toast.makeText(Calculator.this, "Using f3", Toast.LENGTH_LONG).show();
                                    positionInputWBracket("f3()");
                                    break;
                                case 4:
                                    funcNumberBeingUsed = 4;
                                    Toast.makeText(Calculator.this, "Using f4", Toast.LENGTH_LONG).show();
                                    positionInputWBracket("f4()");
                                    break;
                                case 5:
                                    funcNumberBeingUsed = 5;
                                    Toast.makeText(Calculator.this, "Using f5", Toast.LENGTH_LONG).show();
                                    positionInputWBracket("f5()");
                                    break;
                            }
                        }
                    });
            builder.create().show();
//            isFuncEditMode = false; //if don't click any option??

        }


        else
        if (str.equals(BackSpace)){
            int cursorPosition = myText.getSelectionStart();
            String tempText = myText.getText().toString();

            isEvaluated = false;

            if (!tempText.equals("")){
                try{
                    myText.setText(tempText.substring(0, cursorPosition - 1) + tempText.substring(cursorPosition));
                    myText.setSelection(cursorPosition - 1);
                }
                catch(StringIndexOutOfBoundsException e){
                    //do nothing
                }
            }
        }


        else
        if (str.equals(LeftArrow)) {
            isEvaluated = false;
            if (myText.getSelectionStart() - 1 >= 0){
                myText.setSelection(myText.getSelectionStart() - 1);
            }
        }
        else
        if (str.equals(RightArrow)) {
            isEvaluated = false;
            if (myText.getSelectionStart() + 1 <= myText.length()) {
                myText.setSelection(myText.getSelectionStart() + 1);
            }
        }


        // Shift key related functions
        else
        if (shiftState.getText().equals("SHIFT")) {
            shiftState.setText("");
            appendShiftedFunction(str);
        }
        else appendNormalButton(str);



    }


    /**
     * An extension of button function defining.
     * Specifically used for defining alternative functionalities of the buttons who have alternative function
     * @param str the text shown on button
     * @author DY
     */
    private void appendShiftedFunction(String str) {
        // for non-input keys
        if (str.equals("x^2")){
            str = "^()";
            positionInputWBracket(str);
        }

        // for input keys
        else
        whetherToClearContent(str);

        if (str.equals("sqrt")){
            str = (CubeRoot) + "()";
            positionInputWBracket(str);
        }


        else
        if (str.equals("ans")){
            str = "abs()";
            positionInputWBracket(str);
        }

        else
        if(str.equals("log10x")){
            str = "log()";
            positionInputWBracket(str);
        }

        else
        if(str.equals("(")){
           str = "ln()";
           positionInputWBracket(str);
        }

        else
        if(str.equals(")")){
            str = "10^()";
            positionInputWBracket(str);
        }

        else
        if (str.equals(",")){
            str = "mod()";
            positionInputWBracket(str);
        }

        else
        if (str.equals("sin") || str.equals("cos") || str.equals("tan")){
            str = "a" + str + "()";
            positionInputWBracket(str);
        }

        else if (str.equals("pi")){
            str = "e";
            positionInputNoBracket(str);
        }

        else
        if (str.equals("drg")){
            str = "" + Math.random();
            int numCharactersToShow = 7;
            positionInputNoBracket(str.substring(0,numCharactersToShow));
        }

    }

    /**
     * An extension of button function defining.
     * Specifically used for defining default functionalities of the buttons who have alternative function
     * @param str the text shown on button
     * @author DY
     */
    private void appendNormalButton(String str) {
        // for non-input keys
        if (str.equals("x^2")){
            str = "^2";
            positionInputNoBracket(str);
        }

        else
        if (str.equals("drg")){
            if (DRGstate.getText().equals("RAD")){
                DRGstate.setText("DEG") ;
                isDegrees = true;
            }
            else{
                DRGstate.setText("RAD");
                isDegrees = false;
            }
        }



        // for input keys
        else
        whetherToClearContent(str);

        if (str.equals("sqrt")){
            str = SquareRoot + "()";
            positionInputWBracket(str);
        }

        else
        if (str.equals(",")){
            positionInputNoBracket(str);
        }

        else
        if (str.equals("ans")){
            if(isFuncEditMode){
                //do nothing
            }
            else{
            str = "ANS";
            positionInputNoBracket(str);}
        }

        else
        if (str.equals("log10x")){
            str = "log(10, )";
            positionInputWBracket(str);
        }

        else
        if (str.equals("(") || str.equals(")") || str.equals("pi")) positionInputNoBracket(str);

        else
        if (str.equals("sin") || str.equals("cos") || str.equals("tan")){
            str = str + "()";
            positionInputWBracket(str);
        }
    }

    /**
     * OnClickListener function
     * To start activity User Guide when button "HELP" is pressed
     * @author DY
     */
    public void jumpToUserGuide(){
        Intent intent = new Intent(this, UserGuide.class)
                .setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    /**
     * Evaluate the input and store the answer to filed "answer"
     * @return the String converted from the calculated result
     * @author DY
     */
    public String calculateResult(){
        try{
            String toEvaluate = myText.getText().toString();
            toEvaluate = toEvaluate.replaceAll("ANS", answer.toString());
                answer = new ExtendedEvaluator().evaluate(toEvaluate);
                return answer.toString();
        }
        catch(IllegalArgumentException e){
            return "Input Error"; //can just say "Error" or be more specific
        }
    }

    /**
     * determines whether to clear user text input (myText)
     * clears text if any button apart from simple operators (+, -, *, /) were last clicked
     * @param str: text of button pressed
     * @author DY
     */
    public void whetherToClearContent(String str){
        if (isEvaluated) {
            isEvaluated = false;
            if (!(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/"))) {
                myText.setText("");
            }
        }
    }

    /**
     * Move the cursor to the end the input string (without brackets)
     * @param inputStr the string needs to be inserted
     * @author DY
     */
    public void positionInputNoBracket(String inputStr){
        String temString = myText.getText().toString();
        int cursorPosition = myText.getSelectionStart();

        // subString(startPosition, endPosition) endposition doesn't include the letter on the endPosition
        myText.setText(temString.substring(0, cursorPosition) + inputStr +
                temString.substring(cursorPosition));
        myText.setSelection(cursorPosition + inputStr.length());
    }

    /**
     * Move the cursor to the end the input string (with brackets)
     * @param inputStr the string needs to be inserted
     * @author DY
     */
    public void positionInputWBracket(String inputStr){
        int cursorPosition = myText.getSelectionStart();
        String temString = myText.getText().toString();

        myText.setText(temString.substring(0, cursorPosition) + inputStr +
                temString.substring(cursorPosition));
        myText.setSelection(cursorPosition + inputStr.length()-1);
    }


    /**
     * Prevent the input keyboard from popping up
     * @param editText input display (EditText)
     * @author DY
     */
    public static void disableKeyboardPopup(EditText editText) {
        //stop keyboard from popping up when click in EditText
        //works for API > 11
        editText.setShowSoftInputOnFocus(false);
//        editText.setTextIsSelectable(false); //make text not selectable because backspace not configured for this
    }

    /**
     * Switch to Editmode
     * @param funcBeingEdited function chosen to be edited
     * @author NB
     */
    public void setEditModeTrue(String funcBeingEdited){
        //set text to func being edited
        if(myText.getText().toString().contains("f") || myText.getText().toString().contains("ANS")){
            myText.setText(""); //can't have user functions be functions of other user functions
        }
        funcNumberBeingEdited = Integer.valueOf(funcBeingEdited.substring(1));
        isFuncEditMode = true;
        TextView isFuncEditingMode = findViewById(R.id.funcEditingMode);
        isFuncEditingMode.setText("Editing "+funcBeingEdited+":");
        resultText.setText(""); //clear result text................
        //can also hide buttons used only for edit mode....
        LinearLayout editModeButtons = findViewById(R.id.row1_2);
        editModeButtons.setVisibility(View.VISIBLE);
        ///////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * Switch off Editmode
     * @author NB
     */
    public void setEditModeFalse(){
        //set text to func being edited
        funcNumberBeingEdited = null; //be careful of this!! could raise a null error somewhere...................
        isFuncEditMode = false;
        TextView isFuncEditingMode = findViewById(R.id.funcEditingMode);
        isFuncEditingMode.setText("");
        //can also hide buttons used only for edit mode....
        LinearLayout editModeButtons = findViewById(R.id.row1_2);
        editModeButtons.setVisibility(View.INVISIBLE);
        ///////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * Edit the action when switching activities
     * @author NB
     */
    @Override
    //ensure data is saved when app is closed....
    protected void onPause() {
        super.onPause();
        UserFunctions.writeToFile(ourSixFunctions.userFuncs, this.getApplicationContext());
    }
}
