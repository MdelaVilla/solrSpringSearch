package com.ejemplo.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.ejemplo.dto.DocumentoDTO;

@Service
public class SolrSearchService {

    private final HttpSolrClient solrClient;

    public SolrSearchService(HttpSolrClient solrClient) {
        this.solrClient = solrClient;
    }

    public List<DocumentoDTO> search(String queryString) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.setQuery(queryString);
        QueryResponse response = solrClient.query(query);
        SolrDocumentList documents = response.getResults();

        List<DocumentoDTO> resultado = new ArrayList<>();
        for (SolrDocument doc : documents) {
            Object tituloField = doc.getFieldValue("titulo");
            Object descripcionField = doc.getFieldValue("descripcion");

            ArrayList<String> tituloList = toStringList(tituloField);
            ArrayList<String> descripcionList = toStringList(descripcionField);

            DocumentoDTO dto = new DocumentoDTO(
                (String) doc.getFieldValue("id"),
                tituloList,
                descripcionList
            );
            resultado.add(dto);
        }

        return resultado;
    }

    // Helper to convert a Solr field value (String or Collection) into ArrayList<String>
    private ArrayList<String> toStringList(Object fieldValue) {
        if (fieldValue == null) {
            return null;
        }
        if (fieldValue instanceof List) {
            List<?> raw = (List<?>) fieldValue;
            ArrayList<String> out = new ArrayList<>();
            for (Object o : raw) {
                if (o != null) out.add(o.toString());
            }
            return out;
        }
        // single value
        return new ArrayList<>(Collections.singletonList(fieldValue.toString()));
    }
}