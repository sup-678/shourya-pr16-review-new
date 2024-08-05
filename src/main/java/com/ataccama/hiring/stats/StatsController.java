package com.ataccama.hiring.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {

    @Autowired
    private StatisticsService service;

    @GetMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StatisticsDTO readStatistics(@RequestParam("name") String name) {
        return service.getStats(name);
    }

}
