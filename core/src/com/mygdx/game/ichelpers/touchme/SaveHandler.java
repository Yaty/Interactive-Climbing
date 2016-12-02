/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.ichelpers.touchme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Hugo Da Roit - contact@hdaroit.fr
 */
public class SaveHandler {
    private String nameLastSave;

    public void save(ArrayList<CheckPoint> checkpoints) {
        try {
            StringWriter writer = new StringWriter();
            writer.write("<?xml version = \"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n");
            XmlWriter xml = new XmlWriter(writer);
            XmlWriter racine =  xml.element("touchme");
            for(CheckPoint checkpoint : checkpoints) {
                racine.element("checkpoint")
                    .element("id")
                        .text(checkpoint.getId())
                    .pop()
                    .element("position")
                        .element("x")
                            .text(checkpoint.getPosition().x)
                        .pop()
                        .element("y")
                            .text(checkpoint.getPosition().y)
                        .pop()
                        .element("hauteur")
                            .text(checkpoint.getHauteur())
                        .pop()
                        .element("largeur")
                            .text(checkpoint.getLargeur())
                        .pop()
                    .pop()
                .pop();
            }
            racine.pop();            
            writer.close();
            FileHandle file = Gdx.files.local("./TouchMe/" + findNewName());
            Writer writer2 = file.writer(false);
            writer2.write(writer.toString());
            writer2.close();
        } catch (IOException ex) {
            Gdx.app.error("SaveHandler", ex.getStackTrace().toString());
        }
    }
    
    private String findNewName() {
        File folder = new File("./TouchMe/");
        File[] listOfFiles = folder.listFiles();
        int max = 1;
        int choix = -1;
        for(int i = 0 ; i < listOfFiles.length ; i++) {
            if(Integer.parseInt(listOfFiles[i].getName().replaceAll("[^0-9]", "")) > max) {
                max = Integer.parseInt(listOfFiles[i].getName().replaceAll("[^0-9]", ""));
                choix = i;
            }
        }
        if(choix != -1) {
            String name = listOfFiles[choix].getName();
            String id = name.replaceAll("[^0-9]", "");
            return "map" + (Integer.parseInt(id)+1) + ".xml";            
        } else {
            return "map1.xml";
        }
    }

    public String getNameLastSave() {
        return nameLastSave;
    }

    public ArrayList<CheckPoint> load(String path) {
        ArrayList<CheckPoint> checkPoints = new ArrayList();
        Texture textureCheckpoint = new Texture(Gdx.files.internal("./checkpoint.png"));
        textureCheckpoint.dispose();
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(Gdx.files.internal("./TouchMe/" + path).path()));
            final Element racine = document.getDocumentElement();
            final NodeList checkPointsNodes = racine.getChildNodes();
            for(int i = 0 ; i < checkPointsNodes.getLength() ; i++) {
                if(checkPointsNodes.item(i).getNodeType() == Node.ELEMENT_NODE) { // Pour chaque check points
                    Element checkPointElement = (Element) checkPointsNodes.item(i);
                    int id = Integer.parseInt(checkPointElement.getElementsByTagName("id").item(0).getTextContent());
                    Element position = (Element) checkPointElement.getElementsByTagName("position").item(0);
                    float x = Float.parseFloat(position.getElementsByTagName("x").item(0).getTextContent());
                    float y = Float.parseFloat(position.getElementsByTagName("y").item(0).getTextContent());
                    float hauteur = Float.parseFloat(position.getElementsByTagName("hauteur").item(0).getTextContent());
                    float largeur = Float.parseFloat(position.getElementsByTagName("largeur").item(0).getTextContent());
                    checkPoints.add(new CheckPoint(id, new Vector2(x, y), hauteur, largeur));
                }
            }
            return checkPoints;
        } catch (final ParserConfigurationException e) {
            Gdx.app.error("SaveHandler", e.getStackTrace().toString());
        } catch (SAXException ex) {
            Gdx.app.error("SaveHandler", ex.getStackTrace().toString());
        } catch (IOException ex) {
            Gdx.app.error("SaveHandler", ex.getStackTrace().toString());
        }
        return null;
    }
    
}
