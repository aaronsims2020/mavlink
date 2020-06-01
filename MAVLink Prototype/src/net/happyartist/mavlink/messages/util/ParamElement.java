package net.happyartist.mavlink.messages.util;

/** Stores param element values for the code generator class. Param element are specific to MAV_CMD Element.
 *  boolean variable names ending _ISSET define true if the param attribute value was set in the xml definition. skip if set to false during code generation.
 *
 * @author Happy Artist
 * Copyright (C) 2020 Happy Artist - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, May 2020
 */
public class ParamElement {
    //'index' attribute value from param element (required)
    /** parameter index - xs:unsignedByte */
    public String INDEX;
    //'label' attribute value from param element
    /** parameter label (name) - xs:string */
    public String LABEL;
    public boolean LABEL_ISSET=false;

    //'units' attribute value from param element
    /** parameter number of decimal places to be displayed in the GUI - xs:unsignedByte */
    public String UNITS;
    public boolean UNITS_ISSET=false;

    //'enum' attribute value from param element
    public String ENUM;
    public boolean ENUM_ISSET=false;

    //'decimalPlaces' attribute value from param element
    public String DECIMALPLACES;
    public boolean DECIMALPLACES_ISSET=false;

    //'increment' attribute value from param element
    /** parameter increment - xs:float */
    public String INCREMENT;
    public boolean INCREMENT_ISSET=false;

    //'minValue' attribute value from param element
    /** parameter minimum value - xs:float */
    public String MINVALUE;
    public boolean MINVALUE_ISSET=false;

    //'maxValue' attribute value from param element
    /** parameter maximum value - xs:float */
    public String MAXVALUE;
    public boolean MAXVALUE_ISSET=false;

    //'reserved' attribute value from param element
    /** parameter is reserved - xs:boolean default=false */
    public String RESERVED;
    public boolean RESERVED_ISSET=false;

    //'default' attribute value from param element
    public String DEFAULT;
    public boolean DEFAULT_ISSET=false;

    /** Not in spec... Added for convenience of reusing this class to hold data as well, rather than creating another class. */
    public String VALUE;
}
