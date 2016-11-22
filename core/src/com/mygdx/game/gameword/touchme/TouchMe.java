/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.gameword.touchme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.gameword.GameWorld;
import com.mygdx.game.ichelpers.touchme.CheckPoint;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Hugo Da Roit
 */
public class TouchMe extends GameWorld {
    private final ArrayList<CheckPoint> checkPoints;
    
    public TouchMe() {
        this.gameRenderer = new TouchMeRenderer(this);
        checkPoints = new ArrayList<CheckPoint>();
        fillCheckPoints();
        started = false;
    }
    
    @Override
    public void update(float delta) {
        if(started) {
            gameRenderer.render(delta);
            if(Gdx.input.isKeyJustPressed(Keys.SPACE))
                checkPoints.remove(0);
            if(timer <= System.currentTimeMillis() || checkPoints.isEmpty())
                Gdx.app.exit();
        } else {
            gameRenderer.renderWait(delta);
            if(Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
                this.timer = System.currentTimeMillis() + 20000;
                started = true;
            }
        }
    }

    @Override
    public void dispose() {
        gameRenderer.dispose();
    }

    // Lecture d'un fichier XML contenant la position et l'identifiant des checkpoints, on mets tout dans une liste.
    private void fillCheckPoints() {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(Gdx.files.internal("./TouchMe/map1.xml").path()));
            final Element racine = document.getDocumentElement();
            final NodeList checkPointsNodes = racine.getChildNodes();
            for(int i = 0 ; i < checkPointsNodes.getLength() ; i++) {
                if(checkPointsNodes.item(i).getNodeType() == Node.ELEMENT_NODE) { // Pour chaque check points
                    Element checkPointElement = (Element) checkPointsNodes.item(i);
                    int id = Integer.parseInt(checkPointElement.getElementsByTagName("id").item(0).getTextContent());
                    Element position = (Element) checkPointElement.getElementsByTagName("position").item(0);
                    float x = Float.parseFloat(position.getElementsByTagName("x").item(0).getTextContent());
                    float y = Float.parseFloat(position.getElementsByTagName("y").item(0).getTextContent());
                    checkPoints.add(new CheckPoint(id, new Vector2(x, y)));
                }
            }
        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<CheckPoint> getCheckPoints() {
        return checkPoints;
    }

    @Override
    public long getTimer() {
        return timer;
    }
    
    @Override
    public boolean isStarted() {
        return started;
    }
}