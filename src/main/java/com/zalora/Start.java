package com.zalora;

import java.util.List;
import java.nio.file.*;
import java.io.IOException;
import java.nio.charset.Charset;
import net.rubyeye.xmemcached.*;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;
import net.rubyeye.xmemcached.exception.MemcachedException;

public class Start {
    public static void main(String[] args) {
        try {
            new Start().reproduceBug();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void reproduceBug() throws IOException, URISyntaxException, MemcachedException, InterruptedException, TimeoutException {
        final MemcachedClient mc = new XMemcachedClient("localhost", 11211);

        List<String> keys = Files.readAllLines(
            Paths.get(getClass().getClassLoader().getResource("keys.txt").toURI()),
            Charset.defaultCharset()
        );

        for (String key : keys) {
            mc.set(key, 0, "ISPN005003: UnknownOperationException");
        }
    }
}
