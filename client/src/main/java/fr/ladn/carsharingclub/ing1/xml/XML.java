package fr.ladn.carsharingclub.ing1.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * The class XML.
 * Allows serialisation operations.
 */
public class XML<T> {

    /**
     * Deserializes an object from a string.
     *
     * @param str the received string.
     * @return an instance of the object.
     */
    public T parse(String str) {
        XMLDecoder d = new XMLDecoder(new ByteArrayInputStream(str.getBytes()));
        T obj = (T) d.readObject();
        d.close();
        return obj;
    }

    /**
     * Serialises an object.
     *
     * @param obj the object to stringify.
     * @return a serialised object.
     */
    public String stringify(T obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder e = new XMLEncoder(baos);
        e.writeObject(obj);
        e.close();
        return new String(baos.toByteArray());
    }

}
