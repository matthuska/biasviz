<?php

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
<title>BiasViz: Amino Acid Composition Applet</title>
<style type="text/css">
html, body {
    margin: 0 0 0 0;
    overflow: hidden;
    height: 100%;
}
</style>
</head>
<body>

      <!--[if !IE]> Firefox and others will use outer object -->
      <object classid="java:CompositionApplet.class" 
              type="application/x-java-applet"
              archive="biasviz.jar" 
              height="100%" width="100%" >
        <!-- Konqueror browser needs the following param -->
        <param name="archive" value="biasviz.jar" />
        <?php
        insert_alignment();
        insert_secondary();
        ?>
      <!--<![endif]-->
        <!-- MSIE (Microsoft Internet Explorer) will use inner object --> 
        <object classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" 
                codebase="http://java.sun.com/update/1.5.0/jinstall-1_5_0-windows-i586.cab"
                height="100%" width="100%" > 
          <param name="code" value="CompositionApplet" />
          <param name="archive" value="biasviz.jar" />
        <?php
        insert_alignment();
        insert_secondary();
        ?>
          <strong>
            This browser does not have a Java Plug-in.
            <br />
            <a href="http://java.sun.com/products/plugin/downloads/index.html">
              Get the latest Java Plug-in here.
            </a>
          </strong>
        </object> 
      <!--[if !IE]> close outer object -->
      </object>
      <!--<![endif]-->

<?php

function insert_alignment() {

    if (!isset($_POST['alignment']) && $_FILES['datafile']['error'] != 0) {
        // No alignment. Skip this function.
        return;
    }

    if ($_FILES['datafile']['error'] == 0) {
        $alignment_raw = file_get_contents($_FILES['datafile']['tmp_name']);
    } else {
        $alignment_raw = $_POST["alignment"];
    }
    
    // Escape quote characters to clean input
    $alignment = htmlspecialchars($alignment_raw, ENT_QUOTES);
    $alines = split("\n", $alignment);

    $numlines = count($alines);
    print "<param name=\"numlines\" value=\"$numlines\" />\n";

    for ($i = 0; $i < $numlines; $i++) {
        $line = rtrim($alines[$i]);
        print "<param name=\"line$i\" value=\"$line\" />\n";
    }

}

function insert_secondary() {
    if (!isset($_POST['secondary']) || strlen(trim($_POST['secondary'])) <= 0) {
        // No secondary structure information. Skip this function.
        return;
    }

    $secondary = htmlspecialchars($_POST['secondary'], ENT_QUOTES);
    $alines = split("\n", $secondary);
    $numlines = count($alines);

    print "<param name=\"snumlines\" value=\"$numlines\" />\n";
    for ($i = 0; $i < $numlines; $i++) {
        $line = rtrim($alines[$i]);
        print "<param name=\"sline$i\" value=\"$line\" />\n";
    }

}
?>
</body>
</html>
<?php

?>
