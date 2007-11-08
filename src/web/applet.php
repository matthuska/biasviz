<?php

?>
<HTML>
    <HEAD>
<TITLE>BiasViz: Amino Acid Composition Applet</TITLE>
<STYLE type="text/css">
* {
    margin: 0 0 0 0;
    overflow: hidden;
}
</STYLE>
</HEAD>
<BODY>
    <!--<APPLET CODE="CompositionApplet.class" WIDTH="100%" HEIGHT="100%">-->
    <APPLET CODE="CompositionApplet" WIDTH="100%" HEIGHT="100%" archive="biasviz.jar" ALT="This page requires the Java plug-in to be installed.">
<?php

    if ($_FILES['datafile']['error'] == 0) {
        $alignment = file_get_contents($_FILES['datafile']['tmp_name']);
    } else {
        $alignment = $_POST["alignment"];
    }
    
    $alines = split("\n", $alignment);

    $numlines = count($alines);
    print "<PARAM NAME=\"numlines\" VALUE=\"$numlines\">";

    for ($i = 0; $i < $numlines; $i++) {
        $line = rtrim($alines[$i]);
        print "<PARAM NAME=\"line$i\" VALUE=\"$line\">\n";
    }
?>
    </APPLET>
</BODY>
</HTML>
<?php

?>
