/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Quaternion;
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
        direction = app.getCamera().getDirection().normalizeLocal();
        if(app.getInputAppState().
                getSpeed()!=0.0f) {
            app.getCarNode().rotate(0.0f, app.getInputAppState().getAngle() * tpf, 0.0f);
        }
        app.getCarNode().move(direction.multLocal(app.getInputAppState().
                getSpeed() * tpf));
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
}
