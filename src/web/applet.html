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

      <object classid="java:MainApplet.class" 
              type="application/x-java-applet"
              archive="biasviz.jar" 
              height="100%" width="100%" >
        <!-- Konqueror browser needs the following param -->
        <param name="archive" value="biasviz.jar" />
      <!--<![endif]-->
        <!-- MSIE (Microsoft Internet Explorer) will use inner object --> 
        <object classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" 
                codebase="http://java.sun.com/update/1.5.0/jinstall-1_5_0-windows-i586.cab"
                height="100%" width="100%" > 
          <param name="code" value="MainApplet" />
          <param name="archive" value="biasviz.jar" />
          <strong>
            This browser does not have a Java Plug-in.
            <br />
            <a href="http://www.java.com/download/">
              Get the latest Java Plug-in here.
            </a>
          </strong>
        </object> 
      <!--[if !IE]> close outer object -->
      </object>
      <!--<![endif]-->

</body>
</html>
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

function insert_userdata() {
    if (!isset($_POST['userData']) || strlen(trim($_POST['userData'])) <= 0) {
        // No secondary structure information. Skip this function.
        return;
    }

    //$secondary = str_replace("\r\n", "|", htmlspecialchars($_POST['userData'], ENT_QUOTES));
    //$secondary = base64_encode($_POST['userData']);
    $secondary = $_POST['userData'];
    $alines = str_split_php4($secondary, 100);
    $numlines = count($alines);

    print "<param name=\"unumlines\" value=\"$numlines\" />\n";
    for ($i = 0; $i < $numlines; $i++) {
        $line = $alines[$i];
        //$line_no_white = str_replace("\t", ",", $line);
        //print "<param name=\"uline$i\" value=\"$line\" />\n";
        print "uline$i $line \n";
    }

}

// From http://www.php.net/str_split
function str_split_php4($text, $split = 1){
    //place each character of the string into and array
    $array = array();
    $i = 0;
    for($i=0; $i < strlen($text) - $split; $i = $i + $split){
        array_push($array, substr($text, $i, $split));
    }
    //array_push($array, substr($text, $i + $split, strlen($text)));
    return $array;
}

?>
