# CouchDB CDC Processor

## Overview
The CouchDB CDC Processor integrates CouchDB with MongoDB using Kafka Connect. It employs the [IBM Cloudant Kafka Connector](https://github.com/IBM/cloudant-kafka-connector) as a source to capture change events from CouchDB and the [MongoDB Kafka Connector](https://github.com/mongodb/mongo-kafka) as a sink to sync those changes to MongoDB. This setup ensures efficient real-time data synchronization between CouchDB and MongoDB.

## Features
- Real-time CouchDB to MongoDB data replication.
- Supports operations: insert, update, replace, delete.
- Extensible CDC handling for additional transformations.

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/AddySrivastava/CouchDBCdcProcessor.git
    ```

2. Install and configure the Kafka connectors:
    - Source: IBM Cloudant Kafka Connector.
    - Sink: MongoDB Kafka Connector.

3. Build the project using Maven:
    ```bash
    mvn clean package
    ```

4. Configure the Kafka Connect environment to use the source and sink connectors.

## Usage
The `CouchdbCdcHandler` class extends `CdcHandler` to manage CouchDB CDC events. It maps the following operations to MongoDB:
- **Insert**: Syncs new documents.
- **Update**: Applies partial updates.
- **Replace**: Replaces documents entirely.
- **Delete**: Removes documents from MongoDB.

The handler utilizes `MongoSinkTopicConfig` for configuration and logs activities via SLF4J.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
