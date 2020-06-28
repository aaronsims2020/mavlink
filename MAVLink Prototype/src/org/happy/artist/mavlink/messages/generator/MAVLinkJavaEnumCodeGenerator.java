package org.happy.artist.mavlink.messages.generator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Java Mavlink Message Enum code automated code generator helper class. The Message Codes are so extensive it was faster to automate than to manually code, and javadoc each variable.
 * @author Happy Artist
 * Copyright (C) 2020 Happy Artist - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, May-June 2020
 */
public class MAVLinkJavaEnumCodeGenerator {
    private static LocalDate currentDate = LocalDate.now();
    private static String currentMonth = currentDate.getMonth().toString();
    private static String currentYear = String.valueOf(currentDate.getYear());  
// TODO: include When building, generator toolchains will merge/append enums in all files, and report duplicate enum entries and messages.
    /** Generate Java SRC for message element Object. Mavlink Message Message element code automated code generation. */
    public static String generateMessageObject(MessageElement message)
    {
    	StringBuilder enumCode = new StringBuilder();
    	// package line
        enumCode.append("package org.happy.artist.mavlink.messages.mavmessages;\n\n");
        // imports
        enumCode.append("import java.util.List;\n");
        enumCode.append("import java.util.ArrayList;\n");
        enumCode.append("import org.happy.artist.mavlink.messages.generator.FieldElement;\n\n");        

        // message javadoc
        if(message.DEPRECATED_ELEM==null&&message.WIP_ELEM==null)
        {
            if(message.DEPRECATED_ELEM==null||message.DESCRIPTION_ELEM.isEmpty())
            {
                // No description defined.
                enumCode.append("/** */\n");
            }
            else
            {
                // description available write Javadocs
                enumCode.append("/** ");
                enumCode.append(message.DESCRIPTION_ELEM);
                enumCode.append(" */\n");
            }
            enumCode.append("public class ");
        }
        else if(message.WIP_ELEM!=null)
        {
            // message is WIP
            if(message.DESCRIPTION_ELEM==null||message.DESCRIPTION_ELEM.isEmpty())
            {
                // No description defined.
                enumCode.append("/** WIP: This message is work-in-progress and it can therefore change. It should NOT be used in stable production environments. ");
                enumCode.append(message.WIP_ELEM);
                enumCode.append("\n*\n* Notes: Empty Strings are defined elements without any attributes or characters data. Null Strings are \n*  defined schema tags not in the xml definition, and non empty Strings are the value or element character data.\n*\n");
                enumCode.append("*  @author Happy Artist\n* Copyright (C) ");
                enumCode.append(currentYear);
                enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
                enumCode.append("*  Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
                enumCode.append(currentMonth);
                enumCode.append(" ");
                enumCode.append(currentYear);
                enumCode.append("\n*/\n");                
            }
            else
            {
                // description available write Javadocs
                enumCode.append("/** WIP: This message is work-in-progress and it can therefore change. It should NOT be used in stable production environments. ");
                enumCode.append(message.WIP_ELEM);
                enumCode.append("\n * ");
                enumCode.append(message.DESCRIPTION_ELEM);
                enumCode.append("\n*\n* Notes: Empty Strings are defined elements without any attributes or characters data. Null Strings are \n*  defined schema tags not in the xml definition, and non empty Strings are the value or element character data.\n*\n");
                enumCode.append("*  @author Happy Artist\n* Copyright (C) ");
                enumCode.append(currentYear);
                enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
                enumCode.append("*  Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
                enumCode.append(currentMonth);
                enumCode.append(" ");
                enumCode.append(currentYear);
                enumCode.append("\n*/\n");
            }
            enumCode.append("public class ");
        }
        else
        {
            // message is deprecated
            if(message.SINCE_ATTR==null)
            {
            	message.SINCE_ATTR="NOT_DEFINED";
            }
            if(message.REPLACED_BY_ATTR==null)
            {
            	message.REPLACED_BY_ATTR="NOT_DEFINED";
            }
            if(message.DESCRIPTION_ELEM==null||message.DESCRIPTION_ELEM.isEmpty())
            {
                // No description defined.
                enumCode.append("/** @deprecated  As of ");
                enumCode.append(message.SINCE_ATTR);
                enumCode.append(", replaced by ");
                enumCode.append(message.REPLACED_BY_ATTR);
                enumCode.append(".");
                enumCode.append("\n*\n* Notes: Empty Strings are defined elements without any attributes or characters data. Null Strings are \n*  defined schema tags not in the xml definition, and non empty Strings are the value or element character data.\n*\n");
                enumCode.append("*  @author Happy Artist\n* Copyright (C) ");
                enumCode.append(currentYear);
                enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
                enumCode.append("*  Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
                enumCode.append(currentMonth);
                enumCode.append(" ");
                enumCode.append(currentYear);
                enumCode.append("\n*/\n");                
            }
            else
            {
                // description available write Javadocs
                enumCode.append("/** @deprecated  As of ");
                enumCode.append(message.SINCE_ATTR);
                enumCode.append(", replaced by ");
                enumCode.append(message.REPLACED_BY_ATTR);
                enumCode.append(".\n * ");
                enumCode.append(message.DESCRIPTION_ELEM);
                enumCode.append("\n*\n* Notes: Empty Strings are defined elements without any attributes or characters data. Null Strings are \n*  defined schema tags not in the xml definition, and non empty Strings are the value or element character data.\n*\n");
                enumCode.append("*  @author Happy Artist\n* Copyright (C) ");
                enumCode.append(currentYear);
                enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
                enumCode.append("*  Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
                enumCode.append(currentMonth);
                enumCode.append(" ");
                enumCode.append(currentYear);
                enumCode.append("\n*/\n");
            }
            enumCode.append("@Deprecated public class ");
        }
        enumCode.append(message.NAME_ATTR);
        enumCode.append("\n{\n");        
        // define class variables
        // id attribute
        enumCode.append("/** The id attribute is the unique index number of this message (in the example above: 147).\r\n  * For MAVLink 1:\r\n  *     Valid numbers range from 0 to 255.\r\n  *     The ids 0-149 and 230-255 are reserved for common.xml. Dialects can use 180-229 \r\n  *     for custom messages (provided these are not used by other included dialects).\r\n  * For MAVLink 2:\r\n  *     Valid numbers range from 0 to 16777215.\r\n  *     All numbers below 255 should be considered reserved unless messages are also intended for MAVLink 1 \r\n*/\n");
        enumCode.append("public String id = \"");
        enumCode.append(message.ID_ATTR);
        enumCode.append("\";\n");
        // name attribute
        enumCode.append("/** The name attribute provides a human readable form for the message (ie \"BATTERY_STATUS\"). \r\n  * It is used for naming helper functions in generated libraries, but is not sent over the wire. \r\n  */\n");
        enumCode.append("public String name = \"");
        enumCode.append(message.NAME_ATTR);
        enumCode.append("\";\n");        
        // description element
        enumCode.append("/** Human readable description of message, shown in user interfaces and in code comments. \r\n  * This should contain all information (and hyperlinks) to fully understand the message. */\n");
        if(message.DESCRIPTION_ELEM.isEmpty())
        {
        	enumCode.append("public String description = \"\";\n");
        }
        else
        {
            enumCode.append("public String description = \"");
            enumCode.append(message.DESCRIPTION_ELEM);
            enumCode.append("\";\n");             	
        }
 
        // extensions_start_index attribute
        enumCode.append("/** The Extensions start index element position in FIELD_ELEMS List. \r\n  * -1 if no EXTENSIONS Element defined in the MAVLink Schema Message Definition. \r\n */\n");
        enumCode.append("public int extensions_start_index = ");
        enumCode.append(String.valueOf(message.EXTENSIONS_START_INDEX));
        enumCode.append(";\n");           

        // field elements
        enumCode.append("/** List of message Field elements in iterative order. */\n");
        enumCode.append("public List<FieldElement> fields = new ArrayList<FieldElement>();\n");
        enumCode.append("{\n");
        String fieldName;
    	for(int i=0;i<message.FIELD_ELEMS.size();i++)
    	{
    		FieldElement field = message.FIELD_ELEMS.get(i);
    		fieldName = "field".concat(String.valueOf(i));
    		enumCode.append("FieldElement ");
    		enumCode.append(fieldName);
    		enumCode.append(" = new FieldElement();\n");
    		// TYPE_ATTR
    		if(field.TYPE_ATTR!=null)
    		{
	    		enumCode.append(fieldName);
	    		enumCode.append(".TYPE_ATTR = \"");
	    		enumCode.append(field.TYPE_ATTR);
	    		enumCode.append("\";\n");
    		}
    		else
    		{
	    		enumCode.append(fieldName);
	    		enumCode.append(".TYPE_ATTR = null;\n");    			
    		}
    		// NAME_ATTR
    		if(field.NAME_ATTR!=null)
    		{
	    		enumCode.append(fieldName);
	    		enumCode.append(".NAME_ATTR = \"");
	    		enumCode.append(field.NAME_ATTR);
	    		enumCode.append("\";\n");
			}
			else
			{
	    		enumCode.append(fieldName);
	    		enumCode.append(".NAME_ATTR = null;\n");    			
			}    		
    		// ENUM_ATTR
    		if(field.ENUM_ATTR!=null)    		
    		{
	    		enumCode.append(fieldName);
	    		enumCode.append(".ENUM_ATTR = \"");
	    		enumCode.append(field.ENUM_ATTR);
	    		enumCode.append("\";\n");
			}
			else
			{
	    		enumCode.append(fieldName);
	    		enumCode.append(".ENUM_ATTR = null;\n");    			
			} 
    		// UNITS_ATTR
    		if(field.UNITS_ATTR!=null)
			{
	    		enumCode.append(fieldName);
	    		enumCode.append(".UNITS_ATTR = \"");
	    		enumCode.append(field.UNITS_ATTR);
	    		enumCode.append("\";\n");
			}
			else
			{
	    		enumCode.append(fieldName);
	    		enumCode.append(".UNITS_ATTR = null;\n");    			
			} 
    		// DISPLAY_ATTR
    		if(field.DISPLAY_ATTR!=null)    		
    		{
	    		enumCode.append(fieldName);
	    		enumCode.append(".DISPLAY_ATTR = \"");
	    		enumCode.append(field.DISPLAY_ATTR);
	    		enumCode.append("\";\n");    
    		}   	
			else
			{
	    		enumCode.append(fieldName);
	    		enumCode.append(".DISPLAY_ATTR = null;\n");    			
			} 
    		// PRINT_FORMAT_ATTR
    		if(field.PRINT_FORMAT_ATTR!=null)    		
    		{
	    		enumCode.append(fieldName);
	    		enumCode.append(".PRINT_FORMAT_ATTR = \"");
	    		enumCode.append(field.PRINT_FORMAT_ATTR);
	    		enumCode.append("\";\n");        		
    		}
			else
			{
	    		enumCode.append(fieldName);
	    		enumCode.append(".PRINT_FORMAT_ATTR = null;\n");    			
			}     		
    		// DEFAULT_ATTR
    		if(field.DEFAULT_ATTR!=null)
    		{
	    		enumCode.append(fieldName);
	    		enumCode.append(".DEFAULT_ATTR = \"");
	    		enumCode.append(field.DEFAULT_ATTR);
	    		enumCode.append("\";\n");
    		}
			else
			{
	    		enumCode.append(fieldName);
	    		enumCode.append(".DEFAULT_ATTR = null;\n");    			
			}     		
    		// DESCRIPTION_CHARDATA
    		if(field.DESCRIPTION_CHARDATA!=null)
    		{
	    		enumCode.append(fieldName);
	    		enumCode.append(".DESCRIPTION_CHARDATA = \"");
	    		enumCode.append(field.DESCRIPTION_CHARDATA);
	    		enumCode.append("\";\n");        		
    		}
			else
			{
	    		enumCode.append(fieldName);
	    		enumCode.append(".DESCRIPTION_CHARDATA_ATTR = null;\n");    			
			} 
    		
    		enumCode.append("fields.add(");
    		enumCode.append(fieldName);
    		enumCode.append(");\n");
    	}
    	// Not really sure how to identify the extensions yet...
    	
        enumCode.append("}\n");
        
    	// end class
    	enumCode.append("}\n");
    	
        return enumCode.toString();
    }

    /** Generate Java SRC for MAV_CMD enum element Object. */
    public static String generateMAVCMDObjects(String enumName, String enumBitmask, String enumDescription, String enumDeprecated, String enumDeprecatedSince, String enumDeprecatedReplacedBy, String enumWIP, List<String> values, List<String> valueNames, List<String> descriptions, List<String> enumNameNameValueDeprecated, List<String> enumNameNameValueDeprecatedSince, List<String> enumNameNameValueDeprecatedReplacedBy, List<String> enumNameNameValueWIP, List<ArrayList<ParamElement>> enumParamElements, List<String> enumEntryIsDestination, List<String> enumEntryHasLocation)
    {
    	StringBuilder output = new StringBuilder();
    	// Note: hasLocation and isDestination are MAV_CMD specific

    	for(int i =0;i<valueNames.size();i++)
    	{
	        StringBuilder enumCode = new StringBuilder();
	    	// package line
	        enumCode.append("package org.happy.artist.mavlink.messages.mavcmd;\n\n");
	        // imports
	        enumCode.append("import org.happy.artist.mavlink.messages.generator.ParamElement;\n\n");
        
	        // class javadoc	        
	        String mavCMDDeprecated = enumNameNameValueDeprecated.get(i);
	        String mavCMDDeprecatedSince = enumNameNameValueDeprecatedSince.get(i);
	        String mavCMDDeprecatedReplacedBy = enumNameNameValueDeprecatedReplacedBy.get(i);	        
	        String mavCMDWIP = enumNameNameValueWIP.get(i);
	        String mavCMDDescription = descriptions.get(i);
	        
	        if(mavCMDDeprecated==null&&mavCMDWIP==null)
	        {
	            if(mavCMDDescription==null||mavCMDDescription.isEmpty())
	            {
	                // No description defined.
	                enumCode.append("/**\n");
	                enumCode.append("*  @author Happy Artist\n* Copyright (C) ");
	                enumCode.append(currentYear);
	                enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
	                enumCode.append("*  Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
	                enumCode.append(currentMonth);
	                enumCode.append(" ");
	                enumCode.append(currentYear);
	                enumCode.append("\n*/\n");                  
	            }
	            else
	            {
	                // description available write Javadocs
	                enumCode.append("/** ");
	                enumCode.append(mavCMDDescription);
	                enumCode.append("*\n");
	                enumCode.append("*  @author Happy Artist\n* Copyright (C) ");
	                enumCode.append(currentYear);
	                enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
	                enumCode.append("*  Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
	                enumCode.append(currentMonth);
	                enumCode.append(" ");
	                enumCode.append(currentYear);
	                enumCode.append("\n*/\n");                 
	            }
	            enumCode.append("public class ");
	        }
	        else if(mavCMDWIP!=null)
	        {
	            // MAV_CMD is WIP
	            if(mavCMDDescription==null||mavCMDDescription.isEmpty())
	            {
	                // No description defined.
	                enumCode.append("/** WIP: This MAV_CMD is work-in-progress and it can therefore change. It should NOT be used in stable production environments. ");
	                enumCode.append(mavCMDWIP);
	                enumCode.append("*\n");
	                enumCode.append("*  @author Happy Artist\n* Copyright (C) ");
	                enumCode.append(currentYear);
	                enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
	                enumCode.append("*  Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
	                enumCode.append(currentMonth);
	                enumCode.append(" ");
	                enumCode.append(currentYear);
	                enumCode.append("\n*/\n");                  
	            }
	            else
	            {
	                // description available write Javadocs
	                enumCode.append("/** WIP: This MAV_CMD is work-in-progress and it can therefore change. It should NOT be used in stable production environments. ");
	                enumCode.append(mavCMDWIP);
	                enumCode.append("\n * ");
	                enumCode.append(mavCMDDescription);
	                enumCode.append("\n*");
	                enumCode.append("*  @author Happy Artist\n* Copyright (C) ");
	                enumCode.append(currentYear);
	                enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
	                enumCode.append("*  Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
	                enumCode.append(currentMonth);
	                enumCode.append(" ");
	                enumCode.append(currentYear);
	                enumCode.append("\n*/\n");                  
	            }
	            enumCode.append("public class ");
	        }
	        else
	        {
	            // MAV_CMD is deprecated
	            if(mavCMDDeprecatedSince==null)
	            {
	            	mavCMDDeprecatedSince="NOT_DEFINED";
	            }
	            if(mavCMDDeprecatedReplacedBy==null)
	            {
	            	mavCMDDeprecatedReplacedBy="NOT_DEFINED";
	            }
	            if(mavCMDDescription==null||mavCMDDescription.isEmpty())
	            {
	                // No description defined.
	                enumCode.append("/** @deprecated  As of ");
	                enumCode.append(mavCMDDeprecatedSince);
	                enumCode.append(", replaced by ");
	                enumCode.append(mavCMDDeprecatedReplacedBy);
	                enumCode.append(". */\n");
	            }
	            else
	            {
	                // description available write Javadocs
	                enumCode.append("/** @deprecated  As of ");
	                enumCode.append(mavCMDDeprecatedSince);
	                enumCode.append(", replaced by ");
	                enumCode.append(mavCMDDeprecatedReplacedBy);
	                enumCode.append(".\n * ");
	                enumCode.append(mavCMDDescription);
	                enumCode.append("\n*/\n");
	            }
	            enumCode.append("@Deprecated public class ");
	        }
	        enumCode.append(valueNames.get(i));
	        enumCode.append("\n{\n");      

	        // define class variables
	        // MAV_CMD name
	        enumCode.append("/** The name of the MAV_CMD entry value (mandatory). This is a string of capitalized, underscore-separated words. */\n");
	        enumCode.append("public final String name = \"");
	        enumCode.append(valueNames.get(i));
	        enumCode.append("\";\n");
	        // MAV_CMD description
	        enumCode.append("/**  (optional): A description of the MAV_CMD. */\n");
	        enumCode.append("public String description = \"");
	        enumCode.append(descriptions.get(i));
	        enumCode.append("\";\n");     
	        // MAV_CMD value
	        enumCode.append("/** (optional): The value for the entry (a number). */\n");
	        enumCode.append("public String value = \"");
	        enumCode.append(values.get(i));
	        enumCode.append("\";\n");            
	        // MAV_CMD hasLocation
	        enumCode.append("/** (optional): A boolean (default true) that provides a hint to a GCS that the entry should be displayed as a \"standalone\" location - rather than as a destination on the flight path. This is applied for entries that contain lat/lon/alt location information in their param 5, 6, and 7 values but which are not on the vehicle path (e.g.: MAV_CMD_DO_SET_ROI_LOCATION). */\n");
	        if(enumEntryHasLocation.get(i)!=null)
	        {
		        enumCode.append("public boolean hasLocation = ");
		        enumCode.append(enumEntryHasLocation.get(i));
		        enumCode.append(";\n");  	        	
	        }
	        else
	        {
		        enumCode.append("public boolean hasLocation = false;\n");
	        }
	        // MAV_CMD isDestination
	        enumCode.append("/** (optional): A boolean (default true) that provides a hint to a GCS that the entry is a location that should be displayed as a point on the flight path. This is applied for entries that contain lat/lon/alt location information in their param 5, 6, and 7 values and which are on the vehicle path (e.g.: MAV_CMD_NAV_WAYPOINT and MAV_CMD_NAV_LAND). */\n");
	        if(enumEntryIsDestination.get(i)!=null)
	        {
		        enumCode.append("public boolean isDestination = ");
		        enumCode.append(enumEntryIsDestination.get(i));
		        enumCode.append(";\n");  	        	
	        }
	        else
	        {
		        enumCode.append("public boolean isDestination = false;\n");
	        }	  
	        ArrayList<ParamElement> params = enumParamElements.get(i);
	        // Process the entry param elements.
	        if(params!=null)
	        {
		        for(int j=0;j<params.size();j++)
		        {
		        	ParamElement element = params.get(j);
		        	if(element!=null)
		        	{	  
		        		if(element.INDEX.equals("5"))
		        		{
			        		enumCode.append("/** (optional): ParamX description */\n");
			        		enumCode.append("public ParamElement param");		        			
			        		enumCode.append("X");	
		        		}
		        		else if(element.INDEX.equals("6"))
		        		{
			        		enumCode.append("/** (optional): ParamY description */\n");
			        		enumCode.append("public ParamElement param");			        
		        			enumCode.append("Y");			        			
		        		}
		        		else if(element.INDEX.equals("7"))
		        		{
			        		enumCode.append("/** (optional): ParamZ description */\n");
			        		enumCode.append("public ParamElement param");		        			
			        		enumCode.append("Z");			        			
		        		}		        		
		        		else
		        		{
			        		enumCode.append("/** (optional): Param");
			        		enumCode.append(element.INDEX);
			        		enumCode.append(" description */\n");
			        		enumCode.append("public ParamElement param");		        			
			        		enumCode.append(element.INDEX);			        			
		        		}
		        		enumCode.append(" = new ParamElement()\n{\n{\n");

		        		enumCode.append("this.INDEX = \"");
		        		enumCode.append(element.INDEX);
		        		enumCode.append("\";\n");
		        		// Param Label
		        		if(element.LABEL_ISSET)
		        		{
		        			enumCode.append("this.LABEL_ISSET = true;\n");	
		        			enumCode.append("this.LABEL = \"");
		        			enumCode.append(element.LABEL);
		        			enumCode.append("\";\n");
		        		}
		        		// Param Units 
		        		if(element.UNITS_ISSET)
		        		{
		        			enumCode.append("this.UNITS_ISSET = true;\n");	
		        			enumCode.append("this.UNITS = \"");
		        			enumCode.append(element.UNITS);
		        			enumCode.append("\";\n");
		        		}
		        		// Param Enum 
		        		if(element.ENUM_ISSET)
		        		{
		        			enumCode.append("this.ENUM_ISSET = true;\n");	
		        			enumCode.append("this.ENUM = \"");
		        			enumCode.append(element.ENUM);
		        			enumCode.append("\";\n");
		        		}
		        		// Param DECIMALPLACES
		        		if(element.DECIMALPLACES_ISSET)
		        		{
		        			enumCode.append("this.DECIMALPLACES_ISSET = true;\n");	
		        			enumCode.append("this.DECIMALPLACES = \"");
		        			enumCode.append(element.DECIMALPLACES);
		        			enumCode.append("\";\n");
		        		}
		        		// Param Increment
		        		if(element.INCREMENT_ISSET)
		        		{
		        			enumCode.append("this.INCREMENT_ISSET = true;\n");	
		        			enumCode.append("this.INCREMENT = \"");
		        			enumCode.append(element.INCREMENT);
		        			enumCode.append("\";\n");
		        		}
		        		// Param MINVALUE
		        		if(element.MINVALUE_ISSET)
		        		{
		        			enumCode.append("this.MINVALUE_ISSET = true;\n");	
		        			enumCode.append("this.MINVALUE = \"");
		        			enumCode.append(element.MINVALUE);
		        			enumCode.append("\";\n");
		        		}		        		
		        		// Param MAXVALUE
		        		if(element.MAXVALUE_ISSET)
		        		{
		        			enumCode.append("this.MAXVALUE_ISSET = true;\n");	
		        			enumCode.append("this.MAXVALUE = \"");
		        			enumCode.append(element.MAXVALUE);
		        			enumCode.append("\";\n");
		        		}
		        		// Param RESERVED
		        		if(element.RESERVED_ISSET)
		        		{
		        			enumCode.append("this.RESERVED_ISSET = true;\n");	
		        			enumCode.append("this.RESERVED = \"");
		        			enumCode.append(element.RESERVED);
		        			enumCode.append("\";\n");
		        		}
		        		// Param DEFAULT
		        		if(element.DEFAULT_ISSET)
		        		{
		        			enumCode.append("this.DEFAULT_ISSET = true;\n");	
		        			enumCode.append("this.DEFAULT = \"");
		        			enumCode.append(element.DEFAULT);
		        			enumCode.append("\";\n");
		        		}
		        		if(element.DESCRIPTION!=null)
		        		{
		        			enumCode.append("this.DESCRIPTION = \"");
		        			enumCode.append(element.DESCRIPTION);
		        			enumCode.append("\";\n");		        			
		        		}
		        		else
		        		{
		        			enumCode.append("this.DESCRIPTION = \"\";\n");		        			
		        		}
		        		enumCode.append("}\n};\n");
	        		    // End param
		        	}
		        }
	        }
	        
	        // end class
	        enumCode.append("}\n\n");
	        // append new MAV_CMD to output
	        output.append(enumCode.toString());
    	}
        return output.toString();
    }

    /** Generate Java SRC for enum class. Mavlink Message Enum code automated code generation. */
    public static String generateEnumObject(String enumName, String enumBitmask, String enumDescription, String enumDeprecated, String enumDeprecatedSince, String enumDeprecatedReplacedBy, String enumWIP, List<String> values, List<String> valueNames, List<String> descriptions, List<String> enumNameNameValueDeprecated, List<String> enumNameNameValueDeprecatedSince, List<String> enumNameNameValueDeprecatedReplacedBy, List<String> enumNameNameValueWIP, List<ArrayList<ParamElement>> enumParamElements, List<String> enumEntryIsDestination, List<String> enumEntryHasLocation)
    {
        StringBuilder enumCode = new StringBuilder();
    	// package line
        enumCode.append("package org.happy.artist.mavlink.messages.mavenums;\n\n");        
        // enum javadoc
        if(enumDeprecated==null&&enumWIP==null)
        {
            if(enumDescription==null||enumDescription.isEmpty())
            {
                // No description defined.
                enumCode.append("/**\n");
                enumCode.append("*  @author Happy Artist\n* Copyright (C) ");
                enumCode.append(currentYear);
                enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
                enumCode.append("*  Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
                enumCode.append(currentMonth);
                enumCode.append(" ");
                enumCode.append(currentYear);
                enumCode.append("\n*/\n");                  
            }
            else
            {
                // description available write Javadocs
                enumCode.append("/** ");
                enumCode.append(enumDescription);
                enumCode.append("*\n");
                enumCode.append("*  @author Happy Artist\n* Copyright (C) ");
                enumCode.append(currentYear);
                enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
                enumCode.append("*  Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
                enumCode.append(currentMonth);
                enumCode.append(" ");
                enumCode.append(currentYear);
                enumCode.append("\n*/\n");                 
            }
            enumCode.append("public enum ");
        }
        else if(enumWIP!=null)
        {
            // enum is WIP
            if(enumDescription==null||enumDescription.isEmpty())
            {
                // No description defined.
                enumCode.append("/** WIP: This enum is work-in-progress and it can therefore change. It should NOT be used in stable production environments. ");
                enumCode.append(enumWIP);
                enumCode.append("*\n");
                enumCode.append("*  @author Happy Artist\n* Copyright (C) ");
                enumCode.append(currentYear);
                enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
                enumCode.append("*  Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
                enumCode.append(currentMonth);
                enumCode.append(" ");
                enumCode.append(currentYear);
                enumCode.append("\n*/\n");                  
            }
            else
            {
                // description available write Javadocs
                enumCode.append("/** WIP: This enum is work-in-progress and it can therefore change. It should NOT be used in stable production environments. ");
                enumCode.append(enumWIP);
                enumCode.append("\n * ");
                enumCode.append(enumDescription);
                enumCode.append("\n*");
                enumCode.append("*  @author Happy Artist\n* Copyright (C) ");
                enumCode.append(currentYear);
                enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
                enumCode.append("*  Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
                enumCode.append(currentMonth);
                enumCode.append(" ");
                enumCode.append(currentYear);
                enumCode.append("\n*/\n");                  
            }
            enumCode.append("public enum ");
        }
        else
        {
            // enum is deprecated
            if(enumDeprecatedSince==null)
            {
                enumDeprecatedSince="NOT_DEFINED";
            }
            if(enumDeprecatedReplacedBy==null)
            {
                enumDeprecatedReplacedBy="NOT_DEFINED";
            }
            if(enumDescription==null||enumDescription.isEmpty())
            {
                // No description defined.
                enumCode.append("/** @deprecated  As of ");
                enumCode.append(enumDeprecatedSince);
                enumCode.append(", replaced by ");
                enumCode.append(enumDeprecatedReplacedBy);
                enumCode.append(". */\n");
            }
            else
            {
                // description available write Javadocs
                enumCode.append("/** @deprecated  As of ");
                enumCode.append(enumDeprecatedSince);
                enumCode.append(", replaced by ");
                enumCode.append(enumDeprecatedReplacedBy);
                enumCode.append(".\n * ");
                enumCode.append(enumDescription);
                enumCode.append("\n*/\n");
            }
            enumCode.append("@Deprecated public enum ");
        }
        enumCode.append(enumName);
        enumCode.append("\n{\n");
        
        for (int i=0;i<values.size();i++)
        {
            String deprecatedEnumField = enumNameNameValueDeprecated.get(i);
            String deprecatedEnumFieldSince = enumNameNameValueDeprecatedSince.get(i);
            String deprecatedEnumFieldReplacedBy = enumNameNameValueDeprecatedReplacedBy.get(i);
            String enumWIPField = enumNameNameValueWIP.get(i);
            String description = descriptions.get(i);

            if(deprecatedEnumField==null&&enumWIPField==null)
            {
                if(description==null||description.isEmpty())
                {
                    // No description defined.
                    enumCode.append("/** */\n");
                }
                else
                {
                    // description available write Javadocs
                    enumCode.append("/** ");
                    enumCode.append(description);
                    enumCode.append(" */\n");
                } 
            }
            else if (enumWIPField!=null)
            {
                // enum field name is wip
                if(description==null||description.isEmpty())
                {
                    // No description defined.
                    enumCode.append("/** WIP: This entry is work-in-progress and it can therefore change. It should NOT be used in stable production environments.");
                    if(enumWIPField.isEmpty()==false)
                    {
                        enumCode.append(" "); 
                        enumCode.append(enumWIPField); 
                    }
                    enumCode.append(" */\n");
                }
                else
                {
                    // description available write Javadocs
                    enumCode.append("/** WIP: This entry is work-in-progress and it can therefore change. It should NOT be used in stable production environments. \n");
                    enumCode.append(" * ");
                    enumCode.append(description);
                    enumCode.append("\n*/\n");
                }
            }
            else
            {
                // enum field name is deprecated
                if(deprecatedEnumFieldSince==null)
                {
                    deprecatedEnumFieldSince="NOT_DEFINED";
                }
                if(deprecatedEnumFieldReplacedBy==null)
                {
                    deprecatedEnumFieldReplacedBy="NOT_DEFINED";
                }
                if(description==null||description.isEmpty())
                {
                    // No description defined.
                    enumCode.append("/** @deprecated  As of ");
                    enumCode.append(deprecatedEnumFieldSince);
                    enumCode.append(", replaced by ");
                    enumCode.append(deprecatedEnumFieldReplacedBy);
                    enumCode.append(". */\n");
                }
                else
                {
                    // description available write Javadocs
                    enumCode.append("/** @deprecated  As of ");
                    enumCode.append(deprecatedEnumFieldSince);
                    enumCode.append(", replaced by ");
                    enumCode.append(deprecatedEnumFieldReplacedBy);
                    enumCode.append(".\n * ");
                    enumCode.append(description);
                    enumCode.append("\n*/\n");
                }
            
            }
            // enum name value
            enumCode.append(valueNames.get(i));
            enumCode.append(" (");
            enumCode.append(values.get(i));
            enumCode.append(")");
            if(i+1!=valueNames.size())
            {
                enumCode.append(",\n");
            }
            else
            {
                enumCode.append(";\n");
            }
        }
        // enum field value methods
        enumCode.append("\nprivate final int code;\n");
        enumCode.append("private ");
        enumCode.append(enumName);
        enumCode.append("(int code)\n{\nthis.code = code;\n}\n");  
        enumCode.append("public int getValue()\n{\nreturn code;\n}\n");
       
        // end enum
        enumCode.append("}\n");

        return enumCode.toString();
    }    
}
