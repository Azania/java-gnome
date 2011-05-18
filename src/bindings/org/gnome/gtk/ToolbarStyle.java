/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2009      Vreixo Formoso
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Claspath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.gtk;

import org.freedesktop.bindings.Constant;

/**
 * Constants that can be used to customize the look-and-feel of a Toolbar, by
 * calling its {@link Toolbar#setStyle(ToolbarStyle) setStyle()} method.
 * 
 * @author Vreixo Formoso
 * @since 4.0.12
 */
public final class ToolbarStyle extends Constant
{
    private ToolbarStyle(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Toolbar buttons will only display an icon.
     */
    public static ToolbarStyle ICONS = new ToolbarStyle(GtkToolbarStyle.ICONS, "ICONS");

    /**
     * Toolbar buttons will only display a text label.
     */
    public static ToolbarStyle TEXT = new ToolbarStyle(GtkToolbarStyle.TEXT, "TEXT");

    /**
     * Toolbar buttons will display both an icon and text label. The text will
     * appear below the icon.
     */
    public static ToolbarStyle BOTH = new ToolbarStyle(GtkToolbarStyle.BOTH, "BOTH");

    /**
     * Toolbar buttons will display both an icon and text label. The text will
     * appear alongside the icon. This has been the default in GNOME since
     * about 2009.
     */
    public static ToolbarStyle BOTH_HORIZ = new ToolbarStyle(GtkToolbarStyle.BOTH_HORIZ, "BOTH_HORIZ");
}
