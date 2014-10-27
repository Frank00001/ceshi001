/*
 * Copyright (c) 2013 Pantheon Technologies s.r.o. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowjava.protocol.api.extensibility;




/**
 * Stores and handles serializers 
 * @author michal.polkorab
 *
 */
public interface SerializerRegistry {

    /**
     * Serializer registry provisioning
     */
    public void init();

    /**
     * @param msgTypeKey lookup key
     * @return serializer or NullPointerException if no serializer was found
     */
    <KEYTYPE, SERIALIZERTYPE extends OFGeneralSerializer> SERIALIZERTYPE 
        getSerializer(MessageTypeKey<KEYTYPE> msgTypeKey);

    /**
     * Registers serializer
     * Throws IllegalStateException when there is
     * a serializer already registered under given key.
     * 
     * If the serializer implements {@link SerializerRegistryInjector} interface,
     * the serializer is injected with SerializerRegistry instance.
     * 
     * @param key used for serializer lookup
     * @param serializer serializer implementation
     */
    <KEYTYPE> void registerSerializer(MessageTypeKey<KEYTYPE> key,
            OFGeneralSerializer serializer);

    /**
     * Unregisters serializer
     * @param key used for serializer lookup
     * @param serializer serializer implementation
     * @return true if serializer was removed,
     *  false if no serializer was found under specified key
     */
    <KEYTYPE> boolean unregisterSerializer(MessageTypeKey<KEYTYPE> key);
}
