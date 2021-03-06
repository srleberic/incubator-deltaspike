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
package org.apache.deltaspike.jpa.api;

import org.apache.deltaspike.core.api.provider.BeanProvider;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.Callable;

/**
 * <p></p>This class allows to execute CDI-unmanaged code blocks in a
 * &#064;Transactional manner. This is handy if you like e.g. to execute
 * database code in a unit test tearDown method.</p>
 *
 * <p><b>Attention:</b> please be aware that this helper only works for
 * &#064;Transactional with auto-detecting the EntityManager!
 * If you need to manually specify the EntityManager Qualifier
 * for another EntityManager, then you need to copy this code and adopt it.</p>
 * <p> Usage:
 * <pre>
 * TODO add example:
 *
 * </pre>
 * </p>
 */
@ApplicationScoped
public class TransactionHelper
{
    public static TransactionHelper getInstance()
    {
        return BeanProvider.getContextualReference(TransactionHelper.class);
    }

    /**
     * Execute the given {@link Callable} in a Transitional manner.
     *
     * @param callable
     * @param <T> the return type of the executed {@link Callable}
     * @return the return value of the executed {@link Callable}
     * @throws Exception
     */
    @Transactional
    public <T> T executeTransactional(Callable<T> callable) throws Exception
    {
        return callable.call();
    }
}
