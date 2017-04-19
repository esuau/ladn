package fr.ladn.carsharingclub.ing1.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * The class XML.
 * Allows serialisation operations on containers.
 */
public abstract class XML {

    /**
     * Deserializes an object from a string.
     *
     * @param str the received string.
     * @return an instance of the object.
     */
    public static Container parse(String str) {
        XMLDecoder d = new XMLDecoder(new ByteArrayInputStream(str.getBytes()));
        Container container = (Container) d.readObject();
        d.close();
        return container;
    }

    /**
     * Serialises an object.
     *
     * @param container the object to stringify.
     * @return a serialised object.
     */
    public static String stringify(Container container) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder e = new XMLEncoder(baos);
        e.writeObject(container);
        e.close();
        return new String(baos.toByteArray());
    }

}
