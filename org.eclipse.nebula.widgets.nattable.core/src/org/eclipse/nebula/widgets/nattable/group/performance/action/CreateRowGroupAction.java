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
package org.eclipse.nebula.widgets.nattable.group.performance.action;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.group.command.CreateRowGroupCommand;
import org.eclipse.nebula.widgets.nattable.group.performance.gui.HeaderGroupNameDialog;
import org.eclipse.nebula.widgets.nattable.group.performance.gui.HeaderGroupNameDialog.HeaderGroupNameDialogLabels;
import org.eclipse.nebula.widgets.nattable.ui.action.IKeyAction;
import org.eclipse.swt.events.KeyEvent;

/**
 * Action to trigger creation of a row group based on the currently fully
 * selected rows and the name provided by the user via dialog.
 *
 * @since 1.6
 */
public class CreateRowGroupAction implements IKeyAction {

    @Override
    public void run(NatTable natTable, KeyEvent event) {
        HeaderGroupNameDialog dialog =
                new HeaderGroupNameDialog(natTable.getShell(), HeaderGroupNameDialogLabels.CREATE_ROW_GROUP);
        int result = dialog.open();
        if (result == IDialogConstants.OK_ID) {
            natTable.doCommand(new CreateRowGroupCommand(dialog.getGroupName()));
        }
    }

}
