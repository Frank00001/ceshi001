/* Copyright (C)2013 Pantheon Technologies, s.r.o. All rights reserved. */
package org.opendaylight.openflowjava.protocol.impl.deserialization.factories;

import io.netty.buffer.ByteBuf;

import org.opendaylight.openflowjava.protocol.impl.deserialization.OFDeserializer;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.augments.rev131002.ExperimenterError;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.augments.rev131002.ExperimenterErrorBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.BadActionCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.BadInstructionCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.BadMatchCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.BadRequestCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.ErrorType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.FlowModFailedCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.GroupModFailedCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.HelloFailedCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.MeterModFailedCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.PortModFailedCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.QueueOpFailedCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.RoleRequestFailedCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.SwitchConfigFailedCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.TableFeaturesFailedCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.common.types.rev130731.TableModFailedCode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.ErrorMessage;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.ErrorMessageBuilder;

/**
 * Translates Error messages
 * @author michal.polkorab
 * @author timotej.kubas
 */
public class ErrorMessageFactory implements OFDeserializer<ErrorMessage> {

    private static ErrorMessageFactory instance;
    
    private ErrorMessageFactory() {
        // do nothing, just singleton
    }
    
    /**
     * @return singleton factory
     */
    public static synchronized ErrorMessageFactory getInstance() {
        if (instance == null) {
            instance = new ErrorMessageFactory();
        }
        return instance;
    }
    
    @Override
    public ErrorMessage bufferToMessage(ByteBuf rawMessage, short version) {
        ErrorMessageBuilder builder = new ErrorMessageBuilder();
        builder.setVersion(version);
        builder.setXid(rawMessage.readUnsignedInt());
        ErrorType type = ErrorType.forValue(rawMessage.readUnsignedShort());
        decodeType(builder, type);
        decodeCode(rawMessage, builder, type);
        if (rawMessage.readableBytes() > 0) {
            builder.setData(rawMessage.readBytes(rawMessage.readableBytes()).array());
        }
        return builder.build();
    }

    private static void decodeCode(ByteBuf rawMessage, ErrorMessageBuilder builder,
            ErrorType type) {
        
        switch (type) {
        case HELLOFAILED:
        {
            HelloFailedCode code = HelloFailedCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case BADREQUEST:
        {
            BadRequestCode code = BadRequestCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case BADACTION:
        {
            BadActionCode code = BadActionCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case BADINSTRUCTION:
        {
            BadInstructionCode code = BadInstructionCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case BADMATCH:
        {
            BadMatchCode code = BadMatchCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case FLOWMODFAILED:
        {
            FlowModFailedCode code = FlowModFailedCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case GROUPMODFAILED:
        {
            GroupModFailedCode code = GroupModFailedCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case PORTMODFAILED:
        {
            PortModFailedCode code = PortModFailedCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case TABLEMODFAILED:
        {
            TableModFailedCode code = TableModFailedCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case QUEUEOPFAILED:
        {
            QueueOpFailedCode code = QueueOpFailedCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case SWITCHCONFIGFAILED:
        {
            SwitchConfigFailedCode code = SwitchConfigFailedCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case ROLEREQUESTFAILED:
        {
            RoleRequestFailedCode code = RoleRequestFailedCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case METERMODFAILED:
        {
            MeterModFailedCode code = MeterModFailedCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case TABLEFEATURESFAILED:
        {
            TableFeaturesFailedCode code = TableFeaturesFailedCode.forValue(rawMessage.readUnsignedShort());
            if (code != null) {
                builder.setCode(code.name());
            }
            break;
        }
        case EXPERIMENTER:
            ExperimenterErrorBuilder expBuilder = new ExperimenterErrorBuilder();
            expBuilder.setExpType(rawMessage.readUnsignedShort());
            expBuilder.setExperimenter(rawMessage.readUnsignedInt());
            builder.addAugmentation(ExperimenterError.class, expBuilder.build());
            break;
        default:
            builder.setCode("UNKNOWN_CODE");
            break;
        }
    }

    private static void decodeType(ErrorMessageBuilder builder, ErrorType type) {
        if (type != null) {
            builder.setType(type.name());
        } else {
            builder.setType("UNKNOWN_TYPE");
        }
    }

}
