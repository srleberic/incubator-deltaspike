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
package org.apache.deltaspike.core.impl.message;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;

/**
 * <p>
 * This bean-wrapper allows you to build a general purpose bean (likely a
 * producer method), and register it for a narrowed type (or qualifiers). For
 * example, you could create a producer method which uses an a String ID to
 * located an object (the object can have any class):
 * </p>
 * <p/>
 * <p/>
 * <pre>
 * &#064;Produces
 * // Use some synthetic scope to prevent this from interfering with other
 * // resolutions
 * &#064;MyProducer
 * Object produce(InjectionPoint ip)
 * {
 *     String id = ip.getAnnotated().getAnnotation(Id.class).value();
 *     // Lookup and return the object for the id
 * }
 * </pre>
 * <p/>
 * <p>
 * The wrapped bean <em>must</em> return an object which can be cast to the
 * type <code>T</code>, otherwise a {@link ClassCastException} will be thrown at
 * runtime when the bean is created.
 * </p>
 * <p/>
 * <p>
 * You can then register a narrowing bean for each type you need:
 * </p>
 * <p/>
 * <p/>
 * <pre>
 * event.addBean(new NarrowingBeanBuilder&lt;T&gt;(delegateBean).readFromType(type)
 *         .create());
 * </pre>
 * <p/>
 * <p>
 * {@link ImmutableBeanWrapper} will use the annotations on
 * <code>defininingType</code> to discover the qualifiers, types, scope,
 * stereotypes of the bean, as well as determine it's name (if any) and whether
 * it is an alternative.
 * </p>
 * <p/>
 * <p>
 * The attributes are immutable, and collections are defensively copied on
 * instantiation. It uses the defaults from the specification for properties if
 * not specified.
 * </p>
 *
 * @see NarrowingBeanBuilder
 */
class ImmutableBeanWrapper<T> extends AbstractImmutableBean<T>
{
    private final Bean<T> wrapped;

    /**
     * Instantiate a new {@link ImmutableBeanWrapper}.
     *
     * @param bean        the bean to wrapped the lifecycle to
     * @param name        the name of the bean
     * @param qualifiers  the qualifiers of the bean
     * @param scope       the scope of the bean
     * @param stereotypes the bean's stereotypes
     * @param types       the types of the bean
     * @param alternative whether the bean is an alternative
     * @param nullable    true if the bean is nullable
     * @param toString    the string which should be returned by #{@link #toString()}
     */
    public ImmutableBeanWrapper(Bean<T> bean,
                                String name,
                                Set<Annotation> qualifiers,
                                Class<? extends Annotation> scope,
                                Set<Class<? extends Annotation>> stereotypes,
                                Set<Type> types,
                                boolean alternative,
                                boolean nullable,
                                String toString)
    {
        super(bean.getBeanClass(), name, qualifiers, scope, stereotypes,
            types, alternative, nullable, bean.getInjectionPoints(), toString);

        this.wrapped = bean;
    }

    @Override
    public T create(CreationalContext<T> creationalContext)
    {
        return wrapped.create(creationalContext);
    }

    @Override
    public void destroy(T instance, CreationalContext<T> creationalContext)
    {
        wrapped.destroy(instance, creationalContext);
    }
}
