/*
 * Copyright (c) 2016 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.couchbase.client.core.endpoint;

/**
 * Features the client negotiates with the server on a per-connection basis.
 *
 * @author Michael Nitschinger
 * @since 1.2.0
 */
public enum ServerFeatures {

    /**
     * The custom datatype feature.
     *
     * @since Couchbase Server 4.0
     */
    DATATYPE((short) 0x01),

    // TLS used to be in the server headers
    // but was never implemented. Keeping it here
    // explicitly to not confuse anyone on the 0x02 gap.
    //TLS((byte) 0x02),

    /**
     * Enable TCP Nodelay.
     *
     * @since Couchbase Server 4.0
     */
    TCPNODELAY((short) 0x03),

    /**
     * Return the sequence number on every mutation.
     *
     * @since Couchbase Server 4.0
     */
    MUTATION_SEQNO((short) 0x04),

    /**
     * Disable TCP Nodelay.
     *
     * @since Couchbase Server 4.0
     */
    TCPDELAY((short) 0x05);

    /**
     * The actual byte representation on the wire.
     */
    private final short value;

    ServerFeatures(short value) {
        this.value = value;
    }

    /**
     * Returns the actual byte value for the wire protocol.
     *
     * @return the actual wire value.
     */
    public short value() {
        return value;
    }

    public static ServerFeatures fromValue(short input) {
        switch(input) {
            case 0x01: return DATATYPE;
            case 0x03: return TCPNODELAY;
            case 0x04: return MUTATION_SEQNO;
            case 0x05: return TCPDELAY;
            default: throw new IllegalStateException("Unrequested server feature: " + input);
        }
    }
}
