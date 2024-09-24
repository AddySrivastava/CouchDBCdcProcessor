package com.mongodb.kafka.connect.sink.cdc;

import org.bson.BsonDocument;

import com.mongodb.client.model.WriteModel;

import com.mongodb.kafka.connect.sink.converter.SinkDocument;

public interface CdcOperation {

    WriteModel<BsonDocument> perform(SinkDocument doc);
}