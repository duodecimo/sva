package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Spatial;

/**
 * @author duodecimo
 */
public class Main extends SimpleApplication {
    private CameraNode cameraNode;
    private float cameraSpeed;
    private InputAppState inputAppState;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
        
    }

    public Main() {
        super(new ConfigAppState());
    }

    @Override
    public void simpleInitApp() {
        Spatial gameLevel = assetManager.loadModel("Scenes/scene.j3o");
        gameLevel.setLocalTranslation(0, -5.2f, 0);
        gameLevel.setLocalScale(2);
        rootNode.attachChild(gameLevel);
        inputAppState = new InputAppState();
        stateManager.attach(inputAppState);
        cameraNode = new CameraNode("cameraNode", getCamera());
        cameraNode.addControl(new TerrainTrackControl());
        cameraNode.addControl(new MovementControl(this));
        cameraSpeed = 0.0f;
        rootNode.attachChild(cameraNode);
    }

    @Override
    public void simpleUpdate(float tpf) {
        super.simpleUpdate(tpf);
        if(getFlyByCamera() != null && getFlyByCamera().isEnabled()) {
            getFlyByCamera().setEnabled(false);
        }
    }

    public CameraNode getCameraNode() {
        return cameraNode;
    }

    public float getCameraSpeed() {
        return cameraSpeed;
    }

    public InputAppState getInputAppState() {
        return inputAppState;
    }

}
