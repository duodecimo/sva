/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author duo
 */
public class MovementControl extends AbstractControl {
    Main app;
    Vector3f direction;

    public MovementControl(Main app) {
        this.app = app;
    }

    @Override
    protected void controlUpdate(float tpf) {
        if(app.getInputAppState().isFoward()) {
            direction = app.getCamera().getDirection().normalizeLocal();
            app.getCameraNode().move(direction.multLocal(15 * tpf));
            app.getInputAppState().setFoward(false);
        }
        if(app.getInputAppState().isBackward()) {
            direction = app.getCamera().getDirection().normalizeLocal();
            app.getCameraNode().move(direction.multLocal(-15 * tpf));
            app.getInputAppState().setBackward(false);
        }
        if(app.getInputAppState().isLeft()) {
            app.getCameraNode().rotate(0, 5 * tpf, 0);
            app.getInputAppState().setLeft(false);
        }
        if(app.getInputAppState().isRight()) {
            app.getCameraNode().rotate(0, -5 * tpf, 0);
            app.getInputAppState().setRight(false);
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
}
