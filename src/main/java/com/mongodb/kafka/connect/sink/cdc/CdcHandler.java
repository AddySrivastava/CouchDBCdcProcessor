package com.mongodb.kafka.connect.sink.cdc;

import java.util.Optional;

import org.bson.BsonDocument;

import com.mongodb.client.model.WriteModel;

import com.mongodb.kafka.connect.sink.MongoSinkTopicConfig;
import com.mongodb.kafka.connect.sink.converter.SinkDocument;

public abstract class CdcHandler {

    private final MongoSinkTopicConfig config;

    public CdcHandler(final MongoSinkTopicConfig config) {
        this.config = config;
    }

    public MongoSinkTopicConfig getConfig() {
        return config;
    }

    public abstract Optional<WriteModel<BsonDocument>> handle(SinkDocument doc);
}