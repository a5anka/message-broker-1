/*
*  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing,
*  software distributed under the License is distributed on an
*  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*  KIND, either express or implied.  See the License for the
*  specific language governing permissions and limitations
*  under the License.
*/
package org.ballerinalang.model.types;

import org.ballerinalang.model.values.BValue;

/**
 * {@code BXMLAttributesType} represents the type of an xml-attribute-map in ballerina.
 *
 * @since 0.89
 */
public class BXMLAttributesType extends BType {

    /**
     * Create a {@code BXMLAttributesType} represents the type an xml-attribute-map in ballerina.
     *
     * @param typeName string name of the type
     * @param pkgPath package path
     */
    BXMLAttributesType(String typeName, String pkgPath) {
        super(typeName, pkgPath, null);
    }

    public <V extends BValue> V getZeroValue() {
        return (V) null;
    }

    @Override
    public <V extends BValue> V getEmptyValue() {
        return (V) null;
    }

    @Override
    public TypeSignature getSig() {
        return null;
    }

    @Override
    public int getTag() {
        return TypeTags.XML_ATTRIBUTES_TAG;
    }
}