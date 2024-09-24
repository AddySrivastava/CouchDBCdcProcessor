package com.mongodb.kafka.connect.sink.cdc.couchdb;

import com.mongodb.kafka.connect.sink.converter.SinkDocument;
import org.apache.kafka.connect.errors.DataException;
import org.bson.BsonDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class OperationHelper {
    private static final String PRIMARY_KEY = "_id";
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationHelper.class);


    public static BsonDocument getFilterWithDocumentKey(final SinkDocument couchdbDocument) {

        BsonDocument couchMessageKey = couchdbDocument.getKeyDoc()
                .orElseThrow(
                        () ->
                                new DataException("Error: key doc must not be missing"));


        if (!couchMessageKey.containsKey(PRIMARY_KEY)) {
            throw new DataException("Missing %s field _id");
        }

        /*
          Prepare a document with the _id for the filter
         */
        BsonDocument bsonDocument = new BsonDocument();

        return bsonDocument.append("_id", couchMessageKey.getString(PRIMARY_KEY));
    }

    public static BsonDocument getDocumentKey(final SinkDocument couchdbDocument) {

        return couchdbDocument.getKeyDoc()
                .orElseThrow(
                        () ->
                                new DataException("Error: key doc must not be missing"));

    }

    public static BsonDocument getDocumentValue(final SinkDocument couchdbDocument) {
        return couchdbDocument.getValueDoc().orElseGet(BsonDocument::new);
    }

}
