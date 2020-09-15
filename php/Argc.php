<?php 
if ($argc !== 2) {
    echo "Usage: php helloWorld.php <name>.\n";
    exit(1);
}
    $name = $argv[1];
    echo "\nHello, $name Hello World Program in PHP\n";
?>