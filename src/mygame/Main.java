package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;

/**
 * @author duodecimo
 */
public class Main extends SimpleApplication {
    private Node carNode;
    private Spatial car;
    private CameraNode cameraNode;
    private float carSpeed;
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
        car = assetManager.loadModel("Models/Carroblend01.j3o");
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        car.setMaterial(mat);
        car.setCullHint(Spatial.CullHint.Never);
        car.scale(2.0f);
        Quaternion quaternion = new Quaternion().fromAngleAxis(-FastMath.HALF_PI, Vector3f.UNIT_Y);
        car.rotate(quaternion);
        car.setLocalTranslation(0.0f, -0.5f, -0.0f);
        carNode = new Node("carNode");
        carNode.attachChild(car);
        carNode.setLocalTranslation(0.0f, -1.0f, 0.0f);
        carNode.addControl(new TerrainTrackControl());
        carNode.addControl(new MovementControl(this));
        cameraNode = new CameraNode("cameraNode", cam);
        carNode.attachChild(cameraNode);
        cameraNode.setLocalTranslation(new Vector3f(-3.0f, 0.0f, 0.0f));
        cameraNode.lookAt(carNode.getLocalTranslation(), Vector3f.UNIT_Y);
        //cameraNode.setLocalTranslation(new Vector3f(0.0f, 0.002f, 0.0f));
        cameraNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        //flyCam.setEnabled(false);
        carSpeed = 0.0f;
        rootNode.attachChild(carNode);
    }

    @Override
    public void simpleUpdate(float tpf) {
        super.simpleUpdate(tpf);
    }

    public CameraNode getCameraNode() {
        return cameraNode;
    }

    public float getCarSpeed() {
        return carSpeed;
    }

    public InputAppState getInputAppState() {
        return inputAppState;
    }

    public Spatial getCar() {
        return car;
    }

    public Node getCarNode() {
        return carNode;
    }

}
