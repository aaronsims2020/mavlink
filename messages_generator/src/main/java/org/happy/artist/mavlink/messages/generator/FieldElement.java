package org.happy.artist.mavlink.messages.generator;

    /** Field class for holding a MAVLink field link. Encodes one field of the message. 
     *  The field value is its name/text string used in GUI documentation (but not sent over the wire). 
    *
    * @author Happy Artist
    * Copyright (C) 2020 Happy Artist - All Rights Reserved
    * Unauthorized copying of this file, via any medium is strictly prohibited
    * Proprietary and confidential
    * Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, May-June 2020
    */
    public class FieldElement
    {
        /** Similar to a field in a C struct - the size of the data required to store/represent the data type.
          * Fields can be signed/unsigned integers of size 8, 16, 23, 64 bits ({u)int8_t, (u)int16_t, (u)int32_t, 
          * (u)int64_t), single/double precision IEEE754 floating point numbers. They can also be arrays of 
          * the other types - e.g. uint16_t[10]. 
        */
        public String TYPE_ATTR = "";
        /** Name of the field (used in code). */
        public String NAME_ATTR = "";  
        /** (optional): Name of an enum defining possible values of the field (e.g. MAV_BATTERY_CHARGE_STATE). */
        public String ENUM_ATTR = null;  
        /**  (optional): The units for message fields that take numeric values (not enums). 
          *  These are defined in the schema (search on name="SI_Unit") */ 
        public String UNITS_ATTR = null;
        /**  (optional): This should be set as display="bitmask" for bitmask fields 
          * (hint to ground station that enum values must be displayed as checkboxes). 
        */
        public String DISPLAY_ATTR = null; 
        /** (optional): TBD. */ 
        public String PRINT_FORMAT_ATTR = null;
        /** (optional): TBD. */
        public String DEFAULT_ATTR = null; 
        /** The field element character data between the start and end field tags. */
        public String DESCRIPTION_CHARDATA = "";
    }
