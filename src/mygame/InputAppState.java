/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;

/**
 *
 * @author duo
 */
public class InputAppState 
    extends AbstractAppState 
    implements ActionListener, AnalogListener {
    private InputManager inputManager;
    private boolean left;
    private boolean right;
    private boolean foward;
    private boolean backward;

    public enum InputMapping {
        RotateLeft, RotateRight, MoveFoward, MoveBackward;
    }

    private void addInputMappings() {
        inputManager.addMapping(InputMapping.RotateLeft.name(), 
                new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping(InputMapping.RotateRight.name(), 
                new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping(InputMapping.MoveFoward.name(), 
                new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping(InputMapping.MoveBackward.name(), 
                new KeyTrigger(KeyInput.KEY_DOWN));
        for(InputMapping i : InputMapping.values()) {
            inputManager.addListener(this, i.name());
        }
    }

    @Override
    public void initialize(AppStateManager appStateManager, 
            Application app) {
        this.inputManager = app.getInputManager();
        addInputMappings();
    }
    
    @Override
    public void onAnalog(String name, float value, float tpf) {
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if(name.equals(InputMapping.MoveFoward.name())) {
            foward = true;
        } else if(name.equals(InputMapping.MoveBackward.name())) {
            backward = true;
        }
        else if(name.equals(InputMapping.RotateRight.name())) {
            right = true;
        }
        else if(name.equals(InputMapping.RotateLeft.name())) {
            left = true;
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        for(InputMapping i : InputMapping.values()) {
            if(inputManager.hasMapping(i.name())) {
                inputManager.deleteMapping(i.name());
            }
            inputManager.removeListener(this);
        }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isFoward() {
        return foward;
    }

    public void setFoward(boolean foward) {
        this.foward = foward;
    }

    public boolean isBackward() {
        return backward;
    }

    public void setBackward(boolean backward) {
        this.backward = backward;
    }

}
