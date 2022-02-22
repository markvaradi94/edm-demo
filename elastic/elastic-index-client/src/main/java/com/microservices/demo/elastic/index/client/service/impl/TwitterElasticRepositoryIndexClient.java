package com.microservices.demo.elastic.index.client.service.impl;

import com.microservices.demo.elastic.index.client.repository.TwitterElasticsearchRepository;
import com.microservices.demo.elastic.index.client.service.ElasticIndexClient;
import com.microservices.demo.elastic.model.index.impl.TwitterIndexModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@ConditionalOnProperty(name = "elastic-config.is-repository", havingValue = "true", matchIfMissing = true)
public class TwitterElasticRepositoryIndexClient implements ElasticIndexClient<TwitterIndexModel> {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterElasticRepositoryIndexClient.class);

    private final TwitterElasticsearchRepository twitterElasticsearchRepository;

    public TwitterElasticRepositoryIndexClient(TwitterElasticsearchRepository twitterElasticsearchRepository) {
        this.twitterElasticsearchRepository = twitterElasticsearchRepository;
    }

    @Override
    public List<String> save(List<TwitterIndexModel> documents) {
        List<TwitterIndexModel> repositoryResponse = (List<TwitterIndexModel>) twitterElasticsearchRepository.saveAll(documents);
        List<String> documentIds = repositoryResponse.stream()
                .map(TwitterIndexModel::getId)
                .collect(toList());

        LOG.info("Documents indexed successfully with type: {}, and ids: {}", TwitterIndexModel.class.getName(), documentIds);

        return documentIds;
    }
}
