package com.zebra.game;

import com.zebra.game.controller.State.BaseState;

import java.util.Stack;
//updates the state of the game continuously from the myZebraGame render method
public class GameStateManager {
    //makes a myZebra game object
    private MyZebraGame myzebragame;
    //makes a baseState object
    private Stack<BaseState> baseStates;
    //makes a singelton object of the gsm
    private static GameStateManager instance;

    //constructor to initialize objects
    public GameStateManager(MyZebraGame myzebragame) {
        baseStates = new Stack<BaseState>();
        this.myzebragame = myzebragame;
    }

    //singelton method to check if other instances has been created
    public static GameStateManager getInstance(MyZebraGame myzebragame) {
        if (instance == null) {
            return new GameStateManager(myzebragame);
        }
        return instance;
    }

    //gets the myZebraGame
    public MyZebraGame getMyZebraGame(){
        return myzebragame;
    }
    //pushes states to the stack of states
    public void push(BaseState baseState) {
        baseStates.push(baseState);
    }
    //pops states from the stack of states
    public void pop() {
        baseStates.pop();
    }
    //sets a new state to the stack of states
    public void set(BaseState baseState) {
        baseStates.pop();
        baseStates.push(baseState);
    }

    //gets the current state
    public BaseState getState(){
        return baseStates.peek();
    }
    //updates the current state
    public void update(float dt) {
        baseStates.peek().update(dt);
    }
}