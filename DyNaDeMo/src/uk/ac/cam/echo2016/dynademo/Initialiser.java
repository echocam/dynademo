
package uk.ac.cam.echo2016.dynademo;

import static uk.ac.cam.echo2016.dynademo.MainApplication.CHARHEIGHT;

import java.util.ArrayList;

import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.shadow.PointLightShadowRenderer;

/**
 * @author tr393
 */
public class Initialiser {
    
    /**
     * 
     * @param app - required for event subscribing and renderer attaching
     * @return 
     */
    public static ArrayList<DemoRoute> initialiseRoutes(MainApplication app) {
        ArrayList<DemoRoute> routes = new ArrayList<DemoRoute>();
        
        DemoRoute route;
        DemoLocEvent e;

        // First Route
        route = new DemoRoute("StartRoute", "Scenes/Scene1.j3o", new Vector3f(0, (CHARHEIGHT / 2) + 2.5f, 0), new Vector3f(1, 0, 0));
        
        Vector3f[] lightCoords = {
            new Vector3f(0f,6f,0f),
            new Vector3f(25f,6f,0f),
//            new Vector3f(-25f,6f,0f),
//            new Vector3f(0f,6f,-30f),
//            new Vector3f(25f,6f,-30f),
//            new Vector3f(-25f,6f,-30f),
//            
//            new Vector3f(25f,6f,-15f),
//            new Vector3f(-25f,6f,-15f),
//            
//            new Vector3f(-60f,6f,0f),
//            new Vector3f(-60f,6f,-30f)
        };
        String[][] spatialNames = {
        	{"Room","Room.001"},
        	{"Room.004","Room.001"}
        };
        
        for(int i = 0; i< spatialNames.length; ++i) {
            PointLight l = new PointLight();
            l.setColor(ColorRGBA.Gray);
            l.setPosition(lightCoords[i]);
            l.setRadius(1000f);
            
            route.lights.add(new DemoLight(l, spatialNames[i]));
            
            PointLightShadowRenderer plsr = new PointLightShadowRenderer(app.getAssetManager(), 1024);
            plsr.setLight(l);
            plsr.setFlushQueues(false);
            plsr.setShadowIntensity(0.1f);
            route.shadowRenderers.add(plsr);
//            viewPort.addProcessor(plsr);
        }
        
        // Starting meeting Event
        e = new DemoLocEvent(0, new Vector3f(-80, 1, -40), 40, 14, 50); 
        e.listeners.add(app);
        route.events.add(e);
        routes.add(route);

        // Second Route
        route = new DemoRoute("Parkour", "Scenes/Scene2.j3o", new Vector3f(0, (CHARHEIGHT / 2) + 2.5f, 0), new Vector3f(-1, 0, 0));
        routes.add(route);
        
        return routes;
    }
}
