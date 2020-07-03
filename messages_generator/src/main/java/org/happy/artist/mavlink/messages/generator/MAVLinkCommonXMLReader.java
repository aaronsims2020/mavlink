package org.happy.artist.mavlink.messages.generator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URISyntaxException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.happy.artist.rmdmia.rcsm.provider.message.MessageCompiler;
import javax.xml.stream.events.Characters;

/** Read and process the MAVLink Message Definition XML files i.e. (common.xml) into Java SRC code.
 *
 * @author Happy Artist
 * Copyright (C) 2020 Happy Artist - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, May 2020
 */
public class MAVLinkCommonXMLReader {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        String url = "https://raw.githubusercontent.com/mavlink/mavlink/master/message_definitions/v1.0/common.xml";
        //String url = "file:/C:/downloads/test.xml";
        List<MessageCompiler.DynamicSourceCodeObject> srcObjects = null;
        try {
            System.out.println("Generating MAVLink Common Messages src code...");
            srcObjects = parseXML(url);
            System.out.println("Compiling Java MAVLink Common Messages src code...");            
            File absolutePath = new File("./bin");
            System.out.println("Classes output directory: ".concat(absolutePath.getCanonicalPath()));
            System.out.println("Success: ".concat(String.valueOf(MessageCompiler.compile(srcObjects, "./bin"))));
            absolutePath = new File("./docs");
            System.out.println("Javadocs output directory: ".concat(absolutePath.getCanonicalPath()));
            System.out.println("Generating Javadocs for Java MAVLink Common Messages classes... If you receive an error here, ensure tools.jar from the JDK is in the classpath.");
            System.out.println("Javadocs created success: ".concat(String.valueOf(MessageCompiler.generateAPIDocs(srcObjects, "./docs"))));
        }
        catch(URISyntaxException ex)
        {
            Logger.getLogger(MAVLinkCommonXMLReader.class.getName()).log(Level.SEVERE, "URI Syntax Exception on include", ex);
        }
         catch (MalformedURLException ex) {
            Logger.getLogger(MAVLinkCommonXMLReader.class.getName()).log(Level.SEVERE, "Malformed URL", ex);
        } catch (Exception ex) {

            Logger.getLogger(MAVLinkCommonXMLReader.class.getName()).log(Level.SEVERE, "Compiler Exception", ex);
		}
        // System.out.println(srcObjects);
    }

    /**
     *  Return Java SRC from MAVLink xml. 
     *
     * @param commonXMLURL the common XMLURL
     * @return the list
     * @throws Exception the exception
     */
    public static List<MessageCompiler.DynamicSourceCodeObject> parseXML(String commonXMLURL) throws Exception {
        return parseXML(commonXMLURL, null, false);
    }
    
    /**
     *  Return Java SRC from MAVLink xml. If is root document set boolean isInclude to false. 
     *
     * @param commonXMLURL the MAVLink Message Definiton XML URL.
     * @param relativeURI the relative URI
     * @param isInclude the is include
     * @return the list
     * @throws Exception the exception
     */
    private static List<MessageCompiler.DynamicSourceCodeObject> parseXML(String commonXMLURL, String relativeURI, boolean isInclude) throws Exception {
        URL url = new URL(commonXMLURL);
        if(isInclude==true&&relativeURI!=null)
        {
            // Resolve URI to URL.
            url = url.toURI().resolve(relativeURI).toURL();
        }
    	List<MessageCompiler.DynamicSourceCodeObject> srcObjects = new ArrayList<MessageCompiler.DynamicSourceCodeObject>();        
        StringBuilder sb = new StringBuilder();
        // Document level variables
        String mavlinkVersion = "";
        String mavlinkDialect = "";
        BufferedReader in;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        // enum level variables
        String enumName = "";
        String enumBitmask = "";
        String enumNameDescription = "";
        String enumNameDeprecated=null;
        String enumNameDeprecatedSince="";
        String enumNameDeprecatedReplacedBy="";
        String enumWIP = null;
        List<String> enumNameValues = new ArrayList<String>();
        List<String> enumValues = new ArrayList<String>();
        List<String> enumNameNameValueDeprecated = new ArrayList<String>();
        List<String> enumNameNameValueDeprecatedSince = new ArrayList<String>();
        List<String> enumNameNameValueDeprecatedReplacedBy = new ArrayList<String>();
        List<String> enumDescriptions = new ArrayList<String>();
        List<String> enumNameNameValueWIP = new ArrayList<String>();
        List<ArrayList<ParamElement>> enumParamElementsArrayList = new ArrayList<ArrayList<ParamElement>>();
        List<String> enumEntryIsDestination = new ArrayList<String>();
        List<String> enumEntryHasLocation = new ArrayList<String>();
        MessageElement message = null;

        int currentMAVCMDEntryIndex = -1;
        boolean isInsideEntry = false;       
        boolean isInsideMessage = false;
        // MAV_CMD is not an enum but a collection of MAV Commands to be processed differently in codegen.
        boolean isInsideMAV_CMD = false;
        // param index checking for duplicate params. Will be used to skip duplicate params, and document a warning to the UI of duplicate element.
        boolean paramIndex1Exists = false;
        boolean paramIndex2Exists = false;
        boolean paramIndex3Exists = false;
        boolean paramIndex4Exists = false;
        boolean paramIndex5Exists = false;
        boolean paramIndex6Exists = false;
        boolean paramIndex7Exists = false;
        boolean skipParamAddToList = false;
        
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(url.openStream());
            while(xmlEventReader.hasNext()){
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
               if (xmlEvent.isStartElement()){
                   StartElement startElement = xmlEvent.asStartElement();
                    if(startElement.getName().getLocalPart().equals("include")&&isInclude==false){
                        String includeUri = getCharacterData(xmlEvent, xmlEventReader);
                        if(includeUri!=null&&includeUri.isEmpty()==false)
                        {
                            // Call parseXML(String commonXMLURL, true);
                            sb.append(parseXML(commonXMLURL, includeUri, true));
                        }
                    }

                    else if(startElement.getName().getLocalPart().equals("enum")){
                    isInsideEntry = false;
                       //Get the 'name' attribute from enum element
                       Attribute nameAttr = startElement.getAttributeByName(new QName("name"));
                       if(nameAttr != null){
                           enumName = nameAttr.getValue();
                           if(enumName.equals("MAV_CMD"))
                           {
                                isInsideMAV_CMD = true;
                           }
                       }
                       //Get the 'bitmask' attribute from entry element - entry has lat/lon/alt location - default is true - xs:boolean
                       Attribute bitmaskAttr = startElement.getAttributeByName(new QName("bitmask"));
                       if(bitmaskAttr != null){
                            if(bitmaskAttr.getValue()==null||bitmaskAttr.getValue().isEmpty())
                            {
                                enumBitmask = "false";
                            }
                            else
                            {
                                enumBitmask = bitmaskAttr.getValue();
                            }
                       }
                       else
                       {
                           // Add null if no value defined.
                           enumBitmask = null;
                       }
                   }

                   else if(startElement.getName().getLocalPart().equals("version")){
                        // mavlink version
                        mavlinkVersion = getCharacterData(xmlEvent, xmlEventReader);
                    }
                   else if(startElement.getName().getLocalPart().equals("dialect")){
                        // mavlink dialect
                        mavlinkDialect = getCharacterData(xmlEvent, xmlEventReader);
                    }
                   else if(startElement.getName().getLocalPart().equals("extensions")){
                        // mavlink message extension tag indicating start of MAVLink 2 messages fields starting at List index #
                        message.EXTENSIONS_START_INDEX = message.FIELD_ELEMS.size();
                    }
                   else if(startElement.getName().getLocalPart().equals("field") && isInsideMessage){
                        // message field element
                        FieldElement field = new FieldElement();

                       //Get the message field 'type' attribute
                       Attribute typeAttr = startElement.getAttributeByName(new QName("type"));
                       if(typeAttr != null){
                            field.TYPE_ATTR=typeAttr.getValue();
                       }
                       //Get the message field 'name' attribute
                       Attribute nameAttr = startElement.getAttributeByName(new QName("name"));
                       if(nameAttr != null){
                            field.NAME_ATTR=nameAttr.getValue();
                       }
                       //Get the message field 'enum' attribute
                       Attribute enumAttr = startElement.getAttributeByName(new QName("enum"));
                       if(enumAttr != null){
                            field.ENUM_ATTR=enumAttr.getValue();
                       }
                       //Get the message field 'units' attribute
                       Attribute unitsAttr = startElement.getAttributeByName(new QName("units"));
                       if(unitsAttr != null){
                            field.UNITS_ATTR=unitsAttr.getValue();
                       }
                       //Get the message field 'display' attribute
                       Attribute displayAttr = startElement.getAttributeByName(new QName("display"));
                       if(displayAttr != null){
                            field.DISPLAY_ATTR=displayAttr.getValue();
                       }
                       //Get the message field 'print_format' attribute
                       Attribute printFormatAttr = startElement.getAttributeByName(new QName("print_format"));
                       if(printFormatAttr != null){
                            field.PRINT_FORMAT_ATTR=printFormatAttr.getValue();
                       }
                       //Get the message field 'default' attribute
                       Attribute defaultAttr = startElement.getAttributeByName(new QName("default"));
                       if(defaultAttr != null){
                            field.DEFAULT_ATTR=defaultAttr.getValue();
                       }
                       field.DESCRIPTION_CHARDATA = getCharacterData(xmlEvent, xmlEventReader);
                       if(field.DESCRIPTION_CHARDATA!=null)
                       {
                    	   field.DESCRIPTION_CHARDATA = field.DESCRIPTION_CHARDATA.replace("\"", "\\\"").replace("\r", "").replace("\n", "");
                       }
                        // add new field to message element
                        message.FIELD_ELEMS.add(field);
                    }
                   else if(startElement.getName().getLocalPart().equals("wip")){
                        if(isInsideMessage)
                        {
                            message.WIP_ELEM = getCharacterData(xmlEvent, xmlEventReader);
                            if(message.WIP_ELEM!=null)
                            {
                            	message.WIP_ELEM = message.WIP_ELEM.replace("\"", "\\\"").replace("\r", "").replace("\n", "");
                            }
                        }
                        else if(isInsideEntry)
                         {
                             enumNameNameValueWIP.add(getCharacterData(xmlEvent, xmlEventReader));
                         }
                         else
                         {
                             enumWIP = getCharacterData(xmlEvent, xmlEventReader);
                         }
                    }
                   else if(startElement.getName().getLocalPart().equals("deprecated")){
                        // deprecated xml attributes
                        String since = "";
                        String replaced_by = "";
                       //Get the 'since' attribute from enum element
                       Attribute sinceAttr = startElement.getAttributeByName(new QName("since"));
                       if(sinceAttr != null){
                            if(isInsideMessage)
                            {
                                message.SINCE_ATTR = sinceAttr.getValue();
                            }
                           else if(isInsideEntry)
                            {
                                enumNameNameValueDeprecatedSince.add(sinceAttr.getValue());
                            }
                            else
                            {
                                enumNameDeprecatedSince = sinceAttr.getValue();
                            }
                       }
                       //Get the 'replaced_by' attribute from enum element
                       Attribute replacedByAttr = startElement.getAttributeByName(new QName("replaced_by"));
                       if(replacedByAttr != null){
                            if(isInsideMessage)
                            {
                                message.REPLACED_BY_ATTR = replacedByAttr.getValue();
                            }
                           else if(isInsideEntry)
                            {
                                enumNameNameValueDeprecatedReplacedBy.add(replacedByAttr.getValue());
                            }
                            else
                            {
                                enumNameDeprecatedReplacedBy = replacedByAttr.getValue();
                            }
                       }
                        if(isInsideMessage)
                        {
                            message.DEPRECATED_ELEM = getCharacterData(xmlEvent, xmlEventReader);
                        }
                        else if(isInsideEntry)
                         {
                             enumNameNameValueDeprecated.add(getCharacterData(xmlEvent, xmlEventReader));
                         }
                         else
                         {
                             enumNameDeprecated = getCharacterData(xmlEvent, xmlEventReader);
                         }
                   }
                   else if(startElement.getName().getLocalPart().equals("description")){
                        if(isInsideMessage)
                        {
                            message.DESCRIPTION_ELEM = getCharacterData(xmlEvent, xmlEventReader);
                            if(message.DESCRIPTION_ELEM!=null)
                            {
                            	message.DESCRIPTION_ELEM = message.DESCRIPTION_ELEM.replace("\"", "\\\"").replace("\r", "").replace("\n", "");
                            }
                        }
                        else if(isInsideEntry)
                        {
                            // enum value description
                        	String messageDescriptionElement = getCharacterData(xmlEvent, xmlEventReader);
                            if(messageDescriptionElement!=null)
                            {
                            	messageDescriptionElement = messageDescriptionElement.replace("\"", "\\\"").replace("\r", "").replace("\n", "");
                            }  
                        	enumDescriptions.add(messageDescriptionElement);                          
                        }
                        else
                        {
                            // enum description
                            enumNameDescription = getCharacterData(xmlEvent, xmlEventReader);
                            if(enumNameDescription!=null)
                            {
                            	enumNameDescription = enumNameDescription.replace("\"", "\\\"").replace("\r", "").replace("\n", "");
                            }                             
                        }
                   }
                    else if(startElement.getName().getLocalPart().equals("message")){
                        isInsideMessage = true;
                        message = new MessageElement();
                       //Get the 'id' attribute from message element
                       Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                       if(idAttr != null){
                            message.ID_ATTR=idAttr.getValue();
                       }
                       //Get the 'name' attribute from message element
                       Attribute nameAttr = startElement.getAttributeByName(new QName("name"));
                       if(nameAttr != null){
                            message.NAME_ATTR=nameAttr.getValue();
                       }
                    }
                    else if(startElement.getName().getLocalPart().equals("entry")){
                        isInsideEntry = true;
                        // Generate a new ArrayList<ParamElement> to a length of 7, and add to enumParamElementsArrayList
                        ArrayList<ParamElement> paramElements = new ArrayList<ParamElement>(7);
                        currentMAVCMDEntryIndex = currentMAVCMDEntryIndex + 1;
                        enumParamElementsArrayList.add(paramElements);
                       //Get the 'value' attribute from entry element
                       Attribute valueAttr = startElement.getAttributeByName(new QName("value"));
                       if(valueAttr != null){
                           enumValues.add(valueAttr.getValue());
                       }
                       else
                       {
                           // Add "-1" to represent NaN in Enums, since null cannot represent an int.
                           enumValues.add("-1");
                       }
                       //Get the 'name' attribute from entry element
                       Attribute nameAttr = startElement.getAttributeByName(new QName("name"));
                       if(nameAttr != null){
                           enumNameValues.add(nameAttr.getValue());
                       }
                       else
                       {
                           // Add null if no valu`e found to pad index.
                           enumNameValues.add(null);
                       }

                       //Get the 'hasLocation' attribute from entry element - entry has lat/lon/alt location - default is true - xs:boolean
                       Attribute hasLocationAttr = startElement.getAttributeByName(new QName("hasLocation"));
                       if(hasLocationAttr != null){
                            if(hasLocationAttr.getValue()==null||hasLocationAttr.getValue().isEmpty())
                            {
                                enumEntryHasLocation.add("true");
                            }
                            else
                            {
                                enumEntryHasLocation.add(hasLocationAttr.getValue());
                            }
                       }
                       else
                       {
                           // Add null if no value defined.
                           enumEntryHasLocation.add(null);
                       }
                       //Get the 'isDestination' attribute from entry element (default is true) entry is a destination - xs:boolean
                       Attribute isDestinationAttr = startElement.getAttributeByName(new QName("isDestination"));
                       if(isDestinationAttr != null){
                            if(isDestinationAttr.getValue()==null||isDestinationAttr.getValue().isEmpty())
                            {
                                enumEntryIsDestination.add("true");
                            }
                            else
                            {
                                enumEntryIsDestination.add(isDestinationAttr.getValue());
                            }
                       }
                       else
                       {
                           // Add null if no valu`e found to pad index.
                           enumEntryIsDestination.add(null);
                       }
                   }
                    else if(startElement.getName().getLocalPart().equals("param")){
                       ParamElement param = new ParamElement();
                       //Get the 'index' attribute from param element (required)
                       Attribute indexAttr = startElement.getAttributeByName(new QName("index"));
                       if(indexAttr != null){
                    	   
                           param.INDEX=indexAttr.getValue();
                           if(param.INDEX.equals("1")&&paramIndex1Exists==false)
                           {
                               paramIndex1Exists = true;                       	   
                           }
                           else if(param.INDEX.equals("1")&&paramIndex1Exists==true)
                           {
                        	   skipParamAddToList = true;
                           }
                           else if(param.INDEX.equals("2")&&paramIndex2Exists==false)
                           {
                               paramIndex2Exists = true;                    	   
                           }
                           else if(param.INDEX.equals("2")&&paramIndex2Exists==true)
                           {
                        	   skipParamAddToList = true;
                           }                           
                           else if(param.INDEX.equals("3")&&paramIndex3Exists==false)
                           {
                               paramIndex3Exists = true;                	   
                           }
                           else if(param.INDEX.equals("3")&&paramIndex3Exists==true)
                           {
                        	   skipParamAddToList = true;
                           }                           
                           else if(param.INDEX.equals("4")&&paramIndex4Exists==false)
                           {
                               paramIndex4Exists = true;                                 	   
                           }
                           else if(param.INDEX.equals("4")&&paramIndex4Exists==true)
                           {
                        	   skipParamAddToList = true;
                           }                           
                           else if(param.INDEX.equals("5")&&paramIndex5Exists==false)
                           {
                               paramIndex5Exists = true;                           	   
                           }
                           else if(param.INDEX.equals("5")&&paramIndex5Exists==true)
                           {
                        	   skipParamAddToList = true;
                           }                           
                           else if(param.INDEX.equals("6")&&paramIndex6Exists==false)
                           {
                               paramIndex6Exists = true;                     	   
                           }
                           else if(param.INDEX.equals("6")&&paramIndex6Exists==true)
                           {
                        	   skipParamAddToList = true;
                           }                           
                           else if(param.INDEX.equals("7")&&paramIndex7Exists==false)
                           {
                               paramIndex7Exists = true;                            	   
                           }                           
                           else if(param.INDEX.equals("7")&&paramIndex7Exists==true)
                           {
                        	   skipParamAddToList = true;
                           }                           
                       }
                       else
                       {
                           // Set index to empty String if no value is defined.
                           param.INDEX="";
                       }
                       //Get the 'label' attribute from param element
                       Attribute labelAttr = startElement.getAttributeByName(new QName("label"));
                       if(labelAttr != null){
                           param.LABEL_ISSET=true;
                           param.LABEL=labelAttr.getValue();
                       }
                       //Get the 'units' attribute from param element
                       Attribute unitsAttr = startElement.getAttributeByName(new QName("units"));
                       if(unitsAttr != null){
                           param.UNITS_ISSET=true;
                           param.UNITS=unitsAttr.getValue();
                       }
                       //Get the 'enum' attribute from param element
                       Attribute enumAttr = startElement.getAttributeByName(new QName("enum"));
                       if(enumAttr != null){
                           param.ENUM_ISSET=true;
                           param.ENUM=enumAttr.getValue();
                       }
                       //Get the 'decimalPlaces' attribute from param element
                       Attribute decimalPlacesAttr = startElement.getAttributeByName(new QName("decimalPlaces"));
                       if(decimalPlacesAttr != null){
                           param.DECIMALPLACES_ISSET=true;
                           param.DECIMALPLACES=decimalPlacesAttr.getValue();
                       }
                       //Get the 'increment' attribute from param element
                       Attribute incrementAttr = startElement.getAttributeByName(new QName("increment"));
                       if(incrementAttr != null){
                           param.INCREMENT_ISSET=true;
                           param.INCREMENT=incrementAttr.getValue();
                       }
                       //Get the 'minValue' attribute from param element
                       Attribute minValueAttr = startElement.getAttributeByName(new QName("minValue"));
                       if(minValueAttr != null){
                           param.MINVALUE_ISSET=true;
                           param.MINVALUE=minValueAttr.getValue();
                       }
                       //Get the 'maxValue' attribute from param element
                       Attribute maxValueAttr = startElement.getAttributeByName(new QName("maxValue"));
                       if(maxValueAttr != null){
                           param.MAXVALUE_ISSET=true;
                           param.MAXVALUE=maxValueAttr.getValue();
                       }
                       //Get the 'reserved' attribute from param element
                       Attribute reservedAttr = startElement.getAttributeByName(new QName("reserved"));
                       if(reservedAttr != null){
                           param.RESERVED_ISSET=true;
                           param.RESERVED=reservedAttr.getValue();
                       }
                       //Get the 'default' attribute from param element
                       Attribute defaultAttr = startElement.getAttributeByName(new QName("default"));
                       if(defaultAttr != null){
                           param.DEFAULT_ISSET=true;
                           param.DEFAULT=defaultAttr.getValue();
                       }
                       param.DESCRIPTION = getCharacterData(xmlEvent, xmlEventReader);
                       if(param.DESCRIPTION!=null)
                       {
                    	   param.DESCRIPTION = param.DESCRIPTION.replace("\"", "\\\"").replace("\r", "").replace("\n", "");
                       }
                       // Skip adding the param element if it contains a duplicate index.
                       if(skipParamAddToList == false)
                       {
                           enumParamElementsArrayList.get(currentMAVCMDEntryIndex).add(param);                    	   
                       }
                       else
                       {
                    	   System.out.println("Warning: Duplicate param element in ".concat(enumNameValues.get(currentMAVCMDEntryIndex)).concat(". Ignoring duplicate, and skipping to next element."));
                    	   // Reset skipParamAddToList = false
                    	   skipParamAddToList = false;
                       }
                   }

               }
               //if enum end element is reached, generate enum src
               if(xmlEvent.isEndElement()){
                   EndElement endElement = xmlEvent.asEndElement();
                    if(endElement.getName().getLocalPart().equals("message")){
                        isInsideMessage = false;
                        srcObjects.addAll(MAVLinkJavaEnumCodeGenerator.generateMessageObject(message));
                        message = null;
                    }
                   else if(endElement.getName().getLocalPart().equals("enum")){
                        if(isInsideMAV_CMD)
                        {      
                            // process to return src for MAV_CMD command objects
                            isInsideMAV_CMD = false;
                            srcObjects.addAll(MAVLinkJavaEnumCodeGenerator.generateMAVCMDObjects(enumName, enumBitmask, enumNameDescription, enumNameDeprecated, enumNameDeprecatedSince, enumNameDeprecatedReplacedBy, enumWIP, enumValues, enumNameValues, enumDescriptions, enumNameNameValueDeprecated, enumNameNameValueDeprecatedSince, enumNameNameValueDeprecatedReplacedBy, enumNameNameValueWIP, enumParamElementsArrayList, enumEntryIsDestination, enumEntryHasLocation));
                        }  
                        else
                        {
                            // process to return src for enum object
                        	srcObjects.addAll(MAVLinkJavaEnumCodeGenerator.generateEnumObject(enumName, enumBitmask, enumNameDescription, enumNameDeprecated, enumNameDeprecatedSince, enumNameDeprecatedReplacedBy, enumWIP, enumValues, enumNameValues, enumDescriptions, enumNameNameValueDeprecated, enumNameNameValueDeprecatedSince, enumNameNameValueDeprecatedReplacedBy, enumNameNameValueWIP, enumParamElementsArrayList, enumEntryIsDestination, enumEntryHasLocation));
                        }
                        // reset enum level variables
                        currentMAVCMDEntryIndex = -1;
                        enumName = "";
                        enumBitmask="";
                        enumNameDescription = "";
                        enumNameDeprecated = null;
                        enumNameDeprecatedSince = null;
                        enumNameDeprecatedReplacedBy = null;
                        enumWIP = null;
                        enumNameValues.clear();
                        enumNameNameValueDeprecated.clear();
                        enumNameNameValueDeprecatedSince.clear();
                        enumNameNameValueDeprecatedReplacedBy.clear();
                        enumValues.clear();
                        enumDescriptions.clear();
                        enumNameNameValueWIP.clear();
                        enumParamElementsArrayList.clear();
                        enumEntryIsDestination.clear();
                        enumEntryHasLocation.clear();
                   }
                    else if(endElement.getName().getLocalPart().equals("entry")){
                        isInsideEntry = false;
                        // Some entry elements do not have descriptions, so add an empty description string if none exist to pad the List index element equal to the values & names List.
                        if(enumDescriptions.size()!=enumValues.size())
                        {
                            enumDescriptions.add("");
                        }
                        // Handle deprecated enum name name values
                        if(enumNameNameValueDeprecated.size()!=enumValues.size())
                        {
                            enumNameNameValueDeprecated.add(null);
                        }
                        // Handle enum name name deprecated since values
                        if(enumNameNameValueDeprecatedSince.size()!=enumValues.size())
                        {
                            enumNameNameValueDeprecatedSince.add(null);
                        }
                        // Handle enum name name deprecated replaced_by values
                        if(enumNameNameValueDeprecatedReplacedBy.size()!=enumValues.size())
                        {
                            enumNameNameValueDeprecatedReplacedBy.add(null);
                        }
                        // Handle enum name name value WIP List size by adding an empty string
                        if(enumNameNameValueWIP.size()!=enumValues.size())
                        {
                            enumNameNameValueWIP.add(null);
                        }
                        // Handle enum param elements List size by adding a null if no param was defined in xml.
                        if(enumParamElementsArrayList.size()!=enumValues.size())
                        {
                            enumParamElementsArrayList.add(null);
                        }
                        // Handle enum element entry attribute isDestination List size by adding a null if no param was defined in xml.
                        if(enumEntryIsDestination.size()!=enumValues.size())
                        {
                            enumEntryIsDestination.add(null);
                        }
                        // Handle enum element entry attribute hasLocation List size by adding a null if no param was defined in xml.
                        if(enumEntryHasLocation.size()!=enumValues.size())
                        {
                            enumEntryHasLocation.add(null);
                        }
                        paramIndex1Exists = false;
                        paramIndex2Exists = false;
                        paramIndex3Exists = false;
                        paramIndex4Exists = false;
                        paramIndex5Exists = false;
                        paramIndex6Exists = false;
                        paramIndex7Exists = false;
                        skipParamAddToList = false;
                    }
               }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            Logger.getLogger(MAVLinkCommonXMLReader.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(MAVLinkCommonXMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return srcObjects;
    }
    
    /**
     * Gets the character data.
     *
     * @param event the event
     * @param eventReader the event reader
     * @return the character data
     * @throws XMLStreamException the XML stream exception
     */
    private static String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }
    
}