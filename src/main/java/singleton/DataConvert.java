/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleton;

import java.io.IOException;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

/**
 *
 * @author carlo
 */
public class DataConvert {
    
    public static Document StringToDocument(String xmlContent) throws ParsingException, ValidityException, IOException{
        Builder builder = new Builder();
        return builder.build(xmlContent, null);
    }
}
