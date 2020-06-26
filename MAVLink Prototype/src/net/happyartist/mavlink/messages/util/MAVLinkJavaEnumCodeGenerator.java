package net.happyartist.mavlink.messages.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Java Mavlink Message Enum code automated code generator helper class. The Message Codes are so extensive it was faster to automate than to manually code, and javadoc each variable.
 * @author Happy Artist
 * Copyright (C) 2020 Happy Artist - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, May 2020
 */
public class MAVLinkJavaEnumCodeGenerator {
    private static LocalDate currentDate = LocalDate.now();
    private static String currentMonth = currentDate.getMonth().toString();
    private static String currentYear = String.valueOf(currentDate.getYear());  
// TODO: Completed in XML Reader - Field/Message, extension elements..., Attributes hasLocation, isDestination, bitmask, and the tag param, include (only top level includes allowed),.
// TODO: include When building, generator toolchains will merge/append enums in all files, and report duplicate enum entries and messages.
    /** Generate Java SRC for message element Object. Mavlink Message Message element code automated code generation. */
    public static String generateMessageObject(MessageElement message)
    {
        // TODO: Generate message code.
        return "// TODO: Generate message code.";
    }

    /** Generate Java SRC for MAV_CMD enum element Object. */
    public static String generateMAVCMDObjects(String enumName, String enumBitmask, String enumDescription, String enumDeprecated, String enumDeprecatedSince, String enumDeprecatedReplacedBy, String enumWIP, List<String> values, List<String> valueNames, List<String> descriptions, List<String> enumNameNameValueDeprecated, List<String> enumNameNameValueDeprecatedSince, List<String> enumNameNameValueDeprecatedReplacedBy, List<String> enumNameNameValueWIP, List<ArrayList<ParamElement>> enumParamElements, List<String> enumEntryIsDestination, List<String> enumEntryHasLocation)
    {
    	StringBuilder output = new StringBuilder();
    	// hasLocation and isDestination are MAV_CMD specific

    	for(int i =0;i<valueNames.size();i++)
    	{
	        StringBuilder enumCode = new StringBuilder();
	    	// package line
	        enumCode.append("package net.happyartist.mavlink.messages.mavcmd;\n\n");
	        // imports
	        enumCode.append("import net.happyartist.mavlink.messages.util.ParamElement;\n\n");
	    	// class javadoc
	        enumCode.append("/** ");
	        enumCode.append(descriptions.get(i));
	        enumCode.append("\n* \n* @author Happy Artist\n* Copyright (C) ");
	        enumCode.append(currentYear);
	        enumCode.append("  Happy Artist - All Rights Reserved.\n* Unauthorized copying of this file, via any medium is strictly prohibited\n");
	        enumCode.append("* Proprietary and confidential\n* Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, ");
	        enumCode.append(currentMonth);
	        enumCode.append(" ");
	        enumCode.append(currentYear);
	        enumCode.append("\n*/\n");
	        // define class
	        enumCode.append("public class ");
	        enumCode.append(valueNames.get(i));
	        enumCode.append(" {\n");
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
        // enum javadoc
        if(enumDeprecated==null&&enumWIP==null)
        {
            if(enumDescription==null||enumDescription.isEmpty())
            {
                // No description defined.
                enumCode.append("/** */\n");
            }
            else
            {
                // description available write Javadocs
                enumCode.append("/** ");
                enumCode.append(enumDescription);
                enumCode.append(" */\n");
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
                enumCode.append(" */\n");
            }
            else
            {
                // description available write Javadocs
                enumCode.append("/** WIP: This enum is work-in-progress and it can therefore change. It should NOT be used in stable production environments. ");
                enumCode.append(enumWIP);
                enumCode.append("\n * ");
                enumCode.append(enumDescription);
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
