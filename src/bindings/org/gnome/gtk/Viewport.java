/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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

/**
 * An adapter that allows a large Widget to only have a limited view be
 * presented. Specifically, Viewports are for scrolling. Generally you don't
 * need to create one of these yourself; you can add your Widget to a
 * ScrolledWindow in one step with
 * {@link ScrolledWindow#addWithViewport(Widget) addWithViewport()}.
 * 
 * @author Andrew Cowie
 * @since 4.0.8
 */
public class Viewport extends Bin
{
    protected Viewport(long pointer) {
        super(pointer);
    }

    /**
     * Construct a Viewport, specifying the Adjustment objects used to control
     * the panning. If you're adding your child Widget to a ScrolledWindow,
     * use its {@link ScrolledWindow#addWithViewport(Widget)
     * addWithViewport()} instead of constructing a Viewport manually.
     * 
     * @since 4.0.8
     */
    public Viewport(Adjustment hadjustment, Adjustment vadjustment) {
        super(GtkViewport.createViewport(hadjustment, vadjustment));
    }

    /**
     * Get the Adjustment that is being used to drive the horizontal extent of
     * the region of the child Widget being shown.
     * 
     * @since 4.0.8
     */
    public Adjustment getHAdjustment() {
        return GtkViewport.getHadjustment(this);
    }

    /**
     * Get the Adjustment that is being used to drive the vertical extent of
     * the region of the child Widget being shown.
     * 
     * @since 4.0.8
     */
    public Adjustment getVAdjustment() {
        return GtkViewport.getVadjustment(this);
    }

    /**
     * Set the type of decoration you want around the Viewport. If you're
     * using a ScrolledWindow, you can get at this via its
     * {@link ScrolledWindow#setShadowType(ShadowType) setShadowType()}; it
     * does the same thing.
     * 
     * @since 4.0.8
     */
    public void setShadowType(ShadowType type) {
        GtkViewport.setShadowType(this, type);
    }
}
