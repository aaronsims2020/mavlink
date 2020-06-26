package net.happyartist.mavlink.messages.util;

/**
 *
 * @author Aaron
 */
public class MAVLinkParamTypeConverter {

    public static String getJavaType(String paramType)
    {
        if(paramType.equals("float"))
        {
            return "float";
        }
        else if(paramType.equals("double"))
        {
            return "double";
        }
        else if(paramType.equals("char"))
        {
        	return "char";
        }
        else if(paramType.equals("int8_t"))
        {
            // signed 8 bit int.
            return "byte";
        }
        else if(paramType.equals("uint8_t"))
        {
            // unsigned 8 bit int to Java 16 bit short.
            return "short";
        }
        else if(paramType.equals("uint8_t_mavlink_version"))
        {
            // unsigned 8 bit int to Java 16 bit short. Used to represent the MAVLink version.
            return "short";        
        }

        else if(paramType.equals("int16_t"))
        {
            // signed 16 bit int to Java 16 bit short. 
            return "short";   
        }
        else if(paramType.equals("uint16_t"))
        {
            // unsigned 16 bit int to Java 32 bit int. 
            return "int";
        }
        else if(paramType.equals("int32_t"))
        {
            // signed 32 bit int to Java 32 bit int. 
            return "int";
        }
        else if(paramType.equals("uint32_t"))
        {
            // unsigned 32 bit int to Java 64 bit long. 
            return "long";
        }
        else if(paramType.equals("int64_t"))
        {
            // signed 64 bit int to Java 64 bit long. 
            return "long";
        }
        else if(paramType.equals("uint64_t"))
        {
            // unsigned 64 bit int to Java 64 bit long. Only Java 8 and above support unsigned long.
            return "long";
        }
        else if(paramType.indexOf("array")!=-1)
        {
        	return "ARRAY_NOT_IMPLEMENTED";
        }
        else
        {
            // Undefined type
        	return "UNDEFINED_TYPE";
        }
    }
}
