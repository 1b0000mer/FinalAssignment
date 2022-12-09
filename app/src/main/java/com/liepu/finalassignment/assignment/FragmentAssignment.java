package com.liepu.finalassignment.assignment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.liepu.finalassignment.R;
import com.liepu.finalassignment.assignment.models.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressLint("DefaultLocale")
public class FragmentAssignment extends Fragment implements GameAdapter.ItemClickListener {

    GameAdapter adapter;
    RecyclerView recyclerView;
    int userCount = 0;
    int maxCount = 20;
    public List<Card> deck, newDeck;

    TextView tvTurn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        deck = new ArrayList<>();
        String[] suits = {"spades", "clubs", "diamond", "hearts"};
        String[] values = {"a", "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k"};

        //gen deck
        for (String suit : suits) {
            for (String value : values) {
                deck.add(new Card(suit, value, false, false, String.format("%s%s", suit, value)));
            }
        }

        //gen joker
        deck.add(new Card("joker", "black", true, false, "blackjoker"));
        deck.add(new Card("joker", "red", true, false, "redjoker"));

        tvTurn = view.findViewById(R.id.tv_turn);

        Button reset = view.findViewById(R.id.btn_reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });

        recyclerView = view.findViewById(R.id.rv_assignment);
        int numberOfColumns = 6;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));

        newGame();
    }

    private void newGame() {
        userCount = 0;
        maxCount = 20;
        tvTurn.setText(String.format("Turn Remaining: %d", (maxCount - userCount)));
        //TODO: do shuffle deck
        newDeck = new ArrayList<>();
        for (Card card: deck) {
            card.setTouch(false);
            newDeck.add(card);
        }
        Collections.shuffle(newDeck);

        adapter = new GameAdapter(getContext(), newDeck, false);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, ImageView iv, int position) {
        Card selectedCard = adapter.getItem(position);

        if (!selectedCard.isTouch()) {
            int id = getResources().getIdentifier(selectedCard.getUrl(), "drawable",
                    getContext().getPackageName());
            iv.setImageResource(id);
            userCount += 1;
            selectedCard.setTouch(true);

            if (selectedCard.isJoker()) {
                createDialog("Congratulations!", "You have won!");
            }
            else if (userCount == maxCount) {
                createDialog("Game Over!", "Too Bad! Out of Moves :(");
            }

            tvTurn.setText(String.format("Turn Remaining: %d", (maxCount - userCount)));
        }
    }

    private void createDialog(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("Continue!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                maxCount += 10;
                tvTurn.setText(String.format("Turn Remaining: %d", (maxCount - userCount)));
            }
        });
        builder.setNeutralButton("Try again!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                newGame();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                adapter.setClickListener(null);
                showAllCards();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void showAllCards() {
        recyclerView.setAdapter(new GameAdapter(getContext(), newDeck, true));
    }
}
