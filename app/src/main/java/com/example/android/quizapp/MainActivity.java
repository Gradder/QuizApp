package com.example.android.quizapp;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.drm.DrmStore;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    int numOfCompletedAnswers = 1;
    int numOfRightAnswers = 0;
    int current_question = 0;
    int checked_radiobutton = 4;
    TextView currentQuestion;
    TextView textQuestion;
    View ind;
    EditText eText;
    Button submitButton;
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    CheckBox cb4;
    ImageView image;
    HashMap<Integer, String> questions = new HashMap<>();
    HashMap<Integer, Integer> correctAnswers = new HashMap<>();
    ArrayList<Boolean> finalResults = new ArrayList<>(); //True - correct, false - not
    ArrayList<Integer> listOfNumsOfQusetions = new ArrayList<>();
    RadioGroup radioGroup;
    int[] indicators = new int[]{R.id.ind_1, R.id.ind_2, R.id.ind_3, R.id.ind_4, R.id.ind_5, R.id.ind_6, R.id.ind_7, R.id.ind_8, R.id.ind_9, R.id.ind_10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_horizontal);
        }

        Toolbar mActionBarToolbar = findViewById(R.id.toolbar_actionbar);
        mActionBarToolbar.setTitle("");
        setActionBar(mActionBarToolbar);
        initializeQuestions();
        radioGroup = findViewById(R.id.radiogroup);
        eText = findViewById(R.id.edit_text_field);
        currentQuestion = findViewById(R.id.txtCurrentQuestion);
        inflateContent(0);
        View ind1 = findViewById(R.id.ind_1);
        ind1.setBackgroundColor(Color.YELLOW);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_horizontal);
            inflateContent(current_question);
            currentQuestion = findViewById(R.id.txtCurrentQuestion);
            currentQuestion.setText(Integer.toString(numOfCompletedAnswers));
            changeStatus();
            if (numOfCompletedAnswers >= 10) {
                setResultsOnScreen();
                submitButton = findViewById(R.id.answer_question);
                submitButton.setVisibility(View.INVISIBLE);
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
            inflateContent(current_question);
            currentQuestion = findViewById(R.id.txtCurrentQuestion);
            currentQuestion.setText(Integer.toString(numOfCompletedAnswers));
            changeStatus();
            if (numOfCompletedAnswers >= 10) {
                setResultsOnScreen();
                submitButton = findViewById(R.id.answer_question);
                submitButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void initializeQuestions() {

        questions.put(0, getString(R.string.question1));
        questions.put(1, getString(R.string.question2));
        questions.put(2, getString(R.string.question3));
        questions.put(3, getString(R.string.question4));
        questions.put(4, getString(R.string.question5));
        questions.put(5, getString(R.string.question6));
        questions.put(6, getString(R.string.question7));
        questions.put(7, getString(R.string.question8));
        questions.put(8, getString(R.string.question9));
        questions.put(9, getString(R.string.question10));

        correctAnswers.put(0, 2);
        correctAnswers.put(1, 0);
        correctAnswers.put(2, 1);
        correctAnswers.put(3, 1);
        correctAnswers.put(4, 3);
        correctAnswers.put(5, 4);
        correctAnswers.put(6, 3);
        correctAnswers.put(7, 2);
        correctAnswers.put(8, 1);
        correctAnswers.put(9, 4);

        for (int i = 0; i < 10; i++) {
            listOfNumsOfQusetions.add(i);
        }
    }

    public void inflateContent(int numOfQuestion) {

        radioGroup = findViewById(R.id.radiogroup);
        String question = questions.get(numOfQuestion);
        textQuestion = findViewById(R.id.text_question);
        textQuestion.setText(question);

        image = findViewById(R.id.image_question);
        RadioButton RadioButton1 = findViewById(R.id.rb1);
        RadioButton RadioButton2 = findViewById(R.id.rb2);
        RadioButton RadioButton3 = findViewById(R.id.rb3);
        RadioButton RadioButton4 = findViewById(R.id.rb4);
        switch (numOfQuestion) {
            case 0:
                setCheckboxesInvisible(true);
                setETextInvisible(true);
                image.setImageResource(R.mipmap.science3_box);
                radioGroup.setVisibility(View.VISIBLE);
                RadioButton1.setText(getString(R.string.answer1));
                RadioButton2.setText(getString(R.string.answer2));
                RadioButton3.setText(getString(R.string.answer3));
                RadioButton4.setText(getString(R.string.answer4));
                break;
            case 1:
                setCheckboxesInvisible(true);
                setETextInvisible(true);
                image.setImageResource(R.mipmap.science4_blockchain);
                radioGroup.setVisibility(View.VISIBLE);
                RadioButton1.setText(getString(R.string.answer5));
                RadioButton2.setText(getString(R.string.answer2));
                RadioButton3.setText(getString(R.string.answer6));
                RadioButton4.setText(getString(R.string.answer7));
                break;
            case 2:
                setCheckboxesInvisible(true);
                setETextInvisible(true);
                image.setImageResource(R.mipmap.science5_ai);
                radioGroup.setVisibility(View.VISIBLE);
                RadioButton1.setText(getString(R.string.answer2));
                RadioButton2.setText(getString(R.string.answer1));
                RadioButton3.setText(getString(R.string.answer8));
                RadioButton4.setText(getString(R.string.answer9));
                break;
            case 3:
                setCheckboxesInvisible(true);
                setETextInvisible(true);
                image.setImageResource(R.mipmap.science6_ar);
                radioGroup.setVisibility(View.VISIBLE);
                RadioButton1.setText(getString(R.string.answer10));
                RadioButton2.setText(getString(R.string.answer11));
                RadioButton3.setText(getString(R.string.answer1));
                RadioButton4.setText(getString(R.string.answer3));
                break;
            case 4:
                setCheckboxesInvisible(true);
                setETextInvisible(true);
                image.setImageResource(R.mipmap.science7_vr);
                radioGroup.setVisibility(View.VISIBLE);
                RadioButton1.setText(getString(R.string.answer3));
                RadioButton2.setText(getString(R.string.answer9));
                RadioButton3.setText(getString(R.string.answer12));
                RadioButton4.setText(getString(R.string.answer10));
                break;
            case 5:
                setCheckboxesInvisible(false);
                setETextInvisible(true);
                image.setImageResource(R.mipmap.science8_bigdata);
                radioGroup.setVisibility(View.INVISIBLE);
                break;
            case 6:
                setCheckboxesInvisible(true);
                setETextInvisible(true);
                image.setImageResource(R.mipmap.science9_iot);
                radioGroup.setVisibility(View.VISIBLE);
                RadioButton1.setText(getString(R.string.answer9));
                RadioButton2.setText(getString(R.string.answer5));
                RadioButton3.setText(getString(R.string.answer4));
                RadioButton4.setText(getString(R.string.answer12));
                break;
            case 7:
                setCheckboxesInvisible(true);
                setETextInvisible(true);
                image.setImageResource(R.mipmap.science10_3d);
                radioGroup.setVisibility(View.VISIBLE);
                RadioButton1.setText(getString(R.string.answer17));
                RadioButton2.setText(getString(R.string.answer18));
                RadioButton3.setText(getString(R.string.answer19));
                RadioButton4.setText(getString(R.string.answer20));
                break;
            case 8:
                setCheckboxesInvisible(true);
                setETextInvisible(true);
                image.setImageResource(R.mipmap.science11_dnn);
                radioGroup.setVisibility(View.VISIBLE);
                RadioButton1.setText(getString(R.string.answer3));
                RadioButton2.setText(getString(R.string.answer9));
                RadioButton3.setText(getString(R.string.answer1));
                RadioButton4.setText(getString(R.string.answer8));
                break;
            case 9:
                setCheckboxesInvisible(true);
                setETextInvisible(false);
                image.setImageResource(R.mipmap.science11_iiot);
                radioGroup.setVisibility(View.INVISIBLE);
                break;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        if (!(current_question == 5 || current_question == 9)) {
                            checked_radiobutton = 4;
                        }
                        break;
                    case R.id.rb1:
                        checked_radiobutton = 0;
                        break;
                    case R.id.rb2:
                        checked_radiobutton = 1;
                        break;
                    case R.id.rb3:
                        checked_radiobutton = 2;
                        break;
                    case R.id.rb4:
                        checked_radiobutton = 3;
                        break;

                    default:
                        checked_radiobutton = 4;
                        break;
                }
            }
        });
    }

    public void nextQuestion(View view) {

        if (numOfCompletedAnswers > 10) {
            return;
        }
        if (checked_radiobutton == 4) {
            Toast.makeText(getApplicationContext(), "Please answer the question",
                    Toast.LENGTH_SHORT).show();
        } else {
            checkAnswer();
            if (listOfNumsOfQusetions.size() > 0) {
                current_question = listOfNumsOfQusetions.get((int) (Math.random() * listOfNumsOfQusetions.size()));
                inflateContent(current_question);
                ind = findViewById(indicators[finalResults.size()]);
                ind.setBackgroundColor(Color.YELLOW);
            }
            changeStatus();
            radioGroup.clearCheck();

            if (numOfCompletedAnswers >= 10) {
                setResultsOnScreen();
                return;
            }

            currentQuestion = findViewById(R.id.txtCurrentQuestion);
            numOfCompletedAnswers++;
            currentQuestion.setText(Integer.toString(numOfCompletedAnswers));
            if (numOfCompletedAnswers == 10) {
                submitButton = findViewById(R.id.answer_question);
                submitButton.setText("Submit");
            }
        }
    }

    public void setResultsOnScreen() {
        if (numOfCompletedAnswers >= 10) {
            radioGroup.setVisibility(View.INVISIBLE);
            setCheckboxesInvisible(true);
            setETextInvisible(true);
            submitButton.setVisibility(View.INVISIBLE);
            image.setImageResource(R.mipmap.science_white);
            textQuestion.setText(getString(R.string.finish));
            TextView hidden = findViewById(R.id.hidden_result);
            hidden.setVisibility(View.VISIBLE);
            String resultText = getString(R.string.result1part);
            resultText = resultText + " " + numOfRightAnswers + "/10.";
            if (numOfRightAnswers > 7) {
                resultText += getString(R.string.result2part1);
            } else if (numOfRightAnswers < 5) {
                resultText += getString(R.string.result2part3);
            } else {
                resultText += getString(R.string.result2part2);
            }
            hidden.setText(resultText);
        }
    }

    public void checkAnswer() {

        switch (current_question) {
            case 9:
                String eTextAnswer = eText.getText().toString();
                eTextAnswer = eTextAnswer.toLowerCase();
                eTextAnswer = eTextAnswer.replaceAll("\\s+", "");
                if (eTextAnswer.compareTo(getString(R.string.answer21)) == 0) {
                    finalResults.add(true);
                    numOfRightAnswers++;
                    checked_radiobutton = 0;
                } else {
                    finalResults.add(false);
                    checked_radiobutton = 0;
                }
                break;
            case 5:
                if (cb1.isChecked() && cb4.isChecked()) {
                    finalResults.add(true);
                    numOfRightAnswers++;
                    checked_radiobutton = 0;
                } else {
                    finalResults.add(false);
                    checked_radiobutton = 0;
                }
                break;
            default:
                if (checked_radiobutton == 4) break;
                if (checked_radiobutton == correctAnswers.get(current_question)) {
                    finalResults.add(true);
                    numOfRightAnswers++;
                } else {
                    finalResults.add(false);
                }
                checked_radiobutton = 4;
                break;
        }
        questions.remove(current_question);
        listOfNumsOfQusetions.remove(listOfNumsOfQusetions.indexOf(current_question));
    }

    public void setCheckboxesInvisible(boolean invisible) {

        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        if (invisible) {
            cb1.setVisibility(View.INVISIBLE);
            cb2.setVisibility(View.INVISIBLE);
            cb3.setVisibility(View.INVISIBLE);
            cb4.setVisibility(View.INVISIBLE);
        } else {
            cb1.setVisibility(View.VISIBLE);
            cb2.setVisibility(View.VISIBLE);
            cb3.setVisibility(View.VISIBLE);
            cb4.setVisibility(View.VISIBLE);
        }
    }

    public void setETextInvisible(boolean invisible) {
        eText = findViewById(R.id.edit_text_field);
        if (invisible) {
            eText.setVisibility(View.INVISIBLE);
        } else {
            eText.setVisibility(View.VISIBLE);
        }
    }

    public void changeStatus() {

        for (int i = 0; i < finalResults.size(); i++) {
            ind = findViewById(indicators[i]);
            if (finalResults.get(i)) {
                ind.setBackgroundColor(Color.GREEN);
            } else {
                ind.setBackgroundColor(Color.RED);
            }
        }
        if (listOfNumsOfQusetions.size() > 0) {
            ind = findViewById(indicators[finalResults.size()]);
            ind.setBackgroundColor(Color.YELLOW);
        }
    }
}
