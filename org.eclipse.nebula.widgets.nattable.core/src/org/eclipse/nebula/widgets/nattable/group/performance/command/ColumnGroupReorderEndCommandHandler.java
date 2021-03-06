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
import org.eclipse.nebula.widgets.nattable.group.performance.ColumnGroupHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.LayerUtil;

/**
 * Command handler for the {@link ColumnGroupReorderEndCommand}. Needed for
 * reordering via drag mode.
 *
 * @see ColumnGroupReorderStartCommandHandler
 *
 * @since 1.6
 */
public class ColumnGroupReorderEndCommandHandler extends AbstractLayerCommandHandler<ColumnGroupReorderEndCommand> {

    private final ColumnGroupHeaderLayer columnGroupHeaderLayer;

    public ColumnGroupReorderEndCommandHandler(ColumnGroupHeaderLayer columnGroupHeaderLayer) {
        this.columnGroupHeaderLayer = columnGroupHeaderLayer;
    }

    @Override
    protected boolean doCommand(ColumnGroupReorderEndCommand command) {
        int toColumnPosition =
                LayerUtil.convertColumnPosition(this.columnGroupHeaderLayer, command.getColumnPosition(), this.columnGroupHeaderLayer.getPositionLayer());

        // Bug 437744
        // if not reorderToLeftEdge we increase toColumnPosition by 1
        // as the following processing is calculating the reorderToLeftEdge
        // value out of the given toColumnPosition and the column count
        if (!command.isReorderToLeftEdge()) {
            toColumnPosition++;
        }

        return this.columnGroupHeaderLayer.reorderColumnGroup(
                command.getLevel(),
                this.columnGroupHeaderLayer.getReorderFromColumnPosition(),
                toColumnPosition);
    }

    @Override
    public Class<ColumnGroupReorderEndCommand> getCommandClass() {
        return ColumnGroupReorderEndCommand.class;
    }

}
