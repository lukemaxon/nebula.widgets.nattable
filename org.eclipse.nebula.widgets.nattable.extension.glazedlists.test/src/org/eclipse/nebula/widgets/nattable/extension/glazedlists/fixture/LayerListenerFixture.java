/*******************************************************************************
 * Copyright (c) 2012, 2019 Original authors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Original authors and others - initial API and implementation
 ******************************************************************************/
package org.eclipse.nebula.widgets.nattable.extension.glazedlists.fixture;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.eclipse.nebula.widgets.nattable.layer.ILayerListener;
import org.eclipse.nebula.widgets.nattable.layer.event.ILayerEvent;

/**
 * This class helps us test the event flow by providing access to the
 * ILayerEvent fired.
 */
public class LayerListenerFixture implements ILayerListener {

    // Received events are kept in order
    private final List<ILayerEvent> receivedEvents = new LinkedList<ILayerEvent>();

    private CountDownLatch countDownLatch;

    @Override
    public void handleLayerEvent(ILayerEvent event) {
        this.receivedEvents.add(event);
        if (this.countDownLatch != null) {
            this.countDownLatch.countDown();
        }
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public List<ILayerEvent> getReceivedEvents() {
        return this.receivedEvents;
    }

    public void clearReceivedEvents() {
        this.receivedEvents.clear();
    }

    public int getEventsCount() {
        return this.receivedEvents.size();
    }

    public boolean containsInstanceOf(Class<? extends ILayerEvent> class1) {
        for (ILayerEvent inEvent : this.receivedEvents) {
            if (inEvent.getClass().getName().equals(class1.getName())) {
                return true;
            }
        }
        return false;
    }

    public ILayerEvent getReceivedEvent(Class<? extends ILayerEvent> class1) {
        for (ILayerEvent inEvent : this.receivedEvents) {
            if (inEvent.getClass().getName().equals(class1.getName())) {
                return inEvent;
            }
        }
        return null;
    }

}
