package com.ejemplo.controller;

import com.ejemplo.service.SolrSearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/query")
@CrossOrigin(origins = "*")
public class SolrQueryController {

    private final SolrSearchService searchService;

    public SolrQueryController(SolrSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<?> query(@RequestParam("q") String query) throws SolrServerException, IOException {
        return searchService.search(query);
    }
}
