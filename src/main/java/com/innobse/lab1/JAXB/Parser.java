package com.innobse.lab1.JAXB;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import com.innobse.lab1.Const;
import org.apache.log4j.*;

/**
 * Created by bse71 on 21.02.2017.
 */
public class Parser {
    private static final Logger log = Logger.getLogger(Parser.class);

    public static <T extends Object> void saveObject(String filePath, T obj){
        try {

            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(obj, file);
            //jaxbMarshaller.marshal(obj, System.out);

            log.info(Const.SAVE);

        } catch (JAXBException e) {
            log.error(Const.ERROR + e.getMessage());
        }

    }

    public static <T extends Object> T getObject(String filePath, T any){
        T obj = null;
        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(any.getClass());

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            obj = (T) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        log.info(Const.GET);
        return obj;
    }
}
