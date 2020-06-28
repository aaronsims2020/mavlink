package net.happyartist.mavlink.messages.generator;

import java.util.List;
import java.util.ArrayList;

/** Stores MAVLink Message element Schema Definition values for the code generator class.
 * 
 *  Notes: Empty Strings are defined elements without any attributes or characters data. Null Strings are 
 *  defined schema tags not in the xml definition, and non empty Strings are the value or element character data.
 *
 * @author Happy Artist
 * Copyright (C) 2020 Happy Artist - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Happy Artist &lt;aaronsims2020@gmail.com&gt;, May 2020
 */
public class MessageElement {
    /** The id attribute is the unique index number of this message (in the example above: 147).
      * For MAVLink 1:
      *     Valid numbers range from 0 to 255.
      *     The ids 0-149 and 230-255 are reserved for common.xml. Dialects can use 180-229 
      *     for custom messages (provided these are not used by other included dialects).
      * For MAVLink 2:
      *     Valid numbers range from 0 to 16777215.
      *     All numbers below 255 should be considered reserved unless messages are also intended for MAVLink 1 
    */
    public String ID_ATTR = "";
    /** The name attribute provides a human readable form for the message (ie "BATTERY_STATUS"). 
      * It is used for naming helper functions in generated libraries, but is not sent over the wire. 
    */
    public String NAME_ATTR = "";
    /** Human readable description of message, shown in user interfaces and in code comments. 
      * This should contain all information (and hyperlinks) to fully understand the message. */
    public String DESCRIPTION_ELEM = "";
    /** List of Field elements in iterative order. */
    public List<FieldElement> FIELD_ELEMS = new ArrayList<FieldElement>();
    // Deprecated/WIP variables.
    /** (optional): A tag indicating that the message is "work in progress". */
    public String WIP_ELEM = null;
    /** (optional): A tag indicating that the message is deprecated. */
    public String DEPRECATED_ELEM = null;
    /** deprecated element since attribute. */
    public String SINCE_ATTR = null;
    /** deprecated element replaced_by attribute. */
    public String REPLACED_BY_ATTR = null;

    /** The Extensions start index element position in FIELD_ELEMS List. 
      * -1 if no EXTENSIONS Element defined in the MAVLink Schema Message Definition. 
    */
    public int EXTENSIONS_START_INDEX = -1;
}
