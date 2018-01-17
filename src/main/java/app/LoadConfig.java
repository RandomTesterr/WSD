package app;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import wsd.EmergencyAgent;
import wsd.SignAgent;
import wsd.VehicleAgent;

public class LoadConfig {

    public static void loadAgents() {

        try {
            File inputFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            loadSigns(doc.getElementsByTagName("sign"));
            loadVehicles(doc.getElementsByTagName("vehicle"));
            loadEmergencyVehicles(doc.getElementsByTagName("emergency"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadSigns(NodeList signs) {

        for (int temp = 0; temp < signs.getLength(); temp++) {
            Node sign = signs.item(temp);

            if (sign.getNodeType() == Node.ELEMENT_NODE) {
                Element e_sign = (Element) sign;
                String[] args = {
                        "y_begin:" + e_sign.getElementsByTagName("y_begin").item(0).getTextContent(),
                        "y_end:" + e_sign.getElementsByTagName("y_end").item(0).getTextContent(),
                        "maxSpeed:" + e_sign.getElementsByTagName("max_speed").item(0).getTextContent()
                };

                CarsApplication.createAgent(e_sign.getAttribute("name"),
                        SignAgent.class.getName(),
                        args);
            }
        }
    }

    private static void loadVehicles(NodeList vehicles) {

        for (int temp = 0; temp < vehicles.getLength(); temp++) {
            Node vehicle = vehicles.item(temp);

            if (vehicle.getNodeType() == Node.ELEMENT_NODE) {
                Element e_vehicle = (Element) vehicle;
                String[] args = {
                        "speed:" + e_vehicle.getElementsByTagName("speed").item(0).getTextContent(),
                        "maxSpeed:" + e_vehicle.getElementsByTagName("speed").item(0).getTextContent()
                };

                CarsApplication.createAgent(e_vehicle.getAttribute("name"),
                        VehicleAgent.class.getName(),
                        args);
            }
        }
    }

    private static void loadEmergencyVehicles(NodeList emergency_vehicles) {

        for (int temp = 0; temp < emergency_vehicles.getLength(); temp++)
        {
            Node emergency_vehicle = emergency_vehicles.item(temp);

            if (emergency_vehicle.getNodeType() == Node.ELEMENT_NODE)
            {
                Element e_emergency_vehicle = (Element) emergency_vehicle;
                String[] args = {
                        "speed:" + e_emergency_vehicle.getElementsByTagName("speed").item(0).getTextContent(),
                        "maxSpeed:" + e_emergency_vehicle.getElementsByTagName("speed").item(0).getTextContent()
                };

                CarsApplication.createAgent(e_emergency_vehicle.getAttribute("name"),
                        EmergencyAgent.class.getName(),
                        args);
            }
        }
    }
}
