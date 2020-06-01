package net.happyartist.mavlink.messages.util;

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
// TODO: Completed in XML Reader - Field/Message, extension elements..., Attributes hasLocation, isDestination, bitmask, and the tag param, include (only top level includes allowed),.
// TODO: include When building, generator toolchains will merge/append enums in all files, and report duplicate enum entries and messages.
    /** Generate Java SRC for message element Object. Mavlink Message Message element code automated code generation. */
    public static String generateMessageObject(MessageElement message)
    {
        // TODO: Generate message code.
        return "// TODO: Generate message code.";
    }

// TODO: 
    /** Generate Java SRC for MAV_CMD enum element Object. */
    public static String generateMAVCMDObjects(String enumName, String enumBitmask, String enumDescription, String enumDeprecated, String enumDeprecatedSince, String enumDeprecatedReplacedBy, String enumWIP, List<String> values, List<String> valueNames, List<String> descriptions, List<String> enumNameNameValueDeprecated, List<String> enumNameNameValueDeprecatedSince, List<String> enumNameNameValueDeprecatedReplacedBy, List<String> enumNameNameValueWIP, List<ParamElement> enumParamElements, List<String> enumEntryIsDestination, List<String> enumEntryHasLocation)
    {
        // TODO: Generate MAV_CMD code.
// hasLocation and isDestination are MAV_CMD specific
        return "// TODO: Generate MAV_CMD code.";
    }

    /** Generate Java SRC for enum class. Mavlink Message Enum code automated code generation. */
    public static String generateEnumObject(String enumName, String enumBitmask, String enumDescription, String enumDeprecated, String enumDeprecatedSince, String enumDeprecatedReplacedBy, String enumWIP, List<String> values, List<String> valueNames, List<String> descriptions, List<String> enumNameNameValueDeprecated, List<String> enumNameNameValueDeprecatedSince, List<String> enumNameNameValueDeprecatedReplacedBy, List<String> enumNameNameValueWIP, List<ParamElement> enumParamElements, List<String> enumEntryIsDestination, List<String> enumEntryHasLocation)
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
