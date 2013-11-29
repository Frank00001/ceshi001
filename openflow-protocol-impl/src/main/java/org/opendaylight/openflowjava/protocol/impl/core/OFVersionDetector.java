/* Copyright (C)2013 Pantheon Technologies, s.r.o. All rights reserved. */
package org.opendaylight.openflowjava.protocol.impl.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.opendaylight.openflowjava.protocol.impl.util.EncodeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Detects version of used OpenFlow Protocol and discards unsupported version messages
 * @author michal.polkorab
 */
public class OFVersionDetector extends ByteToMessageDecoder {

    /** Version number of OpenFlow 1.0 protocol */
    private static final byte OF10_VERSION_ID = EncodeConstants.OF10_VERSION_ID;
    /** Version number of OpenFlow 1.3 protocol */
    private static final byte OF13_VERSION_ID = EncodeConstants.OF13_VERSION_ID;
    private static final Logger LOGGER = LoggerFactory.getLogger(OFVersionDetector.class);

    /**
     * Constructor of class.
     */
    public OFVersionDetector() {
        LOGGER.debug("Creating OFVersionDetector");
    }

    @Override
    protected void decode(ChannelHandlerContext chc, ByteBuf bb, List<Object> list) throws Exception {
        if (bb.readableBytes() == 0) {
            LOGGER.debug("not enough data");
            bb.release();
            return;
        }
        LOGGER.debug("RI: " + bb.readerIndex());
        byte version = bb.readByte();

        if ((version == OF13_VERSION_ID) || (version == OF10_VERSION_ID)) {
            LOGGER.debug("detected version: " + version);
        } else {
            LOGGER.warn("detected version: " + version + " - currently not supported");
            bb.skipBytes(bb.readableBytes());
            return;
        }

        ByteBuf messageBuffer = bb.slice();
        list.add(new VersionMessageWrapper(version, messageBuffer));
        messageBuffer.retain();
        bb.skipBytes(bb.readableBytes());
    }

}
