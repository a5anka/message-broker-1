/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.wso2.broker.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents message received from publishers and delivered to subscribers by the broker.
 * This contains the metadata and the content chunks of the message.
 */
public class Message {

    private final Metadata metadata;

    private final ArrayList<ContentChunk> contentChunks;

    private boolean redelivered = false;

    private int redeliveryCount;

    public Message(Metadata metadata) {
        this.metadata = metadata;
        this.contentChunks = new ArrayList<>();
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public List<ContentChunk> getContentChunks() {
        return Collections.unmodifiableList(contentChunks);
    }

    public void addChunk(ContentChunk contentChunk) {
        contentChunks.add(contentChunk);
    }

    public void release() {
        for (ContentChunk contentChunk : contentChunks) {
            contentChunk.release();
        }
    }

    public Message shallowCopy() {
        Message message = new Message(metadata.shallowCopy());
        message.redelivered = redelivered;
        message.redeliveryCount = redeliveryCount;
        shallowCopyContent(message);
        return message;
    }

    public Message shallowCopyWith(long internalId, String routingKey, String exchangeName) {
        Message message = new Message(metadata.shallowCopyWith(internalId, routingKey, exchangeName));
        shallowCopyContent(message);
        return message;
    }

    private void shallowCopyContent(Message message) {
        contentChunks.stream().map(ContentChunk::shallowCopy).forEach(message::addChunk);
    }

    /**
     * Set redelivery flag
     */
    public int setRedeliver() {
        redelivered = true;
        return ++redeliveryCount;
    }

    /**
     * Getter for redeliveryCount
     */
    public int getRedeliveryCount() {
        return redeliveryCount;
    }

    /**
     * Check if redelivery flag is set
     */
    public boolean isRedelivered() {
        return redelivered;
    }

    @Override
    public String toString() {
        return metadata.toString();
    }
}