/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.deltaspike.test.core.api.config.injectable;

import org.apache.deltaspike.core.api.config.annotation.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SettingsBean
{
    @Inject
    @ConfigProperty(name = "configProperty1")
    private Integer intProperty1;

    private Long property2;

    @Inject
    @CustomConfigAnnotationWithMetaDataWithCustomConverter(inverseConvert = true)
    private Integer inverseProperty1;

    private Long inverseProperty2;

    protected SettingsBean()
    {
    }

    @Inject
    public SettingsBean(@SimpleCustomConfigAnnotation Long property2)
    {
        this.property2 = property2;
    }

    @Inject
    protected void init(@CustomConfigAnnotationWithMetaData(inverseConvert = true) Long inverseProperty)
    {
        this.inverseProperty2 = inverseProperty;
    }

    int getProperty1()
    {
        return intProperty1;
    }

    long getProperty2()
    {
        return property2;
    }

    int getInverseProperty1()
    {
        return inverseProperty1;
    }

    long getInverseProperty2()
    {
        return inverseProperty2;
    }
}
