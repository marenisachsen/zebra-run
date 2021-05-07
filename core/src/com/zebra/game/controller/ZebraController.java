package com.zebra.game.controller;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.zebra.game.SoundManager;
import com.zebra.game.model.ZebraState;
import com.zebra.game.model.ZebraModel;
import com.zebra.game.controller.Animation.Animate;
import com.zebra.game.controller.Animation.Slide;
import com.zebra.game.controller.Animation.Run;
import com.zebra.game.controller.Animation.Jump;
import com.zebra.game.controller.Animation.Dizzy;
import com.zebra.game.controller.Animation.Die;


public class ZebraController {

    private ZebraModel zebramodel;

    public boolean controllJump= false;
    public boolean controllSlide = false;
    public boolean controllDizzy = false;
    public boolean controllDie = false;
    public boolean controllPoof = false;
    public boolean controllCurtain = false;


    public ZebraController(ZebraModel zebramodel) {
        this.zebramodel = zebramodel;
    }


    public ZebraModel getZebramodel() {
        return zebramodel;
    }

    //updates the position of the zebra with the zebras given velocity
    public void updateZebraPosition() {
        zebramodel.getPosition().add(zebramodel.getVelocity().x, zebramodel.getVelocity().y);
    }

    //updates the position of the rectangle to the zebra
    public void updateRectangle() {
        zebramodel.getRectangle().setPosition(zebramodel.getPosition().x, zebramodel.getPosition().y);
    }

    //updates the animation to the zebra
    public void updateCurrentFrame() {
        zebramodel.getZebra().updateCurrentFrame();
    }

    //controlles the animations and will switch between states when the animation is finished
    public void animationListener(){
        if(zebramodel.getZebra().isAnimationFinished() && zebramodel.getZebraState() == ZebraState.SLIDE){
            zebraRun();
        }

        if(zebramodel.getZebra().isAnimationFinished() && zebramodel.getZebraState()== ZebraState.DIZZY){
            zebraRun();
            controllPoof = false;
        }

        if(zebramodel.getZebra().isAnimationFinished() && zebramodel.getZebraState() == ZebraState.DIE) {
            controllPoof = false;
            controllCurtain = true;
        }
    }

    //main update function
    public void update() {
        updateRectangle();
        updateCurrentFrame();
        updateZebraPosition();
        animationListener();
    }

    //zebraRun makes the zebra animation run
    public void zebraRun(){
        zebramodel.setZebraState(ZebraState.RUN);
        zebramodel.setPosition(zebramodel.ZEBRA_X, zebramodel.ZEBRA_Y);
        Animate zebra = new Run();
        zebramodel.setZebra(zebra);
        zebramodel.setRectangle(new Rectangle((int) zebramodel.getPosition().x,(int) zebramodel.getPosition().y, zebramodel.ZEBRA_WIDTH-25, zebramodel.ZEBRA_HEIGHT));
    }

    //zebraJump makes the zebras animation jump, and moves the zebra to y-pos 250 and back to start pos when the right side of the screen is touched
    public void zebraJump() {
        if (zebramodel.getZebraState() == ZebraState.DIE) {
            controllJump = false;
        }
        else {
            SoundManager.playJumpSound();
            controllJump = true;
            zebramodel.setZebraState(ZebraState.JUMP);
            if (zebramodel.getPosition().y == zebramodel.ZEBRA_Y) {
                Animate zebra = new Jump();
                zebramodel.setZebra(zebra);
                zebramodel.setVelocity(new Vector2(0, 3.5f));
            }

            if (zebramodel.getPosition().y >= zebramodel.ZEBRA_Y + 250.0 && zebramodel.getVelocity().y >= 0) {
                zebramodel.setVelocity(new Vector2(0, -3.5f));
            }

            if (zebramodel.getPosition().y < zebramodel.ZEBRA_Y + 10 && zebramodel.getVelocity().y < 0) {
                controllJump = false;
                zebramodel.setVelocity(new Vector2(0, 0));
                zebramodel.setPosition(new Vector2(zebramodel.ZEBRA_X, zebramodel.ZEBRA_Y));
                zebramodel.setZebraState(ZebraState.RUN);
                Animate zebra = new Run();
                zebramodel.setZebra(zebra);
            }
        }
    }

    //zebraDie will make the zebras animation die before the game ends
    public void zebraDie() {
        SoundManager.playGameOverSound();
        controllDie = true;
        zebramodel.setZebraState(ZebraState.DIE);
        zebramodel.setPosition(zebramodel.ZEBRA_X, zebramodel.ZEBRA_Y);

        if(controllDie){
            Animate zebra = new Die();
            zebramodel.setZebra(zebra);
            controllDie=false;
        }
    }

    //zebraDizzy will make the zebras animation dizzy when the zebra collide
    public void zebraDizzy() {
        SoundManager.playCollideSound();
        controllDizzy=true;
        controllPoof = true;
        zebramodel.setZebraState(ZebraState.DIZZY);
        zebramodel.setPosition(zebramodel.ZEBRA_X, zebramodel.ZEBRA_Y);

        if(controllDizzy){
            Animate zebra = new Dizzy();
            zebramodel.setZebra(zebra);
            controllDizzy=false;
        }
    }

    //zebraSlide will make the zebras animation slide when the left side of the sceen is touched
    public void zebraSlide() {
        if (zebramodel.getZebraState() == ZebraState.DIE) {
            controllJump = false;
        }

        else {
            SoundManager.playSlideSound();
            controllSlide = true;
            zebramodel.setZebraState(ZebraState.SLIDE);
            zebramodel.setPosition(zebramodel.ZEBRA_X, zebramodel.ZEBRA_Y);
            zebramodel.setRectangle(new Rectangle(zebramodel.getPosition().x, zebramodel.getPosition().y, 1, 1));


            if (controllSlide) {
                Animate zebra = new Slide();
                zebramodel.setZebra(zebra);
                zebramodel.setRectangle(new Rectangle((int) zebramodel.getPosition().x, (int) zebramodel.getPosition().y, zebramodel.ZEBRA_HEIGHT-50, zebramodel.ZEBRA_WIDTH));
                controllSlide = false;
            }
        }
    }

}



