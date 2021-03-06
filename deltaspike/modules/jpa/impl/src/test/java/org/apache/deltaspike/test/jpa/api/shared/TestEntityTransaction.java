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
package org.apache.deltaspike.test.jpa.api.shared;

import javax.persistence.EntityTransaction;

public class TestEntityTransaction implements EntityTransaction
{
    private boolean started = false;
    private boolean committed = false;
    private boolean rolledBack = false;

    @Override
    public void begin()
    {
        if (this.started)
        {
            throw new IllegalStateException("transaction started already");
        }

        this.started = true;
    }

    @Override
    public void commit()
    {
        this.committed = true;
    }

    @Override
    public void rollback()
    {
        this.rolledBack = true;
    }

    @Override
    public void setRollbackOnly()
    {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public boolean getRollbackOnly()
    {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public boolean isActive()
    {
        return this.started && !(this.committed || this.rolledBack);
    }

    public boolean isStarted()
    {
        return started;
    }

    public boolean isCommitted()
    {
        return committed;
    }

    public boolean isRolledBack()
    {
        return rolledBack;
    }
}
