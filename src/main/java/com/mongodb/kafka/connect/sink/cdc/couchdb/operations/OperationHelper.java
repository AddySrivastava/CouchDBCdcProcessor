package com.mongodb.kafka.connect.sink.cdc.couchdb.operations;

import org.apache.kafka.connect.errors.DataException;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class OperationHelper {
    private static final String DOCUMENT_KEY = "_id";
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationHelper.class);


    static BsonDocument getDocumentKey(final BsonDocument couchdbDocument) {

        if (!couchdbDocument.containsKey(DOCUMENT_KEY)) {
            throw new DataException("Missing %s field _id");
        }

        BsonDocument bsonDocument = new BsonDocument();

        LOGGER.info(couchdbDocument.getBsonType().toString());
        LOGGER.info(couchdbDocument.toJson());

        // Add "id" and "123" to the BsonDocument
        return bsonDocument.append("_id", couchdbDocument.getString(DOCUMENT_KEY));
    }

    static BsonDocument getDocumentValue(final BsonDocument couchdbDocument) {
        return couchdbDocument;
    }

}
