<?php
$mc = new Memcached();
$mc->addServer('localhost', 11211);

$keys = file('src/main/resources/keys.txt');
foreach ($keys as $idx => $key) {
    $mc->set(trim($key), "UnknownOperationException");
}
