# mavlink
MAVLink Common Messages implementation
 @author Happy Artist
 Copyright (C) 2020 Happy Artist - All Rights Reserved
 Unauthorized copying of this file, via any medium is strictly prohibited
 Proprietary and confidential
 Written by Happy Artist <aaronsims2020@gmail.com>, July 2020
 
Overview: Read and process MAVLink Message Definitions from XML to Java SRC code, Classes, and Documentation. If only the URL is written all options will be defaulted enabled src, classes, docs, and jars.

Application syntax (enclose output paths in double quotes ""): 
    MAVLinkMessageToolkit [message definition xml URL] -[options]
      Options: 
       -src "[output path]" (This enables source code file generation)
       -classes "[output path]" (This enables class file generation)
       -docs "[output path]" (This enables API documentation file generation)
       -jars "[output path]" (This enables Jar file generation)

Note: including options without output paths will set default output paths of (./src_mavlink-message-definitions,./bin,./docs,./jars)\n");
Example (fill in your classpath information): 

  java org.happy.artist.mavlink.messages.MAVLinkMessageToolkit -classes "./bin" -docs "./docs" -src "./src" -jars "./jars");	

