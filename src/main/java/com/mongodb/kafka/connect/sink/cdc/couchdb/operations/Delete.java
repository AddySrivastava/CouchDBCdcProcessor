package com.mongodb.kafka.connect.sink.cdc.couchdb.operations;

import com.mongodb.client.model.DeleteOneModel;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.WriteModel;
import com.mongodb.kafka.connect.sink.cdc.CdcOperation;
import com.mongodb.kafka.connect.sink.converter.SinkDocument;
import org.apache.kafka.connect.errors.DataException;
import org.bson.BsonDocument;

public class Delete implements CdcOperation {
    @Override
    public WriteModel<BsonDocument> perform(SinkDocument doc) {
        BsonDocument couchdbChangeDocumentKey =
                doc.getKeyDoc()
                        .orElseThrow(
                                () ->
                                        new DataException("Error: key doc must not be missing for delete operation"));


        return new DeleteOneModel<>(
                OperationHelper.getDocumentKey(couchdbChangeDocumentKey));
    }
}
