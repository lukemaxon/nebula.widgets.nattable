/*******************************************************************************
 * Copyright (c) 2019 Dirk Fauth.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dirk Fauth <dirk.fauth@googlemail.com> - initial API and implementation
 ******************************************************************************/
package org.eclipse.nebula.widgets.nattable.group.performance.command;

import org.eclipse.nebula.widgets.nattable.command.AbstractLayerCommandHandler;
import org.eclipse.nebula.widgets.nattable.group.performance.RowGroupHeaderLayer;
import org.eclipse.nebula.widgets.nattable.reorder.command.RowReorderStartCommand;

/**
 * Command handler for the {@link RowReorderStartCommand} that is registered on
 * the positionLayer of the {@link RowGroupHeaderLayer} to avoid handling in
 * case the reordering would break an unbreakable group.
 *
 * @since 1.6
 */
public class GroupRowReorderStartCommandHandler extends AbstractLayerCommandHandler<RowReorderStartCommand> {

    private final RowGroupHeaderLayer rowGroupHeaderLayer;

    public GroupRowReorderStartCommandHandler(RowGroupHeaderLayer rowGroupHeaderLayer) {
        this.rowGroupHeaderLayer = rowGroupHeaderLayer;
    }

    @Override
    protected boolean doCommand(RowReorderStartCommand command) {
        int fromRowPosition = command.getFromRowPosition();

        this.rowGroupHeaderLayer.setReorderFromRowPosition(fromRowPosition);

        // we need to remember the from position in order to check if the
        // reorder operation is valid
        // the command shouldn't get consumed here, since the reordering needs
        // to take place in the RowReorderLayer
        return false;
    }

    @Override
    public Class<RowReorderStartCommand> getCommandClass() {
        return RowReorderStartCommand.class;
    }

}
