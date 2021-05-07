package com.zebra.game;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zebra.game.model.Player;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AndroidInterfaceClass implements FireBaseInterface {

    FirebaseDatabase database;

    DatabaseReference myRef;
    DatabaseReference hsRef;


    public AndroidInterfaceClass() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Games");
        hsRef = database.getReference("HighScore");
    }



    @Override
    public void FirstFirebaseTest () {
        if (myRef != null) {
            myRef.setValue("frrrr");
            } else {
        }
    }


    @Override
    public void SetOnValueChangeListener (final DataHolderClass dataholder){
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void SetValueInDb (String target, String value){
        myRef = database.getReference(target);
        myRef.setValue(value);
    }

    //Called upon when a new game is created and creates a unique gameid under
    //"Games" in Firebase and inserte player name and score
    @Override
    public void createGameDb(Player player, final DataHolderClass dataholder) {
        final ArrayList<Player> playersList = new ArrayList<Player>();

        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
        String gameId = myRef.push().getKey().toUpperCase();
        dataholder.setGamePin(gameId);
        dataholder.addPlayer(player);
        playersList.add(player);


        // pushing user to 'users' node using the userId
        myRef.child(gameId).setValue(playersList);
    }

    //Called upon when a user wants to challenge another player by using an existing
    //game pin. The function checks if the code exists in Firebase and updates dataholder if correct
    public void checkCodeDb(final String gameId, final DataHolderClass dataholder){
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Games");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    String code = datas.getKey();

                    if (code.equals(gameId)) {
                        dataholder.setGamePin(code);
                        dataholder.setChallengerName(datas.child("players").child("0").child("name").getValue().toString());
                        dataholder.setChallengerScore(datas.child("players").child("0").child("score").getValue().toString());


                    } else {

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    //Called upon when a player have played a game, entering an existing game pin.
    //The function inserts this new player to the gamepin in Firebase, making it
    //easier to compare scores
    public void createPlayerDb(final Player chPlayer, final DataHolderClass dataholder) {
        final int data = dataholder.getPlayers().size();
        final ArrayList<Player> playersList = new ArrayList<Player>();

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Games");
        ValueEventListener valid_code = ref.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot datas : snapshot.getChildren()) {
                        String code = datas.getKey();

                        if (code.equals(dataholder.getGamePin()) && dataholder.getPlayers().size() <= data) {
                            final Player dbPlayer = new Player(
                                    datas.child("players").child("0").child("name").getValue().toString(),
                                    datas.child("players").child("0").child("score").getValue().toString()
                            );

                            //Adding new player info to a new player object in dataholder
                            chPlayer.setName(dataholder.getPlayerName());
                            chPlayer.setScore(dataholder.getPlayerScore());
                            dataholder.addPlayer(chPlayer);

                            //Gets name of game creator from Firebase
                            dbPlayer.setName(datas.child("players").child("0").child("name").getValue().toString());

                            playersList.add(dbPlayer);
                            playersList.add(chPlayer);

                            //Adding a new player from input in gameOverView to firebase to correct gamepin address
                            myRef.child(dataholder.getGamePin()).setValue(playersList);

                        }
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    //Called upon after finished game and updates high score list
    @Override
    public void updateHighScore(final Player player, final DataHolderClass dataholder) {
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        Task<DataSnapshot> datasnapshot = hsRef.get();
        datasnapshot.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()) {

                    final ArrayList<Player> highScoreListDb = new ArrayList<Player>();
                    final ArrayList<String> hs = new ArrayList<String>();


                    for (DataSnapshot datas : task.getResult().getChildren()) {
                        String name = datas.child("players").child("0").child("name").getValue().toString();
                        String score = datas.child("players").child("0").child("score").getValue().toString();
                        highScoreListDb.add(new Player(name, score));
                    }

                    for (int i = 0; i < highScoreListDb.size(); i++) {
                        //getHslistscore(highScoreListDb, i)
                        if (Integer.parseInt(player.getScore()) > Integer.parseInt(highScoreListDb.get(i).getScore()) && !highScoreListDb.contains(player)) {
                            //highScoreListDb.push(dataholder.getPlayer());
                            highScoreListDb.add(i, player);
                            highScoreListDb.remove(3);
                        }
                    }

                    for (int i = 0; i < highScoreListDb.size(); i++) {
                        hsRef.child(Integer.toString(i + 1)).child("players").child("0").child("name").setValue(highScoreListDb.get(i).getName());
                        hsRef.child(Integer.toString(i + 1)).child("players").child("0").child("score").setValue(highScoreListDb.get(i).getScore());
                    }

                    dataholder.setHsScore1(highScoreListDb.get(0).getName() + ":   " + highScoreListDb.get(0).getScore());
                    dataholder.setHsScore2(highScoreListDb.get(1).getName() + ":   " + highScoreListDb.get(1).getScore());
                    dataholder.setHsScore3(highScoreListDb.get(2).getName() + ":   " + highScoreListDb.get(2).getScore());
                }
                ;
            }

        });
    }

    //Initializes high score list
            public void setHSList(final DataHolderClass dataholder) {

                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Task<DataSnapshot> datasnapshot = hsRef.get();
                datasnapshot.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            final ArrayList<Player> highScoreListDb = new ArrayList<Player>();

                            for (DataSnapshot datas : task.getResult().getChildren()) {
                                String name = datas.child("players").child("0").child("name").getValue().toString();
                                String score = datas.child("players").child("0").child("score").getValue().toString();
                                highScoreListDb.add(new Player(name, score));
                            }

                            dataholder.setHsScore1(highScoreListDb.get(0).getName() + ":   " + highScoreListDb.get(0).getScore());
                            dataholder.setHsScore2(highScoreListDb.get(1).getName() + ":   " + highScoreListDb.get(1).getScore());
                            dataholder.setHsScore3(highScoreListDb.get(2).getName() + ":   " + highScoreListDb.get(2).getScore());
                        }
                    }
                });
            }

};
