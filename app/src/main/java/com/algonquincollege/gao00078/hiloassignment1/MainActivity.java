/**
 * Purpose/Description of your app
 * This is guessing number game app
 * Kai Gao(gao00078@algonquinlive.com)
 */
package com.algonquincollege.gao00078.hiloassignment1;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    // CLASS VARIABLES (by convention, class vars in upper-case)
    private static final String ABOUT_DIALOG_TAG = "About Dialog";

    private EditText userGuessNumEditText;
    private TextView numOfGuessTextView;
    private Button btnGuess;
    private Button btnReset;
    //generate a random number between 1 and 1000
    Random r = new Random();
    private int Low = 1;
    private int High = 1000;
    private int theNumber = r.nextInt(High - Low) + Low;

    //counter for the times of guessing
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to the EditText field, button Guess and button Reset
        userGuessNumEditText = (EditText) findViewById(R.id.userGuessNum);
        numOfGuessTextView = (TextView) findViewById(R.id.numOfGuess);
        btnGuess = (Button) findViewById(R.id.btnGuess);
        btnReset = (Button) findViewById(R.id.btnReset);


        // register an anonymous inner class as the event handler for button guess
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userGuessNum = userGuessNumEditText.getText().toString();
                int userGuessNumInteger;
                // Validate the userGuessNum
                // Rule: the username is mandatory which cannot be empty
                if (userGuessNum.isEmpty()) {
                    userGuessNumEditText.setError("Please enter a guess number");
                    userGuessNumEditText.requestFocus();
                    return;
                }
                // Rule: input must be a number, reject all other input
                try {
                    userGuessNumInteger = Integer.parseInt(userGuessNum);

                } catch (Exception e) {
                    userGuessNumEditText.setError("Please enter a valid number between 1 to 1000");
                    userGuessNumEditText.requestFocus();
                    return;
                }
                // Rule: a valid number between 1 to 1000
                if (userGuessNumInteger < 1 || userGuessNumInteger > 1000) {
                    userGuessNumEditText.setError("Please enter a valid number between 1 to 1000");
                    userGuessNumEditText.requestFocus();
                    return;
                }

                counter++;

                if (counter > 10) {
                    counter = 0;
                    theNumber = r.nextInt(High - Low) + Low;
                    btnGuess.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "Please Reset!", Toast.LENGTH_LONG).show();
                }
                //compare user guess with theNumber
                if (userGuessNumInteger == theNumber) {
                    if (counter >= 6) {
                        Toast.makeText(getApplicationContext(), "Excellent Win!", Toast.LENGTH_LONG).show();
                        counter = 0;
                        theNumber = r.nextInt(High - Low) + Low;
                        btnGuess.setEnabled(false);
                    } else {
                        Toast.makeText(getApplicationContext(), "Superior Win!", Toast.LENGTH_LONG).show();
                        counter = 0;
                        theNumber = r.nextInt(High - Low) + Low;
                        btnGuess.setEnabled(false);
                    }
                } else if (userGuessNumInteger > theNumber) {
                    userGuessNumEditText.setText("");
                    Toast.makeText(getApplicationContext(), "The guess is too high!", Toast.LENGTH_SHORT).show();
                } else {
                    userGuessNumEditText.setText("");
                    Toast.makeText(getApplicationContext(), "The guess is too low!", Toast.LENGTH_SHORT).show();

                }
                numOfGuessTextView.setText("Guess times: " + counter);


            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                theNumber = r.nextInt(High - Low) + Low;
                btnGuess.setEnabled(true);
                numOfGuessTextView.setText("Guess times: " + counter);
                userGuessNumEditText.setText("");


            }

        });

        btnReset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(), "theNumber is: " + theNumber, Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
