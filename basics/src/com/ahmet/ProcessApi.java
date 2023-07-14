package com.ahmet;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

// Run with java -Xlog:gc=debug:file=gc.txt:none .\ProcessApi.java
public class ProcessApi {
    public static void main(String[] args) {
        ProcessHandle handle = ProcessHandle.current();
        System.out.println(handle); // Print process ID

        ProcessHandle.Info procInfo = handle.info();
        Optional<String[]> arguments = procInfo.arguments();
        Optional<String> cmd = procInfo.command();
        Optional<Instant> startTime = procInfo.startInstant();
        Optional<Duration> cpuUsage = procInfo.totalCpuDuration();

        System.out.println("Arguments: " + Arrays.toString(arguments.orElse(null)));
        System.out.println("Command: " + cmd.orElse(null));
        System.out.println("Start time: " + startTime.orElse(null));
        System.out.println("CPU usage: " + cpuUsage.orElse(null));


        handle.children().forEach(h -> System.out.println("Child process ID: " + h.pid() + ", info: " + h.info()));

        // Destroy:
        handle.children().forEach(ProcessHandle::destroy);
        System.out.println("Destroy completed");
    }
}
