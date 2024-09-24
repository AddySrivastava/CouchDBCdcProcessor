package com.mongodb.kafka.connect.sink.cdc.couchdb.operations;

import com.mongodb.client.model.DeleteOneModel;
import com.mongodb.client.model.WriteModel;
import com.mongodb.kafka.connect.sink.cdc.CdcOperation;
import com.mongodb.kafka.connect.sink.cdc.couchdb.OperationHelper;
import com.mongodb.kafka.connect.sink.converter.SinkDocument;
import org.bson.BsonDocument;

public class Delete implements CdcOperation {
    @Override
    public WriteModel<BsonDocument> perform(SinkDocument doc) {

        return new DeleteOneModel<>(
                OperationHelper.getFilterWithDocumentKey(doc));
    }
}
