package com.company.clients.metrics;

import java.util.List;

public class ClientMetricsCalculator {
    public double average(List<Integer> ages) {
        if (ages == null || ages.isEmpty()) return 0.0;
        long sum = 0;
        for (Integer a : ages) sum += a;
        return sum / (double) ages.size();
    }

    public double stdDev(List<Integer> ages) {
        if (ages == null || ages.isEmpty()) return 0.0;
        double avg = average(ages);
        double varianceSum = 0.0;
        for (Integer a : ages) {
            double diff = a - avg;
            varianceSum += diff * diff;
        }
        double variance = varianceSum / ages.size();
        return Math.sqrt(variance);
    }
}

