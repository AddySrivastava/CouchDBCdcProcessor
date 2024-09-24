package com.mongodb.kafka.connect.sink.cdc.couchdb.operations;

import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.WriteModel;
import com.mongodb.kafka.connect.sink.cdc.CdcOperation;
import com.mongodb.kafka.connect.sink.converter.SinkDocument;
import org.apache.kafka.connect.errors.DataException;
import org.bson.BsonDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Insert implements CdcOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(Insert.class);

    @Override
    public WriteModel<BsonDocument> perform(SinkDocument doc) {
        BsonDocument couchdbChangeDocumentValue =
                doc.getValueDoc()
                        .orElseThrow(
                                () ->
                                        new DataException("Error: value doc must not be missing for insert operation"));

        BsonDocument couchdbChangeDocumentKey =
                doc.getKeyDoc()
                        .orElseThrow(
                                () ->
                                        new DataException("Error: value doc must not be missing for insert operation"));

        BsonDocument filter = OperationHelper.getDocumentKey(couchdbChangeDocumentKey);

        return new ReplaceOneModel<>(
                filter,
                couchdbChangeDocumentValue,
                new ReplaceOptions().upsert(true));
    }
}
