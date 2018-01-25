package com.example.charliesmart.payandcollect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

public class MainActivity extends AppCompatActivity {

    CardInputWidget mCardInputWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupCardWidget();

    }


    public void setupCardWidget(){

        mCardInputWidget = findViewById(R.id.card_input_widget);

        Card cardToSave = mCardInputWidget.getCard();
        if (cardToSave == null) {
           // mErrorDialogHandler.showError("Invalid Card Data");
        }

        cardToSave.setName("Customer Name");

        Stripe stripe = new Stripe(this, "pk_test_6pRNASCoBOKtIshFeQd4XMUh");
        stripe.createToken(
          cardToSave,
          new TokenCallback() {
            public void onSuccess(Token token) {
              // Send token to your server
            }
            public void onError(Exception error) {
              // Show localized error message
            }
          }
        );
    }
}
